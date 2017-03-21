<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>manager-position</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/manager.css">
</head>
<body>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="manager-menu.jsp"/>
    <div id="right">
        <div class="right-title">--所有职位--</div>
        <div id="queryAndDeletePosition">
            <table>
                <tr>
                    <th>职位id</th>
                    <th>职位名</th>
                    <th>创建时间</th>
                    <th>删除</th>
                </tr>
                <c:forEach var="position" items="${requestScope.positions}">
                    <tr>
                        <td>${position.id}</td>
                        <td>${position.name}</td>
                        <td><fmt:formatDate value="${position.createTime}" pattern="yyyy-MM-dd"/></td>
                        <td><a class="deletePosition" href="javascript:void(0)">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
            <script>
            </script>
            <div style="height: 15px"></div>
            <div class="num1">
                <a href="<%=basePath%>/position/queryByPage?currentPage=${requestScope.page.currentPage-1}">上一页</a>
            </div>
            <c:forEach var="num" begin="1" end="${requestScope.page.totalPage}">
                <c:choose>
                    <c:when test="${requestScope.page.currentPage != num}">
                        <div class="num">
                            <a href="<%=basePath%>/position/queryByPage?currentPage=${num}">${num}</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="num" style="background: #ffa3ea;">
                            <a href="<%=basePath%>/position/queryByPage?currentPage=${num}">${num}</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <div class="num1">
                <a href="<%=basePath%>/position/queryByPage?currentPage=${requestScope.page.currentPage+1}">下一页</a>
            </div>
        </div>
        <div class="right-title">--新增职位--</div>
        <div id="insertPosition">
            <form action="<%=basePath%>/position/insertOrUpdate">
                <table>
                    <tr>
                        <td>选择部门:</td>
                        <td>
                            <select name="department.id">
                                <c:forEach var="department" items="${requestScope.departments}">
                                    <option value="${department.id}">${department.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>职位名:</td>
                        <td><input type="text" name="position.name"/></td>
                        <td style="border: none;font-size:16px;color: red">${requestScope.errorInsert}</td
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right"><input type="submit" value="提交"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="right-title">--修改职位--</div>
        <div id="updatePosition">
            <form action="<%=basePath%>/position/insertOrUpdate">
                <table>
                    <tr>
                        <td>选择部门:</td>
                        <td>
                            <select id="department" name="department.id">
                                <c:forEach var="department" items="${requestScope.departments}">
                                    <option class="department" value="${department.id}">${department.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>选择职位:</td>
                        <td>
                            <select id="position" name="position.id">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>职位名:</td>
                        <td><input type="text" name="position.name"/></td>
                        <td style="border: none;font-size:16px;color: red">${requestScope.errorUpdate}</td
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right"><input type="submit" value="提交"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
<script src="<%=basePath%>/static/js/jquery-3.1.1.js"></script>
<script>
    $(function () {
        $(".deletePosition").click(deletePosition);
        $("#department").click(showPosition);
    });

    function showPosition() {
        var departmentId = $(".department:checked").val();
        $("#position").children().remove();
        $.ajax({
            type: "get",
            url: "/position/queryByDepartmentId",
            data: {departmentId: departmentId},
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#position").append("<option value=" + data[i].id + ">" + data[i].name + "</option>")
                }
            }
        });
    }
    function deletePosition() {
        var id = $(this).parent().siblings().eq(0).text();
        var flag = confirm("确认要删除" + id + "号职位吗？");
        if (flag) {
            var position = $(this).parent().parent();
            $.ajax({
                type: "post",
                url: "/position/deleteById",
                data: {id: id},
                success: function (data) {
                    if (data == "1") {
                        alert("该职位下有员工，不能删除!")
                    } else if (data == "0") {
                        position.remove();
                    }
                }
            });
        }
    }


</script>
</html>
