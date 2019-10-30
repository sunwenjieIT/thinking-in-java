package xyz.wenjiesx.demo.designpattern.strategy;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Plus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {

        int[] arrayInt = split(exp, "\\+");
        return arrayInt[0] + arrayInt[1];
    }
}
