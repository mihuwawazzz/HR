<%--
  Created by IntelliJ IDEA.
  User: zhaozhenzhen
  Date: 2017/3/12
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
    <title>login</title>
    <style>
        span{
            font-size: 16px;
            margin-left: 10px;
        }
        div {
            margin: 0 auto 0 auto;
            font-size: 20px;
        }

        .block {
            float: left;
        }

        .items {
            height: 70px;
        }

        #page {
            width: 60%;
        }

        #error {
            color: red;
        }

        label {
            display: block;
            float: left;
            text-align: right;
            width: 150px;
        }

        #email, #password{
            width: 180px;
            height: 22px;
            font-size: 16px;
        }

        input[type=submit] {
            border-radius: 5px;
            height: 30px;
            background: RGB(255, 130, 180);
            border: none;
            width: 70px;
            font-size: 18px;
        }
        span{
            color: red;
            font-size: 16px;
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div id="page">
    <div id="header">
        <div><img src="<%=basePath%>/static/images/logo.png"></div>
    </div>
    <div class="items"></div>
    <div id="items" class="block" style="width: 600px">
        <form action="/user/queryByUser" method="post">
            <div class="items">
                <label class="block">邮箱(账号)：</label><input id="email" type="text" name="email"/>
                <span>${requestScope.error}</span>
            </div>
            <div class="items">
                <label for="password">密码：</label><input id="password" type="password" name="password"/>
                <span></span>
            </div>
            <div style="width: 130px;text-align: right">
                <input type="submit" id="button" value="登录"/>
            </div>
        </form>
    </div>
    <div class="block" style="text-align: right;font-size: 16px">
        -->我还没有账号，<a href="<%=basePath%>/register.jsp">先去注册</a>
    </div>
</div>
</body>
</html>
