package xyz.wenjiesx.prepare.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author wenji
 * @Date 2020/12/7
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {

        Socket             socket       = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream  inputStream  = null;
        InetSocketAddress  addr         = new InetSocketAddress("127.0.0.1", 9999);

        try {
            socket = new Socket();
            socket.connect(addr);

            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeUTF("test");
            outputStream.flush();

            System.out.println(inputStream.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
            if (outputStream != null) outputStream.close();
            if (inputStream != null) inputStream.close();
        }

    }

}
