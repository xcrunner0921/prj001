<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="seoul.research.poll.service.SurveyWebSearchVO" %>
<%@ page import="seoul.research.domain.SurveyWeb" %>
<%@ page import="com.sds.emp.common.*" %>
<%@ page import="java.util.*" %>
<%@ page import="anyframe.common.Page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/surveyTemplate.css"/>

<%
	SurveyWeb questPassTitle = (SurveyWeb)request.getAttribute("questPassTitle");

	String popup_bgImg;
	String popup_btn;
	if(questPassTitle.getPollBgimg().equals("A")){
		popup_bgImg = "1";
		popup_btn = "btn_end02.gif";
	}else if(questPassTitle.getPollBgimg().equals("B")){
		popup_bgImg = "2";
		popup_btn = "btn_end02.gif";
	}else{
		popup_bgImg = "3";
		popup_btn = "btn_end03.png";
	}
%>
<script type="text/javascript">

</script>

</head>
<body>
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
                <span>여러분의 의견이 서울을 바꿉니다.</span>
                설문조사에 응답해 주셔서 감사합니다.
            </p>
            <dl class="endSurvey">
                <dt>조사명 : <em><%= questPassTitle.getPollTitle()%></em></dt>
                <dd>귀하께서는 조사대상이 아니신 관계로 설문을 끝마치지 못하셨습니다.
참여해 주셔서 감사합니다.<br />
 (문의 : 서울시 뉴미디어담당관 여론분석팀 02-2133-6513)
</dd>
            </dl>
        </div>
        <div class="btnWrap">
            <a href="javascript:window.close();"><img src="/img/poll/<%= popup_btn %>" alt="종료" /></a>
        </div>
    </div>
</div>
</body>
</html>