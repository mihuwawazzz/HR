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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/jquery-ui.css">
</head>
<script src="<%=basePath%>/static/js/jquery-ui.js"></script>
<body>
<%
    int[] hours = {9, 10, 11, 12, 13, 14, 15, 16};
    int[] minutes = new int[60];
    for (int i = 0; i < 60; i++) {
        minutes[i] = i;
    }
    request.setAttribute("hours", hours);
    request.setAttribute("minutes", minutes);
%>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="manager-menu.jsp"/>
    <div id="right">
        <div class="trainingDetail" style="margin-top: 50px">
            <form action="<%=basePath%>/training/insertTraining">
                <table class="trainingDetailTable">
                    <tr style="height:50px">
                        <th>标题:</th>
                        <th><input name="title" style="font-size: 22px;height: 50px"></th>
                    </tr>
                    <tr>
                        <td class="trainingDetailItems">开始时间:</td>
                        <td class="trainingDetailItems2">
                            <input class="trainingDay" name="trainingDay" readonly/>
                            <select name="trainingHour">
                                <c:forEach var="hour" items="${requestScope.hours}">
                                    <option value="${hour}">${hour}</option>
                                </c:forEach>
                            </select>点
                            <select name="trainingMinute">
                                <c:forEach var="minute" items="${requestScope.minutes}">
                                    <option value="${minute}">${minute}</option>
                                </c:forEach>
                            </select>分
                        </td>
                    </tr>
                    <tr>
                        <td class="trainingDetailItems">时长:</td>
                        <td class="trainingDetailItems2"><input name="minute"/>分钟</td>
                    </tr>
                    <tr>
                        <td class="trainingDetailItems">讲师:</td>
                        <td class="trainingDetailItems2"><input name="lecturer"/></td>
                    </tr>
                    <tr>
                        <td class="trainingDetailItems">内容:</td>
                        <td class="trainingDetailItems2">
                            <textarea name="context"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="trainingDetailItems">培训部门:</td>
                        <td class="trainingDetailItems2">
                            <c:forEach var="department" items="${requestScope.departments}">
                                <input type="checkbox" name="trainingDepartments"
                                       value="${department.id}"/>${department.name}
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right">
                            <input type="submit" value="提交"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $(".trainingDay").datepicker({
            minDate: +1,
            maxDate: +14,
            beforeShowDay:$.datepicker.noWeekends
        });
    });
</script>
</html>
