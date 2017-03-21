<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <%
        int[] years = {2016, 2017};
        request.setAttribute("years", years);
        int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        request.setAttribute("months", months);
    %>
    <div id="right">
        <c:choose>
            <c:when test="${empty requestScope.todayAttendance}">
                <div class="clock-in-out" id="clock-in">
                    <a href="<%=basePath%>/attendance/insertClockIn/${sessionScope.user.id}">
                        <input type="button" value="上班打卡"/>
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${empty requestScope.todayAttendance.clockOutTime}">
                        <div class="clock-in-out" id="clock-out">
                            <a href="<%=basePath%>/attendance/insertClockOut/${sessionScope.user.id}">
                                <input type="button" value="下班打卡"/>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="clock-in-out">
                            <a>
                                <input disabled="disabled" type="button" value="您辛苦了"/>
                            </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
        <div id="calendar">
            <table id="show">
                <tr>
                    <th colspan="7" id="calendar-title"></th>
                </tr>
                <script>
                    var date = new Date();
                    var year = date.getFullYear();
                    var month = date.getMonth() + 1;
                    var days = new Date(year + "/" + month + "/" + "0").getDate();
                    var firstDay = new Date(year + "/" + month + "/" + "1").getDay();
                    $("#calendar-title").text(year + "年" + month + "月")
                </script>
                <tr>
                    <th>日</th>
                    <th>一</th>
                    <th>二</th>
                    <th>三</th>
                    <th>四</th>
                    <th>五</th>
                    <th>六</th>
                </tr>
                <script>
                    var date = new Date();
                    var year = date.getFullYear();
                    var month = date.getMonth() + 1;
                    var days = new Date(year + "/" + (month + 1) + "/" + "0").getDate();
                    var firstDay = new Date(year + "/" + month + "/" + "1").getDay();
                    var weekNum = 0;
                    var day = 1;
                    for (var i = -6; i < days + 1; i++) {
                        i = i + 7;
                        var wid = "week" + weekNum;
                        $("#show").append("<tr></tr>")
                        $("#show").children().last().attr("id", wid);
                        for (var week = 0; week < 7; week++) {
                            if (week < firstDay && day < firstDay) {
                                $("#" + wid).append("<td></td>");
                            } else {
                                if (day < days + 1) {
                                    $("#" + wid).append("<td>" + day + "</td>");
                                    $("#show").children().children().last().attr("id", day);
                                    day++;
                                } else {
                                    $("#" + wid).append("<td></td>");
                                }
                            }
                        }
                        weekNum++;
                    }
                </script>
                <c:forEach var="attendance" items="${requestScope.attendances}">
                    <c:if test="${attendance.state eq 4}">
                        <script>
                            var id = ${attendance.day}
                                $("#" + id).css("background", "#ff0000");
                        </script>
                    </c:if>
                    <c:if test="${attendance.state eq 3}">
                        <script>
                            var id = ${attendance.day}
                                $("#" + id).css("background", "#ff8313");
                        </script>
                    </c:if>
                    <c:if test="${attendance.state eq 2}">
                        <script>
                            var id = ${attendance.day}
                                $("#" + id).css("background", "#ffed99");
                        </script>
                    </c:if>
                    <c:if test="${attendance.state eq 1}">
                        <script>
                            var id = ${attendance.day}
                                $("#" + id).css("background", "#76fff7")
                        </script>
                    </c:if>
                </c:forEach>
                <script>
                </script>
            </table>
            <div id="attendance-desc">
                <div class="attendance-desc" style="background: #ff0000"></div>
                <div class="attendance-desc-name">旷工</div>
                <div class="attendance-desc" style="background: #ff8313"></div>
                <div class="attendance-desc-name">迟到且早退</div>
                <div class="attendance-desc" style="background: #ffed99"></div>
                <div class="attendance-desc-name">早退</div>
                <div class="attendance-desc" style="background: #76fff7"></div>
                <div class="attendance-desc-name">迟到</div>
            </div>
        </div>
        <div>
            <div style="margin-top: 90px;margin-left: 50px;float: left">查询具体信息:</div>
            <div style="margin-top: 66px;margin-left: 50px;float: left;">
                <form action="<%=basePath%>/attendance/queryByDate" method="post">
                    <input hidden="hidden" name="userId" value="${sessionScope.user.id}"/>
                    <select id="year" name="year">
                        <c:forEach var="year" items="${requestScope.years}">
                            <option name="year" value="${year}">${year}</option>
                        </c:forEach>
                    </select>年
                    <select id="month" name="month">
                        <c:forEach var="month" items="${requestScope.months}">
                            <option name="month" value="${month}">${month}</option>
                        </c:forEach>
                    </select>月
                    <select id="day" name="day">
                        <c:forEach var="i" begin="1" end="31">
                            <option name="day" value="${i}">${i}</option>
                        </c:forEach>
                    </select>日
                    <input type="submit" value="查询">
                </form>
            </div>
        </div>
        <div style="float: right;margin-right: 25px">
            <c:choose>
                <c:when test="${!empty requestScope.attendance}">
                    <table id="attendance-detail">
                        <tr>
                            <th>id</th>
                            <th>上班时间</th>
                            <th>下班时间</th>
                        </tr>
                        <tr>
                            <td style="width: 40px">${requestScope.attendance.id}</td>
                            <td style="width: 215px">
                                <fmt:formatDate value="${requestScope.attendance.clockInTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td style="width: 215px">
                                <fmt:formatDate value="${requestScope.attendance.clockOutTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                        </tr>
                    </table>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $("#month").click(countDay);
        $("#year").click(countDay);
    });
</script>
</html>
