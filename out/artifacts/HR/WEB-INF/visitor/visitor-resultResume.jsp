
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>visitor-resultResume</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/visitor.css">
</head>
<body>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="visitor-menu.jsp"/>
    <div id="right" style="font-size: 18px;padding-left: 20px;padding-top: 20px">
        <c:choose>
            <c:when test="${requestScope.resume.state eq -1}">
                您还未提交过简历哟！
            </c:when>
            <c:when test="${requestScope.resume.state eq 0}">
                简历已经提交了，请耐心等待结果吧！
            </c:when>
            <c:when test="${requestScope.resume.state eq -2}">
                很抱歉，简历没有通过！
            </c:when>
            <c:when test="${requestScope.resume.interviewState eq 0}">
                <div id="resumeState">简历通过啦！请确认接收下面的面试信息：</div>
                <div id="acceptOrRefuse" style="margin-top: 50px">
                        ${requestScope.resume.interviewNote}<br/>
                    <input id="acceptInterview" type="button" value="接受面试">
                    <input id="refuseInterview" type="button" value="拒绝面试">
                </div>
            </c:when>
            <c:when test="${requestScope.resume.interviewState eq 1}">
                请耐心等待面试结果！
            </c:when>
            <c:when test="${requestScope.resume.interviewState eq -1}">
                很抱歉，面试没有通过或者您拒绝了面试！
            </c:when>
        </c:choose>
    </div>
</div>
</body>
<script src="<%=basePath%>/static/js/jquery-3.1.1.js"></script>
<script>
    $(function () {
        var id = ${requestScope.resume.id};
        $("#acceptInterview").click(function () {
            var confirm = window.confirm("您确定接收面试？");
            if (confirm) {
                $.ajax({
                    url: "/resume/updateInterviewState",
                    type: "post",
                    data: {id: id, state: 1},
                    success: function (data) {
                        $("#acceptOrRefuse").remove();
                        $("#resumeState").remove();
                        $("#right").append("请耐心等待面试结果！");
                    }
                })
            }
        });
        $("#refuseInterview").click(function () {
            var confirm = window.confirm("您确定拒绝面试？");
            if (confirm) {
                $.ajax({
                    url: "/resume/updateInterviewState",
                    type: "post",
                    data: {id: id, state: -1},
                    success: function (data) {
                        $("#acceptOrRefuse").remove();
                        $("#resumeState").remove();
                        $("#right").append("很抱歉，面试没有通过或者您拒绝了面试！");
                    }
                })
            }
        });
    })
</script>
</html>
