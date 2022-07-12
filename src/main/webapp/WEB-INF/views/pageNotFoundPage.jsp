<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/06/27
  Time: 1:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
              </ul>
            </div>
            <button type="button" class="btn btn-dark" data-bs-dismiss="offcanvas" aria-label="Close">돌아가기</button>
          </div>
        </div>
      </nav>
    <!-- navbar + offcanvas -->

    <!-- 메시지 -->
    <div class="page-not-found-messege">
        <h1 class="page-not-found-font">페이지를 찾을 수 없습니다...</h1>
    </div>
        
    <!-- 메시지 -->


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>