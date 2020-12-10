package xyz.wenjiesx.rpc.server2.core;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenji
 * @Date 2020/12/10
 */
@Service
public class RegisterService {

    private static final Map<String, Object> serviceCache = new ConcurrentHashMap<>();

    public void regService(String serviceName, Object impl) {
        serviceCache.put(serviceName, impl);
    }

    public Object getService(String serviceName) {
        return serviceCache.get(serviceName);
    }
}
