package lq.pre20170513.xml.jaxb2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Java2Xml {  
    public static void main(String[] args) throws Exception {  
        JAXBContext jc = JAXBContext.newInstance(Customer.class);  
   
        Customer customer = new Customer();  
        customer.getEmailAddresses().add("example@example.com");  
        customer.getEmailAddresses().add("example@example.org");  
   
        Marshaller marshaller = jc.createMarshaller();  
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
        marshaller.marshal(customer, System.out);  
    }  
}  