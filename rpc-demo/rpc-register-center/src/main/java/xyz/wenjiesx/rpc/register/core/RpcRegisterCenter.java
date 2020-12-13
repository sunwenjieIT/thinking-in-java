package xyz.wenjiesx.rpc.register.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.wenjiesx.rpc.core.register.RegisterService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wenji
 * @Date 2020/12/11
 */
@Service
public class RpcRegisterCenter {

    //注册中心
    //TODO 暂无实现心跳相关的功能, 所以也无法发现服务的状态变化

    @Autowired
    private RegisterService registerService;

    private static class TaskServer implements Runnable {

        private Socket          socket;
        private RegisterService registerService;

        public TaskServer(Socket socket, RegisterService registerService) {
            this.socket = socket;
            this.registerService = registerService;
        }

        @Override
        public void run() {
            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

                String methodName = inputStream.readUTF();
                Object[] params = (Object[]) inputStream.readObject();
                Class<?>[] paramTypes = (Class<?>[]) inputStream.readObject();

                Method method = registerService.getClass().getMethod(methodName, paramTypes);
                Object result = method.invoke(registerService, params);
                outputStream.writeObject(result);
                outputStream.flush();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

/*        @Override
        public void run() {
            try (ObjectInputStream inputStream =
                         new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream outputStream =
                         new ObjectOutputStream(socket.getOutputStream())) {

                //检查当前请求是注册服务还是获得服务
                boolean isGetService = inputStream.readBoolean();
                //服务查询服务，获得服务提供者
                if (isGetService) {
                    String serviceName = inputStream.readUTF();
                    //取出服务提供者集合
                    List<InetSocketAddress> result = registerService.getService(serviceName);
                    //返回给客户端
                    outputStream.writeObject(result);
                    outputStream.flush();
                    System.out.println("将已注册的服务[" + serviceName + "提供给客户端");
                } else {
                    //取得新服务提供方的ip和端口
                    String serviceName = inputStream.readUTF();
                    String host        = inputStream.readUTF();
                    int    port        = inputStream.readInt();
                    //在注册中心保存
                    registerService.regService(serviceName, host, port);
                    outputStream.writeBoolean(true);
                    outputStream.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        */
    }

    public void startService(int port) throws IOException {

        ServerSocket socket = null;
        try {
            socket = new ServerSocket();
            socket.bind(new InetSocketAddress(port));

            System.out.println("register started in 19999");
            while (true) {
                new Thread(new TaskServer(socket.accept(), registerService)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
        }
    }

    @PostConstruct
    public void init() {

        new Thread(() -> {
            try {
                startService(19999);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
