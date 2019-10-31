package xyz.wenjiesx.demo.designpattern.mediator;

/**
 * @author wenji
 * @date 2019/10/31
 */
public class MyMediator implements Mediator {

    private User user1;
    private User user2;

    @Override
    public void createMediator() {
        user1 = new UserImpl1(this);
        user2 = new UserImpl2(this);
    }

    @Override
    public void workAll() {
        user1.work();
        user2.work();

    }
}
