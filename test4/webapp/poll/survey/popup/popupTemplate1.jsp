<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="seoul.research.poll.service.SurveyWebSearchVO" %>
<%@ page import="seoul.research.domain.SurveyWeb" %>
<%@ page import="com.sds.emp.common.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="anyframe.common.Page" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/popup.css"/>

<%
	SurveyWeb questTitle = (SurveyWeb)request.getAttribute("questTitle");

	Page resultPage = (Page)request.getAttribute("resultPage");
	ArrayList resultList = (ArrayList)resultPage.getList();	
	int pagesize = resultPage.getPagesize();
	
	ArrayList askCodeList = (ArrayList)request.getAttribute("askCodeList");

	//전역변수선언
	String QuestType = "";
	String sNoAnswer = "";
	int ListSize = resultList.size();
	int nextQuestNo = 0;

	QuestType = questTitle.getQuestType();
	int questNo = questTitle.getQuestNo();
	int questCount = questTitle.getQuestCount();
	String lastQuest = "N";
	String branchYn = questTitle.getBranchYn();

	if(branchYn.equals("Y") && QuestType.equals("HH101")){
		//단수문항에 분기가능할시 조건문 삽입
	}else{
		nextQuestNo = questNo + 1;
	}
	
	
	String popup_bgImg;
	String popup_btn;
	if(questTitle.getPollBgimg().equals("A")){
		popup_bgImg = "1";
		popup_btn = "btn_next01.gif";
	}else if(questTitle.getPollBgimg().equals("B")){
		popup_bgImg = "2";
		popup_btn = "btn_next02.gif";
	}else{
		popup_bgImg = "3";
		popup_btn = "btn_next03.png";
	}
	
	int ProgressBar2;
	int ProgressBar = questTitle.getProgressBar();
	if(ProgressBar > 93){
		ProgressBar2 = 93;
	}else{
		ProgressBar2 = ProgressBar;
	}
	
%>

<script type="text/javascript" src="/include/jquery-latest.min.js"></script> 
<script type="text/javascript">

	var answerChkCount	= 0;
	var chkGubunYn		= "N";
	var arrVal			= new Array();
	var ListSize 		= <%= ListSize %>;
	var selectCnt		= 1;
	var checkCnt		= 0;
	var processCnt		= 0;
	var userBirthDay 	= '<%=session.getAttribute("userBirthDay") %>';
	
$(document).ready(function(){
	
if( navigator.userAgent.match(/Android/i)
	|| navigator.userAgent.match(/webOS/i)
	|| navigator.userAgent.match(/iPhone/i)
	|| navigator.userAgent.match(/iPad/i)
	|| navigator.userAgent.match(/iPod/i)
	|| navigator.userAgent.match(/BlackBerry/i)
	|| navigator.userAgent.match(/Windows Phone/i) 
){//mobile
	$("#contactGubun").val("mobile");
}else {//web
	$("#contactGubun").val("web");
}


	
	
	$("input[name='example_radio']").click(function(){
		$("#answer").val($(this).val());

		var num 		= $(this).val();
		var branchNo 	= "branchNo" + num;
		var nextQuestNo	= $("#"+branchNo).val();
		$("#nextQuestNo").val(nextQuestNo);

		if($(this).val() == '99'){
			$("#etcAnswer").attr("disabled", false);
		}else{
			$("#etcAnswer").attr("disabled", true);
		}
			
	});

	//복수형
	$("input[name='qCheckBox']").click(function(){
		answerChkCount 	= Number($(this).val());
		var qCheckBox	= "qCheckBox";
		var questType	= $("#questType").val();
		var choiceCnt	= $("#choiceCnt").val();
		var choiceRange = $("#choiceRange").val();

		if(this.checked == true){
			checkCnt += 1;
		}else{
			checkCnt -= 1;
		}

		if(questType == "HH102"){
			if(choiceRange == "KK101"){	//cnt 이하
				if(checkCnt > choiceCnt){
					alert(choiceCnt + "개 이하 선택해 주셔야합니다.");
					checkCnt -= 1;
					return false;
				}
			} 
		}
		
		if(answerChkCount == "99"){
			answerChkCount = ListSize;
		}
		
		if(chkGubunYn == "N"){
			for(var i=1; i <= ListSize; i++){
				arrVal[i] = "0"
			}
		}
		chkGubunYn = "Y"
		
		if(this.checked == true){
			arrVal[answerChkCount] = "1";
			if($(this).val() == '99'){
				$("#etcAnswer").attr("disabled", false);
			}
		}else{
			arrVal[answerChkCount] = "0";
			if($(this).val() == '99'){
				$("#etcAnswer").attr("disabled", true);
				$("#etcAnswer").val("");
			}
		}
		$("#SNoAnswer").val(arrVal);
	});


	//순위형
	$("input[name='qCheckBoxRank']").click(function(){
		answerChkCount 	= Number($(this).val());

		var TitleDesc_	= "TitleDesc"+$(this).val();
		var TitleDesc	= $("#"+TitleDesc_).val();

		var rankSpan_ = "rankSpan"+selectCnt;
		var rankSpan = $("#"+rankSpan_).html(TitleDesc);

		var qCheckBoxRank = "qCheckBoxRank"+$(this).val();
		
		var questType	= $("#questType").val();
		var choiceCnt	= $("#choiceCnt").val();
		var choiceRange = $("#choiceRange").val();

		if(this.checked == true){
			checkCnt += 1;
		}else{
			checkCnt -= 1;
		}

		if(choiceCnt < checkCnt){
			alert("총 "+choiceCnt+"순위 까지 선택해야합니다.\n다시 하시길 원하시면 우측하단에 있는 '초기화'버튼을 눌러주세요.");
			checkCnt -= 1;
			return false;
		}
		
		if(answerChkCount == "99"){
			answerChkCount = ListSize;
		}
		
		if(chkGubunYn == "N"){
			for(var i=1; i <= ListSize; i++){
				arrVal[i] = "0"
			}
		}
		chkGubunYn = "Y"
		
		if(this.checked == true){
			arrVal[answerChkCount] = selectCnt;
			if($(this).val() == '99'){
				$("#etcAnswer").attr("disabled", false);
			}
		}else{
			arrVal[answerChkCount] = "0";
			if($(this).val() == '99'){
				$("#etcAnswer").attr("disabled", true);
				$("#etcAnswer").val("");
			}
		}
		$("#SNoAnswer").val(arrVal);



		$("#"+qCheckBoxRank).attr("disabled",true);
		selectCnt++;
		
	});	

	
});

function rankReset(){

	var QuestNo 	= $("#questNo").val();
	var rankSpan;
	var choiceCnt = $("#choiceCnt").val();
	
	if(confirm(QuestNo+"번 문제를 다시 진행 하시겠습니까?")==true){
		$("input[name='qCheckBoxRank']").attr("disabled",false);
		$("input[name='qCheckBoxRank']").attr("checked",false);
		checkCnt = 0;
		selectCnt = 1; 
		chkGubunYn		= "N";
		arrVal = new Array();
		$("#SNoAnswer").val("");
		
		for(var i=1; i<=choiceCnt; i++){
			rankSpan = "rankSpan" + i;
			$("#"+rankSpan).html(i+"순위를 보기에서 선택해주세요.");
		}
	}
}

function chkExam(){

	var uSession = "<%=session.getAttribute("userId") %>";
	if(uSession == null || uSession =="" || uSession == "null"){
		alert("로그아웃 상태입니다 \n서울시 통합회원 로그인 후 참여해 주시기 바랍니다.");
		this.close();
		return;
	}
	
	var answerChk 		= $("#answer").val();
	var sNoAnswerChk	= $("#SNoAnswer").val();
	var etcAnswerChk	= $("#etcAnswer").val();
	var sNoChk 			= $("#SNo").val();
	var questType		= $("#questType").val(); //문제유형
	var filterAnswerChk = $("#filterAnswer").val();
	var filterAnswerArr = "";
	var filterGubun		= "Y";	//Y:Filter안됨 N:Filter됨
	var filterYn		= $("#filterYn").val();
	
	if(sNoAnswerChk.indexOf("1") == -1){
		sNoAnswerChk = 0;
	}
	
	if(answerChk == "" &&  sNoAnswerChk == 0 && (etcAnswerChk == null || etcAnswerChk == "")){
		alert("질문에 대한 답이 없습니다. 확인해 주십시오.");
		return;
	}
	
	if(questType == "HH103"){
		if(etcAnswerChk.replace( /(\s*)/g, "" ) == ""){
			alert("답변엔 공백을 넣을수 없습니다.");
			$("#etcAnswer").val("");
			$("#etcAnswer").focus();
			return;
		}
		if(etcAnswerChk.length > 500){
			alert("500자 이내로 기재해주시기 바랍니다. (공백포함)");
			return;
		}
	}
	
	if(questType == "HH101"){			//단수형일때 기타에대한 의견 유무 확인
		if(answerChk == '99'){
			if(etcAnswerChk == ""){
				alert("기타의견을 기재해 주시기 바랍니다.");
				$("#etcAnswer").focus();
				return;
			}
		}
	}else if(questType == "HH102"){		//복수형일때 기타에 대한 의견 유무 확인
		sNoAnswerChk = sNoAnswerChk.substr(sNoAnswerChk.length -1, 1);
		if(sNoAnswerChk == 1){
			if(etcAnswerChk == ""){
				alert("기타의견을 기재해 주시기 바랍니다.");
				$("#etcAnswer").focus();
				return;
			}
		}
	}
	
	if((questType == "HH101")){
		if(answerChk == '99'){
			if(etcAnswerChk.replace( /(\s*)/g, "" ) == ""){
				alert("답변엔 공백을 넣을수 없습니다.");
				$("#etcAnswer").val("");
				$("#etcAnswer").focus();
				return;
			}
			if(etcAnswerChk.length > 50){
				alert("50자 이내로 기재해주시기 바랍니다. (공백포함)");
				return;
			}
		}
	}

	if((questType == "HH102")){
		sNoAnswerChk = sNoAnswerChk.substr(sNoAnswerChk.length -1, 1);
		if(sNoAnswerChk == 1){
			if(etcAnswerChk.replace( /(\s*)/g, "" ) == ""){
				alert("답변엔 공백을 넣을수 없습니다.");
				$("#etcAnswer").val("");
				$("#etcAnswer").focus();
				return;
			}
			if(etcAnswerChk.length > 50){
				alert("50자 이내로 기재해주시기 바랍니다. (공백포함)");
				return;
			}
		}
	}
	
	
	//스크리닝
	if(questType == "HH101"){
		if(filterYn == "Y"){
			if(filterAnswerChk.length >= 1){
				filterAnswerArr = filterAnswerChk.split(",");
				for(var i=0; i<filterAnswerArr.length; i++){
					if(filterAnswerArr[i] == answerChk){
						$("#filterGubun").val("N");
					}
				}
			}
		}
	}
	//filterYn 문제자체가 스크리닝 여부 체크
	//$("#filterYn").val($("#filterGubun").val());	//스크리닝시 저장 구분값 변경
	
	var questType	= $("#questType").val();
	var choiceCnt	= $("#choiceCnt").val();
	var choiceRange = $("#choiceRange").val();
	
	if((questType == "HH102") || (questType == "HH104")){
		if(choiceRange == "KK102"){			//cnt 이상	Over
			if(checkCnt < choiceCnt){
				alert(choiceCnt+"개 이상 선택해 주셔야합니다.");
				return;
			}
		}else if(choiceRange == "KK103"){	//cnt 같다	Same
			if(checkCnt != choiceCnt){
				alert(choiceCnt+"개 선택해 주셔야합니다.");
				return;
			}
		}
	}



	var age = computerAge(userBirthDay);
	var generation = "";
	if(age>=14 && age<19){
		generation = "10대";
	}else if(age>=19 && age<30){
		generation = "20대";
	}else if(age>=30 && age<40){
		generation = "30대";
	}else if(age>=40 && age<50){
		generation = "40대";
	}else if(age>=50 && age<60){
		generation = "50대";
	}else if(age>=60 && age<130){
		generation = "60대 이상";
	}else{
		generation = "대상제외";
	}
	$("#ageGroup").val(generation);

	if(processCnt > 1){
		alert("데이타 처리중입니다.\n잠시만 기다려주세요.");
		return;
	}else{
		processCnt = processCnt++;
		//완료후 펀션이동
		nextExam();
	}
}
function nextExam(){
	document.frm.action = "<c:url value='/addSurveyWebPop.do'/>";
    document.frm.submit();
}

function chek_close(){
	//opener.location.reload();
	window.opener.location.href = window.opener.location.href; 
}

//마우스 오른 쪽 금지
document.oncontextmenu = function() { return false; }
//새로고침 방지
// Ctrl+R, Ctrl+N, F5 키 막음
function doNotReload(){
	if((event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) ){ // function F5 //78 ,82 ctrl+N , ctrl+R 
		event.keyCode = 0;
		event.cancelBubble = true;
		event.returnValue = false;
	}
}
document.onkeydown = doNotReload;




function lastday(calyear, calmonth){
	var monthDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

	if( ((calyear % 4 == 0) && (calyear % 100 != 0)) || (calyear % 400 == 0) ){
		monthDays[1] = 29;
	}

	var nDays = monthDays[calmonth - 1];
	return nDays;
}
function computerAge(birthday) { 
	var bday=parseInt(birthday.substring(6,8)); 
	var bmo=(parseInt(birthday.substring(4,6))-1); 
	var byr=parseInt(birthday.substring(0,4)); 
	var now = new Date(); 
	tday=now.getDate(); 
	tmo=(now.getMonth()); 
	tyr=(now.getFullYear()); 

	var yearChk		=parseInt(birthday.substr(0,4)); 
	var monthChk	=parseInt(birthday.substr(4,2)); 
	var dateChk 	=parseInt(birthday.substr(6,2));

	if(yearChk <= 1900){
		userAgeChk = "Y";
		return;
	}
	if(monthChk > 12){
		userAgeChk = "M";
		return;
	}

	lastdayChk = lastday(yearChk,monthChk);
	if(dateChk > lastdayChk){
		userAgeChk = "D";
		return;
	}

	userAgeChk = "P";
	
	if((tmo > bmo)||(tmo==bmo & tday>=bday)){ 
		age=byr 
	}else{ 
		age=byr+1; 
	} 
	return tyr-age; 
}

</script>

</head>
<body onunload="chek_close();">
<form name="frm" method="post">
<input type="hidden" 		id="answer" 		name="answer"		/>
<input type="hidden" 		id="pollIdx" 		name="pollIdx" 			value="<%= questTitle.getPollIdx() %>" />
<input type="hidden" 		id="questNo" 		name="questNo" 			value="<%= questTitle.getQuestNo() %>" />
<input type="hidden" 		id="filterYn" 		name="filterYn" 		value="<%= questTitle.getFilterYn() %>" />
<input type="hidden" 		id="filterAnswer" 	name="filterAnswer" 	value="<%= questTitle.getFilterAnswer() %>" />
<input type="hidden" 		id="surveyTitle" 	name="surveyTitle" 		value="<%= questTitle.getQuestTitle() %>" />
<input type="hidden" 							name="searchGubun" 		value="<%= questTitle.getPollIdx()%>" />
<!--  input type="hidden" 							name="searchQuest" 		value="2" /-->
<input type="hidden" 		id="nextQuestNo"	name="nextQuestNo" 		value="<%= nextQuestNo %> " />
<input type="hidden" 		id="progYn" 		name="progYn" 			value="N" />
<input type="hidden"		id="SNo"			name="SNo"				value="0" />
<input type="hidden"		id="SNoAnswer"		name="SNoAnswer"		value="0" />
<input type="hidden"		id="questCount"		name="questCount"		value="<%= questTitle.getQuestCount()%>" />
<input type="hidden"		id="questType"		name="questType"		value="<%= questTitle.getQuestType()%>" />
<input type="hidden"		id="filterGubun"	name="filterGubun"		value="Y" />
<input type="hidden"		id="choiceCnt"		name="choiceCnt"		value="<%= questTitle.getChoiceCnt()%>" />
<input type="hidden"		id="choiceRange"	name="choiceRange"		value="<%= questTitle.getChoiceRange()%>" />
<input type="hidden"		id="contactGubun"	name="contactGubun"		/>
<input type="hidden"		id="ageGroup"		name="ageGroup"			/>

<div class="popup_wrap type<%= popup_bgImg %>">
<div class="popup">
    <div class="popup_header">
	
        <strong><img src="/img/poll/img_logoJoin.png" alt="서울시 여론조사 참여하기" /></strong>
        <div class="progress">
            <span class="bar">
                <img src="/img/poll/bar_progress01.png" class="bar1" alt="" />
                <img src="/img/poll/bar_progress02.png" class="bar2" alt="" style="width:<%= ProgressBar2 %>%;" />
                <img src="/img/poll/bar_progress03.png" class="bar3" alt="" /> 
            </span>
            <span style="position:absolute; top:21px; left:<%= ProgressBar %>%; font-size:11px; color:#fd6c01;"><%= ProgressBar %>%</span>
        </div>
    </div>
    <div class="top"></div>
    <div class="popup_content">
        <div class="txtWrap">        
        <% 
        	String[] positionArry = questTitle.getPosition().split(",");
        	if(!"".equals(questTitle.getPosition())){
        %>
        	<% 
        		if(positionArry.length == 1){
        			if("U".equals(positionArry[0].toString())){
        	%>
        		<div>
					<img src="<%= questTitle.getFilePath2_2() %><%= questTitle.getSavFname2_2() %>" alt="<%= questTitle.getOrgFname2_2() %>" />
				</div>		
        				<%
        			}
        		}else if(positionArry.length == 2){
            %>
           		<div>
   					<img src="<%= questTitle.getFilePath2_2() %><%= questTitle.getSavFname2_2() %>" alt="<%= questTitle.getOrgFname2_2() %>" />
   				</div>		
           	<%
        		}
		 } %>
		
            <p class="question">
                <span><%= questTitle.getQuestNumber() %></span>
                	  <%= questTitle.getQuestTitle() %>
            </p>
            
            <% if(!"".equals(questTitle.getPosition()) && null != questTitle.getPosition()){ 
            	
        		if(positionArry.length == 1){
        			if("D".equals(positionArry[0].toString())){
        	%>
        		<div>
					<img src="<%= questTitle.getFilePath2_1() %><%= questTitle.getSavFname2_1() %>" alt="<%= questTitle.getOrgFname2_1() %>" /><br />
				</div>		
        				<%
        			}
        		}else if(positionArry.length == 2){
            %>
           		<div>
   					<img src="<%= questTitle.getFilePath2_1() %><%= questTitle.getSavFname2_1() %>" alt="<%= questTitle.getOrgFname2_1() %>" /><br />
   				</div>		
           	<%
        		}
			} %>
            
            <div class="answer">
                <ul class="a2List">
                <%               
                if (resultList.size() > 0) {
                	if(QuestType.equals("HH101")){	//단수
                		for (int i = 0; i < resultList.size(); i++) {
							SurveyWeb surveyWeb = (SurveyWeb)resultList.get(i);
				%>
								<li><input type="radio" id="a0<%= surveyWeb.getExamNo()%>" 				name="example_radio" 	value="<%= surveyWeb.getExamNo()%>" /> <label for="a01" onclick="javascript:return false;"><%= surveyWeb.getExamTitle()%></label>
						
						<% if("II102".equals(surveyWeb.getExamType())){  %>
<label for="a01" onclick="javascript:return false;"><%= surveyWeb.getFileDesc()%></label> <br/><!-- 이미지 이름 임시 by 류은희 -->
								<img src="<%= surveyWeb.getFilePath() %><%= surveyWeb.getSavFname() %>"/>
								<br/><br/>
						<% } %>
						
						<% if(Integer.toString(surveyWeb.getExamNo()).equals("99")){  %><!-- 기타가 있으면 input창을 넣는다 -->
								<input type="text" id="etcAnswer" name="etcAnswer" value="" disabled /></li>
						<% }else{ %>
								</li>
						<% } %>
						
						<% if("Y".equals(surveyWeb.getBranchYn())){  %>	<!-- branchYn이 Y이면 분기할 설문번호를 세팅하고 N이면 다음 설문번호를 세팅한다 -->
								<input type="hidden"	id="branchNo<%= surveyWeb.getExamNo()%>"	value="<%= surveyWeb.getBranchNo()%>"/>
						<% }else{ %>
								<input type="hidden"	id="branchNo<%= surveyWeb.getExamNo()%>"	value="<%= nextQuestNo %>"/>
						<% } %>
					<% } %>
				
				
				<%
                	}else if(QuestType.equals("HH102")){	//복수
                		for (int i = 0; i < resultList.size(); i++) {
                			sNoAnswer = sNoAnswer + "0";
							SurveyWeb surveyWeb = (SurveyWeb)resultList.get(i);
				%>
							<li><input type="checkbox"	id="qCheckBox<%= surveyWeb.getExamNo()%>" 	name="qCheckBox" 	value="<%= surveyWeb.getExamNo()%>" /> <label for="a01" onclick="javascript:return false;"><%= surveyWeb.getExamTitle()%></label>
							
					<% if("II102".equals(surveyWeb.getExamType())){  %>
								<img src="<%= surveyWeb.getFilePath() %><%= surveyWeb.getSavFname() %>"/>
								<br/><br/><br/><br/>
					<% } %>
					
					<% if(Integer.toString(surveyWeb.getExamNo()).equals("99")){  %>
						<input type="text" id="etcAnswer" name="etcAnswer" value="" disabled /></li>
					<% }else{ %>
						</li>
					<% } %>
				<% } %>
				
				
				<%
                	}else if(QuestType.equals("HH104")){	//순위
                		int ChoiceCnt =  questTitle.getChoiceCnt();
                %>
		                <ol class="aLank">
					<% for (int i = 0; i < ChoiceCnt; i++){ %>
	                    <li><em><%= i+1 %>순위</em> <span id="rankSpan<%= i+1 %>"><%= i+1 %>순위를 보기에서 선택해주세요.</span></li>
					<% } %>
		                </ol>
		                
		                <ul>
		                <%
				        	for (int i = 0; i < resultList.size(); i++) {
		                			sNoAnswer = sNoAnswer + "0";
									SurveyWeb surveyWeb = (SurveyWeb)resultList.get(i);
						%>
									<li><input type="checkbox"	id="qCheckBoxRank<%= surveyWeb.getExamNo()%>" 	name="qCheckBoxRank" 	value="<%= surveyWeb.getExamNo()%>" /> 
									<% if("II102".equals(surveyWeb.getExamType())){ %>
										<label for="a01" onclick="javascript:return false;"><%= surveyWeb.getFileDesc()%></label>
									<%}else{ %>
										<label for="a01" onclick="javascript:return false;"><%= surveyWeb.getExamTitle()%></label>
									<% } %>
									
									<% if("II102".equals(surveyWeb.getExamType())){  %>
										<input type="hidden" id="TitleDesc<%= surveyWeb.getExamNo()%>" value="<%= surveyWeb.getFileDesc()%>" />
									<% }else{ %>
										<input type="hidden" id="TitleDesc<%= surveyWeb.getExamNo()%>" value="<%= surveyWeb.getExamTitle()%>" />
									<% } %>
							
							<% if("II102".equals(surveyWeb.getExamType())){  %>
										<img src="<%= surveyWeb.getFilePath() %><%= surveyWeb.getSavFname() %>" />
										<br/><br/><br/><br/>
							<% } %>
							
							<% if(Integer.toString(surveyWeb.getExamNo()).equals("99")){  %>
								<input type="text" id="etcAnswer" name="etcAnswer" value="" disabled /></li>
							<% }else{ %>
								</li>
							<% } %>
						<% } %>
		                </ul>
                <%		
                	}else if(QuestType.equals("HH103")){	//주관식
                %>
							<!-- p>
					                    조사에 대한 여러분의 의견을 듣고자 합니다.<br />
					                    조사에 대한 여러분의 의견을 듣고자 합니다.
			                </p> -->
			                <textarea rows="7" cols="104" title="조사에 대한 의견" id="etcAnswer" name="etcAnswer"></textarea>
			                <!-- p>* 조사후기를 남겨주신 분들 중 매월 말 10분을 추첨하여 적립금 5000원을 드립니다.</p> -->
                <% } %>
                <% } %>
                </ul>
            </div>
		
        </div>
        <div class="btnWrap">
            <a href="javascript:chkExam();"><img src="/img/poll/<%= popup_btn %>" alt="다음" /></a>
            
            <% if(QuestType.equals("HH104")){ %>
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:rankReset();"><img src="/img/poll/btn_clear.gif" alt="다시하기" /></a>
            <% } %>
            
            
        </div>
        <div class="bottom"></div>
    </div>
</div>
</div>
</form>
</body>
</html>