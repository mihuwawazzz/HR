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
        <h3>以下是还未开始的培训：</h3>
        <c:choose>
            <c:when test="${empty requestScope.trainings}">
                <div style="margin-left:150px">
                    <h4>!没有未开始的培训!</h4>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="training" items="${requestScope.trainings}">
                    <div class="training">
                        <a href="javascript:void(0)" style="color: black">
                            <input hidden="hidden" value="trainingDetail${training.id}">
                            <div style="width: 450px;text-align: center;font-size: 20px">${training.title}</div>
                            开始时间：<fmt:formatDate value="${training.beginDate}" pattern="yyyy-MM-dd HH:mm"/><br/>
                            讲师：${training.lecturer}<br/>
                            状态：
                            <c:if test="${training.state eq 0}">
                                还未开始
                            </c:if>
                            <c:if test="${training.state eq -1}">
                                已被取消
                            </c:if>
                        </a>
                    </div>
                </c:forEach>
                <c:forEach var="training" items="${requestScope.trainings}">
                    <div class="trainingDetail" id="trainingDetail${training.id}" hidden="hidden">
                        <table class="trainingDetailTable">
                            <tr>
                                <th colspan="2" style="text-align: center"><h3>${training.title}</h3></th>
                            </tr>
                            <tr>
                                <td class="trainingDetailItems">开始时间:</td>
                                <td class="trainingDetailItems2"><fmt:formatDate value="${training.beginDate}"
                                                                                 pattern="yyyy-MM-dd HH:mm"/></td>
                            </tr>
                            <tr>
                                <td class="trainingDetailItems">时长:</td>
                                <td class="trainingDetailItems2">${training.minute}分钟</td>
                            </tr>
                            <tr>
                                <td class="trainingDetailItems">讲师:</td>
                                <td class="trainingDetailItems2">${training.lecturer}</td>
                            </tr>
                            <tr>
                                <td class="trainingDetailItems">培训部门:</td>
                                <td class="trainingDetailItems2">${training.trainingDes}</td>
                            </tr>
                            <tr>
                                <td class="trainingDetailItems">内容:</td>
                                <td class="trainingDetailItems2">
                                    <textarea disabled="disabled">
                                            ${training.context}
                                    </textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <form class="trainingUpdate" action="<%=basePath%>/training/updateDate">
                                        <input hidden="hidden" name="id" value="${training.id}"/>
                                        更新时间：
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
                                        <input type="submit" value="提交"/>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <form action="<%=basePath%>/training/updateState">
                                        <input hidden="hidden" name="id" value="${training.id}"/>
                                        取消培训：
                                        <input class="cancelTraining" type="button" value="取消"/>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
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

        $(".training").mouseover(function () {
            $(this).css("opacity", "0.7");
        });
        $(".training").mouseout(function () {
            $(this).css("opacity", "1");
        });
        $(".training").click(function () {
            var id = $(this).children().children().eq(0).val();
            $(".training").hide();
            $("#" + id).show();
        });
        $(".cancelTraining").click(function () {
            var flag = window.confirm("确定要取消培训吗?");
            if (flag) {
                $(this).parent().submit();
            }
        });
    });
</script>
</html>
