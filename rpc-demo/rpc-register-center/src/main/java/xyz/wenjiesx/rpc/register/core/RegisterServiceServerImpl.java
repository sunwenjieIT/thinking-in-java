package xyz.wenjiesx.rpc.register.core;

import org.springframework.stereotype.Service;
import xyz.wenjiesx.rpc.core.register.RegisterService;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenji
 * @Date 2020/12/11
 */
@Service
public class RegisterServiceServerImpl implements RegisterService {

    private final Map<String, List<InetSocketAddress>> groupCacheService = new ConcurrentHashMap<>();

    private final Map<String, InetSocketAddress> instanceCacheService = new ConcurrentHashMap<>();

    @Override
    public void regService(String groupName, String host, int port) {
        synchronized (this) {
            System.out.println("service "+groupName+" register start");
            String instanceKey = groupName + "#" + host + "#" + port;
            if (instanceCacheService.containsKey(instanceKey)) {
                return;
            }

            InetSocketAddress inetAddr = new InetSocketAddress(host, port);
            instanceCacheService.put(instanceKey, inetAddr);

            List<InetSocketAddress> groupList = groupCacheService.getOrDefault(groupName, new ArrayList<>());
            groupList.add(inetAddr);
            if (groupList.size() == 1)
                groupCacheService.put(groupName, groupList);
            System.out.println("service " + groupName + " register success, host: " + host + " port: " + port);
        }
    }

    @Override
    public List<InetSocketAddress> getService(String groupName) {
        return groupCacheService.get(groupName);
    }

}
