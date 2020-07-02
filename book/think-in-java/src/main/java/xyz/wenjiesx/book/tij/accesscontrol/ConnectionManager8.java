package xyz.wenjiesx.book.tij.accesscontrol;

/**
 *
 *
 * @author wenji
 * @Date 6/15/2020
 */
public class ConnectionManager8 {

    private int countLeft = 3;
    private Connection[] ca = new Connection[3];
    {
        for (Connection x : ca) {
            x = Connection.makeConnection();
        }
    }

    public Connection getInstance() {
        if (countLeft > 0) {
            return ca[--countLeft];
        } else {
            System.out.println("No more connections");
            return null;
        }
    }

    public static void main(String[] args) {

        ConnectionManager8 cm = new ConnectionManager8();
        //因为在同个class内, 所以还是可以访问到的. 否则需要通过method访问
        System.out.println(cm.countLeft);
        cm.getInstance();
        System.out.println(cm.countLeft);
        cm.getInstance();
        System.out.println(cm.countLeft);
        cm.getInstance();
        System.out.println(cm.getInstance());
        System.out.println(cm.countLeft);
        //        ConnectionManager8 cm = new ConnectionManager8();
//        System.out.println();

    }

}

class Connection {
    private static int count = 0;
    private int i = 0;

    public Connection() {
        System.out.println("Connection()");
    }

    static Connection makeConnection() {
        count++;
        return new Connection();
    }

    public static int getCount() {
        return count;
    }
    @Override
    public String toString() {
        return "Connection: " + count;
    }

}