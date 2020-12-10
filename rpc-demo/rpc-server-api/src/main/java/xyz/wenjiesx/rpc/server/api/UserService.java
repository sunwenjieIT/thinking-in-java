package xyz.wenjiesx.rpc.server.api;

import xyz.wenjiesx.rpc.server.dto.UserInfo;

/**
 * @author wenji
 * @Date 2020/12/10
 */
public interface UserService {

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return
     */
    UserInfo getUser(Long userId);

}
