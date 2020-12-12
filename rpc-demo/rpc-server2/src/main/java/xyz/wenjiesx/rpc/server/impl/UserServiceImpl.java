package xyz.wenjiesx.rpc.server.impl;

import org.springframework.stereotype.Service;
import xyz.wenjiesx.rpc.server.api.UserService;
import xyz.wenjiesx.rpc.server.dto.UserInfo;

/**
 * @author wenji
 * @Date 2020/12/10
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserInfo getUser(Long userId) {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUsername("张三");
        userInfo.setAge(10);
        System.out.println(userInfo);
        return userInfo;
    }
}
