package lq.lq.design_pattern.builder;

/**
 * @Description 合同工的薪资计算（产品）实现类 
 * @author liuquan
 * @time 2016年1月6日 上午12:34:00
 */
public class ContractBasePay implements BasePay {

	@Override
	public String doBasePay(String name) {
		// TODO Auto-generated method stub
		return name + "基本工资开始计算";
	}
}