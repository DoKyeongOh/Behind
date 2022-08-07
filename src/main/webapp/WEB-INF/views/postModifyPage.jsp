<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/08/06
  Time: 3:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% request.setAttribute("postCreate", request.getContextPath() + "/post"); %>
<% request.setAttribute("mainPage", request.getContextPath() + "/main/page"); %>
<% request.setAttribute("postCreatePage", request.getContextPath() + "/post/page/1"); %>
<% request.setAttribute("postModify", request.getContextPath() + "/post"); %>

<% request.setAttribute("myPage", request.getContextPath() + "/my/page"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Behind</title>
    <link rel="stylesheet" type="text/css" href= "../../css/postCreate.css"/>
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

<!--이미지-->
<c:forEach var="index" begin="1" end="10">
    <img src="../../pictures/origin/${index}.jpeg" class="not-display sr-only" id="img-${index}">
</c:forEach>
<!--이미지-->

<!-- 입력 창 -->
<h1 id="center-text">글 수정하기!</h1>
<form id="post-input-form" method="post" action="${postModify}">
    <div class="display-inline-block" id="post-wrapper">
        <div id="title-wrapper">
            <h2 id="title-text">Title</h2>
            <input name="title" type="text" class="form-control" value="${post.title}">
        </div>

        <div id="content-wrapper">
            <h2 id="content-text">Content</h2>
            <textarea name="content" rows="15" class="form-control">${post.content}</textarea>
        </div>
    </div>

    <div class="display-inline-block pictures" id="before-picture"
         oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onkeydown="return false">
        <img src="../../pictures/origin/1.jpeg" class="card-img-top">
        <span class="btn btn-outline-primary btn-picture-change" id="btn-previous-img">◁</span>
    </div>

    <div class="display-inline-block pictures" id="now-picture"
         oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onkeydown="return false">
        <img src="../../pictures/origin/2.jpeg" class="card-img-top">
        <span class="btn btn-outline-primary btn-picture-change" id="btn-now-img"> now</span>
    </div>

    <div class="display-inline-block pictures" id="after-picture"
         oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onkeydown="return false">
        <img src="../../pictures/origin/3.jpeg" class="card-img-top">
        <span class="btn btn-outline-primary btn-picture-change" id="btn-next-img">▷</span>
    </div>

    <div id="checkbox-option" class="">
        <c:if test="${post.isUseAnonymousName}">
            <c:set var="nameChecked" value="checked"/>
        </c:if>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="true" id="flexCheckDefault" name="isAnonName" ${nameChecked}>
            <label class="form-check-label" for="flexCheckDefault">
                닉네임 공개하지 않기
            </label>
        </div>

        <c:if test="${post.isUseAnonymousCity}">
            <c:set var="cityChecked" value="checked"/>
        </c:if>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="true" id="flexCheckChecked" name="isAnonCity" ${cityChecked}>
            <label class="form-check-label" for="flexCheckChecked">
                지역 공개하지 않기
            </label>
        </div>
    </div>

    <input type="hidden" name="postNo" value="${post.postNo}">
    <input type="hidden" name="_method" value="put">
    <input type="hidden" name="imgNo" value="1" id="selected-img-no">
    <button id="btn-post" type="submit" class="btn btn-success">게시하기</button>
</form>
<!-- 입력 창 -->

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


<script src="../../js/postCreate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>