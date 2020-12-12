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

    public <T> T getRemoteProxyObject(Class<?> serviceInterface) throws Exception {
        return getRemoteProxyObject(serviceInterface, null);
    }

    public <T> T getRemoteProxyObject(Class<?> serviceInterface, List<InetSocketAddress> addrList) {
        if (addrList == null)
            addrList = getService(serviceInterface.getName());

        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface}, new DynamicProxy(serviceInterface, addrList, serviceInterface.getName()));
    }

    private static class DynamicProxy implements InvocationHandler {

        private String serviceName;
        private Class<?>                serviceInterface;
        private List<InetSocketAddress> addrList;

        public DynamicProxy(Class<?> serviceInterface, List<InetSocketAddress> addrList, String serviceName) {
            this.serviceInterface = serviceInterface;
            this.addrList = addrList;
            this.serviceName = serviceName;
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
            //负载均衡简易实现 随机
            int    size     = addrList.size();
            Random random   = new Random();
            int    useIndex = random.nextInt(size);

            InetSocketAddress inetSocketAddress = addrList.get(useIndex);
            System.out.println("=======服务" + serviceName + "选择" + useIndex + "号实例请求, " + inetSocketAddress);
            return inetSocketAddress;
        }
    }

    private List<InetSocketAddress> getService(String serviceName) {

        if (registerService == null)
            throw new RuntimeException("register service is null");

        List<InetSocketAddress> serviceAddrList = registerService.getService(serviceName);
        return serviceAddrList;
    }

    private static InetSocketAddress getServiceAddress(String serviceName) {
        return new InetSocketAddress("127.0.0.1", 9999);
    }

}
