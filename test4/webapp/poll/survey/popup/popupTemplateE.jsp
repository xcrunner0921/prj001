

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="seoul.research.poll.service.SurveyWebSearchVO" %>
<%@ page import="seoul.research.domain.SurveyWeb" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/surveyTemplate.css"/>
<%@ include file="/include/useCheck.jsp" %>  
<%
	SurveyWeb questExitTitle = (SurveyWeb)request.getAttribute("questExitTitle");

	String title 	= questExitTitle.getPollTitle();
	String message 	= questExitTitle.getPollMessage().replaceAll("\n", "<br />");
	
	String pollMileage	= "";
	String serviceId 	= "";
	String type			= "I";
	int pollIdx 		= 0;
	String userId 		= "";
	String eTitle 		= "";
	
	pollMileage	= Integer.toString(questExitTitle.getPollMileage());
	pollIdx		= questExitTitle.getPollIdx();
	eTitle 		= java.net.URLEncoder.encode(title);
	userId		= (String) session.getAttribute("userId");
	
	if("300".equals(pollMileage)){
		serviceId = "P2101T76";
	}else if("400".equals(pollMileage)){
		serviceId = "P2101T77";
	}else if("500".equals(pollMileage)){
		serviceId = "P2101T78";
	}
	
	//System.out.println("===========serviceId============ : " + serviceId);
	//System.out.println("===========pollIdx============ : " + pollIdx);
	//System.out.println("===========userId============1 : " + userId);
	//System.out.println("===========userid============2 : " + (String) session.getAttribute("userid"));
	//System.out.println("===========title============ : " + title);
	
	String popup_bgImg;
	String popup_btn;
	if(questExitTitle.getPollBgimg().equals("A")){
		popup_bgImg = "1";
		popup_btn = "btn_end02.gif";
	}else if(questExitTitle.getPollBgimg().equals("B")){
		popup_bgImg = "2";
		popup_btn = "btn_end02.gif";
	}else{
		popup_bgImg = "3";
		popup_btn = "btn_end03.png";
	}
%>

<script type="text/javascript" src="/include/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var serviceid	= "<%= serviceId %>";
	var type		= "I";
	var pollIdx		= "<%= pollIdx %>";
	var idx			= "<%= pollIdx %>" + "_" + "<%= userId %>";
	var userid		= "<%= userId %>";
	var title		= "<%= eTitle %>";
	<%System.out.println("=========== 마일리지 호출하기 시작 ~~~~~~  ============ : "); %>
	<%System.out.println("=========== 마일리지 호출  : " + "http://mileage.seoul.go.kr/source/source.jsp?serviceid="+serviceId+"&type="+ type +"&idx="+ pollIdx+ "_" + userId +"&userid="+ userid +"&title="+eTitle); %>

	var url = "http://mileage.seoul.go.kr/source/source.jsp?serviceid="+ serviceid +"&type="+ type +"&idx="+ idx +"&userid="+ userid +"&title="+title;	
	
	$.ajax({
		type: "GET",
		url: url
	});
	<%System.out.println("=========== 마일리지 호출하기 완료 ~~~~~~  ============ : "); %>
});


//마우스 오른 쪽 금지
document.oncontextmenu = function() { return false; }
//새로고침 방지
// Ctrl+R, Ctrl+N, F5 키 막음
function doNotReload(){
	if((event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) ){ // function F5 //78 ,82 ctrl+N , ctrl+R 
		event.keyCode = 0;
		event.cancelBubble = true;
		event.returnValue = false;
	}
}
document.onkeydown = doNotReload;
</script>

</head>
<body>
<form id="frm" name="frm" method="post" >
<div class="popup_wrap type<%= popup_bgImg %>">
    <div class="popup_header">
        <strong><img src="/img/poll/img_logoJoin.png" alt="서울시 여론조사 참여하기" /></strong>
        <!-- div class="progress">
            <span class="bar">
                <img src="/img/poll/bar_progress01.png" class="bar1" alt="" />
                <img src="/img/poll/bar_progress02.png" class="bar2" alt="" />
                <img src="/img/poll/bar_progress03.png" class="bar3" alt="" />
            </span>
            <span>45%</span>
        </div-->
    </div>
    <div class="popup_content bg2">
        <div class="txtWrap">
            <p class="endCopy">
                <span>여러분의 의견이 서울시를 바꿉니다.</span>
                여론조사가 정상적으로 완료되었습니다.
            </p>
            <dl class="endSurvey">
                <dt>조사명 : <em><%= title %></em></dt>
                <dd><%= message %></dd>
            </dl>
        </div>
        <div class="btnWrap">
            <a href="javascript:opener.location.reload(); this.close();"><img src="/img/poll/<%= popup_btn %>" alt="종료" /></a>
        </div>
    </div>
</div>
</form>
</body>
</html>