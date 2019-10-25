package xyz.wenjiesx.demo.hash;

import java.util.HashMap;

/**
 * @author wenji
 * @date 2019/10/24
 */
public class HashDemo {

    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("abc", 1);
        String a = "abc'";
//        a.hashCode()
        int abc = 112;
        Integer c = abc;
    }
}
