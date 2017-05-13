package lq.lq.design_pattern.builder;

/**
 * @Description 劳务工的信自己算（产品）实现类 
 * @author liuquan
 * @time 2016年1月6日 上午12:36:11
 */
public class WorkerBasePay implements BasePay {

	@Override
	public String doBasePay(String name) {
		return name + "基本工资开始计算";
	}

}