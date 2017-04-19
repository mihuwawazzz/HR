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
    <title>manager-Interview</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/manager.css">
</head>

<body>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="manager-menu.jsp"/>
    <div id="right">
        <h3>以下是您未处理的面试：</h3>
        <c:choose>
            <c:when test="${empty requestScope.resumes}">
                <div style="margin-left:150px">
                    <h4>!太勤快啦，没有未处理面试!</h4>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="resume" items="${requestScope.resumes}">
                    <a href="javascript:void(0)" style="color: black">
                        <div class="resume">
                            <input hidden="hidden" value="resumeDetail${resume.id}">
                            面试者：${resume.username}<br/>
                            出生日期：<fmt:formatDate value="${resume.birthday}" pattern="yyyy-MM-dd"/><br/>
                            <a href="javascript:void(0)">
                                <form action="<%=basePath%>/resume/passInterviewById">
                                    <input hidden="hidden" name="id" value="${resume.id}"/>
                                    <input hidden="hidden" name="basicSalary"/>
                                    <input class="passInterview" type="button" value="面试通过">
                                </form>
                            </a>
                            <a href="<%=basePath%>/resume/unPassInterviewById?id=${resume.id}">
                                <input id="unPassInterview" type="button" value="面试未通过">
                            </a>
                        </div>
                    </a>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
<script>
    $(function () {
        $(".passInterview").click(function () {
            var basicSalary = window.prompt("请输入此员工基本工资：");
            $(this).prev().val(basicSalary);
            $(this).parent().submit();
        });
    });
</script>
</html>
