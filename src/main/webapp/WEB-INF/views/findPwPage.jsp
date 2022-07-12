<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/06/28
  Time: 10:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("loginPage", request.getContextPath() + "/login/page"); %>
<% request.setAttribute("registerPage", request.getContextPath() + "/register/page/1"); %>
<% request.setAttribute("indexPage", request.getContextPath() + "/"); %>

<% request.setAttribute("pwCert", request.getContextPath() + "/pw/cert"); %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href= "../../css/findId.css"/>
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
    <h1 class="main-text-font">Find Password</h1>
</div>

<!-- 아이디, 이메일 입력 폼 -->
<div class="form-auth-input">
    <form class="input-form" method="get" action="${pwCert}">
        <p><input type="text" class="form-control" placeholder="ID" name="id"/></p>
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Email" name="email">
            <span class="input-group-text">@</span>
            <select class="form-select" name="domain">
                <option selected>gmail.com</option>
                <!-- <option value="1">gmail.com</option> -->
            </select>
        </div>

        <button type="submit" class="btn btn-primary btn-login-submit btn-auth-margin">인증번호 받기</button>
        <button type="submit" class="btn btn-outline-primary btn-login-submit btn-auth-margin">인증번호 다시받기</button>
    </form>
</div>
<!-- 아이디, 이메일 입력 폼 -->

<!-- 인증번호 입력 폼 -->
    <form class="form-auth-check text-center" method="post" action="${pwCert}">
        <span><input type="password" name="pwCertificationInput"></span>
        <span><button class="btn btn-outline-primary btn-sm btn-auth-check" type="submit">인증하고 비밀번호 재설정하기</button></span>
    </form>
<!-- 인증번호 입력 폼 -->

<!-- 인증 완료 여부 라벨 -->
<h6 class="text-center user-id-label">${noticeMessage}</h6>
<!-- 인증 완료 여부 라벨 -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>