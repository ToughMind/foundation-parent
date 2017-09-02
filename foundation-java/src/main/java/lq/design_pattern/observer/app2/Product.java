package lq.design_pattern.observer.app2;

import java.util.Observable;

public class Product extends Observable {
	private String name;
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
		setChanged();
		notifyObservers(price);
	}
}
