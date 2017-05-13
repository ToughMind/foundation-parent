package lq.pre20170513.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Java2Xml {
	public static void main(String[] args) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Person.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf8");
		//是否格式化生成的xml串
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//是否省略xml头信息 <?……>
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
		Person p = new Person();
		marshaller.marshal(p, System.out);		
	}
	
}

 
 