<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script src="<%=basePath%>/static/js/jquery-3.1.1.js"></script>
<script src="<%=basePath%>/static/js/jquery-ui.js"></script>
<script src="<%=basePath%>/static/js/myJs.js"></script>
<div id="left">
    <div id="left-resume">
        <div class="left-title">简历/面试</div>
        <div class="items">
            <a href="<%=basePath%>/resume/queryResumeByState">处理简历</a><br/>
            <a href="<%=basePath%>/resume/queryResumeByInterviewState">处理面试</a><br/>
        </div>
    </div>
    <div id="left-department-position">
        <div class="left-title">部门/职位</div>
        <div class="items">
            <a href="<%=basePath%>/department/queryAll">管理部门</a><br/>
            <a href="<%=basePath%>/position/queryByPage">管理职位</a><br/>
        </div>
    </div>
    <div id="left-user">
        <div class="left-title">员工</div>
        <div class="items">
            <a href="<%=basePath%>/user/goToUser">员工信息</a><br/>
            <a href="<%=basePath%>/attendance/queryByMonthAll">查看考勤</a><br/>
        </div>
    </div>
    <div id="left-salary">
        <div class="left-title">工资</div>
        <div class="items">
            <a href="<%=basePath%>/salaryAndRewAndPun/query">薪资结算</a><br/>
            <a href="<%=basePath%>/salaryAndRewAndPun/queryRPByState">奖惩处理</a><br/>
            <a href="<%=basePath%>/salaryAndRewAndPun/queryAllEmployees">工资奖惩</a><br/>
        </div>
    </div>
    <div id="left-train">
        <div class="left-title">培训</div>
        <div class="items">
            <a href="<%=basePath%>/training/queryAllBeforeCurrentTime">管理培训</a><br/>
            <a href="<%=basePath%>/training/insertTrainingBefore">新增培训</a><br/>
        </div>
    </div>
</div>