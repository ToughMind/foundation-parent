package lq.design_pattern.command;

public class CommonWindow implements Command {
	public void execute() {
		System.out.println("普通窗口，办理普通存取款业务");
	}
}
