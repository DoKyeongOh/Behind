<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/07/19
  Time: 12:30 PM
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
                    <li class="nav-item"><a class="nav-link text-center" href="${mainPage}">글 작성하기</a></li>
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

<!-- 글 목록 정렬 방식 버튼 -->

<!-- 글 목록 정렬 방식 버튼 -->

<!-- 글 목록 테이블 -->

<!-- 글 목록 테이블 -->

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