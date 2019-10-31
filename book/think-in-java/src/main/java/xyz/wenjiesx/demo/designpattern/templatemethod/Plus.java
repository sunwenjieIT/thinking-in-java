package xyz.wenjiesx.demo.designpattern.templatemethod;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Plus extends AbstractCalculator {
    @Override
    protected int calculate(int num1, int num2) {

        return num1 + num2;
    }


}
