<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri='/WEB-INF/anyframe-page.tld' prefix='anyframe' %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.*" %>
<%@ page import="com.sds.emp.common.*" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.poll.service.SurveyResultSearchVO" %>
<%@ page import="seoul.research.domain.SurveyResult" %>
<%@ page import="seoul.research.domain.PopupMng" %>

<%
	SurveyResultSearchVO surveyResultSearchVO = (SurveyResultSearchVO)request.getAttribute("surveyResultSearchVO");

	String searchPollTitle = EmpUtil.null2str(surveyResultSearchVO.getSearchPollTitle());
	
	//여론조사 결과목록  
	Page resultPage = (Page)request.getAttribute("resultPage");
	ArrayList resultList = (ArrayList)resultPage.getList();	
	int pagesize = resultPage.getPagesize();
	
	//팝업존 
	Page resultPopup = (Page)request.getAttribute("resultPopup");
	ArrayList resultListPop = (ArrayList)resultPopup.getList();	
	int pagesizePop = resultPopup.getPagesize();
	//PopupMng popMng = (PopupMng)resultListPop.get(0);
	//System.out.println("============>>>>  resultPopup : pagesizePop :  " + popMng.getFilePath() + popMng.getSavFname()      );
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/common.css" /> 
<link rel="stylesheet" type="text/css" href="/css/content.css" />
<script type="text/javascript" src="/include/jquery-latest.min.js"></script> 
<script type="text/javascript">
<!--
var idx = 0 ;
var popup1_timer = null;
var popup1_delay_time = 5000;

//이미지 롤링 
function rolling(){
	popZone();
	popup1_timer = setInterval("popZone()", popup1_delay_time);
}

//팝업존 이미지 설정
function popZone(){
	for(i=0;i<<%= resultListPop.size()%>;i++){
		if(i==idx){
			document.getElementById("popupBtn"+(i+1)).src="/img/poll/btn_on.png"
			document.getElementById("popupMain"+(i+1)).style.display = 'block';
		}else{
			document.getElementById("popupBtn"+(i+1)).src="/img/poll/btn_off.png"
			document.getElementById("popupMain"+(i+1)).style.display = 'none';
		}		
	}
	idx = (idx + 1) % <%= resultListPop.size()%>;
}

//선택버튼 클릭 
function cNext(my){
	 idx = my;
	 popZone();
}

//여론조사 결과 검색 
function fncGetSurveyWebList(arg) {
	if(arg == "1"){
		document.main.pageIndex.value = 1;
	}	
   	document.main.action="<c:url value='/main.do'/>";
   	document.main.submit();		
}

-->
</script>
</head>
<body onload="javascript:rolling();">
<form id="main" name="main" method="post" action="<c:url value='/main.do'/>">
<input type="hidden" name="pageIndex" value="<%=resultPage.getCurrentPage()%>"/>
<div id="wrap">
    <!-- header -->
     <%@ include file="/include/top.jsp" %>
    <!-- //header -->
 
    <!-- container -->
    <div id="container" class="index">
        <div class="wrap_colLeft">
            <h2 class="slogan">시민의 생각이 서울시를 바꿉니다</h2>
            <div class="postList notice">
                <h3><img src="/img/poll/mtit_notice.gif" alt="공지사항" /></h3>
                <ul>
                <iframe title="공지사항" name="board" src="http://www.seoul.go.kr/tongboard/silguk/research/B1612_MainList_626.html" width="320px" height="80" frameborder="0" marginheight="0" marginwidth="0" scrolling="no"></iframe>
                </ul>
                <a href="/poll/board/p050101.jsp?bid=" class="link_more">더보기</a>
            </div>
            <div class="postList surveyResult">
                <h3><img src="/img/poll/mtit_surveyResult.gif" alt="여론조사결과" /></h3>
                <span class="mSearchBox">
                <input type="text" name="searchPollTitle" value="<%= searchPollTitle %>" title="여론조사결과 검색" /><a href="javascript:fncGetSurveyWebList('1');"><span>검색</span></a></span>
                <%
				int currentPageIndex = resultPage.getCurrentPage();
				int no = (currentPageIndex - 1) * pagesize;
				
				if (resultList.size() > 0) {
				%>						 
					<ul>
				<%
					for (int i = 0; i < resultList.size(); i++) {
						SurveyResult surveyResult = (SurveyResult)resultList.get(i);
						int titleLength = surveyResult.getPollTitle().length();
				%>
		                    <li><a href="/listSurveyResult.do"><%=(titleLength> 20 ? surveyResult.getPollTitle().substring(0,20)+"..." : surveyResult.getPollTitle()) %></a><span><%=surveyResult.getRegDate() %></span></li>
				<%	
					}	
				%>						 
					</ul>                
                <% }else{ %>
	                <ul>
	                    <li>조회결과 없습니다.</li>
	                </ul>
                <%} %>
                
                <a href="/listSurveyResult.do" class="link_more">더보기</a>
            </div>
            <div class="quickService">
                <h3><img src="/img/poll/tit_quickService.gif" alt="빠른 서비스" /></h3>
                <a href="/poll/panel/p010101.jsp"><img src="/img/poll/bnr_introJoinPanel.gif" alt="패널가입안내" /></a>
                <a href="/poll/board/p050201.jsp" class="bnr_faq"><img src="/img/poll/bnr_faq.gif" alt="자주 찾는 질문" /></a>
            </div>
        </div>
        <div class="wrap_colRight">
        	<%
        	if (resultListPop.size() > 0) {
			%>
            <span class="tab_issue"> <!-- 우측상단 작은 원 -->
            	<%
					for (int i = 0; i < resultListPop.size(); i++) {
						PopupMng popupMng = (PopupMng)resultListPop.get(i);
						if(i==0){
				%>
                <a href="javascript:cNext(<%=i %>);"><img src="/img/poll/btn_on.png" id="popupBtn<%=(i+1) %>" alt="<%=popupMng.getTitle()%>" /></a>
                		<%}else{ %>
                <a href="javascript:cNext(<%=i %>);"><img src="/img/poll/btn_off.png" id="popupBtn<%=(i+1) %>" alt="<%=popupMng.getTitle()%>" /></a>
                		<%} %>
                <%} %>
            </span>
            <%} %>
            
            
        	<%
        	if (resultListPop.size() > 0) {
			%>
            <div class="issue"> <!-- 팝업존 이미지 -->
            	<%
					for (int i = 0; i < resultListPop.size(); i++) {
						PopupMng popupMng = (PopupMng)resultListPop.get(i);
						if(i==0){				
				%>
                <a href="<%=popupMng.getLinkUrl() %>" title="<%=popupMng.getTitle() %>"><img src="<%=popupMng.getFilePath()+popupMng.getSavFname() %>" id="popupMain<%=(i+1) %>" alt="<%=popupMng.getTitle() %>" /></a>
                		<%}else{ %>
                <a href="<%=popupMng.getLinkUrl() %>" title="<%=popupMng.getTitle() %>"><img src="<%=popupMng.getFilePath()+popupMng.getSavFname() %>" id="popupMain<%=(i+1) %>" alt="<%=popupMng.getTitle() %>" /></a>
                		<%} %>
                <%	} %>
            </div>
            <%} %>
            
            
            
            <ul class="seoulSectionLink">
                <li class="tit"><strong><img src="/img/poll/tit_seoulSection.png" alt="서울시 주요 여론조사 분야" /></strong></li>
                <li class="link1"><span><img src="/img/poll/btn_welfare.png" alt="복지,어르신,장애인" /></span></li>
                <li class="link2"><span><img src="/img/poll/btn_woman.png" alt="여성,보육,아동청소년" /></span></li>
                <li class="link3"><span><img src="/img/poll/btn_economy.png" alt="경제,일자리" /></span></li>
                <li class="link4"><span><img src="/img/poll/btn_environment.png" alt="환경,공원,상수도" /></span></li>
                <li class="link5"><span><img src="/img/poll/btn_culture.png" alt="문화,관광,체육,디자인" /></span></li>
                <li class="link6"><span><img src="/img/poll/btn_traffic.png" alt="교통" /></span></li>
            </ul>
        </div>
    </div>
    <!-- //container -->

    <!-- footer -->
    <%@ include file="/include/footer.jsp" %>
    <!-- //footer -->
</div>
</form>
</body>
</html>