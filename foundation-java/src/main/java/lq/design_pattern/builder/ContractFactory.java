package lq.design_pattern.builder;

/**
 * @Description 定义合同工的工厂类
 * @author liuquan
 * @time 2016年1月6日 上午12:28:55
 */ 
public class ContractFactory extends Factory {
	public BasePay getBasePay(){
		return new ContractBasePay();
	}
	public Bonus getBonus(){
		return new ContractBonus();
	}
}