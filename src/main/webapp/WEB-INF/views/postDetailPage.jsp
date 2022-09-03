<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/07/19
  Time: 12:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("indexPage", request.getContextPath() + "/"); %>
<% request.setAttribute("mainPage", request.getContextPath() + "/main/page"); %>
<% request.setAttribute("realTimePosts", request.getContextPath() + "/posts?type=1"); %>
<% request.setAttribute("daysMostPosts", request.getContextPath() + "/posts?type=2"); %>
<% request.setAttribute("weeksMostPosts", request.getContextPath() + "/posts?type=3"); %>
<% request.setAttribute("postCreatePage", request.getContextPath() + "/post/page/1"); %>
<% request.setAttribute("postModifyPage", request.getContextPath() + "/post/page/3"); %>

<% request.setAttribute("myPage", request.getContextPath() + "/my/page"); %>

<% request.setAttribute("like", request.getContextPath() + "/like"); %>
<% request.setAttribute("entryComment", request.getContextPath() + "/comment"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Behind</title>
    <link rel="stylesheet" type="text/css" href= "../../css/postDetail.css"/>
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


<!-- 글 제목 -->
<br><br><br><br>
<div class="title-and-nicname">
    <h2 id="post-title"> ${post.title} </h2>
    <h6 id="poster-name">
        <label style="font-size: 130%"> 작성자 : ${post.nicname}</label><br>
        <label class="" style="padding-top: 0.5%;">지역 : ${posterCity} </label><br>
        <label class="" style="padding-top: 0.5%;">작성 일시 : ${post.postedDate}</label>
    </h6>
</div>
<!-- 글 제목 -->


<!-- 글 본문 -->
<div id="post-wrapper" class="bg-dark">
    <div class="text-center">
        <img class="inline-block" src="../../pictures/origin/${post.pictureNo}.jpeg" id="post-img"
             oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onkeydown="return false">
        <div class="inline-block text-center" id="post-content">
            <c:if test="${post ne null}">
                ${post.content}
            </c:if>
        </div>
    </div>
</div>
<!-- 글 본문 -->

<div id="label-wrapper">
    <label id="label-comment-count" class="badge rounded-pill bg-dark"> 댓글 ${comments.size()}개</label> /
    <label id="label-like-count" class="badge rounded-pill bg-dark"> 좋아요 ${post.likeCount}개</label>
</div>

<!-- 좋아요, 신고하기 버튼 -->
<div class="text-right inline-block" style="width: 59%; padding-top: 1%">
    <c:set var="profile" value="${sessionScope.get('profile')}"/>
    <form action="${postModifyPage}" method="get" id="form-modify">
        <input type="hidden" value="${post.postNo}" name="postNo">
        <c:if test="${post.accountNo eq profile.accountNo}">
            <button type="submit" class="btn btn-outline-success btn-sm">수정하기</button>
        </c:if>
    </form>
    <form action="/report/page" method="get" id="form-report">
        <input type="hidden" name="reportType" value="post">
        <input type="hidden" name="no" value="${post.postNo}">
        <button class="btn btn-outline-danger btn-sm">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-emoji-dizzy" viewBox="0 0 16 16">
                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                <path d="M9.146 5.146a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708.708l-.647.646.647.646a.5.5 0 0 1-.708.708l-.646-.647-.646.647a.5.5 0 1 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 0-.708zm-5 0a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 1 1 .708.708l-.647.646.647.646a.5.5 0 1 1-.708.708L5.5 7.207l-.646.647a.5.5 0 1 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 0-.708zM10 11a2 2 0 1 1-4 0 2 2 0 0 1 4 0z"/>
            </svg>
            신고하기
        </button>
    </form>
    <form action="${like}" method="post" id="form-like">
        <input type="hidden" name="postNo" value="${post.postNo}">
        <input type="hidden" name="accountNo" value="${sessionScope.get("profile").accountNo}">
        <c:if test="${isLike eq true}">
        <button class="btn btn-primary btn-sm">
            </c:if>
            <c:if test="${isLike ne true}">
            <button class="btn btn-outline-primary btn-sm">
                </c:if>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-hand-thumbs-up" viewBox="0 0 16 16">
                    <path d="M8.864.046C7.908-.193 7.02.53 6.956 1.466c-.072 1.051-.23 2.016-.428 2.59-.125.36-.479 1.013-1.04 1.639-.557.623-1.282 1.178-2.131 1.41C2.685 7.288 2 7.87 2 8.72v4.001c0 .845.682 1.464 1.448 1.545 1.07.114 1.564.415 2.068.723l.048.03c.272.165.578.348.97.484.397.136.861.217 1.466.217h3.5c.937 0 1.599-.477 1.934-1.064a1.86 1.86 0 0 0 .254-.912c0-.152-.023-.312-.077-.464.201-.263.38-.578.488-.901.11-.33.172-.762.004-1.149.069-.13.12-.269.159-.403.077-.27.113-.568.113-.857 0-.288-.036-.585-.113-.856a2.144 2.144 0 0 0-.138-.362 1.9 1.9 0 0 0 .234-1.734c-.206-.592-.682-1.1-1.2-1.272-.847-.282-1.803-.276-2.516-.211a9.84 9.84 0 0 0-.443.05 9.365 9.365 0 0 0-.062-4.509A1.38 1.38 0 0 0 9.125.111L8.864.046zM11.5 14.721H8c-.51 0-.863-.069-1.14-.164-.281-.097-.506-.228-.776-.393l-.04-.024c-.555-.339-1.198-.731-2.49-.868-.333-.036-.554-.29-.554-.55V8.72c0-.254.226-.543.62-.65 1.095-.3 1.977-.996 2.614-1.708.635-.71 1.064-1.475 1.238-1.978.243-.7.407-1.768.482-2.85.025-.362.36-.594.667-.518l.262.066c.16.04.258.143.288.255a8.34 8.34 0 0 1-.145 4.725.5.5 0 0 0 .595.644l.003-.001.014-.003.058-.014a8.908 8.908 0 0 1 1.036-.157c.663-.06 1.457-.054 2.11.164.175.058.45.3.57.65.107.308.087.67-.266 1.022l-.353.353.353.354c.043.043.105.141.154.315.048.167.075.37.075.581 0 .212-.027.414-.075.582-.05.174-.111.272-.154.315l-.353.353.353.354c.047.047.109.177.005.488a2.224 2.224 0 0 1-.505.805l-.353.353.353.354c.006.005.041.05.041.17a.866.866 0 0 1-.121.416c-.165.288-.503.56-1.066.56z"/>
                </svg>
                좋아요
            </button>
    </form>
</div>
<!-- 좋아요, 신고하기 버튼 -->
<hr>

<!-- 댓글 -->
<div class="">
    <ul id="ul-comments" class="list-group list-group-flush">
        <c:forEach var="comment" items="${comments}">
            <li class="list-group-item">
                <span class="commenter-nicname">${comment.nicname}</span><br>
                <span class="comment-content"> ${comment.content} </span>
                <button id="btn-expand-reply" class="btn btn-sm" onclick="location.href='/comment?no=${comment.commentNo}'">➥</button>
            </li>
        </c:forEach>
        <button type="button" class="btn btn-dark" id="btn-more-comment">더 보기</button>
    </ul>
    <form style="margin-top: 2%" action="${entryComment}" method="post">
        <div class="form-check">
            <input class="form-check-input" type="checkbox" id="isAnonymous" name="nameAnonymous">
            <label class="form-check-label" for="isAnonymous">
                익명으로 게시하기
            </label>
        </div>
        <div class="input-group text-center">
            <input type="hidden" name="postNo" value="${post.postNo}">
            <input type="hidden" name="accountNo" value="${sessionScope.get("profile").accountNo}">
            <input type="text" class="form-control" placeholder="댓글을 입력해주세요!" name="content" >
            <button class="btn btn-outline-secondary" type="submit" id="btn-comment-submit">등록</button>
        </div>
    </form>
</div>
<!-- 댓글 -->


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


<script src="../../js/postDetail.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>