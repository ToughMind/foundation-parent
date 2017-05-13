package lq.lq.design_pattern.proxy.aop;

public class ControlAdvice extends TimeAdvice {

	@Override
	public void before() {
		super.before();
		System.out.println("判断系统的权限 ");
	}

	@Override
	public void after() {
		super.after();
	}
}
