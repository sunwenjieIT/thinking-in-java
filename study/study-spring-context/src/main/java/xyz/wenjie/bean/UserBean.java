package xyz.wenjie.bean;

import org.springframework.stereotype.Component;

/**
 * @author wenji
 * @Date 2020/3/18
 */
//@Component
public class UserBean {

    private String name = "wenjie";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
