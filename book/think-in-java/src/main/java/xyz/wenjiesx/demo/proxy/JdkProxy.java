package xyz.wenjiesx.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wenji
 * @date 2019/10/23
 */
public class JdkProxy implements InvocationHandler {

    private Object target; // 需要代理的目标对象


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK proxy start.");
        Object result = method.invoke(target, args);
        System.out.println("JDK proxy end.");

        return result;
    }

    private Object getJdkProxy(Object targetObject) {

        this.target = targetObject;
        Object o = Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), this);
        return o;

    }

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        UserManager userManager = (UserManager) jdkProxy.getJdkProxy(new UserManagerImpl());
        userManager.addUser("admin", "123");
        userManager.delUser("admin");

    }

}
