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
	PopupMng modifyPopupzone = (PopupMng)request.getAttribute("modifyPopupzone");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String today = sdf.format(date);
	
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

function cancel(){
   	document.frm.action = "<c:url value='/listPopupzoneMng.do' />";
   	document.frm.submit();
}

function fncAddChk(){

	if($("#title").val() == ""){
		alert("제목은 필수 항목입니다.");
		$("#title").focus();
		return
	}
	if($("#porder").val() == ""){
		alert("순서는 필수 항목입니다.");
		$("#porder").focus();
		return
	}

	if($("#linkUrl").val() == ""){
		alert("링크주소는 필수 항목입니다.");
		$("#linkUrl").focus();
		return
	}
	 
	if($("#pdesc").val() == ""){
		alert("참고사항은 필수 항목입니다.");
		$("#pdesc").focus();
		return
	}
	
	fncUpdatePopupzoneMng();
}

function fncUpdatePopupzoneMng(){

	
    document.frm.action = "<c:url value='/updatePopupzoneMng.do' />";
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
<input type="hidden" id="SNo" name="SNo" value="<%= modifyPopupzone.getSNo() %>" />
<input type="hidden" id="beforePorder" name="beforePorder" value="<%= modifyPopupzone.getPorder() %>" />
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
				<h2>팝업존 수정</h2>
				
				<p class="tr"><span class="red">*</span>표는 필수입력 항목입니다.</p>
				<table class="list_tbl edit">
					<colgroup>
						<col width="100px;" />
						<col width="*" />
						<col width="100px;" />
						<col width="80px;" />
					</colgroup>
					<tr>
						<th class="letter2"><span class="red">*</span>제목</th>
						<td colspan="3">
							<input type="text" name="title" id="title" title="제목" class="ip100" value="<%= modifyPopupzone.getTitle() %>" />		
						</td>
					</tr>
					<tr>
						<th class="letter2"><span class="red">*</span>기간</th>
						<td>
							<input type="text" name="strDateYmd" id="strDateYmd" title="등록일" class="ip80" value="<%= modifyPopupzone.getStrDateYmd() %>" readonly required fieldTitle="시작년월일" />&nbsp;
							<select name="strDateHour" id="strDateHour" style="width:40px" required fieldTitle="시작시">
							<%
								for (int i = 0; i < 24; i++) {
							%>
								<option value="<%= (i >= 10) ? "" + i : "0" + i %>" <%= (Integer.parseInt(modifyPopupzone.getStrDateHour()) == i) ? "selected" : "" %>><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>		
							</select>
							:
							<select name="strDateMinute" id="strDateMinute" style="width:40px" required fieldTitle="시작분">
							<%
								for (int i = 0; i < 60; i++) {
							%>
								<option value="<%= (i >= 10) ? "" + i : "0" + i %>" <%= (Integer.parseInt(modifyPopupzone.getStrDateMinute()) == i) ? "selected" : "" %>><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>
							</select>~
							&nbsp;<input type="text" name="endDateYmd" id="endDateYmd" title="" class="ip80" value="<%= modifyPopupzone.getEndDateYmd() %>" readonly required fieldTitle="종료년월일" />&nbsp;
							<select name="endDateHour" id="endDateHour" style="width:40px" required fieldTitle="종료시">
							<%
								for (int i = 0; i < 24; i++) {
							%>
								<option value="<%= (i >= 10) ? "" + i : "0" + i %>" <%= (Integer.parseInt(modifyPopupzone.getEndDateHour()) == i) ? "selected" : "" %>><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>				
							</select>
							:
							<select name="endDateMinute" id="endDateMinute" style="width:40px" required fieldTitle="종료분">
							<%
								for (int i = 0; i < 60; i++) {
							%>
								<option value="<%= (i >= 10) ? "" + i : "0" + i %>" <%= (Integer.parseInt(modifyPopupzone.getEndDateMinute()) == i) ? "selected" : "" %>><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>
							</select>
						</td>
						<th class="letter2"><span class="red">*</span>순서</th>
						<td>
							<select name="porder" id="porder" style="width:40px" >
							<%
							if(PorderList.size() > 0){
								int j = 0;
								int k = 0;	
								PopupMng popupMng;
								
								k = modifyPopupzone.getPorder();
								
								for (int i = 0; i < PorderList.size(); i++){
									
									System.out.println("====>>  PorderList.size() : " + PorderList.size() + "    =====   i : " + i );
									
							%>
								<option value="<%= (i+1) %>" <%= k == (i+1) ? "selected" : "" %>><%= (i+1) %></option>
							<%
								}
							}
							%>
							</select>			
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>링크주소</th>
						<td colspan="3">
							<input type="text" name="linkUrl" id="linkUrl" title="제목" class="ip100" value="<%= modifyPopupzone.getLinkUrl() %>" />				
						</td>	
					</tr>
					<tr>
						<th><span class="red">*</span>참고사항</th>
						<td colspan="3">
							<textarea name="pdesc" id="pdesc" cols="30" rows="5" class="ip100" ><%= modifyPopupzone.getPdesc() %></textarea>
						</td>
					</tr>
					<tr>
						<th class="letter3"><span class="red">*</span>이미지</th>
						<td colspan="3">
							<input type="file" name="popupzoneAddfile" id="popupzoneAddfile" style="width:400px;"/>
							<a href="javascript:fncDownloadPopupzoneMng('<%= modifyPopupzone.getFilePath() %>', '<%= modifyPopupzone.getSavFname() %>', '<%= modifyPopupzone.getOrgFname() %>');"><%= modifyPopupzone.getOrgFname() %></a>
						</td>
					</tr>
				</table><!-- .srch_tbl -->
				
				<div class="btnareaC">
					<span class="btn radiusB"><a href="javascript:fncAddChk();">저장</a></span>
					<span class="btn radiusB"><a href="javascript:cancel();">취소</a></span>
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
