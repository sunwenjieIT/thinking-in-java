package xyz.wenjiesx.demo.designpattern.bridge;

/**
 * bridge桥接模式示例
 * @author wenji
 * @date 2019/10/30
 */
public class BridgeTest {

    public static void main(String[] args) {
        Bridge bridge = new MyBridge();


        SourceSub1 source1 = new SourceSub1();
        bridge.setSource(source1);
        bridge.method();


        SourceSub2 source2 = new SourceSub2();
        bridge.setSource(source2);
        bridge.method();
    }
}
