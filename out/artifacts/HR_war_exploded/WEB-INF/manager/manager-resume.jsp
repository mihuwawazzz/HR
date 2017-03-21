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
    <title>manager-resume</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/manager.css">
</head>
<body>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="manager-menu.jsp"/>
    <div id="right">
        <h3>以下是您未处理的简历：</h3>
        <c:choose>
            <c:when test="${empty requestScope.resumes}">
                <div style="margin-left:150px">
                    <h4>!太勤快啦，没有未处理简历!</h4>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="resume" items="${requestScope.resumes}">
                    <a href="javascript:void(0)" style="color: black">
                        <div class="resume">
                            <input hidden="hidden" value="resumeDetail${resume.id}">
                            应聘者：${resume.username}<br/>
                            出生日期：<fmt:formatDate value="${resume.birthday}" pattern="yyyy-MM-dd"/><br/>
                            应聘职位：
                            <c:forEach var="position" items="${requestScope.positions}">
                                <c:if test="${position.id eq resume.positionId}">
                                    ${position.name}
                                </c:if>
                            </c:forEach>
                        </div>
                    </a>
                </c:forEach>
                <c:forEach var="resume" items="${requestScope.resumes}">
                    <div class="resumeDetail" id="resumeDetail${resume.id}">
                        <table class="resumeDetailTable">
                            <tr>
                                <th colspan="2" style="text-align: center"><h2>iotek公司求职简历表</h2></th>
                            </tr>
                            <tr>
                                <td class="r">姓名:</td>
                                <td>${resume.username}</td>
                            </tr>
                            <tr>
                                <td class="r">邮箱:</td>
                                <td>${resume.email}</td>
                            </tr>
                            <tr>
                                <td class="r">出生日期:</td>
                                <td><fmt:formatDate value="${resume.birthday}" pattern="yyyy-MM-dd"/></td>
                            </tr>
                            <tr>
                                <td class="r">性别:</td>
                                <td>
                                    <c:if test="${resume.gender == 0}">女</c:if>
                                    <c:if test="${resume.gender == 1}">男</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="r">应聘职位:</td>
                                <td>
                                    <c:forEach var="position" items="${requestScope.positions}">
                                        <c:if test="${position.id eq resume.positionId}">
                                            ${position.name}
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td class="r">工作经验:</td>
                                <td>${resume.workExperience}</td>
                            </tr>
                            <tr>
                                <td class="r">自我介绍:</td>
                                <td>${resume.introduction}</td>
                            </tr>
                            <tr>
                                <td class="r" rowspan="2">您的评价：</td>
                                <td>
                                    <form action="<%=basePath%>/resume/updatePassResume" method="post">
                                        <input hidden="hidden" name="id" value="${resume.id}"/>
                                        <textarea name="interviewNote" style="width: 350px;height: 50px;font-size: 16px">在此输入面试地址和时间</textarea>
                                        <input id="passResume" type="submit" value="通过并安排面试"/>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="<%=basePath%>/resume/updateStateOfResumeById?state=-2&id=${resume.id}">
                                        <input type="button" value="此简历未通过"/>
                                    </a>
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
<script src="<%=basePath%>/static/js/jquery-3.1.1.js"></script>
<script>
    $(function () {
        $(".resumeDetail").hide();
        $(".resume").mouseover(function () {
            $(this).css("opacity", "0.7");
        });
        $(".resume").mouseout(function () {
            $(this).css("opacity", "1");
        });
        $(".resume").click(function () {
            var id = $(this).children().eq(0).val();
            $(".resume").hide();
            $(".resumeDetail").hide();
            $("#" + id).show();
        });
    });
</script>
</html>
