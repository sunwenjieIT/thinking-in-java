package xyz.wenjiesx.demo.designpattern.strategy;

/**
 *
 * @author wenji
 */
public interface ICalculator {

    /**
     * 抽象计算方法
     * @param exp
     * @return
     */
    public int calculate(String exp);

}
