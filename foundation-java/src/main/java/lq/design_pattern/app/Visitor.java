package lq.design_pattern.app;

//首先定义一个访问者类
public class Visitor {
	public double visit(NoAbateMemeber noAbateMemeber) {
		return noAbateMemeber.doComputer();
	}

	public double visit(NineAbateMemeber nineAbateMemeber) {
		return nineAbateMemeber.doComputer();
	}

	public double visit(EightAbateMemeber eightAbateMemeber) {
		return eightAbateMemeber.doComputer();
	}

	public double visit(SevenAbateMemeber sevenAbateMemeber) {
		return sevenAbateMemeber.doComputer();
	}
}
