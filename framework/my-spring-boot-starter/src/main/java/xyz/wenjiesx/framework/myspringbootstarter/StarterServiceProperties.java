package xyz.wenjiesx.framework.myspringbootstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wenji
 * @date 2019/10/25
 */
@ConfigurationProperties("example.service")
public class StarterServiceProperties {

    private String config;

    public void setConfig(String config) {
        this.config = config;
    }

    public String getConfig() {
        return config;
    }

}
