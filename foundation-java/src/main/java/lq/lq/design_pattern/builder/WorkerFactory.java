package lq.lq.design_pattern.builder;

/**
 * @Description 劳务工的工厂类 
 * @author liuquan
 * @time 2016年1月6日 上午12:31:31
 */
public class WorkerFactory extends Factory {
	public BasePay getBasePay(){
		return new WorkerBasePay();
	}
	public Bonus getBonus(){
		return new WorkerBonus();
	}
}
