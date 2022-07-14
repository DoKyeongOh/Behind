<%@ page import="org.mytoypjt.models.entity.Profile" %><%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/06/27
  Time: 12:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("loginPage", request.getContextPath() + "/login/page"); %>
<% request.setAttribute("registerPage", request.getContextPath() + "/register/page/1"); %>
<% request.setAttribute("indexPage", request.getContextPath() + "/"); %>
<% request.setAttribute("login", request.getContextPath() + "/login"); %>

<% request.setAttribute("idPage", request.getContextPath() + "/id/page"); %>
<% request.setAttribute("pwPage", request.getContextPath() + "/pw/page/1"); %>
<% request.setAttribute("mainPage", request.getContextPath() + "/main/page"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href= "../../css/login.css"/>
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
        <button class="navbar-toggler btn-dark" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar">
            <div class="text-center"><br/>
                <h5 class="offcanvas-title">사이드바 메뉴</h5>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3"><br/>
                    <li class="nav-item"><a class="nav-link text-center" href="${registerPage}">회원가입 하러가기</a></li>
                </ul>
            </div>
            <button type="button" class="btn btn-dark" data-bs-dismiss="offcanvas">돌아가기</button>
        </div>
    </div>
</nav>
<!-- navbar + offcanvas -->

<div class="text-center main-text">
    <h1 class="main-text-font">Log in</h1>
</div>

<!-- 로그인 폼 -->
<div>
    <form class="input-form" method="post" action="${login}">
        <div class="mb-3">
            <label class="form-label">ID</label>
            <input type="text" class="form-control" name="accountId">
        </div>
        <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" class="form-control" name="accountPw">
        </div>
        <div>
            <button type="submit" class="btn btn-primary btn-login-submit">로그인 하기</button>
        </div>
    </form>
</div>
<!-- 로그인 폼 -->

<!-- 아이디/비밀번호 찾기 버튼 -->
<div class="text-center form-find-account">
    <div class="label-find-account">
        <h6>아이디를 잃어버리셨나요?</h6>
        <button class="btn btn-outline-primary btn-find-account" onclick="location.href='${idPage}'">아이디 찾기</button>
    </div>
    <div class="label-find-account">
        <h6>비밀번호를 잃어버리셨나요?</h6>
        <button class="btn btn-outline-primary btn-find-account" onclick="location.href='${pwPage}'">비밀번호 찾기</button>
    </div>
</div>
<!-- 아이디/비밀번호 찾기 버튼 -->




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>