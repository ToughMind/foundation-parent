package lq.pre20170513.xml.jaxb2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) 

public class Customer {
	 
	@XmlList 
	@XmlValue 
	private List<String> emailAddresses;

	public Customer() {
		emailAddresses = new ArrayList<String>();
	}

	public List<String> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(List<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

}