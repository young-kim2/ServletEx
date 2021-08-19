<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie Test Form</title>
</head>
<body>
<h1>Cookie Test Form</h1>

<h3>Add/Edit Cookie</h3>
<form method="POST" action="<%= request.getContextPath() %>/cookies">
<label for="example">Cookie Value</label>

<input type="text" name="example" id="example">
<input type="submit" value="쿠키 설정" />

</form>
</body>
</html>