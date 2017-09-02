package lq.design_pattern.builder;

/**
 * @Description 抽象工厂类 
 * @author liuquan
 * @time 2016年1月6日 上午12:28:41
 */
public abstract class Factory {
	protected abstract BasePay getBasePay();
	protected abstract Bonus getBonus();

}