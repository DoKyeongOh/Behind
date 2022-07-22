<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/07/19
  Time: 12:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("mainPage", request.getContextPath() + "/main/page"); %>
<% request.setAttribute("realTimePosts", request.getContextPath() + "/posts?type=1"); %>

<% request.setAttribute("like", request.getContextPath() + "/like"); %>
<% request.setAttribute("entryComment", request.getContextPath() + "/comment"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: dokyeongoh
  Date: 2022/07/19
  Time: 12:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("mainPage", request.getContextPath() + "/main/page"); %>
<% request.setAttribute("realTimePosts", request.getContextPath() + "/posts?type=1"); %>

<% request.setAttribute("like", request.getContextPath() + "/like"); %>
<% request.setAttribute("entryComment", request.getContextPath() + "/comment"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
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

<!-- 글 제목 -->
<br><br><br><br>
<h2 id="post-title"> ${post.title} </h2>
<!-- 글 제목 -->


<!-- 글 본문 -->
<div id="post-wrapper" class="bg-dark">
    <div class="text-center">
        <img class="inline-block" src="../../pictures/origin/1.jpeg" id="post-img">
        <div class="inline-block text-center" id="post-content">
            <c:if test="${post ne null}">
                ${post.content}
            </c:if>
        </div>
    </div>
</div>
<!-- 글 본문 -->

<h3 id="post-comments" class="inline-block"> 댓글 </h3>

<!-- 좋아요, 신고하기 버튼 -->
<div class="text-right inline-block" style="width: 92%;">
    <form action="/report/page" method="get" id="form-report">
        <button class="btn btn-outline-danger">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-flag" viewBox="0 0 16 16">
                <path d="M14.778.085A.5.5 0 0 1 15 .5V8a.5.5 0 0 1-.314.464L14.5 8l.186.464-.003.001-.006.003-.023.009a12.435 12.435 0 0 1-.397.15c-.264.095-.631.223-1.047.35-.816.252-1.879.523-2.71.523-.847 0-1.548-.28-2.158-.525l-.028-.01C7.68 8.71 7.14 8.5 6.5 8.5c-.7 0-1.638.23-2.437.477A19.626 19.626 0 0 0 3 9.342V15.5a.5.5 0 0 1-1 0V.5a.5.5 0 0 1 1 0v.282c.226-.079.496-.17.79-.26C4.606.272 5.67 0 6.5 0c.84 0 1.524.277 2.121.519l.043.018C9.286.788 9.828 1 10.5 1c.7 0 1.638-.23 2.437-.477a19.587 19.587 0 0 0 1.349-.476l.019-.007.004-.002h.001M14 1.221c-.22.078-.48.167-.766.255-.81.252-1.872.523-2.734.523-.886 0-1.592-.286-2.203-.534l-.008-.003C7.662 1.21 7.139 1 6.5 1c-.669 0-1.606.229-2.415.478A21.294 21.294 0 0 0 3 1.845v6.433c.22-.078.48-.167.766-.255C4.576 7.77 5.638 7.5 6.5 7.5c.847 0 1.548.28 2.158.525l.028.01C9.32 8.29 9.86 8.5 10.5 8.5c.668 0 1.606-.229 2.415-.478A21.317 21.317 0 0 0 14 7.655V1.222z"/>
            </svg>
            신고하기
        </button>
    </form>
    <form action="${like}" method="post" id="form-like">
        <input type="hidden" name="postNo" value="${post.postNo}">
        <input type="hidden" name="accountNo" value="${sessionScope.get("profile").accountNo}">
        <c:if test="${isLike eq true}">
            <button class="btn btn-primary">
        </c:if>
        <c:if test="${isLike ne true}">
            <button class="btn btn-outline-primary">
        </c:if>

                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star" viewBox="0 0 16 16" style="margin-bottom: 7%;">
                    <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"/>
                </svg>
                좋아요
            </button>
    </form>
    <label id="label-comment-count"> 댓글 ${comments.size()}개</label> ・
    <label id="label-like-count"> 좋아요 ${post.likeCount}개</label>
</div>
<!-- 좋아요, 신고하기 버튼 -->


<!-- 댓글 -->
<div class="comment-wrapper">
    <ul class="list-group">
        <c:forEach var="comment" items="${comments}">
            <li class="list-group-item">
                <c:if test="${comment.isUseAnonymousName ne true}">
                    ${comment.nicname}
                </c:if>
                <c:if test="${comment.isUseAnonymousName eq true}">
                    익명
                </c:if>
                : ${comment.content}
                <button class="btn btn-outline-secondary btn-sm" onclick="location.href='/comment?no=${comment.commentId}'">자세히보기</button>
            </li>
        </c:forEach>
    </ul>
    <form class="" action="${entryComment}" method="post">
        <div class="input-group text-center">
            <input type="hidden" name="postNo" value="${post.postNo}">
            <input type="hidden" name="accountNo" value="${sessionScope.get("profile").accountNo}">
            <input type="text" class="form-control" placeholder="댓글을 입력해주세요!" name="content" >
            <button class="btn btn-outline-secondary" type="submit" id="btn-comment-submit">등록</button>
        </div>

        <div class="form-check">
            <input class="form-check-input" type="checkbox" id="isAnonymous" name="isUseAnonymousName">
            <label class="form-check-label" for="isAnonymous">
                익명으로 게시하기
            </label>
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


<script src="../../js/mainPage.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>