package xyz.wenjie.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.wenjie.bean.UserBean;

/**
 * @author wenji
 * @Date 2020/3/18
 */
public class SpringContextTest {


    @Test
    public void test1() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("xyz.wenjie");
        UserBean userBean = (UserBean) context.getBean("userBean");
        System.out.println("annotation context, bean field name: " + userBean.getName());
    }

    @Test
    public void test2() {

        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserBean userBean = (UserBean) xmlApplicationContext.getBean("userBean");
        System.out.println("xml context, bean field name: " + userBean.getName());
    }
}

