package xyz.wenjiesx.framework.fakespringbootstarter;

//import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;

/**
 * @author wenji
 * @Date 2020/4/16
 */
//@Slf4j
public class FakeSpringBootApplication {

    public static void run() throws Exception {

//        log.info("start fake spring boot application");
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9090);
        Context context = tomcat.addWebapp("/access", "D:\\tmp\\");
//            tomcat.addServlet("/access", "demoServlet", new IndexServlet());
//        context.addServletMappingDecoded("/demo.do", "demoServlet");

        tomcat.start();
        tomcat.getServer().await();



    }

}
