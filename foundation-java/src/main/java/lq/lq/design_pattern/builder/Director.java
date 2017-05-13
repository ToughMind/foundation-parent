package lq.lq.design_pattern.builder;

/**
 * @Description 定义创建者类 
 * @author liuquan
 * @time 2016年1月6日 上午12:44:21
 */
public class Director {
	
	private Factory factory;

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}


    /**
     * 薪资计算过程
     */
    public String doSalary(String name){
    	BasePay basePay = factory.getBasePay();
    	Bonus bonus = factory.getBonus();
        return basePay.doBasePay(name) + "---"  + "---" + bonus.doBonus(name) ;
    }

}
