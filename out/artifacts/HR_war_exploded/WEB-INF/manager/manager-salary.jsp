<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>manager-salary</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/manager.css">
</head>
<body>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="manager-menu.jsp"/>
    <div id="right">
        <div id="settlement">
            <a href="<%=basePath%>/salaryAndRewAndPun/insertSalary"><input id="settlementSalary" type="button" value="结算工资"/></a>
        </div>
        <div id="userDetail">
            <form action="<%=basePath%>/user/queryByUsername">
                请输入员工名字：<input name="username" type="text"/>
                <input type="submit" value="查询"/>
            </form>
            <table>
                <c:if test="${!empty requestScope.users}">
                    <tr>
                        <th>id</th>
                        <th>姓名</th>
                        <th>邮箱</th>
                        <th>出生日期</th>
                        <th>基本工资</th>
                    </tr>
                </c:if>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></td>
                        <td>${user.basicSalary}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="insertRewardAndPunishment">
            <div style="height: 30px">为指定用户添加奖惩信息：<input style="width: 70px" id="insertRAndPButton" type="button" value="添加奖惩"/></div>
            <div style="margin-left: 50px">
                <form action="<%=basePath%>/salaryAndRewAndPun/insertRewardAndPunishment">
                    <table id="insertRAndP">
                        <tr>
                            <td>用户id:</td>
                            <td><input id="insertRU" type="number" min="1" name="userId"/></td>
                        </tr>
                        <tr class="insert">
                            <td>原因:</td>
                            <td><input type="text" name="rewardAndPunishments[0].reason"/></td>
                            <td>金额(元):</td>
                            <td><input type="number" name="rewardAndPunishments[0].money"/></td>
                        </tr>
                    </table>
                    <div style="margin-left: 308px">
                        <input style="width: 60px" id="insertRR" type="submit" value="提交"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        var num = 1;
        $("#insertRAndPButton").click(function () {
            var cl = "insert"+num;
            var insert = $(".insert").clone(true).removeClass("insert").addClass(cl);
            var name1 = "rewardAndPunishments[" + num + "].reason";
            var name2 = "rewardAndPunishments[" + num + "].money";
            $("#insertRAndP").append(insert);
            $("."+cl).find("input:first").attr("name",name1);
            $("."+cl).find("input:last").attr("name",name2);
            num++;
        });
    })
</script>
</html>
