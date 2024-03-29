<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2022-09-28
  Time: 오후 10:42
  To change this template use File | Settings | File Templates.
--%>

<% request.setAttribute("loginPage", request.getContextPath() + "/login/page"); %>
<% request.setAttribute("registerPage", request.getContextPath() + "/register/page/1"); %>
<% request.setAttribute("indexPage", request.getContextPath() + "/"); %>
<% request.setAttribute("login", request.getContextPath() + "/login"); %>
<% request.setAttribute("loginChange", request.getContextPath() + "/login"); %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Behind</title>
    <link rel="stylesheet" type="text/css" href= "../../css/loginFailPage.css"/>
    <link rel="stylesheet" type="text/css" href= "../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"/>
</head>
<body>

<!-- navbar + offcanvas -->
<nav class="navbar bg-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="${indexPage}">
            <img class="logo-img" src="../../icons/chat-left-heart-fill.svg" onclick="location.href='${indexPage}'"/>
            <strong class="logo-text" onclick="location.href='${indexPage}'">Blind</strong>
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
                    <li class="nav-item"><a class="nav-link text-center" aria-current="page" href=${loginPage}>로그인 하러가기</a></li>
                    <li class="nav-item"><a class="nav-link text-center" href=${registerPage}>회원가입 하러가기</a></li>
                </ul>
            </div>
            <button type="button" class="btn btn-dark" data-bs-dismiss="offcanvas">돌아가기</button>
        </div>
    </div>
</nav>
<!-- navbar + offcanvas -->

<div class="text-center main-text">
    <h1 id="message-login-fail">이미 로그인 되어있음!</h1>
    <form action="${loginChange}" method="post">
        <input type="hidden" name="_method" value="put">
        <button type="submit" class="btn btn-outline-primary" id="btn-login-change">여기서 다시 로그인</button><br>
    </form>

    <button type="button" class="btn btn-primary" id="btn-move-index-page" onclick="location.href='${indexPage}'">
        첫 화면으로 이동
    </button>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>