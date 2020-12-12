package xyz.wenjiesx.rpc.core.register;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author wenji
 * @Date 2020/12/12
 */
public interface RegisterService {

    void regService(String groupName, String host, int port);

    List<InetSocketAddress> getService(String groupName);

}
