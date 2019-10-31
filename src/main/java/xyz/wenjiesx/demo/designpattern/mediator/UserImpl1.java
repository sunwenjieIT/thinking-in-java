package xyz.wenjiesx.demo.designpattern.mediator;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class UserImpl1 extends User {
    public UserImpl1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user1 work");
    }
}
