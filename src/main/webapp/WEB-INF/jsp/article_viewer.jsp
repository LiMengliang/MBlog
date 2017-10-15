<%--
  Created by IntelliJ IDEA.
  User: Mengliang Li
  Date: 5/7/2017
  Time: 9:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>文档</title>
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/editormd/css/editormd.preview.min.css" />
    <link rel="stylesheet" href="/resources/editormd/css/editormd.css" />
    <style>
        .tag {
            margin-right: 10px;
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
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-8">
                <div id="layout">
                    <h1 id="title">正在加载...</h1>
                    <hr style="height:1px;border:none;border-top:1px solid lightslategray;"/>
                    <div class="container-fluid">
                        <div class="row-fluid">
                            <div class="col-sm-8">
                                <h5 id="tags"></h5>
                            </div>
                            <div id="date" class="col-sm-4"></div>
                        </div>
                    </div>
                    <div id="content"></div>
                </div>
            </div>
            <div class="col-sm-2">
            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath()%>/resources/editormd/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/editormd/lib/marked.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/editormd/lib/prettify.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/editormd/editormd.min.js"></script>
    <script type="text/javascript">
        editormd.markdownToHTML("content");
        function initialize() {
            showArticle();
        }

        function showArticle() {
//            $.get("/rest/article/" + "${article_id}",
            $.ajax({
                type: "get",
                url: "/rest/article/" + "${article_id}",
                success: function(article) {
                    // add tags
                    var tags = article.tags;
                    var tagList = tags.split(" ");
                    for (var i = 0; i < tagList.length; i++) {
                        var span = document.createElement("span");
                        span.setAttribute("class", "label label-default");
                        span.style.marginRight = 10;
                        span.textContent = tagList[i];
                        $("#tags").add(span).appendTo("#tags");
                    }

                    // add title
                    $("#title").html(article.title);

                    // add creation time
                    $("#date").html(" 创建于" + article.date);

                    // article content
                    var htmlArticle = "<div>" + marked(article.markDown) + "</div>";
                    // only use append() can make the view render correctly, use add()can't make it correctly.
                    $("#content").append(htmlArticle);
                },
                error: function(error) {
                    if (error.status == 404) {
                        $("#title").html(error.status + " 并没有找到这篇文章... *_*");
                    } else if (error.status == 500) {
                        $("#title").html("服务器发生错误，请重试...");
                    }
                }

            })
        }
    </script>
</body>
</html>
