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
        <h3>以下是还未开始的培训：</h3>
        <c:choose>
            <c:when test="${empty requestScope.trainingNotices}">
                <div style="margin-left:150px">
                    <h4>!没有未开始的培训!</h4>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="trainingNotice" items="${requestScope.trainingNotices}">
                    <div class="training">
                        <a href="javascript:void(0)" style="color: black">
                            <input hidden="hidden" value="${trainingNotice.id}">
                            <input hidden="hidden" value="trainingDetail${trainingNotice.training.id}">
                            <div style="width: 450px;text-align: center;font-size: 20px">${trainingNotice.training.title}</div>
                            开始时间：<fmt:formatDate value="${trainingNotice.training.beginDate}" pattern="yyyy-MM-dd HH:mm"/><br/>
                            讲师：${trainingNotice.training.lecturer}<br/>
                            状态：
                            <c:if test="${trainingNotice.training.state eq 0}">
                                还未开始
                            </c:if>
                            <c:if test="${trainingNotice.training.state eq -1}">
                                已被取消
                            </c:if>
                        </a>
                    </div>
                </c:forEach>
                <c:forEach var="trainingNotice" items="${requestScope.trainingNotices}">
                    <div class="trainingDetail" id="trainingDetail${trainingNotice.training.id}" hidden="hidden">
                        <table class="trainingDetailTable">
                            <tr>
                                <th colspan="2" style="text-align: center"><h3>${trainingNotice.training.title}</h3></th>
                            </tr>
                            <tr>
                                <td class="trainingDetailItems">开始时间:</td>
                                <td class="trainingDetailItems2"><fmt:formatDate value="${trainingNotice.training.beginDate}"
                                                                                 pattern="yyyy-MM-dd HH:mm"/></td>
                            </tr>
                            <tr>
                                <td class="trainingDetailItems">时长:</td>
                                <td class="trainingDetailItems2">${trainingNotice.training.minute}分钟</td>
                            </tr>
                            <tr>
                                <td class="trainingDetailItems">讲师:</td>
                                <td class="trainingDetailItems2">${trainingNotice.training.lecturer}</td>
                            </tr>
                            <tr>
                                <td class="trainingDetailItems">培训部门:</td>
                                <td class="trainingDetailItems2">${trainingNotice.training.trainingDes}</td>
                            </tr>
                            <tr>
                                <td class="trainingDetailItems">内容:</td>
                                <td class="trainingDetailItems2">
                                    <textarea disabled="disabled">
                                            ${trainingNotice.training.context}
                                    </textarea>
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
        $(".training").mouseover(function () {
            $(this).css("opacity", "0.7");
        });
        $(".training").mouseout(function () {
            $(this).css("opacity", "1");
        });
        $(".training").click(function () {
            var tnid = $(this).children().children().eq(0).val();
            $.ajax({
                type: "get",
                url: "/trainingNotice/updateState",
                data: {id:tnid}
            });
            var id = $(this).children().children().eq(1).val();
            $(".training").hide();
            $("#" + id).show();
        });
    });
</script>
</html>
