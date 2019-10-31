package xyz.wenjiesx.demo.proxy;

/**
 * 用于测试动态代理的工作接口
 *
 * @author wenji
 * @date 2019/10/23
 */
public interface UserManager {

    void addUser(String userName, String password);

    void delUser(String userName);

}
