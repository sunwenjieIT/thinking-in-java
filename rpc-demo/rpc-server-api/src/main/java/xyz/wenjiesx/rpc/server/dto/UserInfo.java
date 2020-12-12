package xyz.wenjiesx.rpc.server.dto;

import java.io.Serializable;

/**
 * @author wenji
 * @Date 2020/12/10
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -5120876390519366828L;

    private Long userId;

    private String username;

    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}


