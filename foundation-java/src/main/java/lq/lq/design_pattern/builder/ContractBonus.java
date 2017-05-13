package lq.lq.design_pattern.builder;

/**
 * @Description 合同工的奖金计算（产品）实现类 
 * @author liuquan
 * @time 2016年1月6日 上午12:39:07
 */
public class ContractBonus implements Bonus {

	@Override
	public String doBonus(String name) {
		// TODO Auto-generated method stub
		return name + "奖金开始计算";
	}

}