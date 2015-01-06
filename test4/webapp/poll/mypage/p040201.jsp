<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int gMenuIndex		= 4;
	int gSubMenuIndex	= 2;
	
	//마일리지 조회 
	String isrc = "http://mileage.seoul.go.kr/main/MyInfoSub.do";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/content.css"/>

<script type="text/javascript">
<!--
//로그인후 returnPage
function joinSeoul(){
	var currentPage = document.location.href; 
	document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&amp;refresh_url=" + currentPage;
}
-->
</script>

</head>
<body>
<div id="wrap">
    <!-- header -->
    <%@ include file="/include/top.jsp" %>
    <!-- //header -->

    <!-- container -->
    <div id="container" class="myMileage">
        <!-- snb -->
        <%@ include file="/include/panel/left.jsp" %>
        <!-- //snb -->
        <!-- content -->
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="/img/poll/tit_myMileage.gif" alt="마일리지조회" /></h2>
            </div>
            <div class="article">
				<%if (userid.equals("")) {%>
                 <h3><em class="emph">서울시 통합정보 시스템에 로그인 후 확인이 가능합니다. </em></h3>
                <br /><br />
                <p><a href="javascript:joinSeoul();" class="btn_modifyMyInfo"><img src="/img/poll/btn_mypageJoinSeoul.gif" alt="통합회원 로그인" /></a></p>
                <%}else{ %>
                <iframe width="720" height="660" src="<%=isrc%>" frameborder="0"></iframe>
                <%} %>
            </div>
        </div>
        <!-- //content -->
    </div>
    <!-- //container -->

    <!-- footer -->
    <%@ include file="/include/footer.jsp" %>
    <!-- //footer -->
</div>
</body>
</html>