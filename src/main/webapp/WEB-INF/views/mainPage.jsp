<%@ page import="org.mytoypjt.models.entity.Profile" %><%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/06/30
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("indexPage", request.getContextPath() + "/"); %>
<% request.setAttribute("mainPage", request.getContextPath() + "/main/page"); %>
<% request.setAttribute("logout", request.getContextPath() + "/login"); %>
<% request.setAttribute("realTimePosts", request.getContextPath() + "/posts?type=1"); %>
<% request.setAttribute("daysMostPosts", request.getContextPath() + "/posts?type=2"); %>
<% request.setAttribute("weeksMostPosts", request.getContextPath() + "/posts?type=3"); %>
<% request.setAttribute("searchPosts", request.getContextPath() + "/posts?type=4"); %>

<% request.setAttribute("post", request.getContextPath() + "/post"); %>
<% request.setAttribute("postCreatePage", request.getContextPath() + "/post/page/1"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Behind</title>
    <link rel="stylesheet" type="text/css" href= "../../css/mainPage.css"/>
    <link rel="stylesheet" type="text/css" href= "../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"/>
</head>
<body>

<!-- navbar + offcanvas -->
<nav class="navbar bg-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="${mainPage}">
            <img class="logo-img" src="../../icons/chat-left-heart-fill.svg" onclick="location.href='${indexPage}'"/>
            <strong class="logo-text" onclick="location.href='${mainPage}'">Blind</strong>
        </a>
        <button class="navbar-toggler btn-dark" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar">
            <div class="text-center"><br/>
                <h5 class="offcanvas-title">???????????? ??????</h5>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3"><br/>

                    <li class="nav-item dropdown text-center">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">??? ?????? ??????</a>
                        <ul class="dropdown-menu text-center">
                            <li><a class="dropdown-item" href="${realTimePosts}">????????? ???</a></li>
                            <li><a class="dropdown-item" href="${daysMostPosts}">?????? ?????????</a></li>
                            <li><a class="dropdown-item" href="${weeksMostPosts}">?????? ?????????</a></li>
                        </ul>
                    </li>

                    <li class="nav-item"><a class="nav-link text-center" href="${mainPage}">???????????????</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="${postCreatePage}">??? ????????????</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="${mainPage}">?????? ?????? ??????</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="${mainPage}">?????? ????????????</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="${mainPage}">????????? ?????????</a></li>
                </ul>
            </div>
            <form action="${logout}" method="post">
                <input type="hidden" name="_method" value="delete">
                <button type="submit" class="btn btn-outline-dark" data-bs-dismiss="offcanvas" id="btn-logout">???????????? ??????</button>
            </form>

            <button type="button" class="btn btn-dark" data-bs-dismiss="offcanvas">????????????</button>
        </div>
    </div>
</nav>
<!-- navbar + offcanvas -->

<div class="text-center main-text">
    <h1 class="main-text-font">Behind</h1>
</div>

<hr id="line-mid">

<!-- ??? ?????? ?????? ?????? ?????? -->
<div class="text-center" id="posts-sort-type">
    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
        <input type="radio" class="btn-check" name="btnradio" id="btnradio1" onclick="location.href='${realTimePosts}'" ${realtimeChecked}>
        <label class="btn btn-outline-dark btn-sort-type" for="btnradio1">????????? ???</label>

        <input type="radio" class="btn-check" name="btnradio" id="btnradio2" onclick="location.href='${daysMostPosts}'" ${daysChecked}>
        <label class="btn btn-outline-dark btn-sort-type" for="btnradio2">?????? ?????????</label>

        <input type="radio" class="btn-check" name="btnradio" id="btnradio3" onclick="location.href='${weeksMostPosts}'" ${weeksChecked}>
        <label class="btn btn-outline-dark btn-sort-type" for="btnradio3">?????? ?????????</label>
    </div>
</div>
<!-- ??? ?????? ?????? ?????? ?????? -->

<!-- ??? ?????? ????????? -->
<div>
    <table id="table-posts">
        <c:forEach var="tr" begin="0" end="2">
            <tr>
                <c:forEach var="td" begin="0" end="3">
                    <c:set var="idx" scope="request" value="${tr * 4 + td}"/>
                    <c:if test="${empty posts[idx] eq false}">
                        <td>
                            <div class="card" style="width: 300px;">
                                <a href="${post}?no=${posts[idx].postNo}">
                                    <img src="../../pictures/mini/${posts[idx].pictureNo}.jpeg" class="card-img-top"
                                         oncontextmenu="return false" ondragstart="return false" onkeydown="return false">
                                </a>

                                <div class="card-body">

                                    <h5 class="title-font">${posts[idx].title}</h5>

                                    <c:if test="${posts[idx].is_use_anonymous_name eq true}">
                                        <label class="label-poster-name">??????</label><br>
                                    </c:if>
                                    <c:if test="${posts[idx].is_use_anonymous_name ne true}">
                                        <label class="label-poster-name">${posts[idx].nicname}</label><br>
                                    </c:if>

                                    <div class="text-right">
                                        <label class="label-poster-name display-inline-block">
                                                ${cities[idx]}
                                        </label><br>
                                        <label class="like-label display-inline-block">?????? ${posts[idx].commentCount} ??? ??? ????????? ${posts[idx].likeCount} ???</label>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </c:if>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${empty posts eq true}">
        <h1 class="text-center" id="label-not-posted">?????? ???????????? ???????????????...</h1>
    </c:if>
</div>
<!-- ??? ?????? ????????? -->

<!-- ?????????????????? -->
<ul class="pagination" id="pagenation">
    <c:set var="PostsOption" value="${sessionScope.get('PostsOption')}" />
    <c:set var="startNo" value="${PostsOption.pageStartNo}" />
    <c:set var="endNo" value="${PostsOption.pageEndNo}" />
    <c:set var="pageNo" value="${PostsOption.pageNo}" />
    <c:set var="pageCount" value="${PostsOption.displayPageCount}" />

    <c:if test="${startNo ne 1}">
        <li class="page-item"><a class="page-link" href="/posts?pageNo=${pageNo-1}">Previous</a></li>
    </c:if>

    <c:forEach var="pageIndex" begin="${startNo}" end="${endNo}">
        <c:if test="${pageNo eq pageIndex}">
            <li class="page-item active"><a class="page-link" href="/posts?pageNo=${pageIndex}">${pageIndex}</a></li>
        </c:if>

        <c:if test="${pageNo ne pageIndex}">
            <li class="page-item"><a class="page-link" href="/posts?pageNo=${pageIndex}">${pageIndex}</a></li>
        </c:if>
    </c:forEach>

    <c:if test="${endNo - startNo >= (pageCount - 1)}">
        <li class="page-item"><a class="page-link" href="/posts?pageNo=${pageNo + 1}">Next</a></li>
    </c:if>
</ul>
<!-- ?????????????????? -->

<!-- ?????? ??? -->
<form method="get" action="${searchPosts}" id="search-block" class="input-group">
    <select name="searchOption" class="form-select" style="width: 15%">
        <option value="1" selected>??????</option>
        <option value="2">??????</option>
    </select>
    <input type="text" name="searchWord" class="form-control" style="width: 60%">
    <button type="submit" style="width: 15%" class="btn btn-outline-secondary form-control">????????????</button>
</form>
<!-- ?????? ??? -->


<!-- footer -->
<div class="container footer">
    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Home</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Features</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Pricing</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">FAQs</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">About</a></li>
        </ul>
        <p class="text-center text-muted">?? 2022 Company, Inc</p>
    </footer>
</div>
<!-- footer -->


<script src="../../js/mainPage.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>