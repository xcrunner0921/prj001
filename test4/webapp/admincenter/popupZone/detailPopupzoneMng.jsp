<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.domain.PopupMng" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>

<%
	ArrayList PorderList = (ArrayList)request.getAttribute("PorderList");
	PopupMng detailPopupzone = (PopupMng)request.getAttribute("detailPopupzone");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String today = sdf.format(date);
	
	//out.println("session : " + (String)session.getAttribute("userId"));
	String role = (String)session.getAttribute("role");
%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사 시스템 관리자 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
<script src="/include/jquery-latest.min.js"></script>
<script src="/include/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
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
	
	$("#strDateYmd").datepicker({
	    showOtherMonths: true,
	    selectOtherMonths: true,
		showOn: "button",
		buttonImage: "/img/admincenter/icon_month.gif",
		buttonImageOnly: true
	});

  	$("#endDateYmd").datepicker({
	    showOtherMonths: true,
	    selectOtherMonths: true,
		showOn: "button",
		buttonImage: "/img/admincenter/icon_month.gif",
		buttonImageOnly: true
	});
});
</script>

<script language="javascript" src="<c:url value='/sample/javascript/CommonScript.js'/>"></script>
<script language="JavaScript">
function fncLogOut() {
	if (confirm("정말 로그아웃하시겠습니까?")) {
		document.frm.target = "_top";
	    document.frm.action = "<c:url value='/logout.do' />";
	    document.frm.submit();	
    }
}

//삭제
function fncDeletePopMng(str){
	if(confirm("팝업존을 삭제하시겠습니까?")){
		$("#SNo").val(str);	
		document.frm.action = "<c:url value='/deletePopupzoneMng.do' />";
		document.frm.submit();
	}
}

//수정
function modify(str){
	$("#SNo").val(str);
	document.frm.action="<c:url value='/modifyPopupzoneMng.do'/>";
   	document.frm.submit();	
}

//취소
function cancel(){
   	document.frm.action = "<c:url value='/listPopupzoneMng.do' />";
   	document.frm.submit();
}

function fncDownloadPopupzoneMng(filePath, savFname, orgFname) {
	document.frm.filePath.value = filePath;
	document.frm.savFname.value = savFname;
	document.frm.orgFname.value = orgFname;
	document.frm.action = "<c:url value='/downloadPopupzoneMng.do' />";
    document.frm.submit();
}
</script>
</head>

<body>
<form id="frm" name="frm" method="post" enctype="multipart/form-data">
<input type="hidden" id="SNo" name="SNo" value="<%=detailPopupzone.getSNo() %>" />
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
					<h2>팝업존관리</h2>
					<ul>
						<li><a href="<c:url value='/listPopupzoneMng.do' />" class="active">팝업존관리</a></li>
					</ul>
				</div>
			</div>	
			<!-- // #sidebar -->
			
			<!-- contents -->
			<div id="contents" class="survey">	
				<h2>팝업존 상세보기</h2>
				
				<table class="list_tbl edit">
					<colgroup>
						<col width="100px;" />
						<col width="*" />
						<col width="100px;" />
						<col width="80px;" />
					</colgroup>
					<tr>
						<th class="letter2">제목</th>
						<td colspan="3"><%= detailPopupzone.getTitle() %></td>
					</tr>
					<tr>
						<th class="letter2">기간</th>
						<td><%= detailPopupzone.getStrDateYmd() %>&nbsp;&nbsp;<%=detailPopupzone.getStrDateHour() %>:<%=detailPopupzone.getStrDateMinute() %>&nbsp;~&nbsp;<%= detailPopupzone.getEndDateYmd() %>&nbsp;&nbsp;<%=detailPopupzone.getEndDateHour() %>:<%=detailPopupzone.getEndDateMinute() %>
						</td>
						<th class="letter2">순서</th>
						<td><%=detailPopupzone.getPorder() %></td>
					</tr>
					<tr>
						<th>링크주소</th>
						<td colspan="3"><%= detailPopupzone.getLinkUrl() %></td>	
					</tr>
					<tr>
						<th>참고사항</th>
						<td colspan="3"><%= detailPopupzone.getPdesc() %></td>
					</tr>
					<tr>
						<th class="letter3"></span>이미지</th>
						<td colspan="3" class="sec">
							<a href="javascript:fncDownloadPopupzoneMng('<%= detailPopupzone.getFilePath() %>', '<%= detailPopupzone.getSavFname() %>', '<%= detailPopupzone.getOrgFname() %>');"><%= detailPopupzone.getOrgFname() %></a>
						</td>
					</tr>
				</table><!-- .srch_tbl -->
				
				<div class="btnareaC">
					<%
					if (role.equals("GG101") || role.equals("GG102")) {
					%>
					<span class="btn radiusB"><a href="javascript:modify('<%=detailPopupzone.getSNo() %>');">수정</a></span>
					<span class="btn radiusB"><a href="javascript:fncDeletePopMng('<%=detailPopupzone.getSNo() %>');">삭제</a></span>
					<%
					}
					%>
					<span class="btn radiusB"><a href="javascript:cancel();">목록</a></span>
				</div>
				
			</div>
			<!-- // contents -->
			
		</div>
		<!-- // .inner_wrap -->
	</div>
	<!-- // #wrap -->

<input type="hidden" name="filePath" value="">
<input type="hidden" name="savFname" value="">
<input type="hidden" name="orgFname" value="">

</form>
</body>
</html>
