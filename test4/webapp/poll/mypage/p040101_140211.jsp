<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.*" %>
<%
	int gMenuIndex		= 4;
	int gSubMenuIndex	= 1; 
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
//만 나이 구하기 
function computeAge(birthday) { 
	var bday=parseInt(birthday.substring(6,8)); 
	var bmo=(parseInt(birthday.substring(4,6))-1); 
	var byr=parseInt(birthday.substring(0,4)); 
	var now = new Date(); 
	tday=now.getDate(); 
	tmo=(now.getMonth()); 
	tyr=(now.getFullYear()); 

	if((tmo > bmo)||(tmo==bmo & tday>=bday)){ 
		age=byr 
	}else{ 
		age=byr+1; 
	} 
	return tyr-age; 
}



//패널가입 
function joinPanel(args){
	var birth = '<%=session.getAttribute("userBirthDay")%>';
	var age = computeAge(birth);	//만 나이
	if(age < 14){
		alert("만 14세 미만 회원은 패널가입이 제한됩니다.");
		return;
	}

	//2013.10.10.추가 : 연령대
	var ageGroup = "";
	if(age>=14 && age<19){
		ageGroup = "10대";
	}else if(age>=19 && age<30){
		ageGroup = "20대";
	}else if(age>=30 && age<40){
		ageGroup = "30대";
	}else if(age>=40 && age<50){
		ageGroup = "40대";
	}else if(age>=50 && age<60){
		ageGroup = "50대";
	}else if(age>=60 && age<130){
		ageGroup = "60대 이상";
	}else{
		ageGroup = "기타";
	}

	//시도:서울.경기.인천 이외의 지역은 제한됨
	var sido = '<%=session.getAttribute("userSido")%>';
	if("서울" == sido || "경기" == sido || "인천" == sido ){
		
	}else{
		if("" != '<%=session.getAttribute("userId")%>'){
			alert("서울.경기.인천 지역 이외의 회원은 패널가입이 제한됩니다.\n\n서울시 로그인 후 회원 정보의 거주지를 설정해 주시기 바랍니다.");
			return;
		}
	}
	
	if(args=="N" ){
		var chk = document.getElementById("agreeJoin").checked;
		if(!chk){
			alert("먼저 패널 가입에 동의해주시기 바랍니다.");
			return;
		}
	}
	document.panelForm.age.value = age;
	document.panelForm.ageGroup.value = ageGroup;
	document.panelForm.delflag.value = args;
	document.panelForm.action = "<c:url value='/panelDelflag.do' />";
    document.panelForm.submit();
}

//서울시 로그인 
function joinSeoul(){
	var currentPage = "http://research.seoul.go.kr/main.do";//document.location.href; 
	document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&amp;refresh_url=" + currentPage;
}
-->
</script>
     
</head>
<body>
<form  id="panelForm" name="panelForm" action="">
<input type="hidden" name="delflag" />
<input type="hidden" name="age" />
<input type="hidden" name="ageGroup" />
<div id="wrap">
    <!-- header -->
    <%@ include file="/include/top.jsp" %>
    <!-- //header -->

    <!-- container -->
    <div id="container" class="jonPanel">
        <!-- snb --> 
        <%@ include file="/include/panel/left.jsp" %> 
        <!-- //snb -->
        <!-- content -->
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="/img/poll/tit_joinPanel.gif" alt="패널가입" /></h2>
            </div>
            <div class="article">
 
<%if (userid.equals("")) { %>

				<p>
		                    서울특별시 여론조사 포털 사이트에 방문해 주셔서 감사합니다.<br />
                    <em class="emph">본 홈페이지는 서울특별시 회원 아이디로 통합 관리</em>되므로 서울특별시 홈페이지에 로그인 후 서울시 온라인 패널에 등록할 수 있습니다. 서울시 회원이 아닌 분은 서울시 홈페이지 회원 가입 후 패널에 등록하시면 됩니다.
                </p>
                <h3>온라인 패널 가입대상</h3>
                <p>서울, 경기, 인천 거주자 (만 14세 이상)</p>
                <br /><br />
				<p><a href="javascript:joinSeoul();" class="btn_modifyMyInfo"><img src="/img/poll/btn_mypageJoinSeoul.gif" alt="통합회원 로그인" /></a></p>
<%}else{     

	if(!"Y".equals(real_check_falg)){	%> 
	 		<!-- 실명인증 하지 않은 경우  -->
				 <div class="box_joinChk joinNo">
	                    <p>
	                        <strong><em>귀하는 서울시 통합회원 가입 후 실명인증을 진행하지 않으셨습니다.</em></strong><br />
	                        여론조사 결과의 객관성 및 신뢰성을 위해 실명인증을 한 회원을 대상으로 온라인 패널을 모집하고 있습니다.<br />
	                      실명인증을 진행한 후 온라인 패널가입을 진행해 주세요. (20)<br />
	                    </p>
	                    <a href="https://www.seoul.go.kr/seoul/servlet/seoul?div=mem&a=UserInfoViewRenewalApp&sNextVal=jsp%2Fmyseoul%2Fmyseoul_auth.jsp&pageTitle=1" target="_blank">
	                    <img src="/img/poll/btn_realcheck.gif" alt="실명 인증하기" width="141" height="53" /></a>
	                </div>	    
	
	<%}else{%>
	<!-- 실명인증 된 경우  -->	
	                
		<% if("N".equals(session.getAttribute("userDelFlag"))){	%>               
                <h3>온라인 패널 가입여부</h3>              
                <div class="box_joinChk joinNo">
                    <p>
                        <strong><em><%=session.getAttribute("userNm") %></em>님은 서울시에서 운영하는 온라인 여론조사패널에 <em>가입상태</em>입니다.</strong><br />
                        서울시에서는 온라인 패널의 정보를 여론조사의 분석 목적을 위해서만 사용하며,개인적 의견이나 신상 정보를 유출하지 않으며 통계적 분석자료로만 사용합니다.
                    <br />
                    </p>
                    <p class="chkBox">서울시 온라인 패널 탈퇴를 원하시는 분은 아래 탈퇴하기 버튼을 이용하시기 바랍니다</p>
                    <a href="javascript:joinPanel('Y');" class="btn_deleteMyInfo"><img src="/img/poll/btn_deleteMyInfo.gif" alt="온라인 패널 탈퇴하기" /></a>
                </div>
		<%	}else{%>
	  		
	     
		<!-- 실명인증 되었지만  성별이 이상한 경우 실명인증 다시 체크 -->
			<%if (!"1".equals(session.getAttribute("userMf")) && !"2".equals(session.getAttribute("userMf"))){ %>
					<div class="box_joinChk joinNo">
			                   <p>
			                       <strong><em>귀하는 서울시 통합회원 가입 후 실명인증을 진행하지 않으셨습니다.</em></strong><br />
			                       여론조사 결과의 객관성 및 신뢰성을 위해 실명인증을 한 회원을 대상으로 온라인 패널을 모집하고 있습니다.<br />
			            귀하의 정보를 다시 한 번 확인해 주시기 바랍니다. <br />
			                     실명인증을 진행한 후 온라인 패널가입을 진행해 주세요. (30)<br />
			                   </p>
			                   <a href="https://www.seoul.go.kr/seoul/servlet/seoul?div=mem&a=UserInfoViewRenewalApp&sNextVal=jsp%2Fmyseoul%2Fmyseoul_auth.jsp&pageTitle=1" target="_blank">
			                   <img src="/img/poll/btn_realcheck.gif" alt="실명 인증하기" width="141" height="53" /></a>
				    </div>
			<%}else if (session.getAttribute("userBirthDay") == null) { %>
	
	                <div class="box_joinChk joinNo">
			                   <p>
			                       <strong><em>귀하는 서울시 통합회원 가입 후 실명인증을 진행하지 않으셨습니다.</em></strong><br />
			                       여론조사 결과의 객관성 및 신뢰성을 위해 실명인증을 한 회원을 대상으로 온라인 패널을 모집하고 있습니다.<br />
			            귀하의 정보를 다시 한 번 확인해 주시기 바랍니다. <br />
			                     실명인증을 진행한 후 온라인 패널가입을 진행해 주세요. (30)<br />
			                   </p>
			                   <a href="https://www.seoul.go.kr/seoul/servlet/seoul?div=mem&a=UserInfoViewRenewalApp&sNextVal=jsp%2Fmyseoul%2Fmyseoul_auth.jsp&pageTitle=1" target="_blank">
			                   <img src="/img/poll/btn_realcheck.gif" alt="실명 인증하기" width="141" height="53" /></a>
				    </div>
			<%}else{ %>
	
	                <div class="box_joinChk joinNo">
	                    <p>
	                        <strong><em><%=session.getAttribute("userNm") %></em>님은 서울시에서 운영하는 온라인 여론조사패널에 <em>미가입상태</em>입니다.</strong><br />
	                        서울시에서는 온라인 패널의 정보를 여론조사의 분석 목적을 위해서만 사용하며,개인적 의견이나 신상 정보를 유출하지 않으며 통계적 분석자료로만 사용합니다.
	                    </p>
	                    <p class="chkBox"><input type="checkbox" id="agreeJoin" name="agreeJoin" value="" /> <label for="agreeJoin"><em>서울시에서 운영하는 온라인 여론조사패널에 가입하고 활동하는 것에 동의합니다.</em></label></p>
	                    <a href="javascript:joinPanel('N');"><img src="/img/poll/btn_joinPanel02.gif" alt="패널 가입하기" /></a>
	                </div>
	        <%} %>
	        
		  <%}%>
		   
				
	 	<%} %>
	 	
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
</form>
</body>
</html>