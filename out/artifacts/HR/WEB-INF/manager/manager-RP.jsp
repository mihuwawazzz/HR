<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>manager</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/manager.css">
</head>
<body>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="manager-menu.jsp"/>
    <div id="right">
        <h3>以下是您未处理的奖惩投诉：</h3>
        <c:choose>
            <c:when test="${empty requestScope.rewardAndPunishments}">
                <div style="margin-left:150px">
                    <h4>!没有投诉!</h4>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="rewardAndPunishment" items="${requestScope.rewardAndPunishments}">
                    <a href="javascript:void(0)" style="color: black">
                        <div class="resume">
                            <c:forEach var="user" items="${requestScope.users}">
                                <c:if test="${user.id eq rewardAndPunishment.userId}">
                                    姓名：${user.username}<br/>
                                </c:if>
                            </c:forEach>
                            原因：${rewardAndPunishment.reason}<br/>
                            金额：${rewardAndPunishment.money}元<br/>
                            时间：<fmt:formatDate value="${rewardAndPunishment.date}" pattern="yyyy-MM-dd"/><br/>
                            投诉理由：${rewardAndPunishment.comment}<br/>
                            <a href="<%=basePath%>/salaryAndRewAndPun/backRP/${rewardAndPunishment.id}">
                                <input class="passInterview" type="button" value="理由充分，下月退回">
                            </a>
                            <a href="<%=basePath%>/salaryAndRewAndPun/unBackRP/${rewardAndPunishment.id}">
                                <input id="unPassInterview" type="button" value="理由不足，爱咋咋地">
                            </a>
                        </div>
                    </a>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
