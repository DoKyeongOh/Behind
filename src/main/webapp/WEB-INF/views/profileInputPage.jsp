<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/07/13
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("loginPage", request.getContextPath() + "/login/page"); %>
<% request.setAttribute("registerPage", request.getContextPath() + "/register/page/1"); %>
<% request.setAttribute("indexPage", request.getContextPath() + "/"); %>


<% request.setAttribute("mainPage", request.getContextPath() + "/main/page"); %>
<% request.setAttribute("logout", request.getContextPath() + "/login"); %>
<% request.setAttribute("realTimePosts", request.getContextPath() + "/posts?type=1"); %>
<% request.setAttribute("daysMostPosts", request.getContextPath() + "/posts?type=2"); %>
<% request.setAttribute("weeksMostPosts", request.getContextPath() + "/posts?type=3"); %>
<% request.setAttribute("searchPosts", request.getContextPath() + "/posts?type=4"); %>
<% request.setAttribute("myPage", request.getContextPath() + "/my/page"); %>
<% request.setAttribute("postCreatePage", request.getContextPath() + "/post/page/1"); %>


<html>
<head>
    <title>Behind</title>
    <link rel="stylesheet" type="text/css" href= "../../css/register.css"/>
    <link rel="stylesheet" type="text/css" href= "../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"/>
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
</head>
<body>


<!-- navbar + offcanvas -->
<nav class="navbar bg-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="${mainPage}">
            <img class="logo-img" src="../../icons/chat-left-heart-fill.svg" onclick="location.href='${mainPage}'"/>
            <strong class="logo-text" onclick="location.href='${mainPage}'">Blind</strong>
        </a>

        <c:set var="myProfile" value="${sessionScope.get('profile')}"/>
        <button onclick="location.href='${myPage}'" class="border border-0" id="profile-display"> "${myProfile.nickname}" 님 안녕하세요! </button>

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

                    <li class="nav-item"><a class="nav-link text-center" href="${myPage}">마이페이지</a></li>
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
    <h1 class="main-text-font">Your Profile</h1>
    <strong>please fill this form for use our service</strong>
</div>

<!-- 계정 정보 입력 폼 -->
<div class="text-center">
    <form id="profile-input-form" method="post" action="/profile">
        <input type="hidden" name="_method" value="put" />
        <input type="text" class="form-control" placeholder="사용할 닉네임" id="input-nickname" name="nickname">
    
        <div class="certer-inline-align">
            <select class="form-select" name="age" id="btn-age-dropdown">
                <option selected>나이를 선택하세요</option>
            </select>

            <select class="form-select" name="city" id="btn-city-dropdown">
                <option selected>지역을 선택하세요</option>
            </select>
        </div>

        <div id="gender-select-box">
            <div class="form-check form-check-inline">
                <input class="form-check-input" id="radio-man" type="radio" name="gender" value="남자">
                <label class="form-check-label" id="label-man">남자</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" id="radio-woman" type="radio" name="gender" value="여자">
                <label class="form-check-label" id="label-woman">여자</label>
            </div>
        </div>

        <button type="submit" class="btn btn-primary" id="btn-prifile-apply">적용하기</button>
    </form>

    <button type="submit" class="btn btn-outline-primary" id="btn-prifile-not-apply" onclick="location.href='${indexPage}'">
        건너뛰기
    </button>
</div>
<!-- 계정 정보 입력 폼 -->

<br>
<!--메시지-->
<h6 class="text-center" id="notice-label">${noticeMessage}</h6>
<!--메시지-->

<script src="../../js/profileInput.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>