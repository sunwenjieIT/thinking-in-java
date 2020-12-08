package xyz.wenjiesx.prepare.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author wenji
 * @Date 2020/12/7
 */
public class SocketServer {

    private static final ExecutorService executorService =
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                    Runtime.getRuntime().availableProcessors() * 2, 60, TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(9999);
        socket.bind(inetSocketAddress);

        System.out.println("server start");
        try {

            while (true) {
                executorService.execute(new Task(socket.accept()));
//                new Task(socket.accept()).run();
            }
        } catch (Exception e) {
            socket.close();
        }

    }

    private static class Task implements Runnable {

        private Socket socket;

        public Task(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try (
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ) {

                String s = inputStream.readUTF();
                System.out.println("Accept: " + s);

                outputStream.writeUTF("hello, socket " + s);
                outputStream.flush();
            } catch (IOException e) {
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

}
