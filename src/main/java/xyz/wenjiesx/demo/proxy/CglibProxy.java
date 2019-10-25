package xyz.wenjiesx.demo.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wenji
 * @date 2019/10/23
 */
public class CglibProxy implements MethodInterceptor {
    private Object target; // 需要代理的目标对象

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib proxy start.");

        Object invoke = method.invoke(target, objects);
        System.out.println("cglib proxy end.");
        return invoke;
    }

    public Object getCglibProxy(Object objectTarget) {
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        // cglib代理类是被代理对象的子类
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);
        Object result = enhancer.create();
        return result;
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        UserManager user = (UserManager) cglibProxy.getCglibProxy(new UserManagerImpl());
        user.addUser("admin", "123");
        user.delUser("admin");
    }
}
