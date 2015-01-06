<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	int gMenuIndex		= 4;
	int gSubMenuIndex	= 4;
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
//서울시 로그인 
function joinSeoul(){ 
	var currentPage = document.location.href; 
	document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&amp;refresh_url=" + currentPage;
}

//정보수정
function modify(){
	window.open("https://www.seoul.go.kr/seoul/servlet/seoul?div=mem&amp;a=UserInfoViewRenewalApp","SEOUL");
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
    <div id="container" class="modifyMyInfo">
        <!-- snb -->
        <%@ include file="/include/panel/left.jsp" %>
        <!-- //snb -->
        <!-- content -->
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="/img/poll/tit_modifyMyInfo.gif" alt="패널정보수정" /></h2>
            </div>
<% if (userid.equals("")) { %>
			<div class="article">
				<p>
                    <em class="emph">서울시 온라인 여론조사 패널의 정보는 서울시 홈페이지 회원 정보와 통합 관리</em>됩니다.<br />
                    정보를 확인 하시려면 먼저 서울시 통합회원 로그인 해주시기 바랍니다.<br />아래 로그인 버튼을 눌러주세요. 
                </p>
                <br /><br />
				<p><a href="javascript:joinSeoul();" class="btn_modifyMyInfo"><img src="/img/poll/btn_mypageJoinSeoul.gif" alt="통합회원 로그인" /></a></p>
           </div>     
 <%}else{ %> 
 	<% if("N".equals(session.getAttribute("userDelFlag"))){	%>
 			<div class="article">  
                <p>
                    <em class="emph">서울시 온라인 여론조사 패널의 정보는 서울시 홈페이지 회원 정보와 통합 관리</em>됩니다.<br />
                    이메일, 거주시도 수정을 원하시면 서울시 홈페이지에서 회원정보 수정을 해주세요.
                </p>
                <h3>온라인 패널 가입정보</h3>
                <table class="tbl_view" summary="">
                    <caption></caption>
                    <colgroup>
                        <col width="18%" />
                        <col width="" />
                    </colgroup>
                    <tr>
                        <th scope="row">성명</th>
                        <td class="noLine"><%=session.getAttribute("userNm") %></td>
                    </tr>
                    <tr>
                        <th scope="row">아이디</th>
                        <td class="noLine"><%=session.getAttribute("userId") %></td>
                    </tr>
                    <tr>
                        <th scope="row">이메일</th>
                        <td class="noLine"><%=session.getAttribute("userEmail") %></td>
                    </tr>
                    <tr>
                        <th scope="row">거주시도</th>
                        <td class="noLine"><%=session.getAttribute("userSido") %></td>
                    </tr>
                </table>
                <a href="javascript:modify();" class="btn_modifyMyInfo"><img src="/img/poll/btn_modifyMyInfo.gif" alt="통합회원 정보수정하기" /></a>
		</div>
	<%}else{ %>
		<!-- 패널이 아닌경우  -->
		<div class="article">  
				<div class="box_joinChk joinNo">
	                     <h3>온라인 패널 가입정보</h3>
	                    <p>
	                        <strong><em><%=session.getAttribute("userNm") %></em>님은 서울시에서 운영하는 온라인 여론조사패널에 <font style="color:red">미가입상태</font>입니다.</strong><br />
	                    </p>
	                    <br /><br />
	                    <a href="/poll/mypage/p040101.jsp"><img src="/img/poll/btn_joinPanel02.gif" alt="패널 가입하기" /></a>
	                </div>
		</div>
	<%} %>
<%} %>                
          
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