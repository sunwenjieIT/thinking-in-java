package com.wenjie.springframework.aware;

import com.wenjie.springframework.core.WenjieApplicationContext;

/**
 * @author wenji
 * @Date 2023/1/8
 */
public interface ApplicationContextAware {

    void setApplicationContext(WenjieApplicationContext context);

}
