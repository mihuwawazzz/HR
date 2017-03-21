<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script src="<%=basePath%>/static/js/jquery-3.1.1.js"></script>
<script src="<%=basePath%>/static/js/jquery-ui.js"></script>
<script src="<%=basePath%>/static/js/myJs.js"></script>
<div id="left">
    <div class="resume"><a href="<%=basePath%>/resume/insertResumeByUserId/${sessionScope.user.id}">添加简历</a></div>
    <div class="resume"><a href="<%=basePath%>/resume/updateResumeByUserId/${sessionScope.user.id}">修改简历</a></div>
    <div class="resume"><a href="<%=basePath%>/resume/queryResume/${sessionScope.user.id}">查看简历</a></div>
    <div class="left"><a href="<%=basePath%>/resume/queryStateOfResume/${sessionScope.user.id}">面试信息</a></div>
</div>
<script>
    $(function () {
        setInterval(checkNote(),3*60*1000);
    });
    function checkNote() {
        $.ajax({
            type: "get",
            url: "/resume/queryResumeForInterview",
            data: {userId:${sessionScope.user.id}},
            success: function (data) {
                if (data != "") {
                    if (data.interviewState == 0) {
                        pop_init("公告信息", "有面试通知，嘻嘻，快去查看");
                        $('#pop_div').fadeIn(1000);
                    }
                }
            }
        })
    }
</script>

