package xyz.wenjiesx.rpc.client.core;

import org.springframework.stereotype.Service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author wenji
 * @Date 2020/12/10
 */
@Service
public class RpcClientFrame {

    public <T> T getRemoteProxyObject(Class<?> serviceInterface) throws Exception {

        InetSocketAddress addr = getService(serviceInterface.getName());

        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface}, new DynamicProxy(serviceInterface, addr));

    }


    private static class DynamicProxy implements InvocationHandler {

        private Class<?>          serviceInterface;
        private InetSocketAddress addr;

        public DynamicProxy(Class<?> serviceInterface, InetSocketAddress addr) {
            this.serviceInterface = serviceInterface;
            this.addr = addr;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;

            try {
                socket = new Socket();
                socket.connect(addr);

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
    }

    private static InetSocketAddress getService(String serviceName) {

        return getServiceAddress(serviceName);
    }

    private static InetSocketAddress getServiceAddress(String serviceName) {

        //TODO 服务注册中心化
        return new InetSocketAddress("127.0.0.1", 9999);
//        Socket       socket       = null;
//        ObjectOutputStream outputStream = null;
//        ObjectInputStream  inputStream  = null;
//        try {

//        }

//        return null;
    }

}
