<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri='/WEB-INF/anyframe-page.tld' prefix='anyframe' %> 
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.String" %>
<%@ page import="anyframe.common.Page" %>
<%@ page import="com.sds.emp.common.EmpUtil" %>
<%@ page import="com.sds.emp.domain.Code" %> 
<%@ include file="/include/useCheck.jsp" %>  
 
<script type="text/javascript" src="/include/jquery-latest.min.js"></script> 
<script type="text/javascript" src="/include/jquery-ui.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('.depth1Link').mouseenter(function(){
	    $('#header').height(222);
	    $(this).addClass('depth1_on');
	    $('.gnb').addClass('gnb_on');         
	});
	$('.depth1Link').mouseleave(function(){
	    $(this).removeClass('depth1_on');
	});
	$('#header').mouseleave(function(){	
	    $('#header').height(80);
	    $('.gnb').removeClass('gnb_on');
	});
    
}); 
</script>

<script type="text/javascript">
<!--
//로그인후 returnPage
function joinSeoulTOP(){
	var currentPage = document.location.href; 

	// 로그아웃이 아닌경우 
	var yChk = currentPage.indexOf("?");
	if(yChk == -1){
		//세션정보 생성 순서때문에 설문참여하기는 index로 이동시킨 후 설문참여하도록 한다. 
		if(currentPage == "http://research.seoul.go.kr/listSurveyWeb.do"){
			document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&amp;refresh_url=http://research.seoul.go.kr/";
		}else{
			document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&amp;refresh_url="+currentPage;
		}
	}else{
	// 로그아웃인경우 : ~~index.jsp?logout=Y
		var cLengh = currentPage.length;
		currentPage = currentPage.substring(0, yChk);
		document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&refresh_url="+currentPage;
	}
}
-->
</script>
<div id="header">
	<div class="inner_header">
		<h1 class="logo"><a href="/main.do"><img src="/img/poll/img_logo.gif" alt="서울시 여론조사 로고" /></a></h1>
		<div class="lnb">
			<%if (userid.equals("")) {%><a href="javascript:joinSeoulTOP();"><img src="/img/poll/link_login.gif" alt="로그인" /></a><span> | </span>
			<%}else{ %><a href="http://www.seoul.go.kr/seoul/servlet/seoul?a=UserLogOutApp&amp;div=mem&amp;SITE_GB=GB011&amp;sNextVal=http://research.seoul.go.kr/main.do?logout=Y"><img src="/img/poll/link_logout.gif" alt="로그아웃" /></a><span> | </span><%} %>
			<a href="http://www.seoul.go.kr/" target="_blank"><img src="/img/poll/btn_linkSeoul.gif" alt="서울시" /></a><span> | </span>
			<a href="/poll/sitemap.jsp"><img src="/img/poll/btn_sitemap.gif" alt="사이트맵" /></a>
		</div>
		<!-- gnb -->
		<div class="gnb">
			<ul>
				<li class="depth1">
					<a href="/poll/panel/p010101.jsp" class="depth1Link"><img src="/img/poll/gnb_introPanel.gif" alt="패널안내" /></a>
					<ul class="wrap_depth2">
						<li><a href="/poll/panel/p010101.jsp">패널가입안내</a></li>
						<!-- li><a href="/poll/panel/p010201.jsp">패널현황</a></li -->
		            </ul>
				</li>
				<li class="depth1">
					<a href="/listSurveyWeb.do" class="depth1Link"><img src="/img/poll/gnb_joinSurvey.gif" alt="조사참여" /></a>
					<ul class="wrap_depth2">
						<li><a href="/listSurveyWeb.do">진행중인 여론조사</a></li>
						<li><a href="/poll/survey/p020201.jsp">시정 여론조사 소개</a></li>
					</ul>
				</li>
					<li class="depth1">
						<a href="/listSurveyResult.do" class="depth1Link"><img src="/img/poll/gnb_surveyResult.gif" alt="조사결과" /></a>
						<ul class="wrap_depth2">
							<li><a href="/listSurveyResult.do">여론조사 결과</a></li>
						</ul>
				</li>
				<li class="depth1 gnb_mypage">
					<a href="/poll/mypage/p040101.jsp" class="depth1Link"><img src="/img/poll/gnb_myPage.gif" alt="마이페이지" /></a>
					<ul class="wrap_depth2">
						<li><a href="/poll/mypage/p040101.jsp">패널가입</a></li>
						<li><a href="/poll/mypage/p040201.jsp">마일리지 조회</a></li>
						<li><a href="/listMyPageJoin.do">내가 참여한 조사</a></li>
						<li><a href="/poll/mypage/p040401.jsp">패널정보수정</a></li>
						<!-- li><a href="/poll/mypage/p040501.jsp">패널탈퇴</a></li-->
					</ul>
				</li>
				<li class="depth1 gnb_board">
					<a href="/poll/board/p050101.jsp?bid=" class="depth1Link"><img src="/img/poll/gnb_board.gif" alt="게시판" /></a>
					<ul class="wrap_depth2">
						<li><a href="/poll/board/p050101.jsp?bid=">공지사항</a></li>
						<li><a href="/poll/board/p050201.jsp">자주하는 질문</a></li>
						<li><a href="/poll/board/p050301.jsp">질문과 답변</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- //gnb -->
    </div>
</div>
