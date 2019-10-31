package xyz.wenjiesx.demo.proxy;

/**
 * @author wenji
 * @date 2019/10/23
 */
public class UserManagerImpl implements UserManager {
    public void addUser(String userName, String password) {
        System.out.println("do add user. username: " + userName + " password: " + password);

    }

    public void delUser(String userName) {

        System.out.println("do del user. username: " + userName);
    }
}
