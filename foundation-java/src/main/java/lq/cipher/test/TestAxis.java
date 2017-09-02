package lq.cipher.test;

//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestAxis {
	private static String namespaceUri = "http://localhost:8080/test/services/Version";
	private static String wsdlUrl = "http://localhost:8080/test/services/Version?wsdl";
	
	/*public static void main(String[] args) throws ServiceException, MalformedURLException, RemoteException {
		Service service = new Service();
		Call call = (Call)service.createCall();
		// 调用远程方法
		call.setOperationName(new QName(namespaceUri, "getVersion"));
		// 设置URL
		call.setTargetEndpointAddress(new URL(wsdlUrl));
		// 执行远程调用
		String version = (String) call.invoke(new Object[] {});
		System.out.println(version);
	}*/
}
