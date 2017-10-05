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
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">代码 / 李的博客</a>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="col-sm-5">
            </div>
            <div class="col-sm-2 center-block">
                <h4>用户名：</h4>
                <input type="text" id="user_name" class="form-control" name="user_name" placeholder="你的名字"/>
                <h4>密码：</h4>
                <input type="password" id="password" class="form-control" name="password"/>
                <h4></h4>
                <button class="btn btn-primary center-block" onclick="authentificate()">登录</button>
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
                                window.location.href = "/admin/manage";
                            }
                            else {
                                $("#auth_message").html("用户名或密码错误");
                            }
                        })
                    }
                </script>
            </div>
            <div class="col-sm-5">
            </div>
        </div>
    </div>

</body>
</html>
