package xyz.wenjiesx.rpc.register;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wenji
 * @date 2019/10/25
 */
@ConfigurationProperties("register.service.center")
public class RegisterStarterServiceProperties {

    private String port;
    private String host;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
