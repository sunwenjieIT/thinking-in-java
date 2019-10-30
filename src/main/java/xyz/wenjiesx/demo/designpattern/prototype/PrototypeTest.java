package xyz.wenjiesx.demo.designpattern.prototype;

/**
 * 浅复制和深复制
 * 深复制会将引用类型也完全复制一次
 *
 * @author wenji
 * @date 2019/10/30
 */
public class PrototypeTest {
    public static void main(String[] args) throws Exception {

        Prototype prototype = new Prototype();
        prototype.setString("default");

        Prototype clone = (Prototype) prototype.clone();

        Prototype prototype1 = (Prototype) prototype.deepClone();

    }
}
