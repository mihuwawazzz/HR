<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>visitor</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/visitor.css">
</head>
<body>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="visitor-menu.jsp"/>
    <div id="right">
    </div>
</div>
</body>
<script>
    $(function () {
        var note = "${requestScope.note}";
        if (note != "") {
            alert(note);
        }
    })
</script>
</html>
