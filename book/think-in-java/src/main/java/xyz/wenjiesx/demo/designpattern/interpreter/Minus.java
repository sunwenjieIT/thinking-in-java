package xyz.wenjiesx.demo.designpattern.interpreter;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class Minus implements Expression {
    @Override
    public int interpret(Context context) {
        return context.getNum1() - context.getNum2();
    }
}
