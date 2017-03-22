<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <div id="salaryAndRP">
            <table>
                <c:if test="${!empty requestScope.users}">
                    <tr>
                        <th style="width: 50px">id</th>
                        <th style="width: 90px">姓名</th>
                        <th style="width: 120px">邮箱</th>
                        <th style="width: 100px">基本工资</th>
                        <th style="width: 80px">查看工资</th>
                        <th style="width: 80px">查看奖惩</th>
                    </tr>
                </c:if>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.basicSalary}</td>
                        <td>
                            <a href="<%=basePath%>/salaryAndRewAndPun/querySalariesByUserId?userId=${user.id}">查看</a>
                        </td>
                        <td>
                            <a href="<%=basePath%>/salaryAndRewAndPun/queryRPsByUserId?userId=${user.id}">查看</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="salaryAndRPDetail" style="margin-top: 80px;height: 500px;overflow-y:auto">
            <c:if test="${!empty requestScope.salaries}">
                <div style="width:90px;font-size: 22px;margin-left: 40px;margin-bottom: 10px;border: dashed gray 1px;border-radius: 10px">工资明细</div>
                <div style="margin-left: 40px">
                    <table>
                        <tr>
                            <th>结算日期</th>
                            <th>基本工资</th>
                            <th>奖惩</th>
                            <th>社保</th>
                            <th>总共</th>
                        </tr>
                        <c:forEach var="salary" items="${requestScope.salaries}">
                            <tr>
                                <td style="width: 150px"><fmt:formatDate
                                        value="${salary.settlementDate}"
                                        pattern="yyyy-MM-dd"/></td>
                                <td style="width: 90px">${salary.basicSalary}元</td>
                                <td style="width: 90px">${salary.rewardAndPunishment}元</td>
                                <td style="width: 90px">${salary.socialInsurance}元</td>
                                <td style="width: 90px">${salary.total}元</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>

            <c:if test="${!empty requestScope.rewardAndPunishments}">
                <div style="width:90px;font-size: 22px;margin-left: 40px;margin-bottom: 10px;border: dashed gray 1px;border-radius: 10px">奖惩明细</div>
                <div style="margin-left: 40px">
                    <table>
                        <tr>
                            <th>日期</th>
                            <th>原因</th>
                            <th>金额</th>
                            <th>备注</th>
                        </tr>
                        <c:forEach var="rewardAndPunishment" items="${requestScope.rewardAndPunishments}">
                            <tr>
                                <td style="width: 150px"><fmt:formatDate
                                        value="${rewardAndPunishment.date}" pattern="yyyy-MM-dd"/></td>
                                <td style="width: 150px">${rewardAndPunishment.reason}</td>
                                <td style="width: 100px">${rewardAndPunishment.money}元</td>
                                <td style="width: 150px">${rewardAndPunishment.comment}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
