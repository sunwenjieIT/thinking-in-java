package xyz.wenjiesx.demo.designpattern.adapter;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class AdapterTest {
    public static void main(String[] args) {
        // 类的适配器模式
        Adapter adapter = new Adapter();
        adapter.method2();
        adapter.method1();

        // 对线的适配器模式
        Source source = new Source();
        Wrapper wrapper = new Wrapper(source);
        wrapper.method1();
        wrapper.method2();

        // 接口的适配器模式
        TargetAbleSub1 targetAbleSub1 = new TargetAbleSub1();
        TargetAbleSub2 targetAbleSub2 = new TargetAbleSub2();
        targetAbleSub1.method1();
        targetAbleSub1.method2();
        targetAbleSub2.method1();
        targetAbleSub2.method2();

    }

}
