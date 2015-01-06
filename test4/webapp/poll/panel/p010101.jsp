<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri='/WEB-INF/anyframe-page.tld' prefix='anyframe' %>
 
<%
	int gMenuIndex		= 1;
	int gSubMenuIndex	= 1; 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/content.css"/>
</head>
<body>
<form id="frm" name="frm" action="">
<div id="wrap">
    <!-- header --> 
     <%@ include file="/include/top.jsp" %>
    <!-- //header -->

    <!-- container -->
    <div id="container" class="introJoinPanel">
        <!-- snb -->
        <%@ include file="/include/panel/left.jsp" %>
        <!-- //snb -->
        <!-- content -->
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="/img/poll/tit_introJoinPanel.gif" alt="패널가입 안내" /></h2>
            </div>
            <div class="article">
                <p class="txt_joinPanel">
                   
                    서울시에서는 시민 여러분의 목소리에 귀기울여 시민과 함께 만드는 시정을 구현하고자 시정 각 분야에 걸쳐 전화, 면접, 온라인, FGI(좌담회) 등 다양한 방법으로 여론조사를 실시하고 있습니다.
<br /><br />
                     <em class="emph">온라인 패널이란 이중 서울시에서 진행하는 온라인 여론조사에 참여의사를 밝히고 실명인증한 회원을 말합니다.</em><br />  <br />
                    서울시에서 진행하는 온라인 여론조사에 참여하실 시민들을 온라인 패널로 모집합니다.<br />
온라인 패널분들이 주신 의견이나 정보는 통계적 자료로만 사용되며, 응답자 개개인의 의견이나 신상정보는 법령에 의해 보호됩니다. 


                </p>
                <span class="wrap_btnJoin">
                    <a href="<%if (userid.equals("")) {out.print("http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?refresh_url=http://research.seoul.go.kr/poll/panel/p010101.jsp&amp;SITE_GB=GB011");}else{out.print("/poll/mypage/p040101.jsp");} %>"><img src="/img/poll/btn_joinPanel.gif" alt="패널가입하기" /></a>
                    <a href="javascript:gotojoin();"><img src="/img/poll/btn_joinSeoul.gif" alt="서울시 통합회원 가입하기" /></a>
                </span>
                <h3>온라인 패널 가입대상</h3>
                <p>서울, 경기, 인천 거주자 (만 14세 이상)</p>
                <h3>온라인 패널 혜택</h3>
                <p>
                    온라인 패널 가입 후 온라인 여론조사에 참여하면 소정의 마일리지가 지급됩니다.(300점 ~ 500점)<br />
                    적립한 마일리지로 문자메시지 발송, T-Money 포인트 전환, 도서문화상품권 신청 등에 이용하실 수 있습니다.
                </p>
                <h3>온라인 패널 활동절차</h3>
                <ol class="panelStep">
                    <li class="line3">
                        <strong><img src="/img/poll/img_step01.gif" alt="Step01" />패널가입</strong>
                        서울시 여론조사 홈페이지에서 온라인 패널에 가입합니다.<br />
                        (만약 서울시 통합회원이 아니면 통합회원 가입 후 패널가입이 가능합니다.)
                    </li>
                    <li>
                        <strong><img src="/img/poll/img_step02.gif" alt="Step02" />여론조사 공지</strong>
                        서울시 여론조사 홈페이지와 이메일을 통해 여론조사 실시정보를 확인합니다.
                    </li>
                    <li>
                        <strong><img src="/img/poll/img_step03.gif" alt="Step03" />여론조사 참여</strong>
                        서울시 여론조사 홈페이지에서 여론조사에 참여, 조사결과를 조회하실 수 있습니다.
                    </li>
                    <li class="stepLst">
                        <strong><img src="/img/poll/img_step04.gif" alt="Step04" />마일리지 적립</strong>
                        여론조사 완료 후 소정의 마일리지가 자동 적립됩니다.(정상적으로 참여완료시 1시간내 적립) 
                    </li>
                </ol>
            </div>
        </div>
        <!-- //content -->
    </div>
    <!-- //container -->

    <!-- footer -->
    <%@ include file="/include/footer.jsp" %>
    <!-- //footer -->
</div>

<script type="text/javascript">
//로그인후 returnPage
function joinSeoul(){
	var currentPage = document.location.href; 
	document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&amp;refresh_url=" + currentPage;
}

function gotojoin(){
	<%if (userid.equals("")) {%>
	if(confirm("서울시 통합회원 가입 및 실명인증 후 온라인 패널로 가입해 주세요.\n\n확인을 누르시면 서울시 통합회원 가입 화면으로 이동 합니다."))
	{
		window.open("http://www.seoul.go.kr/seoul/jsp/member2013/regist_01.jsp?SITE_GB=GB011","SEOUL");
	}
	<%}else{%>
	alert("귀하는 서울시 통합회원 가입되어 있습니다.");	
	<%}%>
}


</script>

</form>
<form name="frm2" id="frm2" method="post" action="">
<input type="hidden" name="userId" id="userId" />
</form>
</body>
</html>
