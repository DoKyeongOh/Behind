<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/06/28
  Time: 10:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("loginPage", request.getContextPath() + "/loginPage"); %>
<% request.setAttribute("registerPage", request.getContextPath() + "/registerPage"); %>
<% request.setAttribute("indexPage", request.getContextPath() + "/"); %>

<% request.setAttribute("account", request.getContextPath() + "/account"); %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href= "../../css/register.css"/>
    <link rel="stylesheet" type="text/css" href= "../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"/>
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
</head>
<body>

<!-- navbar + offcanvas -->
<nav class="navbar bg-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img class="logo-img" src="icons/chat-left-heart-fill.svg" onclick="location.href='${indexPage}'"/>
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
    <h1 class="main-text-font">Your Account</h1>
    <strong>please fill this form for use our service</strong>
</div>

<!-- 계정 정보 입력 폼 -->
<div class="text-center">
    <form class="form-input-account" method="post" action="${account}">
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="id" id="input-account-id" name="id">
            <button class="btn btn-outline-secondary" type="button" id="btn-same-check">중복체크</button>
        </div>
        <label id="labelIsUsableId"></label><br><br>

        <p><input type="password" class="form-control" placeholder="Password" id="input-account-pw" name="pw"/></p>
        <p>
            <input type="password" class="form-control" placeholder="Password check" name="pwCheck" id="input-account-pw-check"/>
            <label id="labelIsSamePw"></label>
        </p>
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Email" name="email">
            <span class="input-group-text">@</span>
            <select class="form-select" name="domain">
                <option selected>gmail.com</option>
            </select>
        </div>

        <button type="submit" class="btn btn-outline-primary" id="next-button">이메일 인증하기</button>
    </form>
</div>
<!-- 계정 정보 입력 폼 -->

<br>
<%--메시지--%>
<h6 class="text-center" id="notice-label">${noticeMessage}</h6>
<%--메시지--%>
<script src="../../js/accountInput.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>