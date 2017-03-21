<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script src="<%=basePath%>/static/js/jquery-3.1.1.js"></script>
<script src="<%=basePath%>/static/js/jquery-ui.js"></script>
<script src="<%=basePath%>/static/js/myJs.js"></script>
<div id="left">
    <div id="left-attendance">
        <div class="left-title">考勤</div>
        <div class="left-items">
            <a href="<%=basePath%>/attendance/queryByCurrentMonth/${sessionScope.user.id}">签到/查看</a><br/>
        </div>
    </div>
    <div id="left-user">
        <div class="left-title">个人/部门</div>
        <div class="left-items">
            <a href="<%=basePath%>/user/queryDepAndPosi">个人信息</a>
        </div>
        <div class="left-items">
            <a href="<%=basePath%>/user/queryDepAndPosi">部门职位</a>
        </div>
    </div>
    <div id="left-salary">
        <div class="left-title">薪资/奖惩</div>
        <div class="left-items">
            <a href="javascript:void(0)">查看薪资</a>
        </div>
        <div class="left-items">
            <a href="javascript:void(0)">提出异议</a>
        </div>
    </div>
    <div id="left-train">
        <div class="left-title">培训信息</div>
        <div class="left-items">
            <a href="<%=basePath%>/trainingNotice/queryByUserIdFetch/${sessionScope.user.id}">查看培训</a>
        </div>
    </div>
</div>
<script>
    $(function () {
        setInterval(checkNote,5*60*1000);
    });
    function checkNote() {
        $.ajax({
            type: "get",
            url: "/trainingNotice/queryByUserId",
            data: {userId:${sessionScope.user.id}},
            success: function (data) {
                var flag = false;
                var note = "";
                jQuery.each(data, function (i, item) {
                    if (item.state == 0) {
                        note = note + "有未查看的培训，快去看看吧<br/>";
                        flag = true;
                    }
                    if (item.state == 2) {
                        note = note + "有培训更新了，快去看看吧<br/>";
                        flag = true;
                    }
                    if (item.state == 3) {
                        note = note + "有培训取消了，快去看看吧<br/>";
                        flag = true;
                    }
                });
                if(flag){
                    pop_init("培训通知", note);
                    $('#pop_div').fadeIn(1000);
                }
            }
        })
    }
</script>