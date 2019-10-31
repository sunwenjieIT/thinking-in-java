package xyz.wenjiesx.demo.designpattern.interpreter;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class InterpreterTest {
    public static void main(String[] args) {

        Context context = new Context(10, 20);
        int result = new Minus()
                .interpret(new Context(new Plus().interpret(context), 8));
        System.out.println(result);

    }
}
