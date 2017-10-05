<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 7/7/17
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理博客</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/bootstrap/js/bootstrap-paginator.js"></script>
    <style>
        h5.date
        {
            color: #999999;
        }
        h5.link
        {
            color: #0055FF;
        }
        h5.link:hover
        {
            cursor:pointer;
            text-decoration:underline;
        }
    </style>
</head>
<body onLoad="initialize()">
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">代码 / 李的博客</a>
        </div>
    </div>
</nav>
<div class="container-fluid" style="margin-top: 50px">
    <div class="row-fluid">
        <div class="col-sm-2">
        </div>
        <div class="col-sm-8">
            <br>
            <button type="button" class="btn btn-success" onclick="addArticle()">添加新文章</button>
            <br>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>标题</th>
                    <th>上次修改时间</th>
                    <th>类别</th>
                    <th>操作</th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="articles_list">
                </tbody>
            </table>
        </div>
        <div class="col-sm-2">
        </div>
    </div>
</div>
<script type="text/javascript">
    function initialize() {
        createArticlesTable();
    }

    function createArticlesTable() {
        $.get("/blog/articles", function(articles) {
            for (var i = 0; i < articles.length; i++) {
                var article = articles[i];
                var row = createOneRowOfArticle(article);
                $("#articles_list").add(row).appendTo("#articles_list");
            }
        })
    }

    function createOneRowOfArticle(article) {
        var tr = document.createElement("tr");
        var titleTd = document.createElement("td");
        var dateTd = document.createElement("td");
        var categoryTd = document.createElement("td");
        var editTd = document.createElement("td");
        var delTd = document.createElement("td");

        titleTd.setAttribute("width", "50%");
        dateTd.setAttribute("width", "15%");
        categoryTd.setAttribute("width", "15%");
        editTd.setAttribute("width", "10%");
        delTd.setAttribute("width", "10%")

        tr.appendChild(titleTd);
        tr.appendChild(dateTd);
        tr.appendChild(categoryTd);
        tr.appendChild(editTd);
        tr.appendChild(delTd);

        var title = document.createElement("h5");
        title.setAttribute("class", "link");
        title.textContent = article.title;
        var date = document.createElement("h5");
        date.textContent = article.date;
        date.setAttribute("class", "date");
        var category = document.createElement("h5");
        category.textContent = article.categoryName;
        var edit = document.createElement("h5");
        edit.textContent = "编辑";
        edit.setAttribute("class", "link");
        edit.onclick = function() {
            // $.get("/article/edit", {article_id:article.id});
            window.location.href = "/article/edit?article_id=" + article.id;
        }
        var del = document.createElement("h5");
        del.textContent = "删除";
        del.setAttribute("class", "link");
        del.onclick = function() {
            $.get("/article/delete", {article_id:article.id},
            function() {
                tr.remove();
            })
        }

        titleTd.appendChild(title);
        dateTd.appendChild(date);
        categoryTd.appendChild(category);
        editTd.appendChild(edit);
        delTd.appendChild(del);
        title.onclick = function() {
            window.location.href="/blog/article?article_id=" + article.id;
        }
        return tr;
    }

    function addArticle() {
        // $.get("/article/add");
        window.location.href = "/article/add";
    }
</script>
</body>
</html>
