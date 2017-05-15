<%@page import="java.security.cert.X509Certificate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>属性信息</title>
</head>
<body>
<p> request属性信息</p>
<pre>
<%
	for(Enumeration en = request.getAttributeNames(); en.hasMoreElements();){
		String name = (String)en.nextElement();
		out.println(name);
		out.println("=" + request.getAttribute(name));
		out.println();
	}

%>
</pre>

<p> 数字证书信息</p>
<pre>
<%
	X509Certificate[] certs = (X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate");
	for(X509Certificate cert: certs){
		out.println("版本：\t" + cert.getVersion());
		out.println("序列号：\t" + cert.getSerialNumber());
		out.println("颁布者：\t" + cert.getIssuerDN().getName());
		out.println("使用者：\t" + cert.getSubjectDN().getName());
		out.println("签名算法：\t" + cert.getSigAlgName());
		out.println("证书类型：\t" + cert.getType());
		out.println("有效期从：\t" + cert.getNotBefore());
		out.println("至：\t" + cert.getNotAfter());
		
	}

%>
</pre>
</body>
</html>