package lq.design_pattern.mediator.app;

public class Client {
	public static void main(String[] argv) {
		Messages messages = new MessagesImpl();
		Message a = new Message1();
		Message b = new Message2();
		messages.add(a);
		messages.add(b);
		a.sendMessage("消息队列1", "消息队列2");
	}
}
