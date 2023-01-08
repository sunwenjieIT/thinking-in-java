package com.wenjie.springframework.core;

import com.wenjie.bean.processor.WenjieBeanPostProcessor;
import com.wenjie.springframework.annotation.*;
import com.wenjie.springframework.aware.ApplicationContextAware;
import com.wenjie.springframework.aware.BeanNameAware;
import com.wenjie.springframework.bean.BeanDefinition;
import com.wenjie.springframework.processor.BeanPostProcessor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenji
 * @Date 2023/1/8
 */
public class WenjieApplicationContext {


    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private Map<String, Object>         singleObjectMap   = new HashMap<>();

    private String scanPath;

    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();
    private List<Class<?>>          clazzList             = new ArrayList<>();

    private static final String SINGLETON_FLAG = "singleton";

    public WenjieApplicationContext(Class configClass) {

        scan(configClass);
        collectionBeanPostProcessor();

        for (String beanName : beanDefinitionMap.keySet()) {
            getBean(beanName);
        }

    }

    /**
     * 收集beanPostProcessor的逻辑暂时简化,做成基于clazz从指定的scan目录获取,暂时忽略内置的processor 用例里也无内置processor
     */
    private void collectionBeanPostProcessor() {

        for (Class<?> clazz : clazzList) {
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> i : interfaces) {
                if (i == BeanPostProcessor.class) {
                    try {

                        BeanPostProcessor o = (BeanPostProcessor) clazz.newInstance();
                        beanPostProcessorList.add(o);

                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    private void scan(Class configClass) {

        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String        path       = annotation.value();

            scanPath = path.replace(".", "/");

            ClassLoader classLoader = getClass().getClassLoader();
            URL         resource    = classLoader.getResource(scanPath);

            List<File> classFileList = new ArrayList<>();
            File       file          = new File(resource.getFile());

            scanFile(file, classFileList);

            for (File f : classFileList) {
                String absolutePath = f.getAbsolutePath();
                String className = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"))
                        .replace("\\", ".");

                // 模拟实现时使用类加载器简单实现 源代码里貌似是基于 ASM技术来解析类的元数据的
                try {

                    Class<?> clazz = classLoader.loadClass(className);
                    clazzList.add(clazz);

                    if (clazz.isAnnotationPresent(Component.class)) {
                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setType(clazz);
                        beanDefinition.setLazy(clazz.isAnnotationPresent(Lazy.class));
                        if (clazz.isAnnotationPresent(Scope.class)) {
                            beanDefinition.setScope(clazz.getAnnotation(Scope.class).value());
                        } else {
                            beanDefinition.setScope(SINGLETON_FLAG);
                        }

                        String beanName = clazz.getAnnotation(Component.class).value();
                        if (beanName.isEmpty()) {
                            beanName = Introspector.decapitalize(clazz.getSimpleName());
                        }

                        beanDefinitionMap.put(beanName, beanDefinition);
                    }


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    protected void scanFile(File file, List<File> classFileList) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                scanFile(f, classFileList);
            }
        } else {
            classFileList.add(file);
        }
    }

    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new NullPointerException("bean not exist");
        }

        if (SINGLETON_FLAG.equals(beanDefinition.getScope())) {
            Object result = singleObjectMap.get(beanName);
            if (result == null) {
                result = createBean(beanName, beanDefinition);
                singleObjectMap.put(beanName, result);
            }

            return result;
        } else {
            return createBean(beanName, beanDefinition);
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {

        Class clazz = beanDefinition.getType();

        try {

            Object o = clazz.newInstance();

            // beanPostProcessor 执行 这里忘记掉了 具体的BeanPostProcessor的前置执行是给一个bean实例还是给bennDefinition实例
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                beanPostProcessor.postProcessBeforeInitialization(o, beanName);
            }

            // 依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(field.getName());
                    field.setAccessible(true);
                    field.set(o, bean);
                }
            }

            // contextAware 模拟
            if (o instanceof ApplicationContextAware) {
                ((ApplicationContextAware) o).setApplicationContext(this);
            }

            // beanNameAware 模拟
            if (o instanceof BeanNameAware) {
                ((BeanNameAware) o).setBeanName(beanName);
            }

            // AOP里的transactional注解模拟
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(Transactional.class)) {
                    Enhancer enhancer = new Enhancer();
                    enhancer.setSuperclass(clazz);

                    Object target = o;
                    enhancer.setCallback((MethodInterceptor) (proxy, m, objects, methodProxy) -> {
                        if (m.isAnnotationPresent(Transactional.class)) {
                            System.out.println("事务代码 开始");
                            Object result = m.invoke(target, objects);
                            System.out.println("事务代码 结束");
                            return result;
                        } else {
                            return m.invoke(target, objects);
                        }
                    });

                    o = enhancer.create();
                    break;
                }
            }

            // beanPostProcessor 执行
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                beanPostProcessor.postProcessAfterInitialization(o, beanName);
            }

            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
