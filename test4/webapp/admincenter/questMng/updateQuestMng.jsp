<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.domain.PollMng" %>
<%@ page import="seoul.research.domain.QuestMng" %>
<%@ page import="seoul.research.domain.ExampleMng" %>
<%@ page import="seoul.research.domain.ImgMng" %>
<%@ page import="seoul.research.domain.QuestAddfile" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>서울시 여론조사 시스템 관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
<%
	PollMng pollMng = (PollMng)request.getAttribute("pollMng");

	QuestMng questMng = (QuestMng)request.getAttribute("questMng");
	
	ArrayList graphCodeList = (ArrayList)request.getAttribute("graphCodeList");
	ArrayList questCodeList = (ArrayList)request.getAttribute("questCodeList");
	ArrayList examCodeList = (ArrayList)request.getAttribute("examCodeList");
	ArrayList choiceCodeList = (ArrayList)request.getAttribute("choiceCodeList");

	String role = (String)session.getAttribute("role");
%>
<script language="javascript" src="<c:url value='/sample/javascript/CommonScript.js'/>"></script>
<script language="JavaScript">
<!--
function __NodeList__(length) {
	if (length) {
		for (var i = 0; i < length; i++)
			this[i] = 0;
		this.length = parseInt(length);
	}
	else
    	this.length = 0;
}

__NodeList__.prototype = {
	item: function(index) {
	    return this[index];
	},
	__push: function(node) {
		var __index = this.length;
		this[__index] = node;
		this.length++;
	},
	toString: function() {
		return "[object NodeList]";
	}
}
/*
if (!document.getElementById) {
	if (document.querySelector) {
		document.getElementById = function (id) {
			return document.querySelectorAll("#" + id);
		}
	}
	else if (document.getElementsByTagName) {
		document.getElementById = function (id) {
			var __tags = document.getElementsByTagName("*");
			for (var i = 0; i < __tags.length; i++) {
				if (__tags[i].id == id)
					return __tags[i];
			}
		}
	}
}
*/
if (!document.getElementsByClassName) {
	if (document.querySelector) {
		document.getElementsByClassName = function (className) {
			return document.querySelectorAll("." + className);
		}
	}
	else if (document.getElementsByTagName) {
		document.getElementsByClassName = function (className) {
			var __tags = document.getElementsByTagName("*");
			var __nodeLists = new __NodeList__();
			for (var i = 0; i < __tags.length; i++) {
				if (__tags[i].className == className)
					__nodeLists.__push(__tags[i]);
			}
			return __nodeLists;
		}
	}
} 

function fncLogOut() {
	if (confirm("정말 로그아웃하시겠습니까?")) {
		document.detailForm.target = "_top";
	    document.detailForm.action = "<c:url value='/logout.do' />";
	    document.detailForm.submit();	
    }
}

function fncUpdateQuestMng() {
	if (!FormValidation(document.detailForm))
		return;

	var choiceCnt = 0;
	var examNos = null;
	if (document.detailForm.questType.value == "HH102" && document.detailForm.examType.value == "II101") {
		if (document.detailForm.choiceCnt21.value == "") {
			alert("이 항목은 필수 항목입니다.");
			document.detailForm.choiceCnt21.focus();
			return;
		}
		choiceCnt = Number(document.detailForm.choiceCnt21.value);
		examNos = document.getElementsByName("examNo21");
		if (choiceCnt > examNos.length) {
			alert("선택 수가 문항 수보다 작지 않습니다.");
			document.detailForm.choiceCnt21.focus();
			return;
		}
	}
	else if (document.detailForm.questType.value == "HH104" && document.detailForm.examType.value == "II101") {
		if (document.detailForm.choiceCnt41.value == "") {
			alert("이 항목은 필수 항목입니다.");
			document.detailForm.choiceCnt41.focus();
			return;
		}
		choiceCnt = Number(document.detailForm.choiceCnt41.value);
		examNos = document.getElementsByName("examNo41");
		if (choiceCnt > examNos.length) {
			alert("선택 수가 문항 수보다 작지 않습니다.");
			document.detailForm.choiceCnt41.focus();
			return;
		}
	}
	else if (document.detailForm.questType.value == "HH102" && document.detailForm.examType.value == "II102") {
		if (document.detailForm.choiceCnt21.value == "") {
			alert("이 항목은 필수 항목입니다.");
			document.detailForm.choiceCnt21.focus();
			return;
		}
		choiceCnt = Number(document.detailForm.choiceCnt21.value);
		examNos = document.getElementsByName("examNo22");
		if (choiceCnt > examNos.length) {
			alert("선택 수가 문항 수보다 작지 않습니다.");
			document.detailForm.choiceCnt21.focus();
			return;
		}
	}
	else if (document.detailForm.questType.value == "HH104" && document.detailForm.examType.value == "II102") {
		if (document.detailForm.choiceCnt41.value == "") {
			alert("이 항목은 필수 항목입니다.");
			document.detailForm.choiceCnt41.focus();
			return;
		}
		choiceCnt = Number(document.detailForm.choiceCnt41.value);
		examNos = document.getElementsByName("examNo42");
		if (choiceCnt > examNos.length) {
			alert("선택 수가 문항 수보다 작지 않습니다.");
			document.detailForm.choiceCnt41.focus();
			return;
		}
	}

	if (document.detailForm.examType.value == "II101") {
		var examTitles = null;
		if (document.detailForm.questType.value == "HH101")
			examTitles = document.getElementsByClassName("examTitle11");
		else if (document.detailForm.questType.value == "HH102")
			examTitles = document.getElementsByClassName("examTitle21");
		else if (document.detailForm.questType.value == "HH104")
			examTitles = document.getElementsByClassName("examTitle41");
		if (examTitles != null) {
			for (i = 0; i < examTitles.length; i++) {
				if (examTitles[i].value == "") {
					alert("이 항목은 필수 항목입니다.");
					examTitles[i].focus();
					return;
				}
			}
		}
	}
	
	var filterYn = document.detailForm.filterYn.value;
	if (filterYn == "Y") {
		if (document.detailForm.filterAnswer.value == "") {
			alert("이 항목은 필수 항목입니다.");
			document.detailForm.filterAnswer.focus();
			return;
		}
	}
	
	var mergeType = document.detailForm.mergeType.value;
	if ((mergeType == "1" || mergeType == "3") && examNos.length != 4 ||
		mergeType == "2" && examNos.length != 5) {
		alert("보기병합이 문항 수와 맞지 않습니다.");
		document.detailForm.mergeType.focus();
		return;
	}
	if (mergeType != "9") {
		if (document.detailForm.mergeTitle.value == "") {
			alert("이 항목은 필수 항목입니다.");
			document.detailForm.mergeTitle.focus();
			return;
		}
		else if (document.detailForm.mergeTitle2.value == "") {
			alert("이 항목은 필수 항목입니다.");
			document.detailForm.mergeTitle2.focus();
			return;
		}
	}
	if (mergeType == "2") {
		if (document.detailForm.mergeTitle3.value == "") {
			alert("이 항목은 필수 항목입니다.");
			document.detailForm.mergeTitle3.focus();
			return;
		}
	}
	
	var questNo = Number(document.detailForm.questNo.value);
	
	var branchYn = document.detailForm.branchYn.value;
	if (branchYn == "Y") {
		var branchNos = null;
		if (document.detailForm.examType.value == "II101")
			brachNos = document.getElementsByName("branchNo");
		else if (document.detailForm.examType.value == "II102")
			brachNos = document.getElementsByName("branchNo2");
		for (i = 0; i < brachNos.length; i++) {
			if (brachNos[i].value == "") {
				alert("이 항목은 필수 항목입니다.");
				brachNos[i].focus();
				return;
			}
			if (Number(brachNos[i].value) <= questNo) {
				alert("분기할 문항 번호가 (현재) 문항 번호보다 크지 않습니다.");
				return;
			}
		}
	}
	
	// if (FormValidation(document.detailForm) != false) {
		document.detailForm.action = "<c:url value='/updateQuestMng.do' />";
	    document.detailForm.submit();
	// }
}

function fncDetailQuestMng(pollIdx) {
	document.detailForm.pollIdx.value = pollIdx;
   	document.detailForm.action = "<c:url value='/detailQuestMng.do' />";
   	document.detailForm.submit();
}

var div11 = '<div> ' +
	'<input type="radio" name="" id="" /><label for="" name="la11" class="la11">1</label> ' +
	'<input type="text" name="examTitle11_1" id="" class="ip160 examTitle11" /> ' +
	'<span class="btn lightB"><a href="javascript:addExam11(1);" name="aa11">추가</a></span> ' +
	'<span class="btn lightB"><a href="javascript:deleteExam11(1);" name="ad11">삭제</a></span> ' +
	'<span class="btn dark sp11" name="sp11"><a href="javascript:addEtcExam11();">기타추가</a></span> ' +
	'<input type="hidden" name="examNo11" id="" value="1" /> ' +
	'</div>';
var div2x = '<div> ' +
	'<label for="">선택 수</label> ' +
	'<input type="text" name="choiceCnt21" id="" class="ip50" value="1" num="i" required_ fieldTitle="선택 수" /> 개 ' +
	'<select name="choiceRange21" id="" required fieldTitle="선택 범위"> ' +
	<%
		for (int i = 0; i < choiceCodeList.size(); i++) {
			Code choiceCode = (Code)choiceCodeList.get(i);
	%>
	'<option value="<%= choiceCode.getCodeId() %>"><%= choiceCode.getCodeName() %></option> ' +
	<%
		}
	%>
	'</select> ' +
	'</div>';
var div21 = '<div> ' +
	'<input type="checkbox" name="" id="" /><label for="" name="la21" class="la21">1</label> ' +
	'<input type="text" name="examTitle21_1" id="" class="ip160 examTitle21" /> ' +
	'<span class="btn lightB"><a href="javascript:addExam21(1);" name="aa21">추가</a></span> ' +
	'<span class="btn lightB"><a href="javascript:deleteExam21(1);" name="ad21">삭제</a></span> ' +
	'<span class="btn dark sp21" name="sp21"><a href="javascript:addEtcExam21();">기타추가</a></span> ' +
	'<input type="hidden" name="examNo21" id="" value="1" /> ' +
	'</div>';
var textarea31 = '<textarea name="" id="" cols="30" rows="3" class="ip100" readonly></textarea>';
var div4x = '<div> ' +
	'<label for="">선택 수</label> ' +
	'<input type="text" name="choiceCnt41" id="" class="ip50" value="1" num="i" required_ fieldTitle="선택 수" /> 개 ' +
	'<input type="hidden" name="choiceRange41" id="" value="KK103" /> ' +
	'</div>';
var div41 = '<div> ' +
	'<input type="checkbox" name="" id="" /><label for="" name="la41" class="la41">1</label> ' +
	'<input type="text" name="examTitle41_1" id="" class="ip160 examTitle41" /> ' +
	'<span class="btn lightB"><a href="javascript:addExam41(1);" name="aa41">추가</a></span> ' +
	'<span class="btn lightB"><a href="javascript:deleteExam41(1);" name="ad41">삭제</a></span> ' +
	'<span class="btn dark sp41" name="sp41"><a href="javascript:addEtcExam41();">기타추가</a></span> ' +
	'<input type="hidden" name="examNo41" id="" value="1" /> ' +
	'</div>';

var div12 = '<div> ' +
	'<input type="radio" name="" id="" /><label for="la12" class="la12">1</label> ' +
	'<span class="thumb1"><img src="/img/admincenter/empty.jpg" alt="문항유형이미지" /></span> ' +
	'<input type="file" name="imgMng12_1" id="" class="ip160 imgMng12" /> ' +
	'<input type="text" name="fileDesc12_1" id="" class="ip120 fileDesc12" /> ' +
	'<span class="btn lightB"><a href="javascript:addExam12(1);" name="aa12">추가</a></span> ' +
	'<span class="btn lightB"><a href="javascript:deleteExam12(1);" name="ad12">삭제</a></span> ' +
	'<!--								<span class="btn dark sp12" name="sp12"><a href="javascript:addEtcExam12();">기타추가</a></span>	--> ' +
	'<input type="hidden" name="examNo12" id="" value="1" /> ' +
	' ' +
	'<input type="hidden" name="orgFname12_1" id="" value="" class="orgFname12" />' +
	'<input type="hidden" name="savFname12_1" id="" value="" class="savFname12" />' +
	'<input type="hidden" name="filePath12_1" id="" value="" class="filePath12" />' +
	'<input type="hidden" name="fileSize12" id="" value="" />' +
	'</div>';
var div22 = '<div> ' +
	'<input type="checkbox" name="" id="" /><label for="" name="la22" class="la22">1</label> ' +
	'<span class="thumb1"><img src="/img/admincenter/empty.jpg" alt="문항유형이미지" /></span> ' +
	'<input type="file" name="imgMng22_1" id="" class="ip160 imgMng22" /> ' +
	'<input type="text" name="fileDesc22_1" id="" class="ip120 fileDesc22" /> ' +
	'<span class="btn lightB"><a href="javascript:addExam22(1);" name="aa22">추가</a></span> ' +
	'<span class="btn lightB"><a href="javascript:deleteExam22(1);" name="ad22">삭제</a></span> ' +
	'<!--								<span class="btn dark sp22" name="sp22"><a href="javascript:addEtcExam22();">기타추가</a></span>	--> ' +
	'<input type="hidden" name="examNo22" id="" value="1" /> ' +
	' ' +
	'<input type="hidden" name="orgFname22_1" id="" value="" class="orgFname22" />' +
	'<input type="hidden" name="savFname22_1" id="" value="" class="savFname22" />' +
	'<input type="hidden" name="filePath22_1" id="" value="" class="filePath22" />' +
	'<input type="hidden" name="fileSize22" id="" value="" />' +
	'</div>';
var div42 = '<div> ' +
	'<input type="checkbox" name="" id="" /><label for="" name="la42" class="la42">1</label> ' +
	'<span class="thumb1"><img src="/img/admincenter/empty.jpg" alt="문항유형이미지" /></span> ' +
	'<input type="file" name="imgMng42_1" id="" class="ip160 imgMng42" /> ' +
	'<input type="text" name="fileDesc42_1" id="" class="ip120 fileDesc42" /> ' +
	'<span class="btn lightB"><a href="javascript:addExam42(1);" name="aa42">추가</a></span> ' +
	'<span class="btn lightB"><a href="javascript:deleteExam42(1);" name="ad42">삭제</a></span> ' +
	'<!--								<span class="btn dark sp42" name="sp42"><a href="javascript:addEtcExam42();">기타추가</a></span>	--> ' +
	'<input type="hidden" name="examNo42" id="" value="1" /> ' +
	' ' +
	'<input type="hidden" name="orgFname42_1" id="" value="" class="orgFname42" />' +
	'<input type="hidden" name="savFname42_1" id="" value="" class="savFname42" />' +
	'<input type="hidden" name="filePath42_1" id="" value="" class="filePath42" />' +
	'<input type="hidden" name="fileSize42" id="" value="" />' +
	'</div>';

var divt = '<div class="third"> ' +
	'<label for="">보기번호</label> ' +
	'<!--								<input type="text" name="examNo" id="" class="ip80" value="1" readonly />--> ' +
	'<input type="radio" name="" id="" /><label for="" name="lala" class="lala">1</label> ' +
	'</div>';
var divtt = '<div class="twothird"> ' +
	'<label for="">분기할 문항 번호</label> ' +
	'<input type="text" name="branchNo" id="" class="ip80" disabled /> ' +
	'</div>';
var innert = '<label for="">보기번호</label> ' +
	'<!--								<input type="text" name="examNo" id="" class="ip80" value="1" readonly />--> ' +
	'<input type="radio" name="" id="" /><label for="" name="lala" class="lala">1</label>';
var innertt = '<label for="">분기할 문항 번호</label> ' +
	'<input type="text" name="branchNo" id="" class="ip80" />';

function changeQuestType() {
	var questType = document.detailForm.questType.value;

	if (questType == "HH103") {
		document.detailForm.examType.style.display = "none";

		document.detailForm.examType.value = "II101";
		// changeExamType();
	}
	else
		document.detailForm.examType.style.display = "block";
	
	var examType = document.detailForm.examType.value;
	
	var tr11 = document.getElementById("tr11");
	var td11 = document.getElementById("td11");

	if (questType == "HH101" && examType == "II101") {
		td11.innerHTML = div11;
		td11.setAttribute("class", "sec02");
	}
	else if (questType == "HH102" && examType == "II101") {
		td11.innerHTML = div2x + div21;
		td11.setAttribute("class", "sec02");
	}
	else if (questType == "HH103" && examType == "II101") {
		td11.innerHTML = textarea31;
		td11.setAttribute("class", "");
	}
	else if (questType == "HH104" && examType == "II101") {
		td11.innerHTML = div4x + div41;
		td11.setAttribute("class", "sec02");
	}
	else if (questType == "HH101" && examType == "II102") {
		td11.innerHTML = div12;
		td11.setAttribute("class", "sec02");
	}
	else if (questType == "HH102" && examType == "II102") {
		td11.innerHTML = div2x + div22;
		td11.setAttribute("class", "sec02");
	}
	else if (questType == "HH104" && examType == "II102") {
		td11.innerHTML = div4x + div42;
		td11.setAttribute("class", "sec02");
	}

	var tdtd = document.getElementById("tdtd");
	tdtd.innerHTML = divt + divtt;
	
	var filterYnY = document.getElementById("filterYnY");
	var filterYnN = document.getElementById("filterYnN");

	if (questType == "HH103" || questType == "HH104") {
		filterYnY.disabled = true;
		filterYnN.disabled = true;

		filterYnY.checked = false;
		filterYnN.checked = true;
		clickFilterYn('N');
	}
	else {
		filterYnY.disabled = false;
		filterYnN.disabled = false;
	}

	var branchYnY = document.getElementById("branchYnY");
	var branchYnN = document.getElementById("branchYnN");

	if (questType == "HH101") {
		document.detailForm.mergeType.readOnly = false;

		branchYnY.disabled = false;
		branchYnN.disabled = false;
	}
	else {
		document.detailForm.mergeType.readOnly = true;

		document.detailForm.mergeType.value = "9";
		changeMergeType();
		
		branchYnY.disabled = true;
		branchYnN.disabled = true;

		branchYnY.checked = false;
		branchYnN.checked = true;
		clickBranchYn('N');
	}
}

function changeExamType() {
	var questType = document.detailForm.questType.value;

	if (questType == "HH103") {
		// document.detailForm.examType.readOnly = true;

		document.detailForm.examType.value = "II101";
		// changeExamType();
	}
	// else
		// document.detailForm.examType.readOnly = false;

	var examType = document.detailForm.examType.value;
	
	var tr11 = document.getElementById("tr11");
	var td11 = document.getElementById("td11");

	if (questType == "HH101" && examType == "II101") {
		td11.innerHTML = div11;
		td11.setAttribute("class", "sec02");
	}
	else if (questType == "HH102" && examType == "II101") {
		td11.innerHTML = div2x + div21;
		td11.setAttribute("class", "sec02");
	}
	else if (questType == "HH103" && examType == "II101") {
		td11.innerHTML = textarea31;
		td11.setAttribute("class", "");
	}
	else if (questType == "HH104" && examType == "II101") {
		td11.innerHTML = div4x + div41;
		td11.setAttribute("class", "sec02");
	}
	else if (questType == "HH101" && examType == "II102") {
		td11.innerHTML = div12;
		td11.setAttribute("class", "sec02");
	}
	else if (questType == "HH102" && examType == "II102") {
		td11.innerHTML = div2x + div22;
		td11.setAttribute("class", "sec02");
	}
	else if (questType == "HH104" && examType == "II102") {
		td11.innerHTML = div4x + div42;
		td11.setAttribute("class", "sec02");
	}

	var tdtd = document.getElementById("tdtd");
	tdtd.innerHTML = divt + divtt;
	
	clickBranchYn(document.detailForm.branchYn.value);
}

function renum11() {
	var la11s = document.getElementsByClassName("la11");
	var examTitle11s = document.getElementsByClassName("examTitle11");
	var aa11s = document.getElementsByName("aa11");
	var ad11s = document.getElementsByName("ad11");
	var sp11s = document.getElementsByClassName("sp11");
	var examNo11s = document.getElementsByName("examNo11");
	for (var i = 0; i < la11s.length; i++) {
		la11s[i].firstChild.nodeValue = i + 1;
		examTitle11s[i].setAttribute("name", "examTitle11_" + (i + 1));
		aa11s[i].setAttribute("href", "javascript:addExam11(" + (i + 1) + ");");
		ad11s[i].setAttribute("href", "javascript:deleteExam11(" + (i + 1) + ");");
		if (i == la11s.length - 1 && document.detailForm.etcUseYn11.value == "N")
			sp11s[i].style.visibility = "visible";
		else
			sp11s[i].style.visibility = "hidden";
		if (i == la11s.length - 1 && document.detailForm.etcUseYn11.value == "Y") {
			examTitle11s[i].value = "기타";
			examTitle11s[i].readOnly = true;
			
			examNo11s[i].value = 99;
		}
		else {
			examNo11s[i].value = i + 1;
		}
	}
}

function renum() {
	var lalas = document.getElementsByClassName("lala");
	for (var i = 0; i < lalas.length; i++) {
		lalas[i].firstChild.nodeValue = i + 1;
	}
}

function addExam11(no) {
	var td11 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td11.childNodes.length; i++) {
		var child = td11.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	if (cnt == no && document.detailForm.etcUseYn11.value == "Y")
		return;
	var div = document.createElement("div");
	div.innerHTML = div11;
	if (cnt > no) {
		cnt = 0;
		first = null;
		for (var i = 0; i < td11.childNodes.length; i++) {
			var child = td11.childNodes[i];
			if (child.nodeName.toLowerCase() == "div") {
				cnt++;
				if (cnt == no + 1) {
					first = i;
					break;
				}
			}
		}
		td11.insertBefore(div, td11.childNodes[first]);
	}
	else {
		td11.appendChild(div);
	}
	renum11();
	
	var tdtd = document.getElementById("tdtd");
	cnt = 0;
	first = null;
	var second = null;
	for (var i = 0; i < tdtd.childNodes.length; i++) {
		var child = tdtd.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	div = document.createElement("div");
	div.setAttribute("class", "third");
	div.innerHTML = innert;
	var div2 = document.createElement("div");
	div2.setAttribute("class", "twothird");
	div2.innerHTML = innertt;
	if (cnt > 2 * no) {
		cnt = 0;
		first = null;
		second = null;
		for (var i = 0; i < tdtd.childNodes.length; i++) {
			var child = tdtd.childNodes[i];
			if (child.nodeName.toLowerCase() == "div") {
				cnt++;
				if (cnt == 2 * no + 1)
					first = i;
				else if (cnt == 2 * no + 2) {
					second = i;
					break;
				}
			}
		}
		tdtd.insertBefore(div2, tdtd.childNodes[first]);
		tdtd.insertBefore(div, tdtd.childNodes[first]);
	}
	else {
		tdtd.appendChild(div);
		tdtd.appendChild(div2);
	}
	renum();
}

function deleteExam11(no) {
	var td11 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td11.childNodes.length; i++) {
		var child = td11.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
			if (cnt == no) {
				first = i;
				// break;
			}
		}
	}
	if (cnt == 1)
		return;
	if (cnt == 2 && no == 1 && document.detailForm.etcUseYn11.value == "Y")
		return;
	td11.removeChild(td11.childNodes[first]);
	if (cnt == no)
		document.detailForm.etcUseYn11.value = "N";
	renum11();
	
	var tdtd = document.getElementById("tdtd");
	cnt = 0;
	first = null;
	var second = null;
	for (var i = 0; i < tdtd.childNodes.length; i++) {
		var child = tdtd.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
			if (cnt == 2 * no - 1)
				first = i;
			else if (cnt == 2 * no) {
				second = i;
				break;
			}
		}
	}
	tdtd.removeChild(tdtd.childNodes[second]);
	tdtd.removeChild(tdtd.childNodes[first]);
	renum();
}

function addEtcExam11() {
	var td11 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td11.childNodes.length; i++) {
		var child = td11.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	var div = document.createElement("div");
	div.innerHTML = div11;
	td11.appendChild(div);
	document.detailForm.etcUseYn11.value = "Y";
	renum11();
	
	var tdtd = document.getElementById("tdtd");
	cnt = 0;
	first = null;
	var second = null;
	for (var i = 0; i < tdtd.childNodes.length; i++) {
		var child = tdtd.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	div = document.createElement("div");
	div.setAttribute("class", "third");
	div.innerHTML = innert;
	var div2 = document.createElement("div");
	div2.setAttribute("class", "twothird");
	div2.innerHTML = innertt;
	tdtd.appendChild(div);
	tdtd.appendChild(div2);
	renum();
}

function renum21() {
	var la21s = document.getElementsByClassName("la21");
	var examTitle21s = document.getElementsByClassName("examTitle21");
	var aa21s = document.getElementsByName("aa21");
	var ad21s = document.getElementsByName("ad21");
	var sp21s = document.getElementsByClassName("sp21");
	var examNo21s = document.getElementsByName("examNo21");
	for (var i = 0; i < la21s.length; i++) {
		la21s[i].firstChild.nodeValue = i + 1;
		examTitle21s[i].setAttribute("name", "examTitle21_" + (i + 1));
		aa21s[i].setAttribute("href", "javascript:addExam21(" + (i + 1) + ");");
		ad21s[i].setAttribute("href", "javascript:deleteExam21(" + (i + 1) + ");");
		if (i == la21s.length - 1 && document.detailForm.etcUseYn21.value == "N")
			sp21s[i].style.visibility = "visible";
		else
			sp21s[i].style.visibility = "hidden";
		if (i == la21s.length - 1 && document.detailForm.etcUseYn21.value == "Y") {
			examTitle21s[i].value = "기타";
			examTitle21s[i].readOnly = true;
			
			examNo21s[i].value = 99;
		}
		else {
			examNo21s[i].value = i + 1;
		}
	}
}

function addExam21(no) {
	var td21 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td21.childNodes.length; i++) {
		var child = td21.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	if (cnt == no + 1 && document.detailForm.etcUseYn21.value == "Y")
		return;
	var div = document.createElement("div");
	div.innerHTML = div21;
	if (cnt > no + 1) {
		cnt = 0;
		first = null;
		for (var i = 0; i < td21.childNodes.length; i++) {
			var child = td21.childNodes[i];
			if (child.nodeName.toLowerCase() == "div") {
				cnt++;
				if (cnt == no + 2) {
					first = i;
					break;
				}
			}
		}
		td21.insertBefore(div, td21.childNodes[first]);
	}
	else {
		td21.appendChild(div);
	}
	renum21();
}

function deleteExam21(no) {
	var td21 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td21.childNodes.length; i++) {
		var child = td21.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
			if (cnt == no + 1) {
				first = i;
				// break;
			}
		}
	}
	if (cnt == 2)
		return;
	if (cnt == 3 && no == 1 && document.detailForm.etcUseYn21.value == "Y")
		return;
	td21.removeChild(td21.childNodes[first]);
	if (cnt == no + 1)
		document.detailForm.etcUseYn21.value = "N";
	renum21();
}

function addEtcExam21() {
	var td21 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td21.childNodes.length; i++) {
		var child = td21.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	var div = document.createElement("div");
	div.innerHTML = div21;
	td21.appendChild(div);
	document.detailForm.etcUseYn21.value = "Y";
	renum21();
}

function renum41() {
	var la41s = document.getElementsByClassName("la41");
	var examTitle41s = document.getElementsByClassName("examTitle41");
	var aa41s = document.getElementsByName("aa41");
	var ad41s = document.getElementsByName("ad41");
	var sp41s = document.getElementsByClassName("sp41");
	var examNo41s = document.getElementsByName("examNo41");
	for (var i = 0; i < la41s.length; i++) {
		la41s[i].firstChild.nodeValue = i + 1;
		examTitle41s[i].setAttribute("name", "examTitle41_" + (i + 1));
		aa41s[i].setAttribute("href", "javascript:addExam41(" + (i + 1) + ");");
		ad41s[i].setAttribute("href", "javascript:deleteExam41(" + (i + 1) + ");");
		if (i == la41s.length - 1 && document.detailForm.etcUseYn41.value == "N")
			sp41s[i].style.visibility = "visible";
		else
			sp41s[i].style.visibility = "hidden";
		if (i == la41s.length - 1 && document.detailForm.etcUseYn41.value == "Y") {
			examTitle41s[i].value = "기타";
			examTitle41s[i].readOnly = true;
			
			examNo41s[i].value = 99;
		}
		else {
			examNo41s[i].value = i + 1;
		}
	}
}

function addExam41(no) {
	var td41 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td41.childNodes.length; i++) {
		var child = td41.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	if (cnt == no + 1 && document.detailForm.etcUseYn41.value == "Y")
		return;
	var div = document.createElement("div");
	div.innerHTML = div41;
	if (cnt > no + 1) {
		cnt = 0;
		first = null;
		for (var i = 0; i < td41.childNodes.length; i++) {
			var child = td41.childNodes[i];
			if (child.nodeName.toLowerCase() == "div") {
				cnt++;
				if (cnt == no + 2) {
					first = i;
					break;
				}
			}
		}
		td41.insertBefore(div, td41.childNodes[first]);
	}
	else {
		td41.appendChild(div);
	}
	renum41();
}

function deleteExam41(no) {
	var td41 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td41.childNodes.length; i++) {
		var child = td41.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
			if (cnt == no + 1) {
				first = i;
				// break;
			}
		}
	}
	if (cnt == 2)
		return;
	if (cnt == 3 && no == 1 && document.detailForm.etcUseYn41.value == "Y")
		return;
	td41.removeChild(td41.childNodes[first]);
	if (cnt == no + 1)
		document.detailForm.etcUseYn41.value = "N";
	renum41();
}

function addEtcExam41() {
	var td41 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td41.childNodes.length; i++) {
		var child = td41.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	var div = document.createElement("div");
	div.innerHTML = div41;
	td41.appendChild(div);
	document.detailForm.etcUseYn41.value = "Y";
	renum41();
}

function renum12() {
	var la12s = document.getElementsByClassName("la12");
	var imgMng12s = document.getElementsByClassName("imgMng12");
	var fileDesc12s = document.getElementsByClassName("fileDesc12");
	var aa12s = document.getElementsByName("aa12");
	var ad12s = document.getElementsByName("ad12");
	// var sp12s = document.getElementsByClassName("sp12");
	var examNo12s = document.getElementsByName("examNo12");
	
	var orgFname12s = document.getElementsByClassName("orgFname12");
	var savFname12s = document.getElementsByClassName("savFname12");
	var filePath12s = document.getElementsByClassName("filePath12");
	var fileSize12s = document.getElementsByName("fileSize12");
	for (var i = 0; i < la12s.length; i++) {
		la12s[i].firstChild.nodeValue = i + 1;
		imgMng12s[i].setAttribute("name", "imgMng12_" + (i + 1));
		fileDesc12s[i].setAttribute("name", "fileDesc12_" + (i + 1));
		aa12s[i].setAttribute("href", "javascript:addExam12(" + (i + 1) + ");");
		ad12s[i].setAttribute("href", "javascript:deleteExam12(" + (i + 1) + ");");
		// if (i == la12s.length - 1 && document.detailForm.etcUseYn12.value == "N")
			// sp12s[i].style.visibility = "visible";
		// else
			// sp12s[i].style.visibility = "hidden";
			
		orgFname12s[i].setAttribute("name", "orgFname12_" + (i + 1));
		savFname12s[i].setAttribute("name", "savFname12_" + (i + 1));
		filePath12s[i].setAttribute("name", "filePath12_" + (i + 1));
		if (i == la12s.length - 1 && document.detailForm.etcUseYn12.value == "Y") {
			// examTitle12s[i].value = "기타";
			// examTitle12s[i].readOnly = true;
			
			examNo12s[i].value = 99;
		}
		else {
			examNo12s[i].value = i + 1;
		}
	}
}
/*
function renumb12() {
	var lala2s = document.getElementsByClassName("lala2");
	for (var i = 0; i < lala2s.length; i++) {
		lala2s[i].firstChild.nodeValue = i + 1;
	}
}
*/
function addExam12(no) {
	var td12 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td12.childNodes.length; i++) {
		var child = td12.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	if (cnt == no && document.detailForm.etcUseYn12.value == "Y")
		return;
	var div = document.createElement("div");
	div.innerHTML = div12;
	if (cnt > no) {
		cnt = 0;
		first = null;
		for (var i = 0; i < td12.childNodes.length; i++) {
			var child = td12.childNodes[i];
			if (child.nodeName.toLowerCase() == "div") {
				cnt++;
				if (cnt == no + 1) {
					first = i;
					break;
				}
			}
		}
		td12.insertBefore(div, td12.childNodes[first]);
	}
	else {
		td12.appendChild(div);
	}
	renum12();
	
	var tdtd = document.getElementById("tdtd");
	cnt = 0;
	first = null;
	var second = null;
	for (var i = 0; i < tdtd.childNodes.length; i++) {
		var child = tdtd.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	div = document.createElement("div");
	div.setAttribute("class", "third");
	div.innerHTML = innert;
	var div2 = document.createElement("div");
	div2.setAttribute("class", "twothird");
	div2.innerHTML = innertt;
	if (cnt > 2 * no) {
		cnt = 0;
		first = null;
		second = null;
		for (var i = 0; i < tdtd.childNodes.length; i++) {
			var child = tdtd.childNodes[i];
			if (child.nodeName.toLowerCase() == "div") {
				cnt++;
				if (cnt == 2 * no + 1)
					first = i;
				else if (cnt == 2 * no + 2) {
					second = i;
					break;
				}
			}
		}
		tdtd.insertBefore(div2, tdtd.childNodes[first]);
		tdtd.insertBefore(div, tdtd.childNodes[first]);
	}
	else {
		tdtd.appendChild(div);
		tdtd.appendChild(div2);
	}
	renum();
}

function deleteExam12(no) {
	var td12 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td12.childNodes.length; i++) {
		var child = td12.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
			if (cnt == no) {
				first = i;
				// break;
			}
		}
	}
	if (cnt == 1)
		return;
	if (cnt == 2 && no == 1 && document.detailForm.etcUseYn12.value == "Y")
		return;
	td12.removeChild(td12.childNodes[first]);
	if (cnt == no)
		document.detailForm.etcUseYn12.value = "N";
	renum12();
	
	var tdtd = document.getElementById("tdtd");
	cnt = 0;
	first = null;
	var second = null;
	for (var i = 0; i < tdtd.childNodes.length; i++) {
		var child = tdtd.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
			if (cnt == 2 * no - 1)
				first = i;
			else if (cnt == 2 * no) {
				second = i;
				break;
			}
		}
	}
	tdtd.removeChild(tdtd.childNodes[second]);
	tdtd.removeChild(tdtd.childNodes[first]);
	renum();
}

function addEtcExam12() {
	var td12 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td12.childNodes.length; i++) {
		var child = td12.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	var div = document.createElement("div");
	div.innerHTML = div12;
	td12.appendChild(div);
	document.detailForm.etcUseYn12.value = "Y";
	renum12();
	
	var tdtd = document.getElementById("tdtd");
	cnt = 0;
	first = null;
	var second = null;
	for (var i = 0; i < tdtd.childNodes.length; i++) {
		var child = tdtd.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
			if (cnt == 1)
				first = i;
			else if (cnt == 2) {
				second = i;
				break;
			}
		}
	}
	div = document.createElement("div");
	div.setAttribute("class", "third");
	div.innerHTML = innert;
	var div2 = document.createElement("div");
	div2.setAttribute("class", "twothird");
	div2.innerHTML = innertt;
	tdtd.appendChild(div);
	tdtd.appendChild(div2);
	renum();
}

function renum22() {
	var la22s = document.getElementsByClassName("la22");
	var imgMng22s = document.getElementsByClassName("imgMng22");
	var fileDesc22s = document.getElementsByClassName("fileDesc22");
	var aa22s = document.getElementsByName("aa22");
	var ad22s = document.getElementsByName("ad22");
	// var sp22s = document.getElementsByClassName("sp22");
	var examNo22s = document.getElementsByName("examNo22");
	
	var orgFname22s = document.getElementsByClassName("orgFname22");
	var savFname22s = document.getElementsByClassName("savFname22");
	var filePath22s = document.getElementsByClassName("filePath22");
	var fileSize22s = document.getElementsByName("fileSize22");
	for (var i = 0; i < la22s.length; i++) {
		la22s[i].firstChild.nodeValue = i + 1;
		imgMng22s[i].setAttribute("name", "imgMng22_" + (i + 1));
		fileDesc22s[i].setAttribute("name", "fileDesc22_" + (i + 1));
		aa22s[i].setAttribute("href", "javascript:addExam22(" + (i + 1) + ");");
		ad22s[i].setAttribute("href", "javascript:deleteExam22(" + (i + 1) + ");");
		// if (i == la22s.length - 1 && document.detailForm.etcUseYn22.value == "N")
			// sp22s[i].style.visibility = "visible";
		// else
			// sp22s[i].style.visibility = "hidden";
			
		orgFname22s[i].setAttribute("name", "orgFname22_" + (i + 1));
		savFname22s[i].setAttribute("name", "savFname22_" + (i + 1));
		filePath22s[i].setAttribute("name", "filePath22_" + (i + 1));
		if (i == la22s.length - 1 && document.detailForm.etcUseYn22.value == "Y") {
			// examTitle22s[i].value = "기타";
			// examTitle22s[i].readOnly = true;
			
			examNo22s[i].value = 99;
		}
		else {
			examNo22s[i].value = i + 1;
		}
	}
}

function addExam22(no) {
	var td22 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td22.childNodes.length; i++) {
		var child = td22.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	if (cnt == no + 1 && document.detailForm.etcUseYn22.value == "Y")
		return;
	var div = document.createElement("div");
	div.innerHTML = div22;
	if (cnt > no + 1) {
		cnt = 0;
		first = null;
		for (var i = 0; i < td22.childNodes.length; i++) {
			var child = td22.childNodes[i];
			if (child.nodeName.toLowerCase() == "div") {
				cnt++;
				if (cnt == no + 2) {
					first = i;
					break;
				}
			}
		}
		td22.insertBefore(div, td22.childNodes[first]);
	}
	else {
		td22.appendChild(div);
	}
	renum22();
}

function deleteExam22(no) {
	var td22 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td22.childNodes.length; i++) {
		var child = td22.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
			if (cnt == no + 1) {
				first = i;
				// break;
			}
		}
	}
	if (cnt == 2)
		return;
	if (cnt == 3 && no == 1 && document.detailForm.etcUseYn22.value == "Y")
		return;
	td22.removeChild(td22.childNodes[first]);
	if (cnt == no + 1)
		document.detailForm.etcUseYn22.value = "N";
	renum22();
}

function addEtcExam22() {
	var td22 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td22.childNodes.length; i++) {
		var child = td22.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	var div = document.createElement("div");
	div.innerHTML = div22;
	td22.appendChild(div);
	document.detailForm.etcUseYn22.value = "Y";
	renum22();
}

function renum42() {
	var la42s = document.getElementsByClassName("la42");
	var imgMng42s = document.getElementsByClassName("imgMng42");
	var fileDesc42s = document.getElementsByClassName("fileDesc42");
	var aa42s = document.getElementsByName("aa42");
	var ad42s = document.getElementsByName("ad42");
	// var sp42s = document.getElementsByClassName("sp42");
	var examNo42s = document.getElementsByName("examNo42");
	
	var orgFname42s = document.getElementsByClassName("orgFname42");
	var savFname42s = document.getElementsByClassName("savFname42");
	var filePath42s = document.getElementsByClassName("filePath42");
	var fileSize42s = document.getElementsByName("fileSize42");
	for (var i = 0; i < la42s.length; i++) {
		la42s[i].firstChild.nodeValue = i + 1;
		imgMng42s[i].setAttribute("name", "imgMng42_" + (i + 1));
		fileDesc42s[i].setAttribute("name", "fileDesc42_" + (i + 1));
		aa42s[i].setAttribute("href", "javascript:addExam42(" + (i + 1) + ");");
		ad42s[i].setAttribute("href", "javascript:deleteExam42(" + (i + 1) + ");");
		// if (i == la42s.length - 1 && document.detailForm.etcUseYn42.value == "N")
			// sp42s[i].style.visibility = "visible";
		// else
			// sp42s[i].style.visibility = "hidden";
			
		orgFname42s[i].setAttribute("name", "orgFname42_" + (i + 1));
		savFname42s[i].setAttribute("name", "savFname42_" + (i + 1));
		filePath42s[i].setAttribute("name", "filePath42_" + (i + 1));
		if (i == la42s.length - 1 && document.detailForm.etcUseYn42.value == "Y") {
			// examTitle42s[i].value = "기타";
			// examTitle42s[i].readOnly = true;
			
			examNo42s[i].value = 99;
		}
		else {
			examNo42s[i].value = i + 1;
		}
	}
}

function addExam42(no) {
	var td42 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td42.childNodes.length; i++) {
		var child = td42.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
		}
	}
	if (cnt == no + 1 && document.detailForm.etcUseYn42.value == "Y")
		return;
	var div = document.createElement("div");
	div.innerHTML = div42;
	if (cnt > no + 1) {
		cnt = 0;
		first = null;
		for (var i = 0; i < td42.childNodes.length; i++) {
			var child = td42.childNodes[i];
			if (child.nodeName.toLowerCase() == "div") {
				cnt++;
				if (cnt == no + 2) {
					first = i;
					break;
				}
			}
		}
		td42.insertBefore(div, td42.childNodes[first]);
	}
	else {
		td42.appendChild(div);
	}
	renum42();
}

function deleteExam42(no) {
	var td42 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td42.childNodes.length; i++) {
		var child = td42.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
			if (cnt == no + 1) {
				first = i;
				// break;
			}
		}
	}
	if (cnt == 2)
		return;
	if (cnt == 3 && no == 1 && document.detailForm.etcUseYn42.value == "Y")
		return;
	td42.removeChild(td42.childNodes[first]);
	if (cnt == no + 1)
		document.detailForm.etcUseYn42.value = "N";
	renum42();
}

function addEtcExam42() {
	var td42 = document.getElementById("td11");
	var cnt = 0;
	var first = null;
	for (var i = 0; i < td42.childNodes.length; i++) {
		var child = td42.childNodes[i];
		if (child.nodeName.toLowerCase() == "div") {
			cnt++;
			if (cnt == 2) {
				first = i;
				break;
			}
		}
	}
	var div = document.createElement("div");
	div.innerHTML = div42;
	td42.appendChild(div);
	document.detailForm.etcUseYn42.value = "Y";
	renum42();
}

function clickFilterYn(filterYn) {
	document.detailForm.filterAnswer.value = "";
	document.detailForm.filterAnswer.disabled = (filterYn == "Y") ? false : true;

	document.detailForm.filterYn.value = filterYn;
}

function changeMergeType() {
	var mergeType = document.detailForm.mergeType.value;

	document.detailForm.mergeTitle.value = "";
	document.detailForm.mergeTitle2.value = "";
	document.detailForm.mergeTitle3.value = "";
	
	if (mergeType == "1" || mergeType == "3") {
		document.detailForm.mergeTitle.disabled = false;
		document.detailForm.mergeTitle2.disabled = false;
		document.detailForm.mergeTitle3.disabled = true;
	}
	else if (mergeType == "2") {
		document.detailForm.mergeTitle.disabled = false;
		document.detailForm.mergeTitle2.disabled = false;
		document.detailForm.mergeTitle3.disabled = false;
	}
	else {
		document.detailForm.mergeTitle.disabled = true;
		document.detailForm.mergeTitle2.disabled = true;
		document.detailForm.mergeTitle3.disabled = true;
	}
}

function clickBranchYn(branchYn) {
	var trtr = document.getElementById("trtr");

	trtr.style.display = (branchYn == "Y") ? "block" : "none";

	var branchNos = document.getElementsByName("branchNo");
	
	for (i = 0; i < branchNos.length; i++) {
		branchNos[i].value = "";
		branchNos[i].disabled = (branchYn == "Y") ? false : true;
	}

	document.detailForm.branchYn.value = branchYn;
}

function clickExamRotateyn(examRotateyn) {
	document.detailForm.examRotateyn.value = examRotateyn;
}

function fncReset(position) {
	if (position == "U")
		document.detailForm.questAddfileu.select();
	else if (position == "D")
		document.detailForm.questAddfiled.select();
	document.selection.clear();
}

function fncDelete(position) {
	if (position == "U") {
		document.getElementById("spspu").style.visibility = "hidden";
		document.detailForm.delYnu.value = "Y";
	}
	else if (position == "D") {
		document.getElementById("spspd").style.visibility = "hidden";
		document.detailForm.delYnd.value = "Y";
	}
}

function fncDownloadQuestMng(filePath, savFname, orgFname) {
	document.detailForm.filePath.value = filePath;
	document.detailForm.savFname.value = savFname;
	document.detailForm.orgFname.value = orgFname;
	document.detailForm.action = "<c:url value='/downloadQuestMng.do' />";
    document.detailForm.submit();
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
						<li><a href="<c:url value='/listSurveyMng.do' />">여론조사관리</a></li>
						<li><a href="<c:url value='/listQuestMng.do' />" class="active">문항관리</a></li>
						<li><a href="<c:url value='/listAnswerMng.do' />">응답현황</a></li>
					</ul>
				</div>
			</div>	
			<!-- // #sidebar -->
			
			<!-- contents -->
			<div id="contents" class="survey">	
				<h2>문항 수정</h2>
				
				<table class="srch_tbl">
				<colgroup>
					<col width="140px;" />
					<col width="*" />
				</colgroup>
					<tr>
						<th>제목</th>
						<td><%= pollMng.getPollTitle() %></td>
					</tr>
					<tr>
						<th>기간</th>
						<td><%= pollMng.getPollTermstartYmd()%> <%= pollMng.getPollTermstartHour()%>:<%= pollMng.getPollTermstartMinute()%> ~ <%= pollMng.getPollTermendYmd() %> <%= pollMng.getPollTermendHour() %>:<%= pollMng.getPollTermendMinute() %></td>
					</tr>
					<tr>
						<th>개요</th>
						<td><%= pollMng.getPollSubtitle() %></td>
					</tr>
				</table><!-- .srch_tbl -->
				
				
				<p class="tr"><span class="red">*</span>표는 필수입력 항목입니다.</p>
				<!-- 1단수선택 -->
				<table class="list_tbl edit">
					<colgroup>
						<col width="140px;" />
						<col width="240px;" />
						<col width="140px;" />
						<col width="240px;" />
					</colgroup>
					<tr>
						<th><span class="red">*</span>문항 번호</th>
						<td>
							<input type="text" name="questNo" id="" value="<%= questMng.getQuestNo() %>" class="ip100" readonly required fieldTitle="문항 번호" />
						</td>
						<th><span class="red">*</span>그래프 유형</th>
						<td>
							<select name="graphType" id="" required fieldTitle="그래프 유형">
								<%
									for (int i = 0; i < graphCodeList.size(); i++) {
										Code graphCode = (Code)graphCodeList.get(i);
								%>
								<option value="<%= graphCode.getCodeId() %>" <%= questMng.getGraphType().equals(graphCode.getCodeId()) ? "selected" : "" %>><%= graphCode.getCodeName() %></option>
								<%
									}
								%>
							</select>		
						</td>	
					</tr>
					<tr>
						<th>이미지선택</th>
						<td colspan="3">
							<div class="half">
								<input type="file" name="questAddfileu" id="" class="ip80p" />
								<span class="btn light"><a href="javascript:fncReset('U');">취소</a></span>
							</div>
<%
						ArrayList questAddfileList = (ArrayList)questMng.getQuestAddfileList();

						for (int i = 0; i < questAddfileList.size(); i++) {
							QuestAddfile questAddfile = (QuestAddfile)questAddfileList.get(i);
							
							if (questAddfile.getPosition().equals("U")) {
%>
							<div class="half tr">
								<span class="orange" id="spspu"><a href="javascript:fncDownloadQuestMng('<%= questAddfile.getFilePath() %>', '<%= questAddfile.getSavFname() %>', '<%= questAddfile.getOrgFname() %>');"><%= questAddfile.getOrgFname() %></a></span>							
								<span class="btn light"><a href="javascript:fncDelete('U');">삭제</a></span>
								<input type="hidden" name="orgFnameu" id="" value="<%= questAddfile.getOrgFname() %>" />
								<input type="hidden" name="savFnameu" id="" value="<%= questAddfile.getSavFname() %>" />
								<input type="hidden" name="filePathu" id="" value="<%= questAddfile.getFilePath() %>" />
								<input type="hidden" name="fileSizeu" id="" value="<%= questAddfile.getFileSize() %>" />
							</div>
<%
							}
						}
%>							
						</td>
					</tr>
					<tr>
						<th class="letter2"><span class="red">*</span>문항</th>
						<td colspan="3">
							<input type="text" name="questTitle" id="" value="<%= questMng.getQuestTitle() %>" class="ip100" required fieldTitle="문항" />
						</td>
					</tr>
					<tr>
						<th>이미지선택</th>
						<td colspan="3">
							<div class="half">
								<input type="file" name="questAddfiled" id="" class="ip80p" />
								<span class="btn light"><a href="javascript:fncReset('D');">취소</a></span>
							</div>
<%
						for (int i = 0; i < questAddfileList.size(); i++) {
							QuestAddfile questAddfile = (QuestAddfile)questAddfileList.get(i);
							
							if (questAddfile.getPosition().equals("D")) {
%>
							<div class="half tr">
								<span class="orange" id="spspd"><a href="javascript:fncDownloadQuestMng('<%= questAddfile.getFilePath() %>', '<%= questAddfile.getSavFname() %>', '<%= questAddfile.getOrgFname() %>');"><%= questAddfile.getOrgFname() %></a></span>							
								<span class="btn light"><a href="javascript:fncDelete('D');">삭제</a></span>
								<input type="hidden" name="orgFnamed" id="" value="<%= questAddfile.getOrgFname() %>" />
								<input type="hidden" name="savFnamed" id="" value="<%= questAddfile.getSavFname() %>" />
								<input type="hidden" name="filePathd" id="" value="<%= questAddfile.getFilePath() %>" />
								<input type="hidden" name="fileSized" id="" value="<%= questAddfile.getFileSize() %>" />
							</div>
<%
							}
						}
%>							
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>문항유형</th>
						<td>
							<select name="questType" id="" onchange="changeQuestType();" required fieldTitle="문항유형">
								<%
									for (int i = 0; i < questCodeList.size(); i++) {
										Code questCode = (Code)questCodeList.get(i);
								%>
								<option value="<%= questCode.getCodeId() %>" <%= questMng.getQuestType().equals(questCode.getCodeId()) ? "selected" : "" %>><%= questCode.getCodeName() %></option>
								<%
									}
								%>
							</select>					
						</td>	
						<th><span class="red">*</span>보기유형</th>
						<td>
							<select name="examType" id="examType" onchange="changeExamType();" style="display: <%= !questMng.getQuestType().equals("HH103") ? "block" : "none" %>;" required fieldTitle="보기유형">
								<%
									for (int i = 0; i < examCodeList.size(); i++) {
										Code examCode = (Code)examCodeList.get(i);
								%>
								<option value="<%= examCode.getCodeId() %>" <%= questMng.getExamType().equals(examCode.getCodeId()) ? "selected" : "" %>><%= examCode.getCodeName() %></option>
								<%
									}
								%>
							</select>					
						</td>
					</tr>
					<%
					ArrayList exampleMngList = (ArrayList)questMng.getExampleMngList();
					int etcUse = 0;
					ArrayList imgMngList = (ArrayList)questMng.getImgMngList();
					
					if (questMng.getQuestType().equals("HH101") && questMng.getExamType().equals("II101")) {
					%>
					<tr id="tr11">
						<th></th>
						<td colspan="3" class="sec02" id="td11">
						<%
							for (int i = 0; i < exampleMngList.size(); i++) {
								ExampleMng exampleMng = (ExampleMng)exampleMngList.get(i);
								etcUse = exampleMng.getExamNo();
						%>
							<div>
								<input type="radio" name="" id="" /><label for="" name="la11" class="la11"><%= i + 1 %></label>
								<input type="text" name="examTitle11_<%= i + 1 %>" id="" class="ip160 examTitle11" value="<%= exampleMng.getExamTitle() %>" />
								<span class="btn lightB"><a href="javascript:addExam11(<%= i + 1 %>);" name="aa11">추가</a></span>
								<span class="btn lightB"><a href="javascript:deleteExam11(<%= i + 1 %>);" name="ad11">삭제</a></span>	
								<span class="btn dark sp11" name="sp11" style="visibility: <%= (i == exampleMngList.size() - 1 && exampleMng.getExamNo() != 99) ? "visible" : "hidden" %>;"><a href="javascript:addEtcExam11();">기타추가</a></span>	
								<input type="hidden" name="examNo11" id="" value="<%= exampleMng.getExamNo() %>" />
							</div>
						<%
							}
						%>
						</td>
					</tr>
					<%
					}
					else if (questMng.getQuestType().equals("HH102") && questMng.getExamType().equals("II101")) {
					%>
					<tr id="tr11">
						<th></th>
						<td colspan="3" class="sec02" id="td11">
							<div>
								<label for="">선택 수</label>
								<input type="text" name="choiceCnt21" id="" class="ip50" value="<%= questMng.getChoiceCnt() %>" num="i" required_ fieldTitle="선택 수" /> 개								
								<select name="choiceRange21" id="" required fieldTitle="선택 범위">
								<%
									for (int i = 0; i < choiceCodeList.size(); i++) {
										Code choiceCode = (Code)choiceCodeList.get(i);
								%>
									<option value="<%= choiceCode.getCodeId() %>" <%= questMng.getChoiceRange().equals(choiceCode.getCodeId()) ? "selected" : "" %>><%= choiceCode.getCodeName() %></option>
								<%
									}
								%>
								</select>
							</div>
						<%
							for (int i = 0; i < exampleMngList.size(); i++) {
								ExampleMng exampleMng = (ExampleMng)exampleMngList.get(i);
								etcUse = exampleMng.getExamNo();
						%>
							<div>
								<input type="checkbox" name="" id="" /><label for="" name="la21" class="la21"><%= i + 1 %></label>
								<input type="text" name="examTitle21_<%= i + 1 %>" id="" class="ip160 examTitle21" value="<%= exampleMng.getExamTitle() %>" />
								<span class="btn lightB"><a href="javascript:addExam21(<%= i + 1 %>);" name="aa21">추가</a></span>
								<span class="btn lightB"><a href="javascript:deleteExam21(<%= i + 1 %>);" name="ad21">삭제</a></span>	
								<span class="btn dark sp21" name="sp21" style="visibility: <%= (i == exampleMngList.size() - 1 && exampleMng.getExamNo() != 99) ? "visible" : "hidden" %>;"><a href="javascript:addEtcExam21();">기타추가</a></span>	
								<input type="hidden" name="examNo21" id="" value="<%= exampleMng.getExamNo() %>" />
							</div>
						<%
							}
						%>
						</td>	
					</tr>
					<%
					}
					else if (questMng.getQuestType().equals("HH103") && questMng.getExamType().equals("II101")) {
					%>
					<tr id="tr11">
						<th></th>
						<td colspan="3" class="sec02" id="td11">
							<textarea name="" id="" cols="30" rows="3" class="ip100" readonly></textarea>
						</td>	
					</tr>
					<%
					}
					else if (questMng.getQuestType().equals("HH104") && questMng.getExamType().equals("II101")) {
					%>
					<tr id="tr11">
						<th></th>
						<td colspan="3" class="sec02" id="td11">
							<div>
								<label for="">선택 수</label>
								<input type="text" name="choiceCnt41" id="" class="ip50" value="<%= questMng.getChoiceCnt() %>" num="i" required_ fieldTitle="선택 수" /> 개								
								<input type="hidden" name="choiceRange41" id="" value="<%= questMng.getChoiceRange() %>" />
							</div>
						<%
							for (int i = 0; i < exampleMngList.size(); i++) {
								ExampleMng exampleMng = (ExampleMng)exampleMngList.get(i);
								etcUse = exampleMng.getExamNo();
						%>
							<div>
								<input type="checkbox" name="" id="" /><label for="" name="la41" class="la41"><%= i + 1 %></label>
								<input type="text" name="examTitle41_<%= i + 1 %>" id="" class="ip160 examTitle41" value="<%= exampleMng.getExamTitle() %>" />
								<span class="btn lightB"><a href="javascript:addExam41(<%= i + 1 %>);" name="aa41">추가</a></span>
								<span class="btn lightB"><a href="javascript:deleteExam41(<%= i + 1 %>);" name="ad41">삭제</a></span>	
								<span class="btn dark sp41" name="sp41" style="visibility: <%= (i == exampleMngList.size() - 1 && exampleMng.getExamNo() != 99) ? "visible" : "hidden" %>;"><a href="javascript:addEtcExam41();">기타추가</a></span>	
								<input type="hidden" name="examNo41" id="" value="<%= exampleMng.getExamNo() %>" />
							</div>
						<%
							}
						%>
						</td>
					</tr>
					<%
					}
					else if (questMng.getQuestType().equals("HH101") && questMng.getExamType().equals("II102")) {
					%>
					<tr id="tr11">
						<th></th>
						<td colspan="3" class="sec02" id="td11">
						<%
							for (int i = 0; i < exampleMngList.size(); i++) {
								ExampleMng exampleMng = (ExampleMng)exampleMngList.get(i);
								etcUse = exampleMng.getExamNo();
								ImgMng imgMng = null;
								if (i < imgMngList.size()) {
									imgMng = (ImgMng)imgMngList.get(i);
								}
						%>
							<div>
								<input type="radio" name="" id="" /><label for="la12" class="la12"><%= i + 1 %></label>
								<span class="thumb1"><img src="<%= (imgMng != null) ? imgMng.getFilePath() : "" %><%= (imgMng != null) ? imgMng.getSavFname() : "" %>" alt="" width="60px" height="30px" /></span>
								<input type="file" name="imgMng12_<%= i + 1 %>" id="" class="ip160 imgMng12" />
								<input type="text" name="fileDesc12_<%= i + 1 %>" id="" class="ip120 fileDesc12" value="<%= (imgMng != null) ? imgMng.getFileDesc() : "" %>" />
								<span class="btn lightB"><a href="javascript:addExam12(<%= i + 1 %>);" name="aa12">추가</a></span>
								<span class="btn lightB"><a href="javascript:deleteExam12(<%= i + 1 %>);" name="ad12">삭제</a></span>	
<!--								<span class="btn dark sp12" name="sp12"><a href="javascript:addEtcExam12();">기타추가</a></span>	-->
								<input type="hidden" name="examNo12" id="" value="<%= exampleMng.getExamNo() %>" />
								
								<input type="hidden" name="orgFname12_<%= i + 1 %>" id="" value="<%= (imgMng != null) ? imgMng.getOrgFname() : "" %>" class="orgFname12" />
								<input type="hidden" name="savFname12_<%= i + 1 %>" id="" value="<%= (imgMng != null) ? imgMng.getSavFname() : "" %>" class="savFname12" />
								<input type="hidden" name="filePath12_<%= i + 1 %>" id="" value="<%= (imgMng != null) ? imgMng.getFilePath() : "" %>" class="filePath12" />
								<input type="hidden" name="fileSize12" id="" value="<%= (imgMng != null) ? imgMng.getFileSize() : 0 %>" />
							</div>
						<%
							}
						%>
						</td>
					</tr>
					<%
					}
					else if (questMng.getQuestType().equals("HH102") && questMng.getExamType().equals("II102")) {
					%>
					<tr id="tr11">
						<th></th>
						<td colspan="3" class="sec02" id="td11">
							<div>
								<label for="">선택 수</label>
								<input type="text" name="choiceCnt21" id="" class="ip50" value="<%= questMng.getChoiceCnt() %>" num="i" required_ fieldTitle="선택 수" /> 개								
								<select name="choiceRange21" id="" required fieldTitle="선택 범위">
								<%
									for (int i = 0; i < choiceCodeList.size(); i++) {
										Code choiceCode = (Code)choiceCodeList.get(i);
								%>
									<option value="<%= choiceCode.getCodeId() %>" <%= questMng.getChoiceRange().equals(choiceCode.getCodeId()) ? "selected" : "" %>><%= choiceCode.getCodeName() %></option>
								<%
									}
								%>
								</select>
							</div>
						<%
							for (int i = 0; i < exampleMngList.size(); i++) {
								ExampleMng exampleMng = (ExampleMng)exampleMngList.get(i);
								etcUse = exampleMng.getExamNo();
								ImgMng imgMng = null;
								if (i < imgMngList.size()) {
									imgMng = (ImgMng)imgMngList.get(i);
								}
						%>
							<div>
								<input type="checkbox" name="" id="" /><label for="" name="la22" class="la22"><%= i + 1 %></label>
								<span class="thumb1"><img src="<%= (imgMng != null) ? imgMng.getFilePath() : "" %><%= (imgMng != null) ? imgMng.getSavFname() : "" %>" alt="" width="60px" height="30px" /></span>
								<input type="file" name="imgMng22_<%= i + 1 %>" id="" class="ip160 imgMng22" />
								<input type="text" name="fileDesc22_<%= i + 1 %>" id="" class="ip120 fileDesc22" value="<%= (imgMng != null) ? imgMng.getFileDesc() : "" %>" />
								<span class="btn lightB"><a href="javascript:addExam22(<%= i + 1 %>);" name="aa22">추가</a></span>
								<span class="btn lightB"><a href="javascript:deleteExam22(<%= i + 1 %>);" name="ad22">삭제</a></span>	
<!--								<span class="btn dark sp22" name="sp22"><a href="javascript:addEtcExam22();">기타추가</a></span>	-->
								<input type="hidden" name="examNo22" id="" value="<%= exampleMng.getExamNo() %>" />
								
								<input type="hidden" name="orgFname22_<%= i + 1 %>" id="" value="<%= (imgMng != null) ? imgMng.getOrgFname() : "" %>" class="orgFname22" />
								<input type="hidden" name="savFname22_<%= i + 1 %>" id="" value="<%= (imgMng != null) ? imgMng.getSavFname() : "" %>" class="savFname22" />
								<input type="hidden" name="filePath22_<%= i + 1 %>" id="" value="<%= (imgMng != null) ? imgMng.getFilePath() : "" %>" class="filePath22" />
								<input type="hidden" name="fileSize22" id="" value="<%= (imgMng != null) ? imgMng.getFileSize() : 0 %>" />
							</div>
						<%
							}
						%>
						</td>	
					</tr>
					<%
					}
					else if (questMng.getQuestType().equals("HH104") && questMng.getExamType().equals("II102")) {
					%>
					<tr id="tr11" style="display: <%= (questMng.getQuestType().equals("HH104") && questMng.getExamType().equals("II102")) ? "block" : "none" %>;">
						<th></th>
						<td colspan="3" class="sec02" id="td11">
							<div>
								<label for="">선택 수</label>
								<input type="text" name="choiceCnt41" id="" class="ip50" value="<%= questMng.getChoiceCnt() %>" num="i" required_ fieldTitle="선택 수" /> 개								
								<input type="hidden" name="choiceRange41" id="" value="<%= questMng.getChoiceRange() %>" />
							</div>
						<%
							for (int i = 0; i < exampleMngList.size(); i++) {
								ExampleMng exampleMng = (ExampleMng)exampleMngList.get(i);
								etcUse = exampleMng.getExamNo();
								ImgMng imgMng = null;
								if (i < imgMngList.size()) {
									imgMng = (ImgMng)imgMngList.get(i);
								}
						%>
							<div>
								<input type="checkbox" name="" id="" /><label for="" name="la42" class="la42"><%= i + 1 %></label>
								<span class="thumb1"><img src="<%= (imgMng != null) ? imgMng.getFilePath() : "" %><%= (imgMng != null) ? imgMng.getSavFname() : "" %>" alt="" width="60px" height="30px" /></span>
								<input type="file" name="imgMng42_<%= i + 1 %>" id="" class="ip160 imgMng42" />
								<input type="text" name="fileDesc42_<%= i + 1 %>" id="" class="ip120 fileDesc42" value="<%= (imgMng != null) ? imgMng.getFileDesc() : "" %>" />
								<span class="btn lightB"><a href="javascript:addExam42(<%= i + 1 %>);" name="aa42">추가</a></span>
								<span class="btn lightB"><a href="javascript:deleteExam42(<%= i + 1 %>);" name="ad42">삭제</a></span>	
<!--								<span class="btn dark sp42" name="sp42"><a href="javascript:addEtcExam42();">기타추가</a></span>	-->
								<input type="hidden" name="examNo42" id="" value="<%= exampleMng.getExamNo() %>" />
								
								<input type="hidden" name="orgFname42_<%= i + 1 %>" id="" value="<%= (imgMng != null) ? imgMng.getOrgFname() : "" %>" class="orgFname42" />
								<input type="hidden" name="savFname42_<%= i + 1 %>" id="" value="<%= (imgMng != null) ? imgMng.getSavFname() : "" %>" class="savFname42" />
								<input type="hidden" name="filePath42_<%= i + 1 %>" id="" value="<%= (imgMng != null) ? imgMng.getFilePath() : "" %>" class="filePath42" />
								<input type="hidden" name="fileSize42" id="" value="<%= (imgMng != null) ? imgMng.getFileSize() : 0 %>" />
							</div>
						<%
							}
						%>
						</td>
					</tr>
					<%
					}
					%>
					<tr>
						<th><span class="red">*</span>스크리닝 적용 여부</th>
						<td>
							<input type="radio" name="filterYn_" id="filterYnY" onclick="clickFilterYn('Y');" <%= questMng.getFilterYn().equals("Y") ? "checked" : "" %> <%= (questMng.getQuestType().equals("HH103") || questMng.getQuestType().equals("HH104")) ? "disabled" : "" %> /><label for="">예</label>
							<input type="radio" name="filterYn_" id="filterYnN" onclick="clickFilterYn('N');" <%= questMng.getFilterYn().equals("N") ? "checked" : "" %> <%= (questMng.getQuestType().equals("HH103") || questMng.getQuestType().equals("HH104")) ? "disabled" : "" %> /><label for="">아니오</label>							
						</td>
						<th>스크리닝 답</th>
						<td>
							<input type="text" name="filterAnswer" id="" class="ip100" value="<%= questMng.getFilterAnswer() %>" <%= questMng.getFilterYn().equals("N") ? "disabled" : "" %> />
						</td>
					</tr>
					<tr>
						<th>보기병합</th>
						<td>
							<select name="mergeType" id="" onchange="changeMergeType();">
								<option value="1" <%= questMng.getMergeType().equals("1") ? "selected" : "" %>>1+2 / 3+4</option>
								<option value="2" <%= questMng.getMergeType().equals("2") ? "selected" : "" %>>1+2 / 3 / 4+5</option>
								<option value="3" <%= questMng.getMergeType().equals("3") ? "selected" : "" %>>1+2+3 / 4</option>
								<option value="9" <%= questMng.getMergeType().equals("9") ? "selected" : "" %>>없음</option>
							</select>
						</td>
						<th>병합항목</th>
						<td class="sec">
							<input type="text" name="mergeTitle" id="" class="ip100" value="<%= (questMng.getMergeTitle() != null) ? questMng.getMergeTitle() : "" %>" <%= questMng.getMergeType().equals("9") ? "disabled" : "" %> />
							<input type="text" name="mergeTitle2" id="" class="ip100" value="<%= (questMng.getMergeTitle2() != null) ? questMng.getMergeTitle2() : "" %>" <%= questMng.getMergeType().equals("9") ? "disabled" : "" %> />
							<input type="text" name="mergeTitle3" id="" class="ip100" value="<%= (questMng.getMergeTitle3() != null) ? questMng.getMergeTitle3() : "" %>" <%= !questMng.getMergeType().equals("2") ? "disabled" : "" %> />
						</td>
					</tr>
					<tr>
						<th>분기 여부</th>
						<td>
							<input type="radio" name="branchYn_" id="branchYnY" onclick="clickBranchYn('Y');" <%= questMng.getBranchYn().equals("Y") ? "checked" : "" %> <%= !questMng.getQuestType().equals("HH101") ? "disabled" : "" %> /><label for="">예</label>
							<input type="radio" name="branchYn_" id="branchYnN" onclick="clickBranchYn('N');" <%= questMng.getBranchYn().equals("N") ? "checked" : "" %> <%= !questMng.getQuestType().equals("HH101") ? "disabled" : "" %> /><label for="">아니오</label>							
						</td>
						<th>보기 로테이션</th>
						<td>
							<input type="radio" name="examRotateyn_" id="" onclick="clickExamRotateyn('Y');" <%= questMng.getExamRotateyn().equals("Y") ? "checked" : "" %> /><label for="">예</label>
							<input type="radio" name="examRotateyn_" id="" onclick="clickExamRotateyn('N');" <%= questMng.getExamRotateyn().equals("N") ? "checked" : "" %> /><label for="">아니오</label>							
						</td>
					</tr>
					<tr id="trtr" style="display: <%= (questMng.getQuestType().equals("HH101") && questMng.getBranchYn().equals("Y")) ? "block" : "none" %>;">
						<th>분기 문항</th>
						<td colspan="3" class="sec" id="tdtd">
						<%
							for (int i = 0; i < exampleMngList.size(); i++) {
								ExampleMng exampleMng = (ExampleMng)exampleMngList.get(i);
						%>
							<div class="third">
								<label for="">보기번호</label>
<!--								<input type="text" name="examNo" id="" class="ip80" value="1" readonly />-->
								<input type="radio" name="" id="" /><label for="" name="lala" class="lala"><%= i + 1 %></label>
							</div>
							<div class="twothird">
								<label for="">분기할 문항 번호</label>
								<input type="text" name="branchNo" id="" class="ip80" value="<%= exampleMng.getBranchNo() %>" <%= questMng.getBranchYn().equals("N") ? "disabled" : "" %> num="i" />
							</div>
						<%
							}
						%>
						</td>
					</tr>
				</table>
				<!-- // 1단수선택 -->
				
				<div class="btnareaC">
					<span class="btn radiusB"><a href="javascript:fncUpdateQuestMng();">저장</a></span>
					<span class="btn radiusB"><a href="javascript:fncDetailQuestMng('<%= questMng.getPollIdx() %>');">취소</a></span>
				</div>
				
			</div>
			<!-- // contents -->
			
		</div>
		<!-- // .inner_wrap -->
	</div>
	<!-- // #wrap -->

<!-- selected userId -->
<input type="hidden" name="pollIdx" value="<%= pollMng.getPollIdx() %>">

<input type="hidden" name="branchYn" value="<%= questMng.getBranchYn() %>" />
<input type="hidden" name="examRotateyn" value="<%= questMng.getExamRotateyn() %>" />
<input type="hidden" name="filterYn" value="<%= questMng.getFilterYn() %>" />
<input type="hidden" name="etcUseYn11" value="<%= (etcUse == 99) ? "Y" : "N" %>" />
<input type="hidden" name="etcUseYn21" value="<%= (etcUse == 99) ? "Y" : "N" %>" />
<input type="hidden" name="etcUseYn41" value="<%= (etcUse == 99) ? "Y" : "N" %>" />
<input type="hidden" name="etcUseYn12" value="<%= (etcUse == 99) ? "Y" : "N" %>" />
<input type="hidden" name="etcUseYn22" value="<%= (etcUse == 99) ? "Y" : "N" %>" />
<input type="hidden" name="etcUseYn42" value="<%= (etcUse == 99) ? "Y" : "N" %>" />

<input type="hidden" name="delYnu" value="N">
<input type="hidden" name="delYnd" value="N">

<input type="hidden" name="filePath" value="">
<input type="hidden" name="savFname" value="">
<input type="hidden" name="orgFname" value="">
<!-- end of list -->

</form>

<script language="JavaScript">
// document.getElementsByName("questMngName")[0].focus();
</script>

</body>
</html>
