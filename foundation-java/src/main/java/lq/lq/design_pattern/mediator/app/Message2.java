package lq.lq.design_pattern.mediator.app;

public class Message2 extends Message {
	private String name;

	public String getName() {
		return "消息队列2";
	}

	public void receiveMessage(Message message1) {
		System.out.println(message1.getName() + "堵塞了" + getName());
	}
}
