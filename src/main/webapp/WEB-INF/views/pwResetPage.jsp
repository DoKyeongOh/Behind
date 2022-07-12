<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/07/05
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("loginPage", request.getContextPath() + "/login/page"); %>
<% request.setAttribute("registerPage", request.getContextPath() + "/register/page/1"); %>
<% request.setAttribute("indexPage", request.getContextPath() + "/"); %>

<% request.setAttribute("pwPage", request.getContextPath() + "/pw/page/2"); %>
<% request.setAttribute("pwResetRequest", request.getContextPath() + "/pw"); %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href= "../../css/findPw.css"/>
    <link rel="stylesheet" type="text/css" href= "../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"/>
</head>
<body>

<!-- navbar + offcanvas -->
<nav class="navbar bg-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img class="logo-img" src="../../icons/chat-left-heart-fill.svg" onclick="location.href='${indexPage}'"/>
            <strong class="logo-text" onclick="location.href='${indexPage}'">Blind </strong>
        </a>
        <button class="navbar-toggler btn-dark " type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
            <div class="text-center"><br/>
                <h5 class="offcanvas-title" id="offcanvasNavbarLabel">사이드바 메뉴</h5>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3"><br/>
                    <!-- <li class="nav-item">
                        <a class="nav-link text-center" aria-current="page" href="#">로그인 하러가기</a>
                    </li> -->
                    <li class="nav-item">
                        <a class="nav-link text-center" href="${registerPage}">회원가입 하러가기</a>
                    </li>
                </ul>
            </div>
            <button type="button" class="btn btn-dark" data-bs-dismiss="offcanvas" aria-label="Close">돌아가기</button>
        </div>
    </div>
</nav>
<!-- navbar + offcanvas -->

<div class="text-center main-text">
    <h1 class="main-text-font">Password Reset</h1>
</div>

<!-- 비밀번호 입력 폼 -->
<div class="form-auth-input">
    <form class="input-form" method="POST" action="${pwResetRequest}">
        <input type="hidden" name="_method" value="PUT">
        <p><input type="text" class="form-control" placeholder="비밀번호" name="password"/></p>
        <p><input type="text" class="form-control" placeholder="비밀번호 확인" name="passwordCheck"/></p>
        <button type="submit" class="btn btn-primary btn-login-submit btn-auth-margin">재설정하기</button>
    </form>
</div>
<!-- 비밀번호 입력 폼 -->

<!-- 비밀번호 유효성 체크 라벨 -->
<h6 class="text-center user-id-label">${noticeMessage}</h6>
<!-- 비밀번호 유효성 체크 라벨 -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>