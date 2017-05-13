<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<form action="/moreFileUpload" method="post"
		enctype="multipart/form-data">
		<input type="text" name="username"> <br /> <input type="file"
			name="uploadFile1"> <br /> <input type="file"
			name="uploadFile2"> <br /> <input type="file"
			name="uploadFile3"> <br /> <input type="file"
			name="uploadFile4"> <br /> <input type="submit" value="开始上传">
	</form>
</body>
</html>
