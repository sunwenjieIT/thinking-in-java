package xyz.wenjiesx.book.tij.initializationAndCleanup5;

/**
 * 练习10(2) 编写具有finalize()方法的类, 并在方法中打印消息
 * 在main中为该类创建一个对象. 试解释这个程序的行为
 *
 * 运行结果:
 * Error: still logged in
 * @author wenji
 * @Date 2020/6/5
 */
public class Practice10 {

    public static void main(String[] args) {
        WebBank bank1 = new WebBank(true);
        WebBank bank2 = new WebBank(true);
        // Proper cleanup: log out of bank1 before going home
        bank1.logOut();
        // Drop the reference, forget to cleanup:
        new WebBank(true);
        // Force garbage collection and finalization:
        System.gc();
    }
}

class WebBank {
    boolean loggedIn = false;
    WebBank(boolean logStatus) {
        loggedIn = logStatus;
    }
    void logIn() {
        loggedIn = true;
    }
    void logOut() {
        loggedIn = false;
    }

    @Override
    protected void finalize() throws Throwable {
        if (loggedIn) {
            System.out.println("Error: still logged in");
        }
        super.finalize();
    }
}