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
    <title>manager-attendance</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/manager.css">
</head>
<body>
<div id="page">
    <%
        int[] years = {2016, 2017};
        request.setAttribute("years", years);
        int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        request.setAttribute("months", months);
    %>
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="manager-menu.jsp"/>
    <div id="right">
        <div style="margin-left: 30px;margin-right: 30px;margin-top: 30px">
            <form action="<%=basePath%>/attendance/queryByMonthByUserId" method="post">
                用户id：<input type="text" name="userId"/>
                <select id="year" name="year">
                    <c:forEach var="year" items="${requestScope.years}">
                        <option name="year" value="${year}">${year}</option>
                    </c:forEach>
                </select>年
                <select id="month" name="month">
                    <c:forEach var="month" items="${requestScope.months}">
                        <option name="month" value="${month}">${month}</option>
                    </c:forEach>
                </select>月
                <input type="submit" value="查询">
            </form>
        </div>
        <div style="margin-left: 30px;margin-right: 30px;margin-top: 20px;height: 500px;overflow-y: auto">
            <table>
                <tr>
                    <th class="manager-attendance">用户id</th>
                    <th class="manager-attendance">年</th>
                    <th class="manager-attendance">月</th>
                    <th class="manager-attendance">日</th>
                    <th class="manager-attendance">上班时间</th>
                    <th class="manager-attendance">下班时间</th>
                    <th class="manager-attendance">状态</th>
                </tr>
                <c:forEach var="attendance" items="${requestScope.attendances}">
                    <tr>
                        <td class="manager-attendance" style="width: 25px">${attendance.userId}</td>
                        <td class="manager-attendance" style="width: 20px">${attendance.year}</td>
                        <td class="manager-attendance" style="width: 20px">${attendance.month}</td>
                        <td class="manager-attendance" style="width: 20px">${attendance.day}</td>
                        <td class="manager-attendance" style="width: 100px">
                            <fmt:formatDate value="${attendance.clockInTime}"
                                            pattern="HH:mm:ss"/>
                        </td>
                        <td class="manager-attendance" style="width: 100px">
                            <fmt:formatDate value="${attendance.clockOutTime}"
                                            pattern="HH:mm:ss"/>
                        </td>
                        <td class="manager-attendance" style="width: 30px">${attendance.state}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
