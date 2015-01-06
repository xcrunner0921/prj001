<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="anyframe.common.Page" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.admincenter.service.SurveyMngSearchVO" %>
<%@ page import="seoul.research.domain.PollMng" %>
<%@ page import="seoul.research.domain.PollAddfile" %>
<%@ page import="seoul.research.domain.PollPart" %>
<%@ page import="seoul.research.admincenter.service.ApplMngSearchVO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>서울시 여론조사 시스템 관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
<%
	// SurveyMngSearchVO surveyMngSearchVO = (SurveyMngSearchVO)request.getAttribute("surveyMngSearchVO");
	PollMng pollMng = (PollMng)request.getAttribute("pollMng");
	
	// ArrayList mfCodeList = (ArrayList)request.getAttribute("mfCodeList");
	// ArrayList ageCodeList = (ArrayList)request.getAttribute("ageCodeList");
	// ArrayList regionCodeList = (ArrayList)request.getAttribute("regionCodeList");
	// ArrayList pollPartMfList = (ArrayList)request.getAttribute("pollPartMfList");
	// ArrayList pollPartAgeList = (ArrayList)request.getAttribute("pollPartAgeList");
	// ArrayList pollPartRegionList = (ArrayList)request.getAttribute("pollPartRegionList");
	
	ArrayList pollAddfileList = (ArrayList)request.getAttribute("pollAddfileList");
	ArrayList pollAddfile2List = (ArrayList)request.getAttribute("pollAddfile2List");
	
	ApplMngSearchVO applMngSearchVO = (ApplMngSearchVO)request.getAttribute("applMngSearchVO");
	Page resultPage = (Page)request.getAttribute("resultPage");

	// ArrayList subjectCodeList = (ArrayList)request.getAttribute("subjectCodeList");
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
	
	$("#pollTermstartYmd").datepicker({
		showOn: "button",
		buttonImage: "/img/admincenter/icon_month.gif",
		buttonImageOnly: true
	});

  	$("#pollTermendYmd").datepicker({
		showOn: "button",
		buttonImage: "/img/admincenter/icon_month.gif",
		buttonImageOnly: true
	});
});
</script>

<script language="javascript" src="<c:url value='/sample/javascript/CommonScript.js'/>"></script>
<script language="JavaScript">
<!--
function fncLogOut() {
	if (confirm("정말 로그아웃하시겠습니까?")) {
		document.detailForm.target = "_top";
	    document.detailForm.action = "<c:url value='/logout.do' />";
	    document.detailForm.submit();	
    }
}

function fncUpdateApplMngView(pollIdx) {
	document.detailForm.action = "<c:url value='/updateApplMngView.do' />";
	document.detailForm.submit();
}

function fncDeleteApplMng() {
	if (confirm("조사결과를 삭제하시겠습니까?")) {
	    document.detailForm.action = "<c:url value='/deleteApplMng.do' />";
	    document.detailForm.submit();
	}
}

function fncListApplMng() {
	document.detailForm.action = "<c:url value='/listApplMng.do' />";
    document.detailForm.submit();
}

function fncDownloadApplMng(filePath, savFname, orgFname) {
	document.detailForm.filePath.value = filePath;
	document.detailForm.savFname.value = savFname;
	document.detailForm.orgFname.value = orgFname;
	document.detailForm.action = "<c:url value='/downloadApplMng.do' />";
    document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">
<form name="detailForm" method="post">
<input type="hidden" name="searchRegDatestart" 	value="<%= applMngSearchVO.getSearchRegDatestart() %>" />
<input type="hidden" name="searchRegDateend" 	value="<%= applMngSearchVO.getSearchRegDateend() %>" />
<input type="hidden" name="searchPollStop" 		value="<%= applMngSearchVO.getSearchPollStop() %>" />
<input type="hidden" name="searchKeyword" 		value="<%= applMngSearchVO.getSearchKeyword() %>" />
<input type="hidden" name="pageIndex" 			value="<%= resultPage.getCurrentPage() %>" />
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
				<h2>조사결과 상세보기</h2>
				
				<table class="list_tbl edit">
					<colgroup>
						<col width="140px;" />
						<col width="240px;" />
						<col width="140px;" />
						<col width="240px;" />
					</colgroup>
					<tr>
						<th class="letter2">구분</th>
						<td colspan="3"><%= pollMng.getPollFieldName() %></td>
					</tr>
					<tr>
						<th>의뢰부서</th>
						<td><%= pollMng.getPollAskName().replaceAll(", ", "<br />") %></td>
						<th>조사부서</th>
						<td><%= pollMng.getPollDepartmentName() %></td>
					</tr>
					<tr>
						<th class="letter2">제목</th>
						<td colspan="3"><%= pollMng.getPollTitle() %></td>	
					</tr>
					<tr>
						<th class="letter2">기간</th>
						<td colspan="3"><%= pollMng.getPollTermstartYmd() %> <%= pollMng.getPollTermstartHour() %>:<%= pollMng.getPollTermstartMinute() %> ~ <%= pollMng.getPollTermendYmd() %> <%= pollMng.getPollTermendHour() %>:<%= pollMng.getPollTermendMinute() %></td>
					</tr>
					<tr>
						<th>조사대상</th>
						<td><%= pollMng.getPollTarget() %></td>
						<th>참여자수</th>
						<td><%= pollMng.getPollSample() %>명</td>
					</tr>
					<tr>
						<th>조사기관</th>
						<td><%= pollMng.getPollCompanyName() %></td>
						<th>조사방법</th>
						<td><%= pollMng.getPollMethodName() %></td>
					</tr>
					<tr>
						<th>조사내용</th>
						<td colspan="3"><%= pollMng.getPollContents().replaceAll("\n", "<br />") %></td>
					</tr>
					<tr>
						<th>키워드</th>
						<td colspan="3"><%= pollMng.getPollKeyword() %></td>
					</tr>
					<tr>
						<th>일시중지</th>
						<td><%= pollMng.getPollStopyn() %></td>
						<th>홈페이지</th>
						<td><%= pollMng.getPollOpen().equals("Y") ? "공개" : "비공개" %></td>
					</tr>
					<tr>
						<th>공개등급</th>
						<td><%= pollMng.getPollGradeName() %></td>
						<th>결과보고서</th>
						<td><%= (pollMng.getPollHit() == 1) ? "공개" : "비공개" %></td>
					</tr>
					<tr>
						<th>소요예산</th>
						<td><%= pollMng.getPollBudget() %>천원</td>
						<th>계약방법</th>
						<td><%= pollMng.getPollContractName() %></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
						<%
							for (int i = 0; i < pollAddfileList.size(); i++) {
								if (i != 0) {
						%>
							<br />
						<%
								}
								
								PollAddfile pollAddfile = (PollAddfile)pollAddfileList.get(i);
						%>
							<a href="javascript:fncDownloadApplMng('<%= pollAddfile.getFilePath() %>', '<%= pollAddfile.getSavFname() %>', '<%= pollAddfile.getOrgFname() %>');"><%= pollAddfile.getOrgFname() %></a>
						<%
							}
						%>	
						</td>
					</tr>
					<tr>
						<th>도서관 문서</th>
						<td colspan="3">
						<%
							for (int i = 0; i < pollAddfile2List.size(); i++) {
								if (i != 0) {
						%>
							<br />
						<%
								}
								
								PollAddfile pollAddfile = (PollAddfile)pollAddfile2List.get(i);
						%>
							<a href="<%= pollAddfile.getSavFname() %>"><%= pollAddfile.getSavFname() %></a>
						<%
							}
						%>	
						</td>
					</tr> 
					<tr>
						<th>활동내역</th>
						<td colspan="3"><%= pollMng.getPollUtilize().replaceAll("\n", "<br />") %></td>
					</tr>
					<tr>
						<th>참고사항</th>
						<td colspan="3"><%= pollMng.getPollRefer().replaceAll("\n", "<br />") %></td>
					</tr>
					<tr>
						<th>관리자의견</th>
						<td colspan="3"><%= pollMng.getPollOpinion().replaceAll("\n", "<br />") %></td>
					</tr>
				</table><!-- .srch_tbl -->
				
				<div class="btnareaC">
					<%
					if (role.equals("GG101") || role.equals("GG102")) {
					%>
					<span class="btn radiusB"><a href="javascript:fncUpdateApplMngView();">수정</a></span>
					<span class="btn radiusB"><a href="javascript:fncDeleteApplMng();">삭제</a></span>
					<%
					}
					%>
					<span class="btn radiusB"><a href="javascript:fncListApplMng();">목록</a></span>
				</div>
				
			</div>
			<!-- // contents -->
			
		</div>
		<!-- // .inner_wrap -->
	</div>
	<!-- // #wrap -->

<!-- selected userId -->
<input type="hidden" name="pollIdx" value="<%= pollMng.getPollIdx() %>">

<input type="hidden" name="filePath" value="">
<input type="hidden" name="savFname" value="">
<input type="hidden" name="orgFname" value="">
<!-- end of list -->

</form>

<script language="JavaScript">
// document.getElementsByName("surveyMngName")[0].focus();
</script>

</body>
</html>
