package xyz.wenjiesx.rpc.core.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.wenjiesx.rpc.core.register.RegisterService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author wenji
 * @Date 2020/12/10
 */
@Service
public class RpcServerFrame {

    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final ScheduledThreadPoolExecutor heartBeatExecutor =
            new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "schedule-heart-beat-executor");
                }
            });

    @Autowired
    private LocalRegisterService localRegisterService;

    @Autowired
    private RegisterService registerService;

    private Integer port;

    private static class HeatBeatTask implements Runnable {

        private RegisterService registerService;
        private String groupName;
        private String host;
        private int port;

        public HeatBeatTask(RegisterService registerService, String groupName, String host, int port) {
            this.registerService = registerService;
            this.groupName = groupName;
            this.host = host;
            this.port = port;
        }

        @Override
        public void run() {
            try {
                registerService.heartBeat(groupName, host, port);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ServerTask implements Runnable {

        private Socket               socket;
        private LocalRegisterService localRegisterService;

        public ServerTask(Socket socket, LocalRegisterService localRegisterService) {
            this.socket = socket;
            this.localRegisterService = localRegisterService;
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

                Object impl = localRegisterService.getService(clazzName);
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

    public void startService(String name, String host, int port, Object impl) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));

        System.out.println("server " + name + " reg in local success");
        localRegisterService.regService(name, impl);
        System.out.println("server " + name + " reg in remote success");
        registerService.regService(name, host, port);
        System.out.println("rpc server started on port:" + port);
        heartBeatExecutor.scheduleWithFixedDelay(new HeatBeatTask(registerService, name, host, port),
                3, 3, TimeUnit.SECONDS);
        try {
            while (true) {
                executorService.execute(new ServerTask(serverSocket.accept(), localRegisterService));
            }
        } finally {
            serverSocket.close();
        }

    }


}
