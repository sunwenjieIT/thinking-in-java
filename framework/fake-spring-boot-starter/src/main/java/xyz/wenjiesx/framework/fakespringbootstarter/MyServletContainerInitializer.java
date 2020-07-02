package xyz.wenjiesx.framework.fakespringbootstarter;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @author wenji
 * @Date 2020/4/16
 */
public class MyServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {

        System.out.println("------------- run my servlet container initializer onStartUp()-----------------");
    }
}
