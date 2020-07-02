package xyz.wenjiesx.book.tij.initializationAndCleanup5;

/**
 * 编写名Tank的类, 此类的状态可以是"满的"或"空的".
 * 终结条件是:
 * 对象被清理时必须是空状态
 * 请编写finalize以检验终结条件是否成立
 * 在main中测试tank可能发生的几种使用方式
 *
 * @author wenji
 * @Date 2020/6/5
 */
public class Practice12 {

    public static void main(String[] args) {
        Tank tank1 = new Tank(true, "tank1");
        Tank tank2 = new Tank(false, "tank2");
        tank1.full();

        System.runFinalization();
        new Tank(false, "tank3");
        System.gc();
        System.out.println("try deprecated runFinalizersOnExit(true):");
//        System.runFinalizersOnExit(true);
        System.out.println("last forced gc():");
        System.gc();
    }
}


class Tank {

    boolean isEmpty = false;

    String name = "";

    public Tank(boolean isEmpty, String name) {
        this.isEmpty = isEmpty;
        this.name = name;
    }

    public void full() {
        isEmpty = false;
    }

    public void empty() {
        isEmpty = true;
    }

    @Override
    protected void finalize() throws Throwable {
        if (!isEmpty) {
            System.out.println("Error: " + name + " still full");
        }
        //        super.finalize();
    }
}