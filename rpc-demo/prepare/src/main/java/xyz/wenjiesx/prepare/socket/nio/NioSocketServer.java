package xyz.wenjiesx.prepare.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wenji
 * @Date 2020/12/20
 */
public class NioSocketServer {

    public static void main(String[] args) {
        ServerTask serverTask = new ServerTask(13333);
        new Thread(serverTask, "server").start();
    }

    public static class ServerTask implements Runnable {

        private volatile boolean started;
        private ServerSocketChannel serverSocketChannel;
        private Selector selector;

        public ServerTask(int port) {

            try {

                selector = Selector.open();
                serverSocketChannel = ServerSocketChannel.open();

                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.socket().bind(new InetSocketAddress(port));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                started = true;
                System.out.println("服务启动, 端口: " + port);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {

            while (started) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        handleSelectionKey(key);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleSelectionKey(SelectionKey key) throws IOException {
            if (key.isValid()) {
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                    SocketChannel sc = ssc.accept();

                    System.out.println("==============建立连接==============");
                    sc.configureBlocking(false);

                    sc.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer    buffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(buffer);
                    if (readBytes > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String message = new String(bytes, "UTF-8");
                        System.out.println("服务端收到消息: " + message);

                        String result = "hello nio socket " + message;
                        doWrite(sc, result);

                    } else if (readBytes < 0) {
                        key.cancel();
                        sc.close();
                    }

                }
            }
        }

        private void doWrite(SocketChannel sc, String message) throws IOException {
            byte[] bytes = message.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            sc.write(buffer);
        }
    }

}
