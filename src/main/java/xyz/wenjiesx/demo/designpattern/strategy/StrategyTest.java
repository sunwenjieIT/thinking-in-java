package xyz.wenjiesx.demo.designpattern.strategy;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class StrategyTest {
    public static void main(String[] args) {

        String exp = "2+8";
        ICalculator cal = new Plus();
        int result = cal.calculate(exp);
        System.out.println(result);

        ICalculator multiply = new Multiply();
        int multiplyResult = multiply.calculate("2*8");
        System.out.println(multiplyResult);

        ICalculator minus = new Minus();
        int minusResult = minus.calculate("2-8");
        System.out.println(minusResult);
    }
}
