<%--
  Created by IntelliJ IDEA.
  User: Mengliang Li
  Date: 5/19/2017
  Time: 11:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>代码 / 李的博客</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/bootstrap/js/bootstrap-paginator.js"></script>
    <style>
        .root-body {
            background-color: #f1f1f1;
        }
        /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
        .row.content {height: 1000px}

        /* Set gray background color and 100% height */
        .sidenav {
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        .footer {
            background-color: #555;
            color: white;
        }
        .cloud-container{
            position:absolute;
            top:0;
            bottom:0;
            left:0;
            right:0;
            overflow:hidden;
            z-index:-1; /* Remove this line if it's not going to be a background! */
        }
        .right-side {
            background-color:#ffffff;
        }
        .digest-list {
            background-color:#ffffff;
            padding-left:50px;
            padding-top:100px;
        }

        .toppadding {
            padding-top: 100px;
        }

        h3.title:hover
        {
            cursor:pointer;
            text-decoration:underline;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height: auto;}
        }
    </style>
</head>
<body class="root-body" onLoad="initialize()">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">代码 / 李的博客</a>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-2 sidenav toppadding">
            <img class="img-circle center-block" src="/resources/image/myIcon.jpg" alt="Generic placeholder image" width="140" height="140">
            <br>
            <h5 class="text-center">李梦亮</h5>
            <h5 class="text-center">美国国家仪器</h5>
            <ul id="category_list" class="nav nav-pills nav-stacked">
                <li class="active" onclick="showArticleDigestOfAllCategories()"><a>所有类别</a></li>
            </ul>
            <br>
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search Blog..">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </span>
            </div>
        </div>

        <div id="right_side" class="right-side col-sm-10">
            <div id="digest_list" class="digest-list"></div>
            <nav style="text-align: center">
                <ul id="paginator" class="pagination">
                    <li class="previous" onclick="onPreviousClicked()"><a href="#">«</a></li>
                    <li id="first_page_button" class="active" onclick="onPageClicked(1)"><a href="#">1</a></li>
                    <li id="second_page_button" onclick="onPageClicked(2)"><a href="#">2</a></li>
                    <li id="third_page_button" onclick="onPageClicked(3)"><a href="#">3</a></li>
                    <li id="fourth_page_button" onclick="onPageClicked(4)"><a href="#">4</a></li>
                    <li id="fifth_page_button" onclick="onPageClicked(5)"><a href="#">5</a></li>
                    <li class="next" onclick="onNextClicked()"><a href="#">»</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<div>
    <p>Footer Text</p>
</div>
<script type="text/javascript">
    var active_page;
    var active_page_button;
    var all_categories;
    function initialize() {
        queryCategories();
        showArticleDigestOfAllCategories();
    }

    function initializePaginator() {
        active_page = 1;
        active_page_button = 1;
        var category_count = all_categories.length;
        switch (category_count) {
            case 1:
                $("#second_page_button").remove();
                $("#third_page_button").remove();
                $("#fourth_page_button").remove();
                $("#fifth_page_button").remove();
                break;
            case 2:
                $("#third_page_button").remove();
                $("#fourth_page_button").remove();
                $("#fifth_page_button").remove();
                break;
            case 3:
                $("#fourth_page_button").remove();
                $("#fifth_page_button").remove();
                break;
            case 4:
                $("#fifth_page_button").remove();
                break;
            default:
                break;
        }
    }
    function queryCategories() {
        $.get("/blog/categories", function(categories) {
            all_categories = categories;
            for(var i = 0; i < categories.length; i++) {
                var category = categories[i];
                var li = document.createElement("li");
                //if(i == 0) {
                //    li.setAttribute("class", "active");
                //    fetchAndShowDigests(category.category_id);
                // }
                li.setAttribute("category_id", category.id);
                var a = document.createElement("a");
                a.textContent = category.name;
                li.onclick = function() {
                    var lis = $("#category_list").find("li");
                    for(var i = 0; i < lis.length; i++) {
                        var li = lis[i];
                        li.setAttribute("class", "");
                    }
                    this.setAttribute("class", "active");
                    $("#digest_list").children().remove();
                    fetchAndShowDigests(this.getAttribute("category_id"));
                }
                li.appendChild(a);
                $("#category_list").add(li).appendTo("#category_list");
            }
            initializePaginator();
        })
    }

    function showArticleDigestOfAllCategories() {
        var lis = $("#category_list").find("li");
        for(var i = 01; i < lis.length; i++) {
            var li = lis[i];
            li.setAttribute("class", "");
        }
        lis[0].setAttribute("class", "active");
        $("#digest_list").children().remove();
        fetchAndShowDigests("");
    }

    function fetchAndShowDigests(category_id) {
        $.get("/blog/article_digests", {category_id:category_id, start_index:0, count:8},
        function (digests) {
            for(var i = 0; i < digests.length; i++) {
                var digest = digests[i];
                $("#digest_list").add(createDigest(digest)).appendTo("#digest_list");
            }
        })
    }

    function createDigest(articleDigest) {
        var div = document.createElement("div");

        // title
        var title = document.createElement("h3");
        title.textContent = articleDigest.title;
        title.setAttribute("class", "title");
        title.onclick = function() {
            window.location.href="/blog/article?article_id=" + articleDigest.articleId;
        }

        // digest
        var digestText = document.createElement("h5");
        digestText.textContent = articleDigest.digest;
        // tag
        var tagAndDate = document.createElement("h5");
        tagAndDate.setAttribute("class", "pull-right");
        addTags(tagAndDate, articleDigest.tags);
        var timeSpan = document.createElement("span");
        timeSpan.textContent = "    创建于" + articleDigest.date;
        tagAndDate.appendChild(timeSpan);


        div.appendChild(title);
        div.appendChild(digestText);
        div.appendChild(tagAndDate);
        div.appendChild(document.createElement("br"));
        div.appendChild(document.createElement("hr"));
        return div;
    }

    function addTags(div, tags) {
        var tagList = tags.split(" ");
        for (var i = 0; i < tagList.length; i++) {
            var span = document.createElement("span");
            span.setAttribute("class", "label label-default tag");
            span.setAttribute("style", "margin-right: 10px");
            span.textContent = tagList[i];
            div.appendChild(span);
        }
    }

    function onPreviousClicked() {
        if(active_page_button!= 1) {
            active_page_button -= 1;
            onPageClicked(active_page_button);
        }
    }

    function onNextClicked() {
        if(active_page_button!= 5) {
            active_page_button += 1;
            onPageClicked(active_page_button);
        }
    }

    function deactivateAllPageButton() {
        var lis = $("#paginator").find("li");
        for(var i = 0; i < lis.length; i++) {
            var li = lis[i];
            li.setAttribute("class", "");
        }
    }
    function onPageClicked(page) {
        deactivateAllPageButton();
        switch (page) {
            case 1:
                active_page = $("#first_page_button").find("a")[0].textContent;
                var offset = getPaginatorOffset(1, parseInt(active_page), all_categories.length);
                if(offset != 0) {
                    updatePaginator(offset);
                    switch(offset) {
                        case -1:
                            $("#second_page_button").attr("class", "active");
                            active_page = $("#second_page_button").find("a")[0].textContent;
                            active_page_button = 2;
                            break;
                        default:
                            onPageClicked(3);
                            break;
                    }
                }
                else {
                    $("#first_page_button").attr("class", "active");
                    active_page = $("#first_page_button").find("a")[0].textContent;
                    active_page_button = 1;
                }
                break;
            case 2:
                active_page = $("#second_page_button").find("a")[0].textContent;
                var offset = getPaginatorOffset(2, parseInt(active_page), all_categories.length);
                if(offset != 0) {
                    updatePaginator(offset);
                    onPageClicked(3);
                    break;
                }
                else {
                    $("#second_page_button").attr("class", "active");
                    active_page = $("#second_page_button").find("a")[0].textContent;
                    active_page_button = 2;
                }
                break;
            case 3:

                $("#third_page_button").attr("class", "active");
                active_page = $("#third_page_button").find("a")[0].textContent;
                active_page_button = 3;
                break;
            case 4:
                active_page = $("#fourth_page_button").find("a")[0].textContent;
                var offset = getPaginatorOffset(4, parseInt(active_page), all_categories.length);
                if(offset != 0) {
                    updatePaginator(offset);
                    onPageClicked(3);
                    break;
                }
                else {
                    $("#fourth_page_button").attr("class", "active");
                    active_page = $("#fourth_page_button").find("a")[0].textContent;
                    active_page_button = 4;
                }
                break;
            case 5:
                active_page = $("#fifth_page_button").find("a")[0].textContent;
                var offset = getPaginatorOffset(5, parseInt(active_page), all_categories.length);
                if(offset != 0) {
                    updatePaginator(offset);
                    switch(offset) {
                        case 1:
                            $("#fourth_page_button").attr("class", "active");
                            active_page = $("#fourth_page_button").find("a")[0].textContent;
                            active_page_button = 4;
                            break;
                        default:
                            onPageClicked(3);
                            break;
                    }
                }
                else {
                    $("#fifth_page_button").attr("class", "active");
                    active_page = $("#fifth_page_button").find("a")[0].textContent;
                    active_page_button = 5;
                }
                break;
            default:
                break;
        }
    }

    function getPaginatorOffset(active_page_button_index, active_page, total_page) {
        var offset = 0;
        if(active_page_button_index < 3) {
            offset = active_page_button_index - active_page < active_page_button_index - 3 ? active_page_button_index - 3 :
                active_page_button_index - active_page;
        }
        else {
            offset = total_page - (5 - active_page_button_index) - active_page > active_page_button_index - 3 ? active_page_button_index - 3 :
                total_page - (5 - active_page_button_index) - active_page;
        }
        return offset;
    }
    function updatePaginator(offset) {
        var lis = $("#paginator").find("li");
        for(var i = 1; i < lis.length - 1; i++) {
            var li = lis[i];
            var a = li.getElementsByTagName("a")[0];
            a.textContent = parseInt(a.textContent) + offset;
        }
    }
</script>
</body>
</html>
