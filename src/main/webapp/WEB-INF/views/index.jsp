<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/06/27
  Time: 1:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("loginPage", request.getContextPath() + "/login"); %>
<% request.setAttribute("registerPage", request.getContextPath() + "/register"); %>
<% request.setAttribute("indexPage", request.getContextPath() + "/"); %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href= "../../css/index.css"/>
    <link rel="stylesheet" type="text/css" href= "../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"/>
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
                <li class="nav-item">
                    <a class="nav-link text-center" aria-current="page" href=${loginPage}>로그인 하러가기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-center" href=${registerPage}>회원가입 하러가기</a>
                </li>
              </ul>
            </div>
            <button type="button" class="btn btn-dark" data-bs-dismiss="offcanvas" aria-label="Close">돌아가기</button>
          </div>
        </div>
      </nav>
    <!-- navbar + offcanvas -->

    <!-- 로그인,회원가입 버튼 -->
      <div class="btn-margin-center">
        <button class="btn btn-outline-primary btn-size-big" onclick="location.href='${loginPage}'"> 로그인 하러가기 </button>
        <button class="btn btn-outline-primary btn-size-big" onclick="location.href='${registerPage}'"> 회원가입 하러가기 </button>
      </div>
    <!-- 로그인,회원가입 버튼 -->
<%--    <button id="testButton">this is     testButton</button>--%>

    <script src="../../js/test.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
