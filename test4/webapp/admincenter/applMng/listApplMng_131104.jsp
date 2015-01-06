<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri='/WEB-INF/anyframe-page.tld' prefix='anyframe' %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="anyframe.common.Page" %>
<%@ page import="com.sds.emp.common.EmpUtil" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.admincenter.service.ApplMngSearchVO" %> 
<%@ page import="seoul.research.domain.PollMng" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>서울시 여론조사 시스템 관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
<%
	ApplMngSearchVO applMngSearchVO = (ApplMngSearchVO)request.getAttribute("applMngSearchVO");
	String regDatestart = EmpUtil.null2str(applMngSearchVO.getSearchRegDatestart());
	String regDateend = EmpUtil.null2str(applMngSearchVO.getSearchRegDateend());
	String pollStop = EmpUtil.null2str(applMngSearchVO.getSearchPollStop());
	// String pollAsk = EmpUtil.null2str(surveyMngSearchVO.getSearchPollAsk());
	// String condition = EmpUtil.null2str(surveyMngSearchVO.getSearchCondition());
	String keyword = EmpUtil.null2str(applMngSearchVO.getSearchKeyword());

	Page resultPage = (Page)request.getAttribute("resultPage");
	ArrayList resultList = (ArrayList)resultPage.getList();	
	int pagesize = resultPage.getPagesize();
	
	// ArrayList askCodeList = (ArrayList)request.getAttribute("askCodeList");
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

function fncListApplMng(arg) {
	if (arg == "1")
		document.listForm.pageIndex.value = 1;
   	document.listForm.action = "<c:url value='/listApplMng.do' />";
   	document.listForm.submit();		
}

function fncAddApplMngView() {
    document.listForm.action = "<c:url value='/addApplMngView.do' />";
    document.listForm.submit();
}

function fncDetailApplMng(pollIdx) {
	document.listForm.pollIdx.value = pollIdx;
   	document.listForm.action = "<c:url value='/detailApplMng.do' />";
   	document.listForm.submit();
}

function fncAdminAppl(pollIdx) {
	var theURL = "http://research.seoul.go.kr/admincenter/applMng/admin_appl.jsp?pollIdx=" + pollIdx + "&user=admin";
	window.open(theURL, "Example", "scrollbars=no, width=900, height=700, menubar=false");
}

function fncExcelApplMng(pollIdx, target) {
	document.listForm.pollIdx.value = pollIdx;
	document.listForm.target.value = target;
    document.listForm.action = "<c:url value='/excelApplMng.do' />";
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
					<h2>조사결과</h2>
					<ul>
						<li><a href="<c:url value='/listApplMng.do' />" class="active">조사결과현황</a></li>
					</ul>
				</div>
			</div>	
			<!-- // #sidebar -->
			
			<!-- contents -->
			<div id="contents" class="survey">	
				<h2>조사결과 현황</h2>
				
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
						<td rowspan="2" width="70px;"><div class="btnareaC"><span class="btn radiusG"><a href="javascript:fncListApplMng('1');">검색</a></span></div></td>
					</tr>
					<tr>
						<th><label for="">제목</label></th>
						<td colspan="3">
<!--							<select name="" id="">-->
<!--								<option value="">선택</option>-->
<!--								<option value="">진행</option>-->
<!--								<option value="">완료</option>-->
<!--							</select>-->
							<input type="text" name="searchKeyword" id="" value="<%= keyword %>" />
						</td>
					</tr>
				</table><!-- .srch_tbl -->
				
					
				<div class="btnarea">
					<span class="btnareaL total">총 <strong><%= resultPage.getTotalCount() %></strong>건</span>
					<%
					if (role.equals("GG101") || role.equals("GG102")) {
					%>
					<span class="btn radiusB"><a href="javascript:fncAddApplMngView();">등록</a></span>
					<%
					}
					%>
				</div>
				
				<table class="list_tbl">
				<colgroup>
					<col width="40px;" />
					<col width="*" />
					<col width="60px;" />
					<col width="90px;" />
					<col width="90px;" />
					<col width="60px;" />
					<col width="60px;" />
					<col width="60px;" />
<!--					<col width="70px;" />-->
				</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>조사 제목</th>
							<th>엑셀</th>
							<th>기간</th>
							<th>등록일</th>
							<th>참여<br />인원</th>
							<th>노출<br />여부</th>
							<th>상태</th>
<!--							<th>비교</th>-->
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
							<td class="tl">
							<%
								if (pollMng.getPollGubun().equals("LL101")) {
							%>
								<a href="javascript:fncAdminAppl('<%= pollMng.getPollIdx() %>');"><%= pollMng.getPollTitle() %></a>
							<%
								}
								else if (pollMng.getPollGubun().equals("LL102")) {
							%>
								<a href="javascript:fncDetailApplMng('<%= pollMng.getPollIdx() %>');"><%= pollMng.getPollTitle() %></a>
							<%
								}
							%>
							</td>
							<td>
							<%
								if (pollMng.getPollGubun().equals("LL101")) {
							%>
								<a href="javascript:fncExcelApplMng('<%= pollMng.getPollIdx() %>', 'S');">주관식</a><br />
								<a href="javascript:fncExcelApplMng('<%= pollMng.getPollIdx() %>', 'E');">기타</a><br />
								<a href="javascript:fncExcelApplMng('<%= pollMng.getPollIdx() %>', 'R');">로데이터</a>
							<%
								}
							%>
							</td>
							<td><%= pollMng.getPollTermstart() %> ~<br /><%= pollMng.getPollTermend() %></td>
							<td><%= pollMng.getRegDate() %></td>
							<td><%= pollMng.getPanelCnt() %></td>
							<td><%= pollMng.getPollOpen() %></td>
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
<!--							<td><span class="btn lightB"><a href="javascript:fncUpdateApplMngView('<%= pollMng.getPollIdx() %>');">수정</a></span></td>-->
						</tr>
						<%
								}
						%>
					</tbody>
				</table><!-- .list_tbl -->
				
				<div class="paginate">
					<anyframe:pagenavigator linkUrl="javascript:fncListApplMng(2);" 
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
<input type="hidden" name="target">

<input type="hidden" name="pageIndex" value="<%= resultPage.getCurrentPage() %>">
<!-- end of list -->

</form>
<script language="JavaScript">
document.listForm.searchKeyword.focus();
</script>
</body>
</html>
