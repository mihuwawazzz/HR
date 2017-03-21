<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
    <title>employee</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/employee.css">
</head>
<body>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="employee-menu.jsp"/>
    <div id="right">
    </div>
</div>
</body>
</html>
