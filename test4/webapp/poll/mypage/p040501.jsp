<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int gMenuIndex		= 4;
	int gSubMenuIndex	= 5;

	//탈퇴여부 
	String delYN = "N"; //Y:탈퇴 ,   N:가입
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/content.css"/>

<script type="text/javascript">
//로그인후 returnPage
function joinSeoul(){
	var currentPage = document.location.href; 
	document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&refresh_url=" + currentPage;
}

function joinPanel(args){

	if(args=="N" ){
		var chk = document.getElementById("agreeJoin").checked;
		if(!chk){
			alert("먼저 패널 가입에 동의해주시기 바랍니다.");
			return;
		}
	}
	document.panelForm.delflag.value = args;
	document.panelForm.action = "<c:url value='/panelDelflag.do' />";
    document.panelForm.submit();
}

</script>

</head>
<body>
<form  id="panelForm" name="panelForm">
<input type="hidden" name="delflag" />"
<div id="wrap">
    <!-- header -->
    <%@ include file="/include/top.jsp" %>
    <!-- //header -->

    <!-- container -->
<% 
if (userid.equals("")) { %>
    <div id="container" class="deleteMyInfo">
        <!-- snb -->
        <%@ include file="/include/panel/left.jsp" %>
        <!-- //snb -->
        <!-- content -->
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="/img/poll/tit_deleteMyInfo.gif" alt="패널탈퇴" /></h2>
            </div>
            <div class="article">
				<p>
                    <em class="emph">서울시 온라인 여론조사 패널의 정보는 서울시 홈페이지 회원 정보와 통합 관리</em>됩니다.<br />
                    정보를 확인 하시려면 먼저 서울시 통합회원 로그인 해주시기 바랍니다.<br />아래 로그인 버튼을 눌러주세요. 
                </p>
                <br /><br />
				<p><a href="javascript:joinSeoul();" class="btn_modifyMyInfo"><img src="/img/poll/btn_mypageJoinSeoul.gif" alt="통합회원 로그인" /></a></p>
                
 <%}else{ %> 
 	<div id="container" class="jonPanel">
        <!-- snb -->
        <%@ include file="/include/panel/left.jsp" %>
        <!-- //snb -->
        <!-- content -->
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="/img/poll/tit_deleteMyInfo.gif" alt="패널탈퇴" /></h2>
            </div>
            <div class="article">
<%
	if(!"".equals(session.getAttribute("userId"))){
%>    
                <p>
                    그동안 서울시 여론조사에 관심을 가져주셔서 감사합니다.<br />
                    향후 다시 패널활동을 원하시면 패널활동 동의 후 활동이 가능합니다.
                </p>            
		<%if ("N".equals(session.getAttribute("userDelFlag"))){ %>               
                <h3>온라인 패널 가입정보</h3>
                <table style="width:70%" class="tbl_view" summary="">
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
                <br /><a href="javascript:joinPanel('Y');" class="btn_deleteMyInfo"><img src="/img/poll/btn_deleteMyInfo.gif" alt="온라인 패널 탈퇴하기" /></a>
		<%}else{ %>
                <!-- table class="tbl_view" summary="">
                    <caption></caption>
                    <colgroup>
                        <col width="100%" />
                    </colgroup>
                    <tr>
                        <td class="noLine" align="center">서울시에서 운영하는 온라인 여론조사패널에 탈퇴상태입니다. </td>
                    </tr>
                </table-->
                <div class="box_joinChk joinNo">
                    <p>
                        <strong><em><%=session.getAttribute("userNm") %></em>님은 서울시에서 운영하는 온라인 여론조사패널에 <em>미가입상태</em>입니다.</strong><br />
                        서울시에서는 온라인 패널의 정보를 여론조사의 분석 목적을 위해서만 사용하며,개인적 의견이나 신상 정보를 유출하지 않으며 통계적 분석자료로만 사용합니다.
                    </p>
                    <p class="chkBox"><input type="checkbox" id="agreeJoin" name="agreeJoin" value="" /> <label for="agreeJoin">서울시에서 운영하는 온라인 여론조사패널에 가입하고 활동하는 것에 동의합니다.</label></p>
                    <a href="javascript:joinPanel('N');"><img src="/img/poll/btn_joinPanel02.gif" alt="패널 가입하기" /></a>
                </div>
        <%} 
 	}else{ %>
 		<p><em class="emph">패널정보를 확인하기 위해 서울특별시 홈페이지에 로그인을 해주시기 바랍니다.</em></p>
 		<br /> <br />
 		<p><a href="javascript:joinSeoul();" class="btn_modifyMyInfo"><img src="/img/poll/btn_mypageJoinSeoul.gif" alt="통합회원 로그인" /></a></p>
 <%		
 	}
        %>
              </div>
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
</form>
</html>