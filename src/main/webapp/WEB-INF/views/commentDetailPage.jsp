<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/07/27
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% request.setAttribute("mainPage", request.getContextPath() + "/main/page"); %>
<% request.setAttribute("logout", request.getContextPath() + "/login"); %>
<% request.setAttribute("realTimePosts", request.getContextPath() + "/posts?type=1"); %>
<% request.setAttribute("daysMostPosts", request.getContextPath() + "/posts?type=2"); %>
<% request.setAttribute("weeksMostPosts", request.getContextPath() + "/posts?type=3"); %>
<% request.setAttribute("searchPosts", request.getContextPath() + "/posts?type=4"); %>

<% request.setAttribute("myPage", request.getContextPath() + "/my/page"); %>

<% request.setAttribute("post", request.getContextPath() + "/post"); %>
<% request.setAttribute("postCreatePage", request.getContextPath() + "/post/page/1"); %>

<% request.setAttribute("myPage", request.getContextPath() + "/my/page"); %>

<html>
<head>
    <title>Behind</title>
    <link rel="stylesheet" type="text/css" href= "../../css/commentDetail.css"/>
    <link rel="stylesheet" type="text/css" href= "../../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"/>
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

<!-- 글 상세보기 페이지로 돌아가기 버튼 -->
<button id="btn-return-post" class="btn btn-dark btn-sm" onclick="location.href='/post?no=${comment.postNo}'">
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
    </svg>
    &nbsp
    "${parentPost}" (으)로 돌아가기
</button>
<!-- 글 상세보기 페이지로 돌아가기 버튼 -->

<!-- 삭제, 신고 버튼 -->
<div id="btn-crud">
    <c:set var="profile" value="${sessionScope.get('profile')}"/>
    <c:if test="${comment.accountNo eq profile.accountNo}">
        <form class="display-inline-block" method="get" action="/comment/page/1">
            <input type="hidden" name="commentNo" value="${comment.commentNo}" />
            <button type="submit" class="btn btn-outline-dark btn-sm">✒️</button>️
        </form>

        <form class="display-inline-block" method="post" action="/comment">
            <input type="hidden" name="_method" value="delete" />
            <input type="hidden" name="commentNo" value="${comment.commentNo}" />
            <input type="hidden" name="postNo" value="${comment.postNo}" />
            <button type="submit" class="btn btn-outline-dark btn-sm">🗑</button>
        </form>
    </c:if>

    <form action="/report/page" method="get" id="form-report" class="display-inline-block">
        <button class="btn btn-outline-dark btn-sm">
            <input type="hidden" name="reportType" value="comment">
            <input type="hidden" name="no" value="${comment.commentNo}">⚠</button>
    </form>
</div>
<!-- 삭제, 신고 버튼 -->

<!-- 댓글 본문 -->
<div id="comment-wrapper" >
    <ul class="list-group text-center">
        <li class="list-group-item border border-dark border-3">
            <h1 id="comment-text" class="text-center">
                ${comment.content}
            </h1>
            <div id="info-commenter">
                <span class="badge rounded-pill bg-dark">${comment.nickname}</span>
                <span class="badge rounded-pill bg-dark">${comment.commentedDate}</span>
            </div>
        </li>
    </ul>
</div>
<!-- 댓글 본문 -->

<!-- 대댓글 조회 -->
<ul id="reply-list" class="list-group list-group-flush">
    <c:forEach var="reply" items="${replies}">
        <li class="list-group-item display-inline-block">
            <div class="display-inline-block" id="wrap-reply-content">
                <label class="text-secondary reply-item">${reply.nickname}</label><br>
                <span style="margin-left: 1%;">${reply.content}</span>
            </div>

            <c:if test="${sessionScope.get('profile').accountNo eq reply.accountNo}">
                <form action="/reply/page/1" method="get" class="display-inline-block">
                    <input type="hidden" name="replyNo" value="${reply.replyNo}">
                    <button type="submit" class="btn btn-outline-dark btn-sm">✒️</button>
                </form>

                <form action="/reply" method="post" class="display-inline-block">
                    <input type="hidden" name="_method" value="delete">
                    <input type="hidden" name="replyNo" value="${reply.replyNo}">️
                    <input type="hidden" name="commentNo" value="${comment.commentNo}">
                    <button type="submit" class="btn btn-outline-dark btn-sm">🗑️</button>
                </form>
            </c:if>

            <form action="/report/page" method="get" class="display-inline-block">
                <input type="hidden" name="reporterProfile" value="${sessionScope.get('profile')}">
                <input type="hidden" name="reportType" value="reply">
                <input type="hidden" name="no" value="${reply.replyNo}">
                <button type="submit" class="btn btn-outline-dark btn-sm">⚠️</button>
            </form>
        </li>
    </c:forEach>
</ul>
<!-- 대댓글 조회 -->


<!-- 대댓글 입력 폼 -->
<form class="center-content margin-top" action="/reply" method="post">
    <div class="form-check">
        <input class="form-check-input" type="checkbox" id="isAnonymous" name="nameAnonymous">
        <label class="form-check-label" for="isAnonymous">
            익명으로 게시하기
        </label>
    </div>
    <div class="input-group text-center" style="width: 100%">
        <input type="hidden" name="commentNo" value="${comment.commentNo}">
        <input type="text" class="form-control" placeholder="대댓글을 입력해주세요!" name="content" >
        <button class="btn btn-outline-secondary" type="submit" id="btn-comment-submit">등록</button>
    </div>
</form>
<!-- 대댓글 입력 폼 -->


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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

