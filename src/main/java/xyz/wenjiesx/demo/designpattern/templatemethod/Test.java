package xyz.wenjiesx.demo.designpattern.templatemethod;

/**
 * @author wenji
 * @date 2019/10/30
 */
public class Test {
    public static void main(String[] args) {
        AbstractCalculator cal = new Plus();
        int result = cal.calculate("6+2", "\\+");
        System.out.println(result);
    }
}
