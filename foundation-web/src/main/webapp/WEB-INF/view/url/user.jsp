<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<table border="1">
		<thead>Request 参数键值对
		</thead>
		<tr>
			<th>key</th>
			<th>value</th>
		</tr>
		<c:forEach items="${requestScope}" var="v">
			<tr>
				<th>${v.key}</th>
				<th>${v.value}</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>