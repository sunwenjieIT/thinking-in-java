package xyz.wenjiesx.demo.designpattern.templatemethod;

/**
 * @author wenji
 * @date 2019/10/30
 */
public abstract class AbstractCalculator {

    public int[] split(String exp, String opt) {
        String[] array = exp.split(opt);
        int[] arrayInt = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);

        return arrayInt;
    }

    public final int calculate(String exp, String opt) {
        int[] array = split(exp, opt);
        return calculate(array[0], array[1]);
    }

    /**
     * 待重写方法
     * @param num1
     * @param num2
     * @return
     */
    protected abstract int calculate(int num1, int num2);

}
