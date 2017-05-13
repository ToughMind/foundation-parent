package lq.lq.design_pattern.mediator.app;

public interface Messages {
	void send(String messageFrom, String messageTo);

	void add(Message message);
}
