<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri='/WEB-INF/anyframe-page.tld' prefix='anyframe' %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="anyframe.common.Page" %>
<%@ page import="com.sds.emp.common.EmpUtil" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.admincenter.service.AnswerMngSearchVO" %> 
<%@ page import="seoul.research.domain.PollMng" %>
<%@ page import="java.text.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>서울시 여론조사 시스템 관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
<%
	AnswerMngSearchVO answerMngSearchVO = (AnswerMngSearchVO)request.getAttribute("answerMngSearchVO");
	String regDatestart = EmpUtil.null2str(answerMngSearchVO.getSearchRegDatestart());
	String regDateend = EmpUtil.null2str(answerMngSearchVO.getSearchRegDateend());
	String pollStop = EmpUtil.null2str(answerMngSearchVO.getSearchPollStop());
	String pollAsk = EmpUtil.null2str(answerMngSearchVO.getSearchPollAsk());
	String condition = EmpUtil.null2str(answerMngSearchVO.getSearchCondition());
	String keyword = EmpUtil.null2str(answerMngSearchVO.getSearchKeyword());
	
	Page resultPage = (Page)request.getAttribute("resultPage");
	ArrayList resultList = (ArrayList)resultPage.getList();	
	int pagesize = resultPage.getPagesize();
	
	ArrayList askCodeList = (ArrayList)request.getAttribute("askCodeList");

	String role = (String)session.getAttribute("role");
	NumberFormat nf = NumberFormat.getInstance(); // 
	nf.setMaximumIntegerDigits(5); //
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

function fncListAnswerMng(arg) {
	if (arg == "1")
		document.listForm.pageIndex.value = 1;
   	document.listForm.action = "<c:url value='/listAnswerMng.do' />";
   	document.listForm.submit();		
}

function fncAdminAnswer(pollIdx) {
	var theURL = "http://research.seoul.go.kr/admincenter/answerMng/admin_answer.jsp?pollIdx=" + pollIdx;
	window.open(theURL, "Example", "scrollbars=no, width=900, height=700, menubar=false");
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
					<h2>여론조사관리</h2>
					<ul>
						<li><a href="<c:url value='/listSurveyMng.do' />">여론조사관리</a></li>
						<li><a href="<c:url value='/listQuestMng.do' />">문항관리</a></li>
						<li><a href="<c:url value='/listAnswerMng.do' />" class="active">응답현황</a></li>
					</ul>
				</div>
			</div>	
			<!-- // #sidebar -->
			
			<!-- contents -->
			<div id="contents" class="survey">	
				<h2>응답 현황</h2>
				
				<table class="srch_tbl">
					<tr>
						<th width="70px;">등록일</th>
						<td>
							<input type="text" name="searchRegDatestart" id="searchRegDatestart" title="등록일" class="ip80" value="<%= regDatestart %>" readonly />
<!--							<a href=""><img src="../../img/adminCenter/icon_month.gif" alt="등록일 달력"/></a>-->
							~ 
							<input type="text" name="searchRegDateend" id="searchRegDateend" title="등록일 뒷자리" class="ip80" value="<%= regDateend %>" readonly />
<!--							<a href=""><img src="../../img/adminCenter/icon_month.gif" alt="등록일 달력"/></a>-->
						</td>
						<th width="70px;"><label for="">상태</label></th>
						<td>
							<select name="searchPollStop" id="">
								<option value="">전체</option>
<!--								<option value="S" <%= ("S".equals(pollStop) ? "selected" : "")%>>대기</option>-->
								<option value="P" <%= ("P".equals(pollStop) ? "selected" : "")%>>진행</option>
								<option value="C" <%= ("C".equals(pollStop) ? "selected" : "")%>>완료</option>				
								<option value="T" <%= ("T".equals(pollStop) ? "selected" : "")%>>일시중지</option>
							</select>					
						</td>
						<td rowspan="2" width="70px;"><div class="btnareaC"><span class="btn radiusG"><a href="javascript:fncListAnswerMng('1');">검색</a></span></div></td>
					</tr>
					<tr>
						<th><label for="">부서</label></th>
						<td>
							<select name="searchPollAsk" id="" style="width: 236px;">
								<option value="">전체</option>
								<%
									for (int i = 0; i < askCodeList.size(); i++) {
										Code askCode = (Code)askCodeList.get(i);
								%>
								<option value="<%= askCode.getCodeId() %>" <%= (askCode.getCodeId().equals(pollAsk) ? "selected" : "")%>><%= askCode.getCodeName() %></option>
								<%
									}
								%>
							</select>					
						</td>
						<th><label for="">기타</label></th>
						<td>
							<select name="searchCondition" id="">
								<option value="">선택</option>
								<option value="F" <%= ("F".equals(condition) ? "selected" : "")%>>구분</option>
								<option value="T" <%= ("T".equals(condition) ? "selected" : "")%>>조사제목</option>
							</select>
							<input type="text" name="searchKeyword" id="" value="<%= keyword %>" />
						</td>
					</tr>
				</table><!-- .srch_tbl -->
				
					
				<div class="btnarea">
					<span class="btnareaL total">총 <strong><%= resultPage.getTotalCount() %></strong>건</span>
<!--					<span class="btn radiusB"><a href="#">등록</a></span>-->
				</div>
				
				<table class="list_tbl">
				<colgroup>
					<col width="40px;" />
					<col width="60px;" />
					<col width="90px;" />
					<col width="*" />
					<col width="90px;" />
					<col width="60px;" />
					<col width="60px;" />
					<col width="90px;" />
				</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>구분</th>
							<th>부서</th>
							<th>조사 제목</th>
							<th>기간</th>
							<th>목표<br />표본수</th>
							<th>상태</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<%
							int currentPageIndex = resultPage.getCurrentPage();
							int no = (currentPageIndex - 1) * pagesize;
							
							if (resultList.size() > 0) {
								for (int i = 0; i < resultList.size(); i++) {
									PollMng pollMng = (PollMng)resultList.get(i);
						%>
						<tr>
							<td><%= ++no %></td>
							<td><%= pollMng.getPollFieldName() %></td>
							<td><%= pollMng.getPollAskName().replaceAll("\\|", "<br />") %></td>
							<td class="tl"><a href="javascript:fncAdminAnswer('<%= pollMng.getPollIdx() %>');"><%= pollMng.getPollTitle() %></a></td>
							<td><%= pollMng.getPollTermstart() %> ~<br /><%= pollMng.getPollTermend() %></td>
							<td><%= nf.format(pollMng.getPollSample()) %></td>
							<%
									// if ("S".equals(pollMng.getPollStop())) {
							%>
<!--							<td>대기</td>-->
							<%
									//}
									if ("P".equals(pollMng.getPollStop())) {
							%>
							<td>진행</td>
							<%
									}
									else if ("T".equals(pollMng.getPollStop())) {
							%>
							<td>일시중지</td>
							<%
									}
									else if ("C".equals(pollMng.getPollStop())) {
							%>
							<td>완료</td>
							<%
									}
							%>
							<td><%= pollMng.getRegDate() %></td>
						</tr>
						<%
								}
						%>
					</tbody>
				</table><!-- .list_tbl -->
				
				<div class="paginate">
					<anyframe:pagenavigator linkUrl="javascript:fncListAnswerMng(2);" 
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
							<td colspan="8">조회 결과가 없습니다.</td>
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
<input type="hidden" name="pollIdx">

<input type="hidden" name="pageIndex" value="<%= resultPage.getCurrentPage() %>">
<!-- end of list -->

</form>
<script language="JavaScript">
document.listForm.searchKeyword.focus();
</script>
</body>
</html>
