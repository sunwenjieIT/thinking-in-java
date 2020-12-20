package xyz.wenjiesx.prepare.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author wenji
 * @Date 2020/12/20
 */
public class NioSocketClient {

    private static NioClientTask clientTask;

    public static void main(String[] args) throws IOException {
        clientTask = new NioClientTask("127.0.0.1", 13333);
        new Thread(clientTask, "client").start();
        Scanner scanner = new Scanner(System.in);
        while (sendMessage(scanner.next()));
    }

    public static boolean sendMessage(String message) throws IOException {
        clientTask.sendMessage(message);
        return true;
    }


}
