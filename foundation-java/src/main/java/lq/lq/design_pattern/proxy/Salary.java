package lq.lq.design_pattern.proxy;

/**
 * @description 计算薪资的实现类 
 * 
 * @author liuquan
 * @date  2016年1月14日
 */
public class Salary implements SalaryInterface{
	public void doSalary() {
		System.out.println("进行薪资计算的逻辑处理");
	}
}
