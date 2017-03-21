<%--
  Created by IntelliJ IDEA.
  User: zhaozhenzhen
  Date: 2017/3/19
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
    <title>test</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/jquery-ui.css">
</head>
<script src="<%=basePath%>/static/js/jquery-3.1.1.js"></script>
<script src="<%=basePath%>/static/js/jquery-ui.js"></script>
<body>
<input id="testDatePicker"/>
</body>
<script>
    $( function() {
        $( "#testDatePicker" ).datepicker({
            minDate: new Date()+1,
            maxDate: new Date()+30,
        });
    } );
</script>
</html>
