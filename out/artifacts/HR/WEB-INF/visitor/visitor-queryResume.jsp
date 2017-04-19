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
    <title>visitor-updateResume</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/visitor.css">
</head>
<body>
<%
    int[] years = {1986,1987,1988, 1989, 1990, 1991, 1992, 1993, 1994,1995};
    request.setAttribute("years", years);
    int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    request.setAttribute("months", months);
%>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="visitor-menu.jsp"/>
    <div id="right">
        <c:choose>
            <c:when test="${!empty requestScope.resume}">
                <div id="resume">
                        <table>
                            <tr>
                                <th colspan="2"><h2>iotek公司求职简历表</h2></th>
                            </tr>
                            <tr>
                                <td class="items">姓名:</td>
                                <td>${requestScope.resume.username}</td>
                            </tr>
                            <tr>
                                <td class="items">邮箱:</td>
                                <td>${requestScope.resume.email}</td>
                            </tr>
                            <tr>
                                <td class="items">出生日期:</td>
                                <td><fmt:formatDate value="${requestScope.resume.birthday}" pattern="yyyy-MM-dd"/></td>
                            </tr>
                            <tr>
                                <td class="items">性别:</td>
                                <td>
                                    <c:if test="${requestScope.resume.gender == 0}">女</c:if>
                                    <c:if test="${requestScope.resume.gender == 1}">男</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="items">应聘职位:</td>
                                <td>${requestScope.position.name}</td>
                            </tr>
                            <tr>
                                <td class="items">工作经验:</td>
                                <td>${requestScope.resume.workExperience}</td>
                            </tr>
                            <tr>
                                <td class="items">自我介绍:</td>
                                <td>${requestScope.resume.introduction}</td>
                            </tr>
                            <tr>
                                <td class="items">提交简历:</td>
                                <c:choose>
                                    <c:when test="${requestScope.resume.state eq -1}">
                                        <td><a href="<%=basePath%>/resume/updateState?state=0&id=${requestScope.resume.id}">
                                            <input type="button" value="提交"/>
                                        </a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>您提交过了，不可重复提交！</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </table>
                </div>
            </c:when>
            <c:otherwise>
                <div id="note">你还没写过简历，先去新建一个吧</div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
