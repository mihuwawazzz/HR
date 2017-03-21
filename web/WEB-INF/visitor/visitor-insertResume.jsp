<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhaozhenzhen
  Date: 2017/3/13
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>visitor-insertResume</title>
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
            <c:when test="${empty requestScope.resume}">
                <div id="resume">
                    <form action="<%=basePath%>/resume/insertOrUpdate" method="post">
                        <table>
                            <tr>
                                <th colspan="2"><h2>iotek公司求职简历表</h2></th>
                            </tr>
                            <input type="hidden" id="userId" name="userId" value="${sessionScope.user.id}"/>
                            <tr>
                                <td class="items">姓名:</td>
                                <td><input type="text" name="username"/></td>
                            </tr>
                            <tr>
                                <td class="items">邮箱:</td>
                                <td><input type="text" name="email"/></td>
                            </tr>
                            <tr>
                                <td class="items">出生日期:</td>
                                <td>年:
                                    <select id="year" name="birthday">
                                        <c:forEach var="year" items="${requestScope.years}">
                                            <option class="year" value="${year}">${year}</option>
                                        </c:forEach>
                                    </select>
                                    月:
                                    <select id="month" name="birthday">
                                        <c:forEach var="month" items="${requestScope.months}">
                                            <option class="month" value="${month}">${month}</option>
                                        </c:forEach>
                                    </select>
                                    日:
                                    <select id="day" name="birthday">
                                        <c:forEach var="i" begin="1" end="31">
                                            <option class="month" value="${i}">${i}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="items">性别:</td>
                                <td>
                                    <input type="radio" name="gender" value="1"/>男
                                    <input type="radio" name="gender" value="0"/>女
                                </td>
                            </tr>
                            <tr>
                                <td class="items">应聘职位:</td>
                                <td>部门:
                                    <select id="department" name="department">
                                        <c:forEach var="department" items="${requestScope.departments}">
                                            <option class="department" value="${department.id}">${department.name}</option>
                                        </c:forEach>
                                    </select>
                                    职位:
                                    <select id="position" name="positionId">
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="items">工作经验:</td>
                                <td>
                                    <textarea name="workExperience"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="items">自我介绍:</td>
                                <td>
                                    <textarea name="introduction"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="text-align: right;border: none">
                                    <input style="width: 60px" type="submit" value="保存"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div id="note">你写过简历了，请直接去修改</div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
<script src="<%=basePath%>/static/js/jquery-3.1.1.js"></script>
<script>
    $(function () {
        $("#month").click(countDay);
        $("#year").click(countDay);
        $("#department").click(showPosition);
    });
</script>
</html>
