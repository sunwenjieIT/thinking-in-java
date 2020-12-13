package xyz.wenjiesx.rpc.register;

import xyz.wenjiesx.rpc.core.register.RegisterService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

/**
 * 注册中心的接口实现(客户端调用侧)
 *
 * @author wenji
 * @date 2019/10/25
 */
//@Service
public class RegisterServiceClientImpl implements RegisterService {

    private RegisterStarterServiceProperties properties;

    public RegisterServiceClientImpl(RegisterStarterServiceProperties properties) {
        this.properties = properties;
    }

    @Override
    public void regService(String groupName, String host, int port) {
        invoke("regService", new Object[]{groupName, host, port}, new Class[]{String.class, String.class, int.class});
    }

    @Override
    public List<InetSocketAddress> getService(String groupName) {
        return (List<InetSocketAddress>) invoke("getService", new Object[]{groupName}, new Class[]{String.class});
    }

    @Override
    public void heartBeat(String groupName, String host, int port) {
        System.out.println("服务: " + groupName + "#" + host + "#" + port + " 执行心跳保活");
        invoke("heartBeat", new Object[]{groupName, host, port}, new Class[]{String.class, String.class, int.class});
    }

    protected Object invoke(String methodName, Object[] params, Class<?>[] paramTypes) {

        Socket socket = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(properties.getHost(), Integer.parseInt(properties.getPort())));
            try (
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())
            ) {
                outputStream.writeUTF(methodName);
                outputStream.writeObject(params);
                outputStream.writeObject(paramTypes);

                outputStream.flush();
                return inputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
