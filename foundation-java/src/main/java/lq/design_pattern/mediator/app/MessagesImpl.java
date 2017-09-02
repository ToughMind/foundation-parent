package lq.design_pattern.mediator.app;

import java.util.Map;
import java.util.HashMap;

public class MessagesImpl implements Messages {
	private Map map = new HashMap();

	public void send(String messageFrom, String messageTo) {
		Message message1 = (Message) map.get(messageFrom);
		Message message2 = (Message) map.get(messageTo);
		message2.receiveMessage(message1);
	}

	public void add(Message message) {
		message.setMessages(this);
		map.put(message.getName(), message);
	}
}
