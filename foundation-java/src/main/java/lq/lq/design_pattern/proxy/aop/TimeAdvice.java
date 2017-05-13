package lq.lq.design_pattern.proxy.aop;

public class TimeAdvice implements Advice {
	long startTime;
	long endTime;
	@Override
	public void before() {
		startTime=System.nanoTime();   //获取开始时间
	}

	@Override
	public void after() {
		endTime=System.nanoTime(); //获取结束时间
 		System.out.println("计算程序运行时间： "+(endTime-startTime)+"ns");
	}

}
