<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
    <title>register</title>
    <style>
        span {
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

        label {
            display: block;
            float: left;
            text-align: right;
            width: 150px;
        }

        #email, #username, #password, #repassword {
            width: 180px;
            height: 22px;
            font-size: 16px;
        }

        input[type=button] {
            border-radius: 5px;
            height: 30px;
            background: RGB(255, 130, 180);
            border: none;
            width: 70px;
            font-size: 18px;
        }
    </style>
    <script src="<%=basePath%>/static/js/jquery-3.1.1.js"></script>
</head>
<body>
<div id="page">
    <div id="header">
        <div><img src="<%=basePath%>/static/images/logo.png"></div>
    </div>
    <div class="items"></div>
    <div id="items" class="block" style="width: 600px">
        <form action="/user/insertOrUpdate" method="post">
            <div class="items">
                <label class="block">邮箱(账号)：</label><input id="email" type="text" name="email"/>
                <span></span>
            </div>
            <div class="items">
                <label class="block">用户名：</label><input id="username" type="text" name="username"/>
                <span></span>
            </div>
            <div class="items">
                <label for="password">密码：</label><input id="password" type="password" name="password"/>
                <span></span>
            </div>
            <div class="items">
                <label for="repassword">重复密码：</label><input id="repassword" type="password" name="repassword"/>
                <span></span>
            </div>
            <div class="items" style="font-size: 15px">
                <label><input type="checkbox"/></label>我已经认真阅读并且同意使用协议
            </div>
            <div style="width: 130px;text-align: right">
                <input type="button" id="button" value="注册" disabled="disabled"/>
            </div>
        </form>
    </div>
    <div class="block" style="text-align: right;font-size: 16px">
        -->已经注册过啦，<a href="<%=basePath%>/login.jsp">直接登录</a>
    </div>
</div>
</body>
<script>
    $(function () {
        var emailRegex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
        var passwordRegex = /^[a-zA-Z0-9_]{1,20}$/;

        $("#email").focus(function () {
            $(this).next().text("");
        });

        $("#email").blur(function () {
            var email = $(this).val();
            if (!email.match(emailRegex)) {
                $(this).next().text("!邮箱格式不正确!");
                $(this).next().css("color", "red");
            } else {
                $.ajax({
                    type: "post",
                    url: "/user/queryByEmail",
                    data: {email: email},
                    success: function (data) {
                        if (data == "") {
                            $("#email").next().text("");
                        } else {
                            $("#email").next().text("!邮箱重复!");
                            $("#email").next().css("color", "red");
                        }
                    }
                })
            }
        });

        $("#username").focus(function () {
            $(this).next().text("用户名长度1-20字符");
            $(this).next().css("color", "grey");
        });
        $("#username").blur(function () {
            var username = $(this).val().length;
            if (username < 1 || username > 20) {
                $(this).next().text("!用户名格式不正确!");
                $(this).next().css("color", "red");
            } else {
                $(this).next().text("");
            }
        });

        $("#password").focus(function () {
            $(this).next().text("密码数字,字母,'_'，长度1-20字符");
            $(this).next().css("color", "grey");
        });
        $("#password").blur(function () {
            var password = $(this).val();
            if (!password.match(passwordRegex)) {
                $(this).next().text("!密码格式不正确!");
                $(this).next().css("color", "red");
            } else {
                $(this).next().text("");
            }
        });

        $("#repassword").focus(function () {
            $(this).next().text("");
        });
        $("#repassword").blur(function () {
            var repassword = $(this).val();
            var password = $("#password").val();
            if (repassword != password) {
                $(this).next().text("!两次密码不一致!");
                $(this).next().css("color", "red");
            } else {
                $(this).next().text("");
            }
        });

        $("input:checkbox").click(function () {
            if ($(this).is(':checked')) {
                $("#button").removeAttr("disabled");
            } else {
                $("#button").attr("disabled", "disabled");
            }
        });

        $("#button").click(function () {
            var flag = true;
            var email1 = $("#email").val();
            var username1 = $("#username").val().length;
            var password1 = $("#password").val();
            var repassword1 = $("#repassword").val();
            if (!email1.match(emailRegex)) {
                $(this).next().text("!邮箱格式不正确!");
                $(this).next().css("color", "red");
                flag = false;
            } else {
                $.ajax({
                    type: "post",
                    url: "/user/queryByEmail",
                    data: {email: email1},
                    success: function (data) {
                        if (data == "") {
                            $("#email").next().text("");
                        } else {
                            $("#email").next().text("!邮箱重复!");
                            $("#email").next().css("color", "red");
                            flag = false;
                        }
                    }
                })
            }
            if (username1 < 1 || username1 > 20) {
                $(this).next().text("!用户名格式不正确!");
                $(this).next().css("color", "red");
                flag = false;
            }
            if (!password1.match(passwordRegex)) {
                $(this).next().text("!密码格式不正确!");
                $(this).next().css("color", "red");
                flag = false;
            }
            if (repassword1 != password1) {
                $(this).next().text("!两次密码不一致!");
                $(this).next().css("color", "red");
                flag = false;
            }
            if (flag) {
                $("form").submit();
            }
        });
    })
</script>
</html>