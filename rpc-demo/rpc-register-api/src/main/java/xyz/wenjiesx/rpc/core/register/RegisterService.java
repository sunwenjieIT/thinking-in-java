package xyz.wenjiesx.rpc.core.register;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author wenji
 * @Date 2020/12/12
 */
public interface RegisterService {

    /**
     * 注册服务
     *
     * @param groupName 组名
     * @param host 服务host
     * @param port 服务端口
     */
    void regService(String groupName, String host, int port);

    /**
     * 获取服务列表
     *
     * @param groupName 组名
     * @return
     */
    List<InetSocketAddress> getService(String groupName);

    /**
     * 心跳服务
     *
     * @param groupName 组名
     * @param host 服务host
     * @param port 服务端口
     */
    void heartBeat(String groupName, String host, int port);
}
