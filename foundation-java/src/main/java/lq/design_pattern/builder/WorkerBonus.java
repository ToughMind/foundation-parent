package lq.design_pattern.builder;

/**
 * @Description 劳务工的奖金计算（产品）实现类 
 * @author liuquan
 * @time 2016年1月6日 上午12:40:17
 */
public class WorkerBonus implements Bonus {

	@Override
	public String doBonus(String name) {
		// TODO Auto-generated method stub
		return name + "奖金开始计算";
	}

}