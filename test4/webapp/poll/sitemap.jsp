<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/common.css" />
<link rel="stylesheet" type="text/css" href="/css/content.css" />

</head>
<body>
<div id="wrap">
    <!-- header -->
     <%@ include file="/include/top.jsp" %>
    <!-- //header -->

    <!-- container -->
    <div id="container" class="sitemap">
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="/img/poll/tit_sitemap.gif" alt="사이트맵" /></h2>
            </div>
            <div class="article">
                <ul class="allMenu">
                    <li class="menu1">
                        <a href="/poll/panel/p010101.jsp"><img src="/img/poll/tit_sitemapIntroPanel.gif" alt="패널안내" /></a>
                        <ul>
                            <li><a href="/poll/panel/p010101.jsp">패널가입안내</a></li>
                            <li><a href="/poll/panel/p010201.jsp">패널현황</a></li>
                        </ul>
                    </li>
                    <li class="menu1">
                        <a href="/listSurveyWeb.do"><img src="/img/poll/tit_sitemapJoinSurvey.gif" alt="조사참여" /></a>
                        <ul>
                            <li><a href="/listSurveyWeb.do">진행중인 여론조사</a></li>
                            <li><a href="/poll/survey/p020201.jsp">시정 여론조사 소개</a></li>
                        </ul>
                    </li>
                    <li class="menu1">
                        <a href="/listSurveyResult.do"><img src="/img/poll/tit_sitemapSurveyResult.gif" alt="조사결과" /></a>
                        <ul>
                            <li><a href="/listSurveyResult.do">여론조사 결과</a></li>
                        </ul>
                    </li>
                    <li class="menu1">
                        <a href="/poll/mypage/p040101.jsp"><img src="/img/poll/tit_sitemapMyPage.gif" alt="마이페이지" /></a>
                        <ul>
                            <li><a href="/poll/mypage/p040101.jsp">패널가입</a></li>
							<li><a href="/poll/mypage/p040201.jsp">마일리지 조회</a></li>
							<li><a href="/listMyPageJoin.do">내가 참여한 조사</a></li>
							<li><a href="/poll/mypage/p040401.jsp">패널정보수정</a></li>
							<!-- li><a href="/poll/mypage/p040501.jsp">패널탈퇴</a></li-->
                        </ul>
                    </li>
                    <li class="menu1">
                        <a href="/poll/board/p050101.jsp?bid="><img src="/img/poll/tit_sitemapBoard.gif" alt="게시판" /></a>
                        <ul>
							<li><a href="/poll/board/p050101.jsp?bid=">공지사항</a></li>
							<li><a href="/poll/board/p050201.jsp">자주하는 질문</a></li>
							<li><a href="/poll/board/p050301.jsp">질문과 답변</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //container -->

    <!-- footer -->
    <%@ include file="/include/footer.jsp" %>
    <!-- //footer -->
</div>
</body>
</html>