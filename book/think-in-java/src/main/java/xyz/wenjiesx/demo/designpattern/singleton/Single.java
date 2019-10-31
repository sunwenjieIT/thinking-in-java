package xyz.wenjiesx.demo.designpattern.singleton;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class Single {

    private static volatile Single single;

    private Single() {
    }

    public static Single getInstance() {
        if (single == null) {
            synchronized (Single.class) {
                if (single == null) {
                    single = new Single();
                }
            }
        }
        return single;
    }

}
