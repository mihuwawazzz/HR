<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<div id="header" style="height: 80px">
    <div><img src="<%=basePath%>/static/images/logo.png"></div>
</div>
