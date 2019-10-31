package xyz.wenjiesx.demo.designpattern.mediator;

/**
 * @author wenji
 * @date 2019/10/31
 */
public abstract class User {

    private Mediator mediator;

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public abstract void work();
}
