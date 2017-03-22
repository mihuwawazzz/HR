<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>manager</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/manager.css">
</head>
<body>
<%
    int[] years = {1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995};
    request.setAttribute("years", years);
    int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    request.setAttribute("months", months);
    int[] days = new int[31];
    for(int i = 1;i<32;i++){
        days[i-1] = i;
    }
    request.setAttribute("days", days);
%>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="manager-menu.jsp"/>
    <div id="right">
        <div id="userM">
            <form action="<%=basePath%>/user/queryByUsernameForUpdate">
                请输入员工名字：<input name="username" type="text"/>
                <input type="submit" value="查询"/>
            </form>
            <table>
                <c:if test="${!empty requestScope.users}">
                    <tr>
                        <th width="50px">id</th>
                        <th>姓名</th>
                        <th>邮箱</th>
                        <th>基本工资</th>
                        <th>修改</th>
                        <th>删除</th>
                    </tr>
                </c:if>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.basicSalary}</td>
                        <td><a class="userUpdate" href="javascript:void(0)">修改</a></td>
                        <td>
                            <c:choose>
                                <c:when test="${user.state eq 1}">
                                    <input disabled="disabled" type="button" value="不可删除"/>
                                </c:when>
                                <c:otherwise>
                                    <form action="<%=basePath%>/user/deleteUser/${user.id}">
                                        <input class="userDelete" type="button" value="删除"/>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <c:forEach var="user" items="${requestScope.users}">
            <div class="userMDetail" hidden="hidden" id="${user.id}">
                <div style="font-size:30px;margin-bottom: 20px">详细信息</div>
                <form action="<%=basePath%>/user/updateUser">
                    <table>
                        <tr>
                            <td width="150px">id</td>
                            <td width="400px"><input type="text" name="id" value="${user.id}" readonly></td>
                        </tr>
                        <tr>
                            <td>邮箱</td>
                            <td><input type="text" name="email" value="${user.email}" readonly></td>
                        </tr>
                        <tr>
                            <td>姓名</td>
                            <td><input type="text" name="username" value="${user.username}"></td>
                        </tr>
                        <tr hidden="hidden">
                            <td>密码</td>
                            <td><input type="password" name="password" value="${user.password}"></td>
                        </tr>
                        <tr hidden="hidden">
                            <td>等级</td>
                            <td><input type="text" name="level" value="${user.level}"></td>
                        </tr>
                        <tr>
                            <td>出生日期</td>
                            <td>
                                <script>
                                    var date = new Date("<fmt:formatDate value='${user.birthday}' pattern='yyyy/MM/dd'/>");
                                    var year = date.getFullYear();
                                    var month = date.getMonth() + 1;
                                    var day = date.getDate();
                                </script>
                                <select id="year${user.id}" class="year" name="birthday" style="margin-left: 20px;margin-right: 10px">
                                    <c:forEach var="year" items="${requestScope.years}">
                                        <script>
                                            if(year == ${year}){
                                                $("#year${user.id}").append("<option class='year${user.id}' selected='selected' value=${year}>${year}</option>")
                                            }else {
                                                $("#year${user.id}").append("<option class='year${user.id}' value=${year}>${year}</option>")
                                            }
                                        </script>
                                    </c:forEach>
                                </select>年
                                <select id="month${user.id}" class="month" name="birthday" style="margin-right: 10px">
                                    <c:forEach var="month" items="${requestScope.months}">
                                        <script>
                                            if(month == ${month}){
                                                $("#month${user.id}").append("<option class='month${user.id}' selected='selected' value=${month}>${month}</option>")
                                            }else {
                                                $("#month${user.id}").append("<option class='month${user.id}' value=${month}>${month}</option>")
                                            }
                                        </script>
                                    </c:forEach>
                                </select>月
                                <select id="day${user.id}" class="day" name="birthday">
                                    <c:forEach var="day" items="${requestScope.days}">
                                        <script>
                                            if(day == ${day}){
                                                $("#day${user.id}").append("<option class='day${user.id}' selected='selected' value=${day}>${day}</option>")
                                            }else {
                                                $("#day${user.id}").append("<option class='day${user.id}' value=${day}>${day}</option>")
                                            }
                                        </script>
                                    </c:forEach>
                                </select>日
                            </td>
                        </tr>
                        <tr>
                            <td>性别</td>
                            <td>
                                <c:if test="${user.gender eq 0}">
                                    <input type="radio" name="gender" value="1"/>男
                                    <input type="radio" name="gender" checked="checked" value="0"/>女
                                </c:if>
                                <c:if test="${user.gender eq 1}">
                                    <input type="radio" name="gender" checked="checked" value="1"/>男
                                    <input type="radio" name="gender" value="0"/>女
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>就职状态</td>
                            <td>
                                <select name="state">
                                    <c:if test="${user.state eq 0}">
                                        <option value="0" selected="selected">离职</option>
                                        <option value="1">在职</option>
                                        <option value="2">退休</option>
                                    </c:if>
                                    <c:if test="${user.state eq 1}">
                                        <option value="0">离职</option>
                                        <option value="1" selected="selected">在职</option>
                                        <option value="2">退休</option>
                                    </c:if>
                                    <c:if test="${user.state eq 2}">
                                        <option value="0">离职</option>
                                        <option value="1">在职</option>
                                        <option value="2" selected="selected">退休</option>
                                    </c:if>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>职位</td>
                            <td>
                                部门:
                                <select class="department" id="department${user.id}" name="department">
                                    <c:forEach var="department" items="${requestScope.departments}">
                                        <option value="${department.id}"
                                                <c:forEach var="position" items="${department.positions}">
                                                    <c:if test="${position.id eq user.positionId}">
                                                        selected="selected"
                                                    </c:if>
                                                </c:forEach>
                                        >${department.name}</option>
                                    </c:forEach>
                                </select>
                                职位:
                                <select id="position${user.id}" name="positionId">
                                    <c:forEach var="department" items="${requestScope.departments}">
                                        <c:forEach var="position" items="${department.positions}">
                                            <c:if test="${position.id eq user.positionId}">
                                                <c:forEach var="position" items="${department.positions}">
                                                    <option value="${position.id}"
                                                            <c:if test="${position.id eq user.positionId}">
                                                                selected="selected"
                                                            </c:if>
                                                    >${position.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>基本工资</td>
                            <td>
                                <input type="number" name="basicSalary" min=0 value="${user.basicSalary}">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="padding-left: 310px"><input type="submit" value="提交"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </c:forEach>
    </div>
</div>
</body>
<script>
    $(function () {
        $(".month").change(function () {
            var id = $(this).attr("id").replace(/[^0-9]/ig,"");
            countDayU(id);
        });
        $(".year").change(function () {
            var id = $(this).attr("id").replace(/[^0-9]/ig,"");
            countDayU(id);
        });
        $(".department").change(function () {
            var id = $(this).attr("id").replace(/[^0-9]/ig,"");
            showPositionU(id);
        });
        $(".userUpdate").click(function () {
            var id = $(this).parent().parent().children().eq(0).text();
            $(".userMDetail").hide();
            $("#" + id).show();
        });
        $(".userDelete").click(function () {
            var flag = window.confirm("确定要删除这个用户?");
            if (flag) {
                $(this).parent().submit();
            }
        });
    });
    function countDayU(id) {
        $("#day"+id).children().remove();
        var day;
        var year = $("#year"+id).find(":selected").val();
        var month = $("#month"+id).find(":selected").val();
        var flag = isRunU(year);
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            day = 31;
        } else if (month == 2) {
            if (flag) {
                day = 29;
            } else {
                day = 28;
            }
        } else {
            day = 30;
        }
        for (var i = 0; i < day; i++) {
            $("#day"+id).append("<option value=" + (i + 1) + ">" + (i + 1) + "</option>");
        }
    }

    function isRunU(year) {
        return (0 == year % 4 && (year % 100 != 0 || year % 400 == 0));
    }

    function showPositionU(id) {
        var departmentId = $("#department"+id).find(":selected").val();
        $("#position"+id).children().remove();
        $.ajax({
            type: "get",
            url: "/position/queryByDepartmentId",
            data:{departmentId:departmentId},
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#position"+id).append("<option value=" + data[i].id + ">" + data[i].name + "</option>")
                }
            }
        });
    }
</script>
</html>
