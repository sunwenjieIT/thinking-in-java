package xyz.wenjiesx.book.tij.reusingclasses7;

/**
 * @author wenji
 * @Date 6/18/2020
 */
public class LazyInstance1 {

    public static void main(String[] args) {
        ReusingSimple rs = new ReusingSimple();
        System.out.println(rs.useSimple());;
    }

}

class Simple {

    String s = "default";

    public Simple() {
        System.out.println("simple class initialize");
    }

    @Override
    public String toString() {
        return "Simple{" +
                "s='" + s + '\'' +
                '}';
    }
}

class ReusingSimple {
    Simple simple;

    public ReusingSimple() {
        System.out.println("ReusingSimple initialize");
    }

    public String useSimple() {
        if (simple == null)
            simple = new Simple();

        return simple.toString();
    }
}