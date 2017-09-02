package lq.design_pattern.app;

public class Client {
	public static void main(String[] args) {
		NoAbateMemeber noAbateMemeber = new NoAbateMemeber();
		NineAbateMemeber nineAbateMemeber = new NineAbateMemeber();
		EightAbateMemeber eightAbateMemeber = new EightAbateMemeber();
		SevenAbateMemeber sevenAbateMemeber = new SevenAbateMemeber();
		noAbateMemeber.setCount(10);
		noAbateMemeber.setPrice(10);
		nineAbateMemeber.setCount(10);
		nineAbateMemeber.setPrice(10);
		eightAbateMemeber.setCount(10);
		eightAbateMemeber.setPrice(10);
		sevenAbateMemeber.setCount(10);
		sevenAbateMemeber.setPrice(10);
		Memebers memebers = new Memebers();
		memebers.add(noAbateMemeber);
		memebers.add(nineAbateMemeber);
		memebers.add(eightAbateMemeber);
		memebers.add(sevenAbateMemeber);
		ComputerMember computerMemeber = new ComputerMember();
		computerMemeber.account(memebers.getList());
	}
}
