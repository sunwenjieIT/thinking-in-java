package xyz.wenjiesx.rpc.register;

import xyz.wenjiesx.rpc.core.register.RegisterService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

/**
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
        //登记到注册中心
        Socket             socket = null;
        ObjectOutputStream output = null;
        ObjectInputStream  input  = null;

        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(properties.getHost(), Integer.parseInt(properties.getPort())));

            output = new ObjectOutputStream(socket.getOutputStream());
            /*注册服务*/
            output.writeBoolean(false);
            /*提供的服务名*/
            output.writeUTF(groupName);
            /*服务提供方的IP*/
            output.writeUTF(host);
            /*服务提供方的端口*/
            output.writeInt(port);
            output.flush();

            input = new ObjectInputStream(socket.getInputStream());
            if (input.readBoolean()) {
                System.out.println("服务[" + groupName + "]注册成功!");
            }

        } catch (
                IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {

                if (socket != null) socket.close();
                if (output != null) output.close();
                if (input != null) input.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
       /* Socket socket = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(properties.getHost(), Integer.parseInt(properties.getPort())));
            try (
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ) {
                outputStream.writeUTF("regService");
                outputStream.writeObject(new Object[]{groupName, host, port});
                outputStream.writeObject(new Class[]{String.class, String.class, int.class});

                outputStream.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO ERROR", e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    @Override
    public List<InetSocketAddress> getService(String groupName) {

        Socket             socket = null;
        ObjectOutputStream output = null;
        ObjectInputStream  input  = null;

        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(properties.getHost(), Integer.parseInt(properties.getPort())));

            output = new ObjectOutputStream(socket.getOutputStream());
            //需要获得服务提供者
            output.writeBoolean(true);
            //告诉注册中心服务名
            output.writeUTF(groupName);
            output.flush();

            input = new ObjectInputStream(socket.getInputStream());
            List<InetSocketAddress> result
                    = (List<InetSocketAddress>) input.readObject();
//            List<InetSocketAddress> services = new ArrayList<>();
//            for(RegisterServiceVo serviceVo : result){
//                String host = serviceVo.getHost();//获得服务提供者的IP
//                int port = serviceVo.getPort();//获得服务提供者的端口号
//                InetSocketAddress serviceAddr = new InetSocketAddress(host,port);
//                services.add(serviceAddr);
//            }
            System.out.println("获得服务[" + groupName
                    + "]提供者的地址列表[" + result + "]，准备调用.");
            return result;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {

                if (socket != null) socket.close();
                if (output != null) output.close();
                if (input != null) input.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
/*

        Socket socket = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(properties.getHost(), Integer.parseInt(properties.getPort())));
            try (
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())
            ) {
                outputStream.writeUTF("regService");
                outputStream.writeObject(new Object[]{groupName});
                outputStream.writeObject(new Class[]{String.class});

                outputStream.flush();
                List<InetSocketAddress> result = (List<InetSocketAddress>) inputStream.readObject();

                return result;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }


}
