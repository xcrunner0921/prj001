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
	ArrayList gradeCodeList = (ArrayList)request.getAttribute("gradeCodeList");
	ArrayList methodCodeList = (ArrayList)request.getAttribute("methodCodeList");
	ArrayList mfCodeList = (ArrayList)request.getAttribute("mfCodeList");
	ArrayList ageCodeList = (ArrayList)request.getAttribute("ageCodeList");
	ArrayList regionCodeList = (ArrayList)request.getAttribute("regionCodeList");
	ArrayList subjectCodeList = (ArrayList)request.getAttribute("subjectCodeList");

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

function fncAddSurveyMng() {
	if (!FormValidation(document.detailForm))
		return;
	
	var pollSample = Number(document.detailForm.pollSample.value);
	
	var pollMfyn = document.detailForm.pollMfyn.value;
	if (pollMfyn == "Y") {
		var porMfCodes = document.getElementsByName("porMfCode");
		var partNumberMfs = document.getElementsByName("partNumberMf");
		var partNumber = 0;
		for (i = 0; i < porMfCodes.length; i++) {
			if (porMfCodes[i].checked && partNumberMfs[i].value == "") {
				alert("이 항목은 필수 항목입니다.");
				partNumberMfs[i].focus();
				return;
			}
			partNumber += Number(partNumberMfs[i].value);
		}
		if (partNumber != pollSample) {
			alert("표본 할당 (성별) 의 합이 목표 표본수와 같지 않습니다.");
			return;
		}
	}
	
	var pollAgeyn = document.detailForm.pollAgeyn.value;
	if (pollAgeyn == "Y") {
		var porAgeCodes = document.getElementsByName("porAgeCode");
		var partNumberAges = document.getElementsByName("partNumberAge");
		partNumber = 0;
		for (i = 0; i < porAgeCodes.length; i++) {
			if (porAgeCodes[i].checked && partNumberAges[i].value == "") {
				alert("이 항목은 필수 항목입니다.");
				partNumberAges[i].focus();
				return;
			}
			partNumber += Number(partNumberAges[i].value);
		}
		if (partNumber != pollSample) {
			alert("표본 할당 (연령) 의 합이 목표 표본수와 같지 않습니다.");
			return;
		}
	}
	
	var pollRegionyn = document.detailForm.pollRegionyn.value;
	if (pollRegionyn == "Y") {
		var porRegionCodes = document.getElementsByName("porRegionCode");
		var partNumberRegions = document.getElementsByName("partNumberRegion");
		partNumber = 0;
		for (i = 0; i < porRegionCodes.length; i++) {
			if (porRegionCodes[i].checked && partNumberRegions[i].value == "") {
				alert("이 항목은 필수 항목입니다.");
				partNumberRegions[i].focus();
				return;
			}
			partNumber += Number(partNumberRegions[i].value);
		}
		if (partNumber != pollSample) {
			alert("표본 할당 (지역) 의 합이 목표 표본수와 같지 않습니다.");
			return;
		}
	}
	
	// if (FormValidation(document.detailForm) != false) {
		document.detailForm.action = "<c:url value='/addSurveyMng.do' />";
	    document.detailForm.submit();
	// } 
}

function fncListSurveyMng() {
	document.detailForm.action = "<c:url value='/listSurveyMng.do' />";
    document.detailForm.submit();
}

function clickPollMfyn() {
	var porMfCodes = document.getElementsByName("porMfCode");
	var partNumberMfs = document.getElementsByName("partNumberMf");

	for (i = 0; i < porMfCodes.length; i++)
		porMfCodes[i].checked = false;
	
	for (i = 0; i < partNumberMfs.length; i++) {
		partNumberMfs[i].value = "";
		partNumberMfs[i].disabled = true;
	}

	document.detailForm.pollMfyn.value = "N";
}

function clickPorMfCode(obj) {
	var porMfCodes = document.getElementsByName("porMfCode");

	var pollMftf = false;
	for (i = 0; i < porMfCodes.length; i++) {
		if (porMfCodes[i].checked) {
			pollMftf = true;
			break;
		}
	}
	
	document.detailForm.pollMfyn_.checked = !pollMftf;

	var partNumberMf = obj.nextSibling;
	while (partNumberMf.nodeName.toLowerCase() != "input")
		partNumberMf = partNumberMf.nextSibling;

	partNumberMf.value = "";
	partNumberMf.disabled = obj.checked ? false : true;

	document.detailForm.pollMfyn.value = pollMftf ? "Y" : "N";
}

function clickPollAgeyn() {
	var porAgeCodes = document.getElementsByName("porAgeCode");
	var partNumberAges = document.getElementsByName("partNumberAge");

	for (i = 0; i < porAgeCodes.length; i++)
		porAgeCodes[i].checked = false;
	
	for (i = 0; i < partNumberAges.length; i++) {
		partNumberAges[i].value = "";
		partNumberAges[i].disabled = true;
	}

	document.detailForm.pollAgeyn.value = "N";
}

function clickPorAgeCode(obj) {
	var porAgeCodes = document.getElementsByName("porAgeCode");

	var pollAgetf = false;
	for (i = 0; i < porAgeCodes.length; i++) {
		if (porAgeCodes[i].checked) {
			pollAgetf = true;
			break;
		}
	}
	
	document.detailForm.pollAgeyn_.checked = !pollAgetf;

	var partNumberAge = obj.nextSibling;
	while (partNumberAge.nodeName.toLowerCase() != "input")
		partNumberAge = partNumberAge.nextSibling;
	
	partNumberAge.value = "";
	partNumberAge.disabled = obj.checked ? false : true;

	document.detailForm.pollAgeyn.value = pollAgetf ? "Y" : "N";
}

function clickPollRegionyn() {
	var porRegionCodes = document.getElementsByName("porRegionCode");
	var partNumberRegions = document.getElementsByName("partNumberRegion");
	
	for (i = 0; i < porRegionCodes.length; i++)
		porRegionCodes[i].checked = false;
	
	for (i = 0; i < partNumberRegions.length; i++) {
		partNumberRegions[i].value = "";
		partNumberRegions[i].disabled = true;
	}
	
	document.detailForm.pollRegionyn.value = "N";
}

function clickPorRegionCode(obj) {
	var pollRegionyn_ = document.getElementById("pollRegionyn_");
	var porRegionCodes = document.getElementsByName("porRegionCode");

	var pollRegiontf = false;
	for (i = 0; i < porRegionCodes.length; i++) {
		if (porRegionCodes[i].checked) {
			pollRegiontf = true;
			break;
		}
	}
	
	document.detailForm.pollRegionyn_.checked = !pollRegiontf;

	var partNumberRegion = obj.nextSibling;
	while (partNumberRegion.nodeName.toLowerCase() != "input")
		partNumberRegion = partNumberRegion.nextSibling;
	
	partNumberRegion.value = "";
	partNumberRegion.disabled = obj.checked ? false : true;

	document.detailForm.pollRegionyn.value = pollRegiontf ? "Y" : "N";
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
					<h2>여론조사관리</h2>
					<ul>
						<li><a href="<c:url value='/listSurveyMng.do' />" class="active">여론조사관리</a></li>
						<li><a href="<c:url value='/listQuestMng.do' />">문항관리</a></li>
						<li><a href="<c:url value='/listAnswerMng.do' />">응답현황</a></li>
					</ul>
				</div>
			</div>	
			<!-- // #sidebar -->
			
			<!-- contents -->
			<div id="contents" class="survey">	
				<h2>여론조사 등록</h2>
				
				<p class="tr"><span class="red">*</span>표는 필수입력 항목입니다.</p>
				<table class="list_tbl edit">
					<colgroup>
						<col width="70px;" />
						<col width="70px;" />
						<col width="240px;" />
						<col width="140px;" />
						<col width="240px" />
					</colgroup>
					<tr>
						<th colspan="2" class="letter2"><span class="red">*</span>구분</th>
						<td>
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
					</tr>
					<tr>
						<th colspan="2" class="letter2"><span class="red">*</span>제목</th>
						<td colspan="3">
							<input type="text" name="pollTitle" id="" title="제목" class="ip100" required fieldTitle="제목" />				
						</td>	
					</tr>
					<tr>
						<th colspan="2" class="letter2"><span class="red">*</span>개요</th>
						<td colspan="3">
							<input type="text" name="pollSubtitle" id="" title="개요" class="ip100" required fieldTitle="개요" />				
						</td>	
					</tr>
					<tr>
						<th colspan="2" class="letter2"><span class="red">*</span>기간</th>
						<td colspan="3">
							<input type="text" name="pollTermstartYmd" id="pollTermstartYmd" title="등록일" class="ip80" value="<%= today %>" readonly required fieldTitle="시작년월일" />
<!--							<a href=""><img src="../../img/adminCenter/icon_month.gif" alt="등록일 달력"/></a>-->&nbsp;
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
<!--							<a href=""><img src="../../img/adminCenter/icon_month.gif" alt="등록일 달력"/></a>-->&nbsp;
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
						<th colspan="2"><span class="red">*</span>목표 표본수</th>
						<td>
							<input type="text" name="pollSample" id="" title="" class="ip80" maxlength="5" num="i" required fieldTitle="목표 표본수" />
						</td>
						<th><span class="red">*</span>일시중지</th>
						<td>
							<select name="pollStopyn" id="" required fieldTitle="일시중지">
								<option value="Y" selected>Y</option>
								<option value="N">N</option>
							</select>					
						</td>
					</tr>
					<tr>
						<th colspan="2"><span class="red">*</span>종료우선</th>
						<td>
							<input type="radio" name="pollStopcond" id="" value="1" checked /><label for="">기간</label>
							<input type="radio" name="pollStopcond" id="" value="2" /><label for="">목표 표본수</label>					
						</td>
						<th><span class="red">*</span>홈페이지</th>
						<td>
							<input type="radio" name="pollOpen" id="" value="Y" /><label for="">공개</label>
							<input type="radio" name="pollOpen" id="" value="N" checked /><label for="">비공개</label>	
						</td>
					</tr>
					<tr>
						<th colspan="2"><span class="red">*</span>공개등급</th>
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
						<th colspan="2"><span class="red">*</span>마일리지</th>
						<td>
							<input type="text" name="pollMileage" id="" title="" class="ip80" num="i" required fieldTitle="마일리지" /> 점
						</td>
						<th><span class="red">*</span>키워드</th>
						<td>
							<input type="text" name="pollKeyword" id="" title="" class="ip100" required fieldTitle="키워드" />
						</td>
					</tr>
					<tr>
						<th colspan="2">조사대상</th>
						<td>
							<input type="text" name="pollTarget" id="" title="" class="ip100" />
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
						<th rowspan="3">표본<br />할당</th>
						<th>성별</th>
						<td colspan="3" class="sec">
							<div>
								<input type="radio" name="pollMfyn_" id="pollMfyn_" value="N" checked onclick="clickPollMfyn();" /><label for="">제한없음</label>
							</div>
							<%
								for (int i = 0; i < mfCodeList.size(); i++) {
									Code mfCode = (Code)mfCodeList.get(i);
							%>
							<div>
								<input type="checkbox" name="porMfCode" id="" value="<%= mfCode.getCodeId() %>" onclick="clickPorMfCode(this);" /><label for=""><%= mfCode.getCodeName() %></label>
								<input type="text" name="partNumberMf" id="" title="" class="ip80" value="" disabled maxlength="5" num="i" />
							</div>
							<%
								}
							%>
						</td>
					</tr>
					<tr>
						<th>연령</th>
						<td colspan="3" class="sec">
							<div>
								<input type="radio" name="pollAgeyn_" id="pollAgeyn_" value="N" checked onclick="clickPollAgeyn();" /><label for="">제한없음</label>
							</div>
							<%
								for (int i = 0; i < ageCodeList.size(); i++) {
									Code ageCode = (Code)ageCodeList.get(i);
							%>
							<div>
								<input type="checkbox" name="porAgeCode" id="" value="<%= ageCode.getCodeId() %>" onclick="clickPorAgeCode(this);" /><label for=""><%= ageCode.getCodeName() %></label>
								<input type="text" name="partNumberAge" id="" title="" class="ip80" value="" disabled maxlength="5" num="i" />
								<%= "FF202".equals(ageCode.getCodeId()) ? "(19세 포함)" : "" %>
							</div>
							<%
								}
							%>
						</td>
					</tr>
					<tr>
						<th>지역</th>
						<td colspan="3" class="sec">
							<div>
								<input type="radio" name="pollRegionyn_" id="pollRegionyn_" value="N" checked onclick="clickPollRegionyn();" /><label for="">제한없음</label>
							</div>
							<%
								for (int i = 0; i < regionCodeList.size(); i++) {
									Code regionCode = (Code)regionCodeList.get(i);
							%>
							<div>
								<input type="checkbox" name="porRegionCode" id="" value="<%= regionCode.getCodeId() %>" onclick="clickPorRegionCode(this);" /><label for=""><%= regionCode.getCodeName() %></label>
								<input type="text" name="partNumberRegion" id="" title="" class="ip80" value="" disabled maxlength="5" num="i" />
							</div>	
							<%
								}
							%>
						</td>
					</tr>
					<tr>
						<th colspan="2"><span class="red">*</span>패널구분</th>
						<td colspan="3" class="sec">
						<%
							for (int i = 0; i < subjectCodeList.size(); i++) {
								Code subjectCode = (Code)subjectCodeList.get(i);
						%>
							<div>
								<input type="checkbox" name="pollSubject" id="" value="<%= subjectCode.getCodeId() %>" <%= "MM001".equals(subjectCode.getCodeId()) ? "checked" : "" %> /><label for=""><%= subjectCode.getCodeName() %></label>
							</div>
						<%
							}
						%>
						</td>
					</tr>					
					<tr>
						<th colspan="2"><span class="red">*</span>배경이미지</th>
						<td colspan="3" class="sec">
							<div>
								<input type="radio" name="pollBgimg" id="" value="A" checked /><label for="">A 형</label>
							</div>
							<div>
								<input type="radio" name="pollBgimg" id="" value="B" /><label for="">B 형</label>
							</div>
							<div>
								<input type="radio" name="pollBgimg" id="" value="C" /><label for="">C 형</label>
							</div>
						</td>
					</tr>
					<tr>
						<th colspan="2"><span class="red">*</span>조사내용</th>
						<td colspan="3">
							<textarea name="pollContents" id="" cols="30" rows="5" class="ip100" required fieldTitle="조사내용"></textarea>
						</td>
					</tr>
					<tr>
						<th colspan="2">안내문</th>
						<td colspan="3">
							<textarea name="pollIntro" id="" cols="30" rows="5" class="ip100"></textarea>
						</td>
					</tr>
					<tr>
						<th colspan="2">완료 후 메세지</th>
						<td colspan="3">
							<textarea name="pollMessage" id="" cols="30" rows="5" class="ip100"></textarea>
						</td>
					</tr>
					<tr>
						<th colspan="2">첨부파일</th>
						<td colspan="3" class="sec">
<!--							<div class="half">-->
								<input type="file" name="pollAddfile1" id="" class="ip80p" /> <span class="btn light"><a href="javascript:fncReset(1);">취소</a></span><br />
								<input type="file" name="pollAddfile2" id="" class="ip80p" /> <span class="btn light"><a href="javascript:fncReset(2);">취소</a></span><br />
								<input type="file" name="pollAddfile3" id="" class="ip80p" /> <span class="btn light"><a href="javascript:fncReset(3);">취소</a></span><br />
								<input type="file" name="pollAddfile4" id="" class="ip80p" /> <span class="btn light"><a href="javascript:fncReset(4);">취소</a></span><br />
								<input type="file" name="pollAddfile5" id="" class="ip80p" /> <span class="btn light"><a href="javascript:fncReset(5);">취소</a></span>
<!--							</div>-->
<!--							<div class="half tr">-->
<!--								<span class="orange">000.ZIP</span>							-->
<!--								<span class="btn light"><a href="#">삭제</a></span>-->
<!--							</div>-->
						</td>
					</tr>
				</table><!-- .srch_tbl -->
				
				<div class="btnareaC">
<!--					<span class="btn radiusB"><a href="#">목록</a></span>-->
					<span class="btn radiusB"><a href="javascript:fncAddSurveyMng();">저장</a></span>
					<span class="btn radiusB"><a href="javascript:fncListSurveyMng();">취소</a></span>
				</div>
				
			</div>
			<!-- // contents -->
			
		</div>
		<!-- // .inner_wrap -->
	</div>
	<!-- // #wrap -->

<!-- selected userId -->
<input type="hidden" name="pollDepartment" value="AA160">
<!--<input type="hidden" name="pollGrade" value="GG901">-->
<input type="hidden" name="pollMfyn" value="N">
<input type="hidden" name="pollAgeyn" value="N">
<input type="hidden" name="pollRegionyn" value="N">
<input type="hidden" name="pollGubun" value="LL101">
<input type="hidden" name="pollQuestStatus" value="N">
<!-- end of list -->

</form>

<script language="JavaScript">
// document.getElementsByName("surveyMngName")[0].focus();
</script>

</body>
</html>
