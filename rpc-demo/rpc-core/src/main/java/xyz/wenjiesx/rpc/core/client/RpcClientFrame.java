package xyz.wenjiesx.rpc.core.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.wenjiesx.rpc.core.register.RegisterService;

import javax.annotation.Resource;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Random;

/**
 * @author wenji
 * @Date 2020/12/10
 */
@Service
public class RpcClientFrame {

    @Resource
    private RegisterService registerService;

    public <T> T getRemoteProxyObject(Class<?> serviceInterface) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface}, new DynamicProxy(serviceInterface, registerService, serviceInterface.getName()));
    }

    private static class DynamicProxy implements InvocationHandler {

        private final String          serviceName;
        private final Class<?>        serviceInterface;
        private final RegisterService registerService;

        public DynamicProxy(Class<?> serviceInterface, RegisterService registerService, String serviceName) {
            this.serviceInterface = serviceInterface;
            this.serviceName = serviceName;
            this.registerService = registerService;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;

            try {
                socket = new Socket();
                socket.connect(selectOneAddr());

                try (
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())
                ) {

                    //methodName
                    outputStream.writeUTF(method.getName());
                    //clazzName
                    outputStream.writeUTF(serviceInterface.getName());
                    //params
                    outputStream.writeObject(args);
                    //param type
                    outputStream.writeObject(method.getParameterTypes());
                    outputStream.flush();

                    return inputStream.readObject();
                }
            } finally {
                if (socket != null) socket.close();
            }
        }

        public InetSocketAddress selectOneAddr() {
            //直接从远端获取列表, TODO 这里可以做缓存让本地缓存远端列表, 避免每次拉取注册中心的服务列表
            List<InetSocketAddress> addrList = registerService.getService(serviceName);
            //负载均衡简易实现 随机
            int    size     = addrList.size();
            Random random   = new Random();
            int    useIndex = random.nextInt(size);

            InetSocketAddress inetSocketAddress = addrList.get(useIndex);
            System.out.println("=======服务" + serviceName + "选择" + useIndex + "号实例请求, " + inetSocketAddress);
            return inetSocketAddress;
        }
    }

}
