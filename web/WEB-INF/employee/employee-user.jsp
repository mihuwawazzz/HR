<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div style="margin-top: 20px; width: 580px;height: 50px;text-align: center;vertical-align:middle;font-size: 35px">个人信息</div>
        <div id="user-info">
            <table>
                <tr>
                    <th>id</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>出生日期</th>
                    <th>基本工资</th>
                    <th>职位名</th>
                </tr>
                <tr>
                    <td style="width: 40px">${sessionScope.user.id}</td>
                    <td style="width: 90px">${sessionScope.user.username}</td>
                    <td style="width: 70px">
                        <c:if test="${sessionScope.user.gender == 0}">女</c:if>
                        <c:if test="${sessionScope.user.gender == 1}">男</c:if>
                    </td>
                    <td style="width: 120px">${sessionScope.user.email}</td>
                    <td style="width: 140px"><fmt:formatDate value="${sessionScope.user.birthday}"
                                                             pattern="yyyy-MM-dd"/></td>
                    <td style="width: 90px">${sessionScope.user.basicSalary}</td>
                    <td style="width: 120px">
                        <c:forEach var="department" items="${requestScope.departments}">
                            <c:forEach var="position" items="${department.positions}">
                                <c:if test="${position.id eq sessionScope.user.positionId}">
                                    ${position.name}
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </td>
                </tr>
            </table>
        </div>
        <div style="margin-top: 50px; width: 580px;height: 30px;text-align: center;vertical-align:middle;font-size: 35px">部门/职位</div>
        <div id="department-position">
            <div style="width: 300px;padding-left: 140px;">
                <div style="float:left;width:150px;color: blue;font-family: Hei">部门名称</div>
                <div style="width:300px;color: blue;font-family: Hei">创建时间</div>
            </div>
            <c:forEach var="department" items="${requestScope.departments}">
                <div style="width: 400px;padding-left: 100px">
                    <div class="department-items" style="float:left;width:39px;color: red">+</div>
                    <div style="float:left;width: 150px">${department.name}</div>
                    <div style="width: 400px"><fmt:formatDate value="${department.createTime}" pattern="yyyy-MM-dd HH:mm"/></div>
                </div>
                <div hidden="hidden" style="width: 300px;padding-left: 190px">
                    <div style="float:left;width: 150px;color: blue;font-family: Hei">职位名称</div>
                    <div style="width: 300px;color: blue;font-family: Hei">创建时间</div>
                    <c:forEach var="position" items="${department.positions}">
                        <div>
                            <div style="float:left;width: 150px">${position.name}</div>
                            <div style="width:300px"><fmt:formatDate value="${position.createTime}" pattern="yyyy-MM-dd HH:mm"/></div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $(".department-items").click(function () {
            var hideOrShow = $(this).text();
            if (hideOrShow == "+") {
                $(this).text("-");
            } else {
                $(this).text("+");
            }
            $(this).parent().next().toggle();
        });
    });
</script>
</html>
