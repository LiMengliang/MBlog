<%--
  Created by IntelliJ IDEA.
  User: Mengliang Li
  Date: 5/7/2017
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
    <head lang="zh-Hans">
        <title>添加一篇博客</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" href="/resources/editormd/css/editormd.css" />
        <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon" />
        <style>
            .root-body {
            background-color: #f1f1f1;
            }
        </style>
    </head>
    <body onload="initialize()">
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">代码 / 李的博客</a>
                </div>
            </div>
        </nav>
        <div>
            <param name="article_id" value="${creationInfo.get_Article().getId()}">
            <param name="is_edit" value="${creationInfo.is_Edit()}">
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="col-sm-1">
                    </div>
                    <div class="col-sm-10">
                        <div id="layout">
                            <h4>文档标题：</h4>
                            <input id = "title" type="text" class="form-control" name="title" placeholder="输入标题" aria-describedby="sizing-addon1">
                            <h4>文档内容：</h4>
                            <div id="editormd">
                                <textarea id="content_markdown" class="editormd-markdown-textarea" name="markdownDoc" style="display:none;"></textarea>
                            </div>
                            <h4>文档类别：</h4>
                            <input id="category" type="text" class="form-control" name="category" placeholder="输入类别" aria-describedby="sizing-addon1" >
                            <h4>文档标签：</h4>
                            <input id="tags" type="text" class="form-control" name="tags" placeholder="输入标签" aria-describedby="sizing-addon1" >
                            <h4>文档摘要：</h4>
                            <textarea id="digest" type="text" class="form-control" name="digest" placeholder="输入摘要" aria-describedby="sizing-addon1">${creationInfo.get_Article().getDigest()}</textarea>
                            <h4/>
                            <button id="submit_button" type="submit" class="btn btn-primary center-block" onclick="saveAndShowArticle()">发布博客</button>
                        </div>
                    </div>
                    <div class="col-sm-1">
                    </div>
                </div>
            </div>
        </div>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
        <script src="/resources/editormd/js/jquery.min.js"></script>
        <script src="/resources/editormd/editormd.min.js"></script>

        <script type="text/javascript">
            var testEditor;
            function createEditor(markdown) {
                testEditor = editormd("editormd", {
                    width  : "100%",
                    height : 720,
                    path : "<%=request.getContextPath()%>/resources/editormd/lib/",
                    //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
                    saveHTMLToTextarea : true,
                    markdown : markdown,
                    // codeFold : true,
                    // saveHTMLToTextarea : true,
                    // searchReplace : true,
                    // htmlDecode : "style,script,iframe|on*",
                    // emoji : true,
                    // taskList : true,
                    // tocm            : true,         // Using [TOCM]
                    // tex : true,                   // 开启科学公式TeX语言支持，默认关闭
                    // flowChart : true,             // 开启流程图支持，默认关闭
                    // sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
                });
                // the first method
                $("#undo").bind('click', function() {
                    $.proxy(testEditor.toolbarHandlers.undo, testEditor)();
                });
                // the Second method : using manually loaded dialog plugin
                $("#open-html-entities-dialog").bind('click', function() {
                    testEditor.htmlEntitiesDialog();
                });
                $("#insert-bold").bind('click', function() {
                    $.proxy(testEditor.toolbarHandlers.bold, testEditor)();
                    testEditor.focus();
                });
                $("#insert-h6").bind('click', function() {
                    $.proxy(testEditor.toolbarHandlers.h6, testEditor)();
                    testEditor.focus();
                });
                $("#insert-inline-code").bind('click', function() {
                    $.proxy(testEditor.toolbarHandlers.code, testEditor)();
                    testEditor.focus();
                });
                $("#open-help-dialog").bind('click', function() {
                    $.proxy(testEditor.toolbarHandlers.help, testEditor)();
                });
                // using toolbar dialog plugin
                $("#open-link-dialog").bind('click', function() {
                    $.proxy(testEditor.toolbarHandlers.link, testEditor)();
                });
                // or
                $("#open-image-dialog").bind('click', function(){
                    testEditor.executePlugin("imageDialog", "<%=request.getContextPath()%>/resources/editormd/plugins/image-dialog/image-dialog"); // load and execute plugin
                });
            }
//            );

            function initialize() {
                showArticleIfNecessary();
            }

            function showArticleIfNecessary() {
                // if specify an article id, then we need to show article in editor.
                var articleId = "${article_id}";
                if (articleId != "") {
                    $.get("/rest/article/" + articleId, function(article) {
                        // strange, I can onlly set markdown content to editormd when first created.
                        createEditor(article.markDown);
                        $("#title").val(article.title);
                        $("#category").val(article.categoryName);
                        $("#tags").val(article.tags);
                        $("#digest").val(article.digest);
                    })
                } else {
                    createEditor("");
                }
            }

            // save article, after server confirm it's been saved, then show it in article viewer page.
            function saveAndShowArticle() {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "/rest/article", true);
                xhr.setRequestHeader("Content-type", "application/json");
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4) {
                        if (xhr.status == 200) {
                            var article = JSON.parse(xhr.responseText);
                            window.location.href = "/article/" + article.id;
                        }
                    }
                };
                var articleJson = {};
                articleJson.title = $("#title").val();
                articleJson.markDown = $("#content_markdown").val();
                articleJson.categoryName = $("#category").val();
                articleJson.tags = $("#tags").val();
                articleJson.digest = $("#digest").val();

                xhr.send(JSON.stringify(articleJson));
            }
        </script>
    </body>
</html>