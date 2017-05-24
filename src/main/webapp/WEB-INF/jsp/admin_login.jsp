<%--
  Created by IntelliJ IDEA.
  User: Mengliang Li
  Date: 5/11/2017
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"/>
</head>
<body onload="">
    <div>
        <h6 id="auth_message"></h6>
        <input type="text" id="user_name" class="form-control" name="user_name" placeholder="你的名字"/>
        <input type="password" id="password" class="form-control" name="password"/>
        <button class="btn btn-success" onclick="authentificate()">登录</button>
        <script type="text/javascript" src="/resources/js/md5.min.js"></script>
        <script type="text/javascript" src="/resources/js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="/resources/js/json/json2.js"></script>
        <script type="text/javascript" src="/resources/js/json/json_parse.js"></script>
        <script type="text/javascript">
            function authentificate() {
                var user_name = document.getElementById("user_name").value;
                var password = document.getElementById("password").value  +'!@#$%^&*()';
                var md5_password = md5(password);
                $.post("/admin/authentification", {user_name:user_name, password:md5_password}, function(msg) {
                    if(msg.authentificated) {
                        window.location.href = "/article/add";
                    }
                    else {
                        $("#auth_message").html("用户名或密码错误");
                    }
                })
            }
        </script>
    </div>
</body>
</html>
