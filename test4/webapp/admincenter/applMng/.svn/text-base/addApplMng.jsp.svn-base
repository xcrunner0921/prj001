<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sds.emp.domain.Code" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>서울시 여론조사 시스템 관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
<%
	ArrayList fieldCodeList = (ArrayList)request.getAttribute("fieldCodeList");
	ArrayList askCodeList = (ArrayList)request.getAttribute("askCodeList");
	// ArrayList departmentCodeList = (ArrayList)request.getAttribute("departmentCodeList");
	ArrayList companyCodeList = (ArrayList)request.getAttribute("companyCodeList");
	ArrayList methodCodeList = (ArrayList)request.getAttribute("methodCodeList");
	ArrayList gradeCodeList = (ArrayList)request.getAttribute("gradeCodeList");
	ArrayList contractCodeList = (ArrayList)request.getAttribute("contractCodeList");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String today = sdf.format(date);

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

function fncAddApplMng() {
	if (!FormValidation(document.detailForm))
		return;
	
	// if (FormValidation(document.detailForm) != false) {
		document.detailForm.action = "<c:url value='/addApplMng.do' />";
	    document.detailForm.submit();
	// } 
}

function fncListApplMng() {
	document.detailForm.action = "<c:url value='/listApplMng.do' />";
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
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">
<form name="detailForm" method="post" enctype="multipart/form-data">

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
				<h2>조사결과 등록</h2>
				
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
								<option value="<%= fieldCode.getCodeId() %>"><%= fieldCode.getCodeName() %></option>
								<%
									}
								%>
							</select>					
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>의뢰부서</th>
						<td>
							<select name="pollAsk" id="" class="ip100" required fieldTitle="의뢰부서">
								<option value="">선택</option>
								<%
									for (int i = 0; i < askCodeList.size(); i++) {
										Code askCode = (Code)askCodeList.get(i);
								%>
								<option value="<%= askCode.getCodeId() %>"><%= askCode.getCodeName() %></option>
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
								<option value="<%= askCode.getCodeId() %>"><%= askCode.getCodeName() %></option>
								<%
									}
								%>
							</select>			
						</td>
					</tr>
					<tr>
						<th class="letter2"><span class="red">*</span>제목</th>
						<td colspan="3">
							<input type="text" name="pollTitle" id="" title="제목" class="ip100" required fieldTitle="제목" />				
						</td>	
					</tr>
					<tr>
						<th class="letter2"><span class="red">*</span>기간</th>
						<td colspan="3">
							<input type="text" name="pollTermstartYmd" id="pollTermstartYmd" title="등록일" class="ip80" value="<%= today %>" readonly required fieldTitle="시작년월일" />
<!--							<a href=""><img src="../../img/adminCenter/icon_month.gif" alt="등록일 달력"/></a>-->
							<select name="pollTermstartHour" class="ip50" required fieldTitle="시작시">
							<%
								for (int i = 0; i < 24; i++) {
							%>
								<option value="<%= i %>"><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>				
							</select>
							:
							<select name="pollTermstartMinute" class="ip50" required fieldTitle="시작분">
							<%
								for (int i = 0; i < 60; i++) {
							%>
								<option value="<%= i %>"><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>
							</select>
							~ 
							<input type="text" name="pollTermendYmd" id="pollTermendYmd" title="" class="ip80" value="<%= today %>" readonly required fieldTitle="종료년월일" />
<!--							<a href=""><img src="../../img/adminCenter/icon_month.gif" alt="등록일 달력"/></a>-->
							<select name="pollTermendHour" class="ip50" required fieldTitle="종료시">
							<%
								for (int i = 0; i < 24; i++) {
							%>
								<option value="<%= i %>"><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>				
							</select>
							:
							<select name="pollTermendMinute" class="ip50" required fieldTitle="종료분">
							<%
								for (int i = 0; i < 60; i++) {
							%>
								<option value="<%= i %>"><%= (i >= 10) ? "" + i : "0" + i %></option>
							<%
								}
							%>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>조사대상</th>
						<td>
							<input type="text" name="pollTarget" id="" title="" class="ip100" required fieldTitle="조사대상" />
						</td>
						<th><span class="red">*</span>참여자수</th>
						<td>
							<input type="text" name="pollSample" id="" title="" class="ip100" maxlength="5" num="i" required fieldTitle="참여자수" />
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>조사기관</th>
						<td>
							<select name="pollCompany" id="" required fieldTitle="조사기관">
								<option value="">선택</option>
								<%
									for (int i = 0; i < companyCodeList.size(); i++) {
										Code companyCode = (Code)companyCodeList.get(i);
								%>
								<option value="<%= companyCode.getCodeId() %>"><%= companyCode.getCodeName() %></option>
								<%
									}
								%>
							</select>
						</td>
						<th><span class="red">*</span>조사방법</th>
						<td>
							<select name="pollMethod" id="" required fieldTitle="조사방법">
								<option value="">선택</option>
								<%
									for (int i = 0; i < methodCodeList.size(); i++) {
										Code methodCode = (Code)methodCodeList.get(i);
								%>
								<option value="<%= methodCode.getCodeId() %>" <%= "CC002".equals(methodCode.getCodeId()) ? "selected" : "" %>><%= methodCode.getCodeName() %></option>
								<%
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>조사내용</th>
						<td colspan="3">
							<textarea name="pollContents" id="" cols="30" rows="5" class="ip100" required fieldTitle="조사내용"></textarea>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>키워드</th>
						<td colspan="3">
							<input type="text" name="pollKeyword" id="" title="" class="ip100" required fieldTitle="키워드" />
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>일시중지</th>
						<td>
							<select name="pollStopyn" id="" required fieldTitle="일시중지">
								<option value="Y" selected>Y</option>
								<option value="N">N</option>
							</select>
							<input type="hidden" name="pollStopcond" id="" value="1" />					
						</td>
						<th><span class="red">*</span>홈페이지</th>
						<td>
							<label for=""><input type="radio" name="pollOpen" id="" value="Y" />공개</label>
							<label for=""><input type="radio" name="pollOpen" id="" value="N" checked />비공개</label>
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
								<option value="<%= gradeCode.getCodeId() %>"><%= gradeCode.getCodeName() %></option>
								<%
									}
								%>
							</select>
						</td>
						<th><span class="red">*</span>결과보고서</th>
						<td>
							<input type="radio" name="pollHit" id="" value="1" /><label for="">공개</label>
							<input type="radio" name="pollHit" id="" value="0" checked /><label for="">비공개</label>	
						</td>
					</tr>
					<tr>
						<th>소요예산</th>
						<td>
							<input type="text" name="pollBudget" id="" title="" class="ip80" num="i" /> 천원
						</td>
						<th><span class="red">*</span>계약방법</th>
						<td>
							<select name="pollContract" id="" required fieldTitle="계약방법">
								<option value="">선택</option>
								<%
									for (int i = 0; i < contractCodeList.size(); i++) {
										Code contractCode = (Code)contractCodeList.get(i);
								%>
								<option value="<%= contractCode.getCodeId() %>"><%= contractCode.getCodeName() %></option>
								<%
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3" class="sec">
							<input type="file" name="pollAddfile1" id="" class="ip80p" /> <span class="btn light"><a href="javascript:fncReset(1);">취소</a></span><br />
							<input type="file" name="pollAddfile2" id="" class="ip80p" /> <span class="btn light"><a href="javascript:fncReset(2);">취소</a></span><br />
							<input type="file" name="pollAddfile3" id="" class="ip80p" /> <span class="btn light"><a href="javascript:fncReset(3);">취소</a></span><br />
							<input type="file" name="pollAddfile4" id="" class="ip80p" /> <span class="btn light"><a href="javascript:fncReset(4);">취소</a></span><br />
							<input type="file" name="pollAddfile5" id="" class="ip80p" /> <span class="btn light"><a href="javascript:fncReset(5);">취소</a></span>
						</td>
					</tr>
					<tr>
						<th>도서관 문서</th>
						<td colspan="3" class="sec">
							<input type="text" name="pollAddfilea" id="" title="" class="ip100" />
							<input type="text" name="pollAddfileb" id="" title="" class="ip100" />
							<input type="text" name="pollAddfilec" id="" title="" class="ip100" />
						</td>
					</tr> 
					<tr>
						<th>활동내역</th>
						<td colspan="3">
							<textarea name="pollUtilize" id="" cols="30" rows="5" class="ip100"></textarea>
						</td>
					</tr>
					<tr>
						<th>참고사항</th>
						<td colspan="3">
							<textarea name="pollRefer" id="" cols="30" rows="5" class="ip100"></textarea>
						</td>
					</tr>
					<tr>
						<th>관리자의견</th>
						<td colspan="3">
							<textarea name="pollOpinion" id="" cols="30" rows="5" class="ip100"></textarea>
						</td>
					</tr>
				</table><!-- .srch_tbl -->
				
				<div class="btnareaC">
					<span class="btn radiusB"><a href="javascript:fncAddApplMng();">저장</a></span>
					<span class="btn radiusB"><a href="javascript:fncListApplMng();">취소</a></span>
				</div>
				
			</div>
			<!-- // contents -->
			
		</div>
		<!-- // .inner_wrap -->
	</div>
	<!-- // #wrap -->

<!-- selected userId -->
<input type="hidden" name="pollMfyn" value="N">
<input type="hidden" name="pollAgeyn" value="N">
<input type="hidden" name="pollRegionyn" value="N">
<input type="hidden" name="pollGubun" value="LL102">
<input type="hidden" name="pollQuestStatus" value="Y">
<!-- end of list -->

</form>

<script language="JavaScript">
// document.getElementsByName("surveyMngName")[0].focus();
</script>

</body>
</html>
