package xyz.wenjiesx.prepare.socket.nio;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author wenji
 * @Date 2020/12/20
 */
public class NioSocketClient2 {

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
