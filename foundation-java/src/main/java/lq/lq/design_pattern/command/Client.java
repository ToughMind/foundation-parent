package lq.lq.design_pattern.command;

public class Client {
	public static void main(String[] args) {
		Command commonWindow = new CommonWindow();
		Command vipWindow = new VipWindow();
		Lotto lotto = new Lotto();
		lotto.addCommand(commonWindow);
		lotto.addCommand(vipWindow);
		lotto.doCommand();
	}
}
