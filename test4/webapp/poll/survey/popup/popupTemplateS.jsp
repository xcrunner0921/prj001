<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="seoul.research.poll.service.SurveyWebSearchVO" %>
<%@ page import="seoul.research.domain.SurveyWeb" %>
<%@ page import="com.sds.emp.common.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="anyframe.common.Page" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/surveyTemplate.css"/>

<%
	SurveyWeb surveyWeb = (SurveyWeb)request.getAttribute("surveyWeb");
	SurveyWebSearchVO surveyWebSearchVO = (SurveyWebSearchVO)request.getAttribute("surveyWebSearchVO");
	

	String popup_bgImg;
	String popup_btn;
	if(surveyWeb.getPollBgimg().equals("A")){
		popup_bgImg = "1";
		popup_btn = "btn_startSurvey01.gif";
	}else if(surveyWeb.getPollBgimg().equals("B")){
		popup_bgImg = "2";
		popup_btn = "btn_startSurvey02.gif";
	}else{
		popup_bgImg = "3";
		popup_btn = "btn_startSurvey03.png";
	}
%>
<script type="text/javascript">
	var userId 			= '<%= session.getAttribute("userId") %>';
	var Stopcond 		= '<%= surveyWeb.getPollStopcond() %>';
function questStart(){
	if(userId != "admin"){
		var userDelFlag = '<%=session.getAttribute("userDelFlag") %>';
		if("N" != userDelFlag){
			alert("회원님은 패널이 아니기 때문에 참여하실수 없습니다.");
			window.close();
			return;
		}
		if(Stopcond != "Y"){
			alert("참여하실수 없는 여론조사입니다.");
			window.close();
			return;
		}
	}
	document.frm.action = "<c:url value='/getSurveyQuest.do'/>";
    document.frm.submit();
}

</script>
</head>
<body oncontextmenu="return false"; ondragstart="return false"; onselectstart="return false"; >
<form name="frm" method="post">
<input type="hidden" name=pollIdx value="<%= surveyWeb.getPollIdx()%>" />
<input type="hidden" name="questNo" value="1" />
<input type="hidden" name="nextQuestNo" value="1" />
<div class="popup_wrap type<%= popup_bgImg %>">
    <div class="popup_header">
        <strong><img src="/img/poll/img_logoJoin.png" alt="서울시 여론조사 참여하기" /></strong>
    </div>
    <div class="popup_content bg2">
        <div class="txtWrap brBot">
            <ul class="surveyInfo">
                <li class="tit"><em>조 사 명 :</em> <%= surveyWeb.getPollTitle()%></li>
                <li><em>조사기간</em> : <%= surveyWeb.getPollTermstart()%> ~ <%= surveyWeb.getPollTermend()%></li>
                <li><em>안 내 문</em> : <%= surveyWeb.getPollIntro().replaceAll("\n", "<br />") %></li>
                <li><em>조사대상</em> : <%= surveyWeb.getPollTarget()%></li>
                <li><em>적 립 금</em> : 조사 완료 시 <%= surveyWeb.getPollMileage()%>점</li>
            </ul>
        </div>
        <div class="btnWrap">
            <a href="javascript:questStart();"><img src="/img/poll/<%= popup_btn %>" alt="조사참여하기" /></a>
        </div>
    </div>
</div>
</form>
</body>
</html>