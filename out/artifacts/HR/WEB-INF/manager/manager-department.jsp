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
    <title>manager-department</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/manager.css">
</head>
<body>
<div id="page">
    <jsp:include page="../top/top.jsp"/>
    <jsp:include page="manager-menu.jsp"/>
    <div id="right">
        <div class="right-title">--所有部门--</div>
        <div id="queryAndDeleteDepartment">
            <table>
                <tr>
                    <th>部门id</th>
                    <th>部门名</th>
                    <th>创建时间</th>
                    <th>删除</th>
                </tr>
                <c:forEach var="department" items="${requestScope.departments}">
                    <tr>
                        <td>${department.id}</td>
                        <td>${department.name}</td>
                        <td><fmt:formatDate value="${department.createTime}" pattern="yyyy-MM-dd"/></td>
                        <td><a class="deleteDepartment" href="javascript:void(0)">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="right-title">--新增部门--</div>
        <div id="insertDepartment">
            <form action="<%=basePath%>/department/insertOrUpdate">
                <table>
                    <tr>
                        <td>部门名:</td>
                        <td><input type="text" name="name"/></td>
                        <td style="border: none;font-size:16px;color: red">${requestScope.errorInsert}</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right"><input type="submit" value="提交"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="right-title">--修改部门--</div>
        <div id="updateDepartment">
            <form action="<%=basePath%>/department/insertOrUpdate">
                <table>
                    <tr>
                        <td>选择部门:</td>
                        <td>
                            <select name="id">
                                <c:forEach var="department" items="${requestScope.departments}">
                                    <option class="updateDepartment" value="${department.id}">${department.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>部门名:</td>
                        <td><input type="text" name="name"/></td>
                        <td style="border: none;font-size:16px;color: red">${requestScope.errorUpdate}</td>
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
        $(".deleteDepartment").click(function () {
            var id = $(this).parent().siblings().eq(0).text();
            var flag = confirm("确认要删除"+id+"号部门吗？");
            if(flag){
                var department = $(this).parent().parent();
                $.ajax({
                    type:"post",
                    url:"/department/deleteById",
                    data:{id:id},
                    success:function (data) {
                        if(data=="1"){
                            alert("该部门下有员工，不能删除!")
                        }else if(data=="0"){
                            department.remove();
                        }
                    }
                });
            }
        });
    });
</script>
</html>
