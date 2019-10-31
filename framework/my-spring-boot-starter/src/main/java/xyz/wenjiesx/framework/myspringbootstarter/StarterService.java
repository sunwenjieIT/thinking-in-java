package xyz.wenjiesx.framework.myspringbootstarter;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author wenji
 * @date 2019/10/25
 */
//@Service
public class StarterService {

    private String config;

    public StarterService(String config) {
        this.config = config;
    }

    public String[] split(String separatorChar) {
        return StringUtils.split(this.config, separatorChar);
    }


}
