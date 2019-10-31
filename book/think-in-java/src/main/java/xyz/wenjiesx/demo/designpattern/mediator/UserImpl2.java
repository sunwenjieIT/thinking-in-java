package xyz.wenjiesx.demo.designpattern.mediator;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class UserImpl2 extends User {
    public UserImpl2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("user2 work");
    }
}
