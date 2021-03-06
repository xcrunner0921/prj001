<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="anyframe" uri="/WEB-INF/anyframe-message.tld" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sds.emp.common.EmpUtil" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.admincenter.service.ApplMngSearchVO" %>
<%@ page import="seoul.research.domain.PollMng" %>
<%@ page import="seoul.research.domain.PollAddfile" %>
<%@ page import="seoul.research.domain.PollPart" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>서울시 여론조사 시스템 관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
<%
	ApplMngSearchVO applMngSearchVO = (ApplMngSearchVO)request.getAttribute("applMngSearchVO");
	PollMng pollMng = (PollMng)request.getAttribute("pollMng");
	
	// ArrayList pollPartMfList = (ArrayList)request.getAttribute("pollPartMfList");
	// ArrayList pollPartAgeList = (ArrayList)request.getAttribute("pollPartAgeList");
	// ArrayList pollPartRegionList = (ArrayList)request.getAttribute("pollPartRegionList");

	ArrayList pollAddfileList = (ArrayList)request.getAttribute("pollAddfileList");
	ArrayList pollAddfile2List = (ArrayList)request.getAttribute("pollAddfile2List");

	ArrayList fieldCodeList = (ArrayList)request.getAttribute("fieldCodeList");
	ArrayList askCodeList = (ArrayList)request.getAttribute("askCodeList");
	// ArrayList departmentCodeList = (ArrayList)request.getAttribute("departmentCodeList");
	ArrayList companyCodeList = (ArrayList)request.getAttribute("companyCodeList");
	ArrayList methodCodeList = (ArrayList)request.getAttribute("methodCodeList");
	ArrayList gradeCodeList = (ArrayList)request.getAttribute("gradeCodeList");
	ArrayList contractCodeList = (ArrayList)request.getAttribute("contractCodeList");
	
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	// String pollTermstart = sdf.format(pollMng.getPollTermstart());
	// String pollTermend = sdf.format(pollMng.getPollTermend());
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
	    showOtherMonths: true,
	    selectOtherMonths: true,
		showOn: "button",
		buttonImage: "/img/admincenter/icon_month.gif",
		buttonImageOnly: true
	});

  	$("#pollTermendYmd").datepicker({
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
<!--
function fncLogOut() {
	if (confirm("정말 로그아웃하시겠습니까?")) {
		document.detailForm.target = "_top";
	    document.detailForm.action = "<c:url value='/logout.do' />";
	    document.detailForm.submit();	
    }
}

function fncUpdateApplMng() {
	if (!FormValidation(document.detailForm))
		return;

	var cnt = 0;
	$("#pollAsk option:selected").each(function() {
		cnt++;
	});
	if (cnt > 2) {
		alert("2 항목만 선택할 수 있습니다.");
		document.detailForm.pollAsk.focus();
		return;
	}
	
	cnt = 0;
	$("#pollCompany option:selected").each(function() {
		cnt++;
	});
	if (cnt > 2) {
		alert("2 항목만 선택할 수 있습니다.");
		document.detailForm.pollCompany.focus();
		return;
	}
	
	cnt = 0;
	$("#pollMethod option:selected").each(function() {
		cnt++;
	});
	if (cnt > 3) {
		alert("3 항목만 선택할 수 있습니다.");
		document.detailForm.pollMethod.focus();
		return;
	}
	
	// if (FormValidation(document.detailForm) != false) {		
	    document.detailForm.action = "<c:url value='/updateApplMng.do' />";
	    document.detailForm.submit();
	// } 
}

function fncDetailApplMng() {
	document.detailForm.action = "<c:url value='/detailApplMng.do' />";
    document.detailForm.submit();
}

function fncReset(sNo) {
	if (sNo == 1)
		document.detailForm.pollAddfile1.select();
	else if (sNo == 2)
		document.detailForm.pollAddfile2.select();
	else if (sNo == 3)
		document.detailForm.pollAddfile3.select();
	else if (sNo == 4)
		document.detailForm.pollAddfile4.select();
	else if (sNo == 5)
		document.detailForm.pollAddfile5.select();
	document.selection.clear();
}

function fncDelete(sNo) {
	if (sNo == 1) {
		document.getElementById("spsp1").style.visibility = "hidden";
		document.detailForm.delYn1.value = "Y";
	}
	else if (sNo == 2) {
		document.getElementById("spsp2").style.visibility = "hidden";
		document.detailForm.delYn2.value = "Y";
	}
	else if (sNo == 3) {
		document.getElementById("spsp3").style.visibility = "hidden";
		document.detailForm.delYn3.value = "Y";
	}
	else if (sNo == 4) {
		document.getElementById("spsp4").style.visibility = "hidden";
		document.detailForm.delYn4.value = "Y";
	}
	else if (sNo == 5) {
		document.getElementById("spsp5").style.visibility = "hidden";
		document.detailForm.delYn5.value = "Y";
	}
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
<form name="detailForm" method="post" enctype="multipart/form-data">
<input type="hidden" name="searchRegDatestart" 	value="<%= applMngSearchVO.getSearchRegDatestart() %>" />
<input type="hidden" name="searchRegDateend" 	value="<%= applMngSearchVO.getSearchRegDateend() %>" />
<input type="hidden" name="searchPollStop" 		value="<%= applMngSearchVO.getSearchPollStop() %>" />
<input type="hidden" name="searchKeyword" 		value="<%= applMngSearchVO.getSearchKeyword() %>" />

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
				<h2>조사결과 수정</h2>
				
				<p class="tr"><span class="red">*</span>표는 필수입력 항목입니다.</p>
				<table class="list_tbl edit">
					<colgroup>
						<col width="140px;" />
						<col width="240px;" />
						<col width="140px;" />
						<col width="240px;" />
					</colgroup>
					<tr>
						<th class="letter2"><span class="red">*</span>구분</th>
						<td colspan="3">
							<select name="pollField" id="" required fieldTitle="구분">
								<option value="">선택</option>
								<%
									for (int i = 0; i < fieldCodeList.size(); i++) {
										Code fieldCode = (Code)fieldCodeList.get(i);
								%>
								<option value="<%= fieldCode.getCodeId() %>" <%= pollMng.getPollField().equals(fieldCode.getCodeId()) ? "selected" : "" %>><%= fieldCode.getCodeName() %></option>
								<%
									}
								%>
							</select>					
						</td>
					</tr>
					<tr>
						<th>의뢰부서</th>
						<td>
							<select name="pollAsk" id="pollAsk" class="ip100" multiple style="height:80px;" fieldTitle="의뢰부서">
<!--								<option value="">선택</option>-->
								<%
									for (int i = 0; i < askCodeList.size(); i++) {
										Code askCode = (Code)askCodeList.get(i);
								%>
								<option value="<%= askCode.getCodeId() %>" <%= (pollMng.getPollAsk().indexOf(askCode.getCodeId()) != -1) ? "selected" : "" %>><%= askCode.getCodeName() %></option>
								<%
									}
								%>
							</select>
						</td>
						<th><span class="red">*</span>조사부서</th>
						<td>
							<select name="pollDepartment" id="" class="ip100" required fieldTitle="조사부서">
								<option value="">선택</option>
								<%
									for (int i = 0; i < askCodeList.size(); i++) {
										Code askCode = (Code)askCodeList.get(i);
								%>
								<option value="<%= askCode.getCodeId() %>" <%= pollMng.getPollDepartment().equals(askCode.getCodeId()) ? "selected" : "" %>><%= askCode.getCodeName() %></option>
								<%
									}
								%>
							</select>			
						</td>
					</tr>
					<tr>
						<th class="letter2"><span class="red">*</span>제목</th>
						<td colspan="3">
							<input type="text" name="pollTitle" id="" title="제목" class="ip100" value="<%= pollMng.getPollTitle() %>" required fieldTitle="제목" />				
						</td>	
					</tr>
					<tr>
						<th class="letter2"><span class="red">*</span>기간</th>
						<td colspan="3">
							<input type="text" name="pollTermstartYmd" id="pollTermstartYmd" title="등록일" class="ip80" value="<%= pollMng.getPollTermstartYmd() %>" readonly required fieldTitle="시작년월일" />
<!--							<a href=""><img src="../../img/adminCenter/icon_month.gif" alt="등록일 달력"/></a>-->&nbsp;
							<select name="pollTermstartHour" class="ip50" required fieldTitle="시작시">
							<%
								for (int i = 0; i < 24; i++) {
							%>
								<option value="<%= (i >= 10) ? "" + i : "0" + i %>" <%= (Integer.parseInt(pollMng.getPollTermstartHour()) == i) ? "selected" : "" %>><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>				
							</select>
							:
							<select name="pollTermstartMinute" class="ip50" required fieldTitle="시작분">
							<%
								for (int i = 0; i < 60; i++) {
							%>
								<option value="<%= (i >= 10) ? "" + i : "0" + i %>" <%= (Integer.parseInt(pollMng.getPollTermstartMinute()) == i) ? "selected" : "" %>><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>
							</select>
							~ 
							<input type="text" name="pollTermendYmd" id="pollTermendYmd" title="" class="ip80" value="<%= pollMng.getPollTermendYmd() %>" readonly required fieldTitle="종료년월일" />
<!--							<a href=""><img src="../../img/adminCenter/icon_month.gif" alt="등록일 달력"/></a>-->
							<select name="pollTermendHour" class="ip50" required fieldTitle="종료시">
							<%
								for (int i = 0; i < 24; i++) {
							%>
								<option value="<%= (i >= 10) ? "" + i : "0" + i %>" <%= (Integer.parseInt(pollMng.getPollTermendHour()) == i) ? "selected" : "" %>><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>				
							</select>
							:
							<select name="pollTermendMinute" class="ip50" required fieldTitle="종료분">
							<%
								for (int i = 0; i < 60; i++) {
							%>
								<option value="<%= (i >= 10) ? "" + i : "0" + i %>" <%= (Integer.parseInt(pollMng.getPollTermendMinute()) == i) ? "selected" : "" %>><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>조사대상</th>
						<td>
							<input type="text" name="pollTarget" id="" title="" class="ip100" value="<%= pollMng.getPollTarget() %>" required fieldTitle="조사대상" />
						</td>
						<th><span class="red">*</span>참여자수</th>
						<td>
							<input type="text" name="pollSample" id="" title="" class="ip100" value="<%= pollMng.getPollSample() %>" maxlength="5" num="i" required fieldTitle="참여자수" />
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>조사기관</th>
						<td>
							<select name="pollCompany" id="pollCompany" class="ip100" multiple style="height:80px;" required fieldTitle="조사기관">
<!--								<option value="">선택</option>-->
								<%
									for (int i = 0; i < companyCodeList.size(); i++) {
										Code companyCode = (Code)companyCodeList.get(i);
								%>
								<option value="<%= companyCode.getCodeId() %>" <%= (pollMng.getPollCompany().indexOf(companyCode.getCodeId()) != -1) ? "selected" : "" %>><%= companyCode.getCodeName() %></option>
								<%
									}
								%>
							</select>
						</td>
						<th><span class="red">*</span>조사방법</th>
						<td>
							<select name="pollMethod" id="pollMethod" class="ip100" multiple style="height:80px;" required fieldTitle="조사방법">
<!--								<option value="">선택</option>-->
								<%
									for (int i = 0; i < methodCodeList.size(); i++) {
										Code methodCode = (Code)methodCodeList.get(i);
								%>
								<option value="<%= methodCode.getCodeId() %>" <%= (pollMng.getPollMethod().indexOf(methodCode.getCodeId()) != -1) ? "selected" : "" %>><%= methodCode.getCodeName() %></option>
								<%
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>조사내용</th>
						<td colspan="3">
							<textarea name="pollContents" id="" cols="30" rows="5" class="ip100" required fieldTitle="조사내용"><%= pollMng.getPollContents() %></textarea>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>키워드</th>
						<td colspan="3">
							<input type="text" name="pollKeyword" id="" title="" class="ip100" value="<%= pollMng.getPollKeyword() %>" required fieldTitle="키워드" />
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>일시중지</th>
						<td>
							<select name="pollStopyn" id="" required fieldTitle="일시중지">
								<option value="Y" <%= pollMng.getPollStopyn().equals("Y") ? "selected" : "" %>>Y</option>
								<option value="N" <%= pollMng.getPollStopyn().equals("N") ? "selected" : "" %>>N</option>
							</select>
							<input type="hidden" name="pollStopcond" id="" value="1" />					
						</td>
						<th><span class="red">*</span>홈페이지</th>
						<td>
							<input type="radio" name="pollOpen" id="" value="Y" <%= pollMng.getPollOpen().equals("Y") ? "checked" : "" %> /><label for="">공개</label>
							<input type="radio" name="pollOpen" id="" value="N" <%= pollMng.getPollOpen().equals("N") ? "checked" : "" %> /><label for="">비공개</label>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>공개등급</th>
						<td>
							<select name="pollGrade" id="" required fieldTitle="공개등급">
								<option value="">선택</option>
								<%
									for (int i = 0; i < gradeCodeList.size(); i++) {
										Code gradeCode = (Code)gradeCodeList.get(i);
								%>
								<option value="<%= gradeCode.getCodeId() %>" <%= pollMng.getPollGrade().equals(gradeCode.getCodeId()) ? "selected" : "" %>><%= gradeCode.getCodeName() %></option>
								<%
									}
								%>
							</select>
						</td>
						<th><span class="red">*</span>결과보고서</th>
						<td>
							<input type="radio" name="pollHit" id="" value="1" <%= (pollMng.getPollHit() == 1) ? "checked" : "" %> /><label for="">공개</label>
							<input type="radio" name="pollHit" id="" value="0" <%= (pollMng.getPollHit() == 0) ? "checked" : "" %> /><label for="">비공개</label>
						</td>
					</tr>
					<tr>
						<th>소요예산</th>
						<td>
							<input type="text" name="pollBudget" id="" title="" class="ip80" value="<%= pollMng.getPollBudget() %>" num="i" /> 천원
						</td>
						<th><span class="red">*</span>계약방법</th>
						<td>
							<select name="pollContract" id="" required fieldTitle="계약방법">
								<option value="">선택</option>
								<%
									for (int i = 0; i < contractCodeList.size(); i++) {
										Code contractCode = (Code)contractCodeList.get(i);
								%>
								<option value="<%= contractCode.getCodeId() %>" <%= pollMng.getPollContract().equals(contractCode.getCodeId()) ? "selected" : "" %>><%= contractCode.getCodeName() %></option>
								<%
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
							<div class="half">
								<input type="file" name="pollAddfile1" id="" class="ip80p" />
								<span class="btn light"><a href="javascript:fncReset(1);">취소</a></span>
							</div>
<%
						if (pollAddfileList.size() > 0) {
							PollAddfile pollAddfile = (PollAddfile)pollAddfileList.get(0);
%>
							<div class="half tr">
								<span class="orange" id="spsp1"><a href="javascript:fncDownloadApplMng('<%= pollAddfile.getFilePath() %>', '<%= pollAddfile.getSavFname() %>', '<%= pollAddfile.getOrgFname() %>');"><%= pollAddfile.getOrgFname() %></a></span>							
								<span class="btn light"><a href="javascript:fncDelete(1);">삭제</a></span>
								<input type="hidden" name="orgFname1" id="" value="<%= pollAddfile.getOrgFname() %>" />
								<input type="hidden" name="savFname1" id="" value="<%= pollAddfile.getSavFname() %>" />
								<input type="hidden" name="filePath1" id="" value="<%= pollAddfile.getFilePath() %>" />
								<input type="hidden" name="fileSize1" id="" value="<%= pollAddfile.getFileSize() %>" />
							</div>
<%
						}
%>							
							<div class="half">
								<input type="file" name="pollAddfile2" id="" class="ip80p" />
								<span class="btn light"><a href="javascript:fncReset(2);">취소</a></span>
							</div>
<%
						if (pollAddfileList.size() > 1) {
							PollAddfile pollAddfile = (PollAddfile)pollAddfileList.get(1);
%>							
							<div class="half tr">
								<span class="orange" id="spsp2"><a href="javascript:fncDownloadApplMng('<%= pollAddfile.getFilePath() %>', '<%= pollAddfile.getSavFname() %>', '<%= pollAddfile.getOrgFname() %>');"><%= pollAddfile.getOrgFname() %></a></span> 
								<span class="btn light"><a href="javascript:fncDelete(2);">삭제</a></span>
								<input type="hidden" name="orgFname2" id="" value="<%= pollAddfile.getOrgFname() %>" />
								<input type="hidden" name="savFname2" id="" value="<%= pollAddfile.getSavFname() %>" />
								<input type="hidden" name="filePath2" id="" value="<%= pollAddfile.getFilePath() %>" />
								<input type="hidden" name="fileSize2" id="" value="<%= pollAddfile.getFileSize() %>" />
							</div>
<%
						}
%>							
							<div class="half">
								<input type="file" name="pollAddfile3" id="" class="ip80p" />
								<span class="btn light"><a href="javascript:fncReset(3);">취소</a></span>
							</div>
<%
						if (pollAddfileList.size() > 2) {
							PollAddfile pollAddfile = (PollAddfile)pollAddfileList.get(2);
%>							
							<div class="half tr">
								<span class="orange" id="spsp3"><a href="javascript:fncDownloadApplMng('<%= pollAddfile.getFilePath() %>', '<%= pollAddfile.getSavFname() %>', '<%= pollAddfile.getOrgFname() %>');"><%= pollAddfile.getOrgFname() %></a></span> 
								<span class="btn light"><a href="javascript:fncDelete(3);">삭제</a></span>
								<input type="hidden" name="orgFname3" id="" value="<%= pollAddfile.getOrgFname() %>" />
								<input type="hidden" name="savFname3" id="" value="<%= pollAddfile.getSavFname() %>" />
								<input type="hidden" name="filePath3" id="" value="<%= pollAddfile.getFilePath() %>" />
								<input type="hidden" name="fileSize3" id="" value="<%= pollAddfile.getFileSize() %>" />
							</div>
<%
						}
%>							
							<div class="half">
								<input type="file" name="pollAddfile4" id="" class="ip80p" />
								<span class="btn light"><a href="javascript:fncReset(4);">취소</a></span>
							</div>
<%
						if (pollAddfileList.size() > 3) {
							PollAddfile pollAddfile = (PollAddfile)pollAddfileList.get(3);
%>							
							<div class="half tr">
								<span class="orange" id="spsp4"><a href="javascript:fncDownloadApplMng('<%= pollAddfile.getFilePath() %>', '<%= pollAddfile.getSavFname() %>', '<%= pollAddfile.getOrgFname() %>');"><%= pollAddfile.getOrgFname() %></a></span> 
								<span class="btn light"><a href="javascript:fncDelete(4);">삭제</a></span>
								<input type="hidden" name="orgFname4" id="" value="<%= pollAddfile.getOrgFname() %>" />
								<input type="hidden" name="savFname4" id="" value="<%= pollAddfile.getSavFname() %>" />
								<input type="hidden" name="filePath4" id="" value="<%= pollAddfile.getFilePath() %>" />
								<input type="hidden" name="fileSize4" id="" value="<%= pollAddfile.getFileSize() %>" />
							</div>
<%
						}
%>							
							<div class="half">
								<input type="file" name="pollAddfile5" id="" class="ip80p" />
								<span class="btn light"><a href="javascript:fncReset(5);">취소</a></span>
							</div>
<%
						if (pollAddfileList.size() > 4) {
							PollAddfile pollAddfile = (PollAddfile)pollAddfileList.get(4);
%>							
							<div class="half tr">
								<span class="orange" id="spsp5"><a href="javascript:fncDownloadApplMng('<%= pollAddfile.getFilePath() %>', '<%= pollAddfile.getSavFname() %>', '<%= pollAddfile.getOrgFname() %>');"><%= pollAddfile.getOrgFname() %></a></span> 
								<span class="btn light"><a href="javascript:fncDelete(5);">삭제</a></span>
								<input type="hidden" name="orgFname5" id="" value="<%= pollAddfile.getOrgFname() %>" />
								<input type="hidden" name="savFname5" id="" value="<%= pollAddfile.getSavFname() %>" />
								<input type="hidden" name="filePath5" id="" value="<%= pollAddfile.getFilePath() %>" />
								<input type="hidden" name="fileSize5" id="" value="<%= pollAddfile.getFileSize() %>" />
							</div>
<%
						}
%>							
						</td>
					</tr>
					<tr>
						<th>도서관 문서</th>
						<td colspan="3" class="sec">
<%
						PollAddfile pollAddfile2 = null;
						if (pollAddfile2List.size() > 0) {
							pollAddfile2 = (PollAddfile)pollAddfile2List.get(0);
						}
%>
							<input type="text" name="pollAddfilea" id="" title="" class="ip100" value="<%= (pollAddfile2 != null) ? pollAddfile2.getSavFname() : "" %>" />
<%
						pollAddfile2 = null;
						if (pollAddfile2List.size() > 1) {
							pollAddfile2 = (PollAddfile)pollAddfile2List.get(1);
						}
%>							
							<input type="text" name="pollAddfileb" id="" title="" class="ip100" value="<%= (pollAddfile2 != null) ? pollAddfile2.getSavFname() : "" %>" />
<%
						pollAddfile2 = null;
						if (pollAddfile2List.size() > 2) {
							pollAddfile2 = (PollAddfile)pollAddfile2List.get(2);
						}
%>							
							<input type="text" name="pollAddfilec" id="" title="" class="ip100" value="<%= (pollAddfile2 != null) ? pollAddfile2.getSavFname() : "" %>" />
						</td>
					</tr> 
					<tr>
						<th>활동내역</th>
						<td colspan="3">
							<textarea name="pollUtilize" id="" cols="30" rows="5" class="ip100"><%= pollMng.getPollUtilize() %></textarea>
						</td>
					</tr>
					<tr>
						<th>참고사항</th>
						<td colspan="3">
							<textarea name="pollRefer" id="" cols="30" rows="5" class="ip100"><%= pollMng.getPollRefer() %></textarea>
						</td>
					</tr>
					<tr>
						<th>관리자의견</th>
						<td colspan="3">
							<textarea name="pollOpinion" id="" cols="30" rows="5" class="ip100"><%= pollMng.getPollOpinion() %></textarea>
						</td>
					</tr>
				</table><!-- .srch_tbl -->
				
				<div class="btnareaC">
					<span class="btn radiusB"><a href="javascript:fncUpdateApplMng();">저장</a></span>
					<span class="btn radiusB"><a href="javascript:fncDetailApplMng();">취소</a></span>
				</div>
				
			</div>
			<!-- // contents -->
			
		</div>
		<!-- // .inner_wrap -->
	</div>
	<!-- // #wrap -->

<!-- selected userId -->
<input type="hidden" name="pollIdx" value="<%= pollMng.getPollIdx() %>" />






<input type="hidden" name="delYn1" value="N" />
<input type="hidden" name="delYn2" value="N" />
<input type="hidden" name="delYn3" value="N" />
<input type="hidden" name="delYn4" value="N" />
<input type="hidden" name="delYn5" value="N" />

<input type="hidden" name="filePath" value="" />
<input type="hidden" name="savFname" value="" />
<input type="hidden" name="orgFname" value="" />
<!-- end of list -->

</form>

<script language="JavaScript">
// document.getElementsByName("surveyMngName")[0].focus();
</script>

</body>
</html>
