package xyz.wenjiesx.demo.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式的示例
 * @author wenji
 * @date 2019/10/31
 */
public class Test {

    public static void main(String[] args) {
        Collection collection = new MyCollection();

        Iterator it = collection.iterator();

        List<Integer> a = new ArrayList<>();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
