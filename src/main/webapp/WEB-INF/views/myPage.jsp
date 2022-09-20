<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/08/03
  Time: 11:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Behind</title>
    <link rel="stylesheet" type="text/css" href= "../../css/myPage.css"/>
    <link rel="stylesheet" type="text/css" href= "../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"/>
</head>
<body>

<!-- navbar + offcanvas -->
<nav class="navbar bg-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="/main/page">
            <img class="logo-img" src="../../icons/chat-left-heart-fill.svg" onclick="location.href='/'"/>
            <strong class="logo-text" onclick="location.href='/main/page'">Blind</strong>
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
                            <li><a class="dropdown-item" href="/posts?type=1">실시간 글</a></li>
                            <li><a class="dropdown-item" href="/posts?type=2">일일 인기글</a></li>
                            <li><a class="dropdown-item" href="/posts?type=3">주간 인기글</a></li>
                        </ul>
                    </li>

                    <li class="nav-item"><a class="nav-link text-center" href="/my/page">마이페이지</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="/post/page/1">글 작성하기</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="">공지 목록 보기</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="">알림 확인하기</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href="">관리자 페이지</a></li>
                </ul>
            </div>
            <form action="/login" method="post">
                <input type="hidden" name="_method" value="delete">
                <button type="submit" class="btn btn-outline-dark" data-bs-dismiss="offcanvas" id="btn-logout">로그아웃 하기</button>
            </form>

            <button type="button" class="btn btn-dark" data-bs-dismiss="offcanvas">돌아가기</button>
        </div>
    </div>
</nav>
<!-- navbar + offcanvas -->

<!-- 마이페이지 글귀 -->
<h1 id="text-my-page">My Page</h1>
<!-- 마이페이지 글귀 -->


<!-- 마이페이지 내용 -->
<div id="my-page-wrapper">

    <c:set var="profile" value="${sessionScope.get('profile')}" />

    <div id="block-profile">
        <h2 class="text-center font-border border rounded-pill p-3 bg-dark text-white">내 프로필</h2>
        <label class="profile-font mt-3">
            <span class="badge bg-success mb-1">닉네임</span> :
            ${profile.nicname}
        </label><br>
        <label class="profile-font">
            <span class="badge bg-success mb-1">성별</span> : ${profile.gender}
        </label><br>
        <label class="profile-font">
            <span class="badge bg-success mb-1">나이</span> :
            ${profile.age}
        </label><br>
        <label class="profile-font">
            <span class="badge bg-success mb-1">지역</span> :
            ${profile.city}
        </label><br>
        <button id="btn-profile-change" class="btn btn-outline-dark" onclick="location.href='/register/page/3'">프로필 변경하러가기</button>
    </div>

    <div id="my-activities">
        <h2 class="text-center font-border border rounded-pill p-3 bg-dark text-white">나의 활동</h2>
        <ul class="list-group list-group-flush mt-3 activity-list">
            <c:forEach var="logItem" items="${logList}">
                <a class="one-activity mb-1" href="">
                    <li class="list-group-item">
                        <span class="display-inline-block">${logItem.logMsg}</span>
                        <span class="display-inline-block">${logItem.loggingDate}</span>
                    </li>
                </a>
            </c:forEach>
        </ul>
        <label class="badge bg-dark">글 : 5 개</label>
        <label class="badge bg-dark">댓글 : 5 개</label>
        <label class="badge bg-dark">대댓글 : 5 개</label>
        <label class="badge bg-dark">좋아요 : 5 개</label>
    </div>
</div>
<!-- 마이페이지 내용 -->


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


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>