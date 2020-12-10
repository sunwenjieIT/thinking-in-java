package xyz.wenjiesx.rpc.server2.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wenji
 * @Date 2020/12/10
 */
@Service
public class RpcServerFrame {

    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Autowired
    private RegisterService registerService;

    private Integer port;

    private static class ServerTask implements Runnable {

        private Socket          socket;
        private RegisterService registerService;

        public ServerTask(Socket socket, RegisterService registerService) {
            this.socket = socket;
            this.registerService = registerService;
        }

        @Override
        public void run() {

            try (
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())
            ) {
                //methodName
                String methodName = inputStream.readUTF();
                //clazzName name
                String clazzName = inputStream.readUTF();
                //params
                Object[] params = (Object[]) inputStream.readObject();
                //param type
                Class<?>[] paramTypes = (Class<?>[]) inputStream.readObject();

                Object impl = registerService.getService(clazzName);
                if (impl == null)
                    throw new ClassNotFoundException(clazzName + " not found");

                Class<?> clazz  = impl.getClass();
                Method   method = clazz.getMethod(methodName, paramTypes);
                Object   result = method.invoke(impl, params);

                outputStream.writeObject(result);
                outputStream.flush();

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
    }

    public void startService(String name, String host, int port, Class clazz, Object impl) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("rpc server started on port:" + port);

        registerService.regService(name, impl);

        try {
            while (true) {
                executorService.execute(new ServerTask(serverSocket.accept(), registerService));
            }
        } finally {
            serverSocket.close();
        }

    }


}
