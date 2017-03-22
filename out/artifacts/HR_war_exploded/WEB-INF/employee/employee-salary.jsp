<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div style="margin-top: 20px; width: 580px;height: 60px;text-align: center;vertical-align:middle;font-size: 35px">
            上月工资
        </div>
        <div id="lastSalary-info">
            <table>
                <tr>
                    <th>姓名</th>
                    <th>基本工资</th>
                    <%--<th>加班费</th>--%>
                    <th>奖惩</th>
                    <th>社保</th>
                    <th>总共</th>
                    <th>结算日期</th>
                </tr>
                <tr>
                    <c:if test="${!empty requestScope.salary}">
                        <td style="width: 90px">${sessionScope.user.username}</td>
                        <td style="width: 90px">${requestScope.salary.basicSalary}元</td>
                        <%--<td style="width: 80px">${requestScope.salary.overtimePay}元</td>--%>
                        <td style="width: 90px">${requestScope.salary.rewardAndPunishment}元</td>
                        <td style="width: 90px">${requestScope.salary.socialInsurance}元</td>
                        <td style="width: 90px">${requestScope.salary.total}元</td>
                        <td style="width: 150px"><fmt:formatDate
                                value="${requestScope.salary.settlementDate}" pattern="yyyy-MM-dd"/></td>
                    </c:if>
                </tr>
            </table>
        </div>
        <div style="margin-top: 70px; width: 580px;height: 60px;text-align: center;vertical-align:middle;font-size: 35px">
            上月奖惩
        </div>
        <div id="lastRP-info">
            <table>
                <tr>
                    <th>日期</th>
                    <th>原因</th>
                    <th>金额</th>
                    <th>投诉</th>
                    <th>备注</th>
                </tr>
                <c:forEach var="rewardAndPunishment" items="${requestScope.rewardAndPunishments}">
                    <tr>
                        <td style="width: 100px"><fmt:formatDate
                                value="${rewardAndPunishment.date}" pattern="yyyy-MM-dd"/></td>
                        <td style="width: 150px">${rewardAndPunishment.reason}</td>
                        <td style="width: 100px">${rewardAndPunishment.money}元</td>
                        <td style="width: 80px">
                            <c:choose>
                                <c:when test="${rewardAndPunishment.state == 0}">
                                    <form action="<%=basePath%>/salaryAndRewAndPun/updateRP">
                                        <input hidden="hidden" name="userId" value="${sessionScope.user.id}">
                                        <input hidden="hidden" name="id" value="${rewardAndPunishment.id}">
                                        <input hidden="hidden" name="comment" value="">
                                        <input class="changeState" type="button" value="我不服"/>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <input disabled="disabled" type="button" value="不可投诉"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="width: 150px">${rewardAndPunishment.comment}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $(".changeState").click(function () {
            var comment = window.prompt("说出你不服的理由：");
            $(this).prev().val(comment);
            $(this).parent().submit();
        });
    });

</script>
</html>
