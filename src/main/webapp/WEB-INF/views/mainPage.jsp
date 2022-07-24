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
<% request.setAttribute("post", request.getContextPath() + "/post"); %>
<% request.setAttribute("postCreatePage", request.getContextPath() + "/post/page"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
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
                <h5 class="offcanvas-title">사이드바 메뉴</h5>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3"><br/>

                    <li class="nav-item dropdown text-center">
                        <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">글 목록 보기</a>
                        <ul class="dropdown-menu text-center">
                            <li><a class="dropdown-item" href="${realTimePosts}">실시간 글</a></li>
                            <li><a class="dropdown-item" href="${daysMostPosts}">일일 인기글</a></li>
                            <li><a class="dropdown-item" href="${weeksMostPosts}">주간 인기글</a></li>
                        </ul>
                    </li>

                    <li class="nav-item"><a class="nav-link text-center" href="${mainPage}">마이페이지</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="${postCreatePage}">글 작성하기</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="${mainPage}">공지 목록 보기</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="${mainPage}">알림 확인하기</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="${mainPage}">관리자 페이지</a></li>
                </ul>
            </div>
            <form action="${logout}" method="post">
                <input type="hidden" name="_method" value="delete">
                <button type="submit" class="btn btn-outline-dark" data-bs-dismiss="offcanvas" id="btn-logout">로그아웃 하기</button>
            </form>
            
            <button type="button" class="btn btn-dark" data-bs-dismiss="offcanvas">돌아가기</button>
        </div>
    </div>
</nav>
<!-- navbar + offcanvas -->

<div class="text-center main-text">
    <h1 class="main-text-font">Behind</h1>
</div>


<hr id="line-mid">

<!-- 글 목록 정렬 방식 버튼 -->
<div class="text-center" id="posts-sort-type">
    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
        <input type="radio" class="btn-check" name="btnradio" id="btnradio1" onclick="location.href='${realTimePosts}'" ${realtimeChecked}>
        <label class="btn btn-outline-dark btn-sort-type" for="btnradio1">실시간 글</label>

        <input type="radio" class="btn-check" name="btnradio" id="btnradio2" onclick="location.href='${daysMostPosts}'" ${daysChecked}>
        <label class="btn btn-outline-dark btn-sort-type" for="btnradio2">일일 인기글</label>

        <input type="radio" class="btn-check" name="btnradio" id="btnradio3" onclick="location.href='${weeksMostPosts}'" ${weeksChecked}>
        <label class="btn btn-outline-dark btn-sort-type" for="btnradio3">주간 인기글</label>
    </div>
</div>
<!-- 글 목록 정렬 방식 버튼 -->

<!-- 글 목록 테이블 -->
<div>
    <table id="table-posts">
        <c:forEach var="tr" begin="0" end="2">
        <tr>
            <c:forEach var="td" begin="0" end="3">
                <c:set var="idx" scope="request" value="${tr * 4 + td}"/>
                <c:if test="${empty posts[idx] eq false}">
                    <td>
                        <div class="card" style="width: 280px;">
                            <img src="../../pictures/mini/${posts[idx].pictureNo}.jpeg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="title-font">${posts[idx].title}</h5>
                                <a href="${post}?no=${posts[idx].postNo}" class="btn btn-outline-primary btn-show-detail">자세히 보기</a>
                            </div>
                            <label class="like-label">댓글 ${posts[idx].commentCount} 개 ・ 좋아요 ${posts[idx].likeCount} 개</label>
                        </div>
                    </td>
                </c:if>
            </c:forEach>
        </tr>
        </c:forEach>
        </table>

    <c:if test="${empty posts eq true}">
        <h1 class="text-center" id="label-not-posted">글이 게시되지 않았습니다...</h1>
    </c:if>
</div>
<!-- 글 목록 테이블 -->

<!-- 페이지네이션 -->
<ul class="pagination" id="pagenation">
    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item"><a class="page-link" href="#">4</a></li>
    <li class="page-item"><a class="page-link" href="#">5</a></li>
    <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>
<!-- 페이지네이션 -->

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
      <p class="text-center text-muted">© 2022 Company, Inc</p>
    </footer>
  </div>
<!-- footer -->


<script src="../../js/mainPage.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>