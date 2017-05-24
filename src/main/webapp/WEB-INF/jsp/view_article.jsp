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
    <title>文档</title>
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/editormd/css/editormd.preview.min.css" />
    <link rel="stylesheet" href="/resources/editormd/css/editormd.css" />
</head>
<body>
    <p>${article.title}</p>
    <div id="content">${article.htmlDocument}</div>
    <p>${article.categoryName}</p>
    <p>${article.tags}</p>
    <p>${article.digest}</p>
    <script src="<%=request.getContextPath()%>/resources/editormd/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/editormd/lib/marked.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/editormd/lib/prettify.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/editormd/editormd.min.js"></script>
    <script type="text/javascript">
        editormd.markdownToHTML("content");
    </script>
</body>
</html>
