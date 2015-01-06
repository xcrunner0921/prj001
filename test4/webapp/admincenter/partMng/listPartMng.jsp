<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri='/WEB-INF/anyframe-page.tld' prefix='anyframe' %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.*" %>
<%@ page import="anyframe.common.Page" %>
<%@ page import="com.sds.emp.common.EmpUtil" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.domain.PartMng" %>
<%@ page import="seoul.research.admincenter.service.PartMngSearchVO" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>서울시 여론조사 시스템 관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
<%
	PartMngSearchVO partMngSearchVO = (PartMngSearchVO)request.getAttribute("partMngSearchVO");
	String pollIdx = EmpUtil.null2str(partMngSearchVO.getSearchPollIdx());
	String pollTitle = EmpUtil.null2str(partMngSearchVO.getSearchPollTitle());
	String pollStatus = EmpUtil.null2str(partMngSearchVO.getSearchPollStatus());
	String pollType = EmpUtil.null2str(partMngSearchVO.getSearchPollType());
	String pollText = EmpUtil.null2str(partMngSearchVO.getSearchPollText());

	Page resultPage = (Page)request.getAttribute("resultPage");
	ArrayList resultList = (ArrayList)resultPage.getList();	//조회결과
	int pagesize = resultPage.getPagesize();

	NumberFormat nf = NumberFormat.getInstance(); //숫자 컴마 
	nf.setMaximumIntegerDigits(5); //최대5자리

	ArrayList pollMngList = (ArrayList)request.getAttribute("pollMngList"); //여론조사목록

	String role = (String)session.getAttribute("role");
%>
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
<script src="/include/jquery-latest.min.js"></script>
<script src="/include/jquery-ui.js"></script>
<script>
$(function() {
	$.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		duration: 200,
		showAnim: 'show',
		showMonthAfterYear: true,
		yearSuffix: '년'
	};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	
	$("#searchRegDatestart").datepicker({
	    showOtherMonths: true,
	    selectOtherMonths: true,
		showOn: "button",
		buttonImage: "/img/admincenter/icon_month.gif",
		buttonImageOnly: true
	});

  	$("#searchRegDateend").datepicker({
	    showOtherMonths: true,
	    selectOtherMonths: true,
		showOn: "button",
		buttonImage: "/img/admincenter/icon_month.gif",
		buttonImageOnly: true
	});
});
</script>

<script language="JavaScript">
<!--
function fncLogOut() {
	if (confirm("정말 로그아웃하시겠습니까?")) {
		document.listForm.target = "_top";
	    document.listForm.action = "<c:url value='/logout.do' />";
	    document.listForm.submit();	
    }
}
//응답자관리
function fncListPartMng(arg){
	if (arg == "1")
		document.listForm.pageIndex.value = 1;
   	document.listForm.action = "<c:url value='/listPartMng.do' />";
   	document.listForm.submit();		
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">
<form name="listForm" method="post">

	<div id="wrap">
		<div class="inner_wrap">
		
			<!-- #header -->
			<div id="header">
				<h1><a href="/admincenter/"><img src="/img/admincenter/logo.jpg" alt="서울시 여론조사" /></a></h1>
				<p><a href="javascript:fncLogOut();">로그아웃</a></p>
				<div class="gnb">
					<ul class="clearfix">
					<%
					if (role.equals("GG101") || role.equals("GG102")) {
					%>
						<li><a href="<c:url value='/listSurveyMng.do' />">여론조사관리</a></li>
						<li><a href="<c:url value='/listPanelMng.do' />">패널현황</a></li>
						<li><a href="<c:url value='/listPartMng.do' />">응답자관리</a></li>
						<li><a href="<c:url value='/listApplMng.do' />">조사결과</a></li>
						<li><a href="<c:url value='/listPopupzoneMng.do' />">팝업존관리</a></li>
					<%
					}
					else {
					%>
						<li><a href="#">여론조사관리</a></li>
						<li><a href="#">패널현황</a></li>
						<li><a href="#">응답자관리</a></li>
						<li><a href="<c:url value='/listApplMng.do' />">조사결과</a></li>
						<li><a href="#">팝업존관리</a></li>
					<%
					}
					%>
					</ul>
				</div><!-- .gnb -->			
			</div>
			<!-- // #header -->
			
			<!-- #sidebar -->
			<div id="sidebar">
				<div class="inner">
					<h2>응답자관리</h2>
					<ul>
						<li><a href="<c:url value='/listPartMng.do' />" class="active">응답자현황</a></li>
					</ul>
				</div>
			</div>	
			<!-- // #sidebar -->
			
			<!-- contents -->
			<div id="contents" class="survey">	
				<h2>응답자 현황</h2>
				
				<table class="srch_tbl">
					<tr>
						<th>선택</th>
						<td>      
							<select name="searchPollIdx" id="searchPollIdx" style="width:300px" >
								<option value="">전체</option>
								<%
									for (int i = 0; i < pollMngList.size(); i++) {
										PartMng pollList = (PartMng)pollMngList.get(i);
										String sIdx = Integer.toString(pollList.getPollIdx());
								%>
								<option value="<%= pollList.getPollIdx() %>" <%= (sIdx.equals(pollIdx) ? "selected" : "")%>><%= pollList.getPollTitle() %></option>
								<%
									}
								%>
							</select>	
						</td>
						<th><label for="">상태</label></th>
						<td>
							<select name="searchPollStatus" id="searchPollStatus">
								<option value="">전체</option>
								<option value="C" <%= ("C".equals(pollStatus) ? "selected" : "")%>>완료</option>
								<option value="S" <%= ("S".equals(pollStatus) ? "selected" : "")%>>중단</option>				
								<option value="F" <%= ("F".equals(pollStatus) ? "selected" : "")%>>필터</option>
							</select>					
											
						</td>
						<td rowspan="2"><div class="btnareaC"><span class="btn radiusG"><a href="javascript:fncListPartMng('1');">검색</a></span></div></td>
					</tr>
					<tr>
						<th><label for="">기타</label></th>
						<td colspan="3">
							<select name="searchPollType" id="searchPollType">
								<option value="id" <%= ("id".equals(pollType) ? "selected" : "")%>>아이디</option>
								<option value="name" <%= ("name".equals(pollType) ? "selected" : "")%>>이름</option>
							</select>
							<input type="text" name="searchPollText" id="searchPollText" value="<%=pollText %>"/>
						</td>
					</tr>
				</table><!-- .srch_tbl -->
				
					
				<div class="btnarea">
					<span class="btnareaL total">총 <strong><%= nf.format(resultPage.getTotalCount()) %></strong>&nbsp;건</span>
				</div>
				
				<table class="list_tbl">
				<colgroup>
					<col width="40px;" />
					<col width="*" />
					<col width="60px;" />
					<col width="60px;" />
					<col width="110px;" />
					<col width="80px;" />
					<col width="50px;" />
					<col width="80px;" />
					<col width="50px;" />
				</colgroup>
					<thead>
						<tr>
							<th rowspan="2">번호</th>
							<th rowspan="2">조사제목</th>
							<th rowspan="2">아이디</th>
							<th rowspan="2">이름</th>
							<th rowspan="2">기간</th>
							<th rowspan="2">참여일</th>
							<th rowspan="2">상태</th>
							<th colspan="2">마일리지</th>
						</tr>
						<tr>
							<th>지급일</th>
							<th>점수</th>
						</tr>
					</thead>
					<tbody>
					<%
						int currentPageIndex = resultPage.getCurrentPage();
						int no = (currentPageIndex - 1) * pagesize;
						
						if (resultList.size() > 0) {
							for (int i = 0; i < resultList.size(); i++) {
								PartMng partMng = (PartMng)resultList.get(i);
								String panelStatus = partMng.getPollPanelStatus();
								if("C".equals(panelStatus)){
									panelStatus = "완료";
								}else if("F".equals(panelStatus)){
									panelStatus = "<font style='color:orange'>필터</font>";
								}else
									panelStatus = "<font style='color:red'>중단</font>";
					%>
						<tr>
							<td><%= ++no %></td>
							<td class="tl"><%= partMng.getPollTitle() %></td>
							<td><%= partMng.getPollPanelid() %></td>
							<td><%= partMng.getPollPanelname() %></td>
							<td><%= partMng.getPollTermstartYmd() %>&nbsp;~<br />&nbsp;<%= partMng.getPollTermendYmd() %></td>
							<td><%= partMng.getPollAnswerDate() %></td>
							<td><%= panelStatus %></td>
							<td><%= (partMng.getPollMileageDate()== null ? "" : partMng.getPollMileageDate()) %></td>
							<td><%= partMng.getPollMileage() %></td>
						</tr>
						<%
								}
						%>
						
					</tbody>
				</table><!-- .list_tbl -->
				
				<div class="paginate">
					<anyframe:pagenavigator linkUrl="javascript:fncListPartMng(2);" 
							             	pages="<%=resultPage%>" 
							             	formName="listForm"
							              	firstImg="${pageContext.request.contextPath}/img/admincenter/btn_prev_more.gif" 
							              	prevImg="${pageContext.request.contextPath}/img/admincenter/btn_prev.gif" 
							              	lastImg="${pageContext.request.contextPath}/img/admincenter/btn_next.gif" 
							              	nextImg="${pageContext.request.contextPath}/img/admincenter/btn_next_more.gif" />
				</div><!-- .paginate -->
				
						<%
							}
							else {
						%>
						<tr>
							<td colspan="9">조회 결과가 없습니다.</td>
						</tr>
					</tbody>
				</table><!-- .list_tbl -->
						<%
							}
						%>
			</div>
			<!-- // contents -->
			
		</div>
		<!-- // .inner_wrap -->
	</div>
	<!-- // #wrap -->

<!-- selected userId -->
<input type="hidden" name="pollIdx" />
<input type="hidden" name="pageIndex" value="<%= resultPage.getCurrentPage() %>" />
<!-- end of list -->

</form>
<script language="JavaScript">
document.listForm.searchPollText.focus();
</script>
</body>
</html>
