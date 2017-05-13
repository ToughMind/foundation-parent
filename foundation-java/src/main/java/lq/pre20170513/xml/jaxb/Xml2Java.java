package lq.pre20170513.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Xml2Java {
	public static void main(String[] args) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Person.class); 
		Unmarshaller unmarshaller = context.createUnmarshaller();   
		File file = new File("person.xml");
		Person p = (Person) unmarshaller.unmarshal(file);
		System.out.println(p.id);
		System.out.println(p.name);
		System.out.println(p.age);
	}
}
