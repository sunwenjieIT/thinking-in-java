package xyz.wenjiesx.rpc.register.core;

import org.springframework.stereotype.Service;
import xyz.wenjiesx.rpc.core.register.RegisterService;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 注册中心的接口实现(注册服务提供侧)
 *
 * @author wenji
 * @Date 2020/12/11
 */
@Service
public class RegisterServiceServerImpl implements RegisterService {

    private final Map<String, List<InetSocketAddress>> groupCacheServiceMap = new ConcurrentHashMap<>();

    private final Map<String, InetSocketAddress> instanceCacheServiceMap = new ConcurrentHashMap<>();

    private final Map<String, Long> instanceConnectMap = new ConcurrentHashMap<>();

    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, r -> new Thread(r, "schedule-refresh-service-cache"));

    public RegisterServiceServerImpl() {
        System.out.println("======开启延时检查服务==============");
        executor.scheduleWithFixedDelay(new RefreshTask(this), 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public void regService(String groupName, String host, int port) {
        synchronized (this) {
            System.out.println("service " + groupName + " register start");
            String instanceKey = groupName + "#" + host + "#" + port;
            if (instanceCacheServiceMap.containsKey(instanceKey)) {
                return;
            }

            InetSocketAddress inetAddr = new InetSocketAddress(host, port);
            instanceCacheServiceMap.put(instanceKey, inetAddr);
            instanceConnectMap.put(instanceKey, System.currentTimeMillis());

            List<InetSocketAddress> groupList = groupCacheServiceMap.getOrDefault(groupName, new ArrayList<>());
            groupList.add(inetAddr);
            if (groupList.size() == 1)
                groupCacheServiceMap.put(groupName, groupList);
            System.out.println("service " + groupName + " register success, host: " + host + " port: " + port);
        }
    }

    @Override
    public List<InetSocketAddress> getService(String groupName) {
        return groupCacheServiceMap.get(groupName);
    }

    @Override
    public void heartBeat(String groupName, String host, int port) {
        //收到一次服务提供方心跳请求, 刷新最后一次通信时间
        String instanceKey = groupName + "#" + host + "#" + port;
        if (!instanceCacheServiceMap.containsKey(instanceKey)) {
            regService(groupName, host, port);
            return;
        }

        instanceConnectMap.put(instanceKey, System.currentTimeMillis());
        System.out.println("收到服务: " + instanceKey + "心跳, 保活成功");
    }

    protected void refreshCache() {
        long l = System.currentTimeMillis();
        for (Map.Entry<String, Long> entry : instanceConnectMap.entrySet()) {
            String serviceKey             = entry.getKey();
            String groupKey               = serviceKey.split("#")[0];
            Long   lastConnectMillSeconds = entry.getValue();
            if (lastConnectMillSeconds == null || l - 5000 > lastConnectMillSeconds) {
                InetSocketAddress needRemoveAddress = instanceCacheServiceMap.get(serviceKey);
                synchronized (needRemoveAddress) {
                    instanceConnectMap.remove(serviceKey);
                    instanceCacheServiceMap.remove(serviceKey);
                    List<InetSocketAddress> addressList = groupCacheServiceMap.get(groupKey);
                    addressList.remove(needRemoveAddress);
                }
                System.out.println("=====清除服务: " + needRemoveAddress);
                System.out.println(groupCacheServiceMap);
            }
        }
    }

    private static class RefreshTask implements Runnable {

        private RegisterServiceServerImpl registerService;

        public RefreshTask(RegisterServiceServerImpl registerService) {
            this.registerService = registerService;
        }

        @Override
        public void run() {
            System.out.println("执行定时任务刷新服务缓存");
            registerService.refreshCache();
        }
    }


}
