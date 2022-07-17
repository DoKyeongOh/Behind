<%@ page import="org.mytoypjt.models.entity.Profile" %><%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/06/30
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("indexPage", request.getContextPath() + "/"); %>
<% request.setAttribute("mainPage", request.getContextPath() + "/main/page"); %>

<% request.setAttribute("logout", request.getContextPath() + "/login"); %>

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
                            <li><a class="dropdown-item" href="#">실시간 글</a></li>
                            <li><a class="dropdown-item" href="#">일일 인기글</a></li>
                            <li><a class="dropdown-item" href="#">주간 인기글</a></li>
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

<div class="text-center main-text">
    <h1 class="main-text-font">Behind</h1>
    <!-- 당신의 닉네임은 "${profile.nicname}" 입니다!!! -->
</div>

<!-- 글 목록 정렬 방식 버튼 -->
<div class="text-center" id="posts-sort-type">
    <div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-outline-dark" onclick="location.href='/posts?type=1'">실시간 글</button>
        <button type="button" class="btn btn-outline-dark">일일 인기글</button>
        <button type="button" class="btn btn-outline-dark">주간 인기글</button>
    </div>

    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
        <input type="radio" class="btn-check" name="btnradio" id="btnradio1" onclick="location.href='/posts?type=1'" checked>
        <label class="btn btn-outline-dark" for="btnradio1">실시간 글</label>
      
        <input type="radio" class="btn-check" name="btnradio" id="btnradio2" onclick="location.href='/posts?type=2'">
        <label class="btn btn-outline-dark" for="btnradio2">일일 인기글</label>
      
        <input type="radio" class="btn-check" name="btnradio" id="btnradio3" onclick="location.href='/posts?type=3'">
        <label class="btn btn-outline-dark" for="btnradio3">주간 인기글</label>
      </div>
</div>
<!-- 글 목록 정렬 방식 버튼 -->

<!-- 글 목록 테이블 -->
<div>
    <table id="table-posts">
        <c:forEach var="tr" begin="0" end="2">
        <tr>
            <c:forEach var="td" begin="0" end="3">
                <c:set var="idx" scope="request" value="${tr * 4 + td}"/>
                <c:if test="${empty posts[idx] eq false}">
                    <td>
                        <div class="card" style="width: 280px;">
                            <img src="../../pictures/mini/${posts[idx].pictureNo}.jpeg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="">${posts[idx].title}</h5>
                                <a href="#" class="btn btn-outline-primary">자세히 보기</a>
                            </div>
                        </div>
                    </td>
                </c:if>
            </c:forEach>
        </tr>
        </c:forEach>
        </table>
</div>
<!-- 글 목록 테이블 -->



<script src="../../js/mainPage.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>