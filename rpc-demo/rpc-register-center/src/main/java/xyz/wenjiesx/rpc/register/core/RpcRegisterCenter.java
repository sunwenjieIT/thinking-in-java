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

                String     methodName = inputStream.readUTF();
                Object[]   params     = (Object[]) inputStream.readObject();
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
