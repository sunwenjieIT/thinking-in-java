package xyz.wenjiesx.demo.designpattern.strategy;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Multiply extends AbstractCalculator implements ICalculator {
    @Override
    public int calculate(String exp) {
        int[] split = split(exp, "\\*");
        return split[0] * split[1];
    }
}
