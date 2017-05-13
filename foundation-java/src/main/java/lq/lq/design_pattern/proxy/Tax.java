package lq.lq.design_pattern.proxy;

/**
 * @description 计算税率的实现类 
 * 
 * @author liuquan
 * @date  2016年1月14日
 */
public class Tax implements TaxInterface{
	public void doTax() {
		System.out.println("进行所得税计算的逻辑处理");
	}
}
