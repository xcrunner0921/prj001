<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="seoul.research.poll.service.SurveyWebSearchVO" %>
<%@ page import="seoul.research.domain.SurveyWeb" %>
<%@ page import="com.sds.emp.common.*" %>
<%@ page import="java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int gMenuIndex		= 2;
	int gSubMenuIndex	= 1;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/content.css"/>

<%

	SurveyWebSearchVO surveyWebSearchVO = (SurveyWebSearchVO)request.getAttribute("surveyWebSearchVO");
	String searchGubun = EmpUtil.null2str(surveyWebSearchVO.getPollIdx());
	String searchName = EmpUtil.null2str(surveyWebSearchVO.getSearchName());
	String rSearchCount = EmpUtil.null2str(surveyWebSearchVO.getSearchCount());
	String condition = EmpUtil.null2str(surveyWebSearchVO.getSearchCondition());
	String keyword = EmpUtil.null2str(surveyWebSearchVO.getSearchKeyword());

	Page resultPage = (Page)request.getAttribute("resultPage");
	
	ArrayList resultList = (ArrayList)resultPage.getList();	
	int pagesize = resultPage.getPagesize();
	
	NumberFormat nf = NumberFormat.getInstance(); // 숫자에 콤마 표시
	nf.setMaximumIntegerDigits(5); //최대 5자리까지
	
//	ArrayList askCodeList = (ArrayList)request.getAttribute("askCodeList");
%>
<script type="text/javascript" src="/include/jquery-latest.min.js"></script> 
<script type="text/javascript">
<!--
	var userDelFlag 	= '<%=session.getAttribute("userDelFlag") %>';
	var userBirthDay 	= '<%=session.getAttribute("userBirthDay") %>';
	var userSido 		= '<%=session.getAttribute("userSido") %>';
	var userMf 			= '<%=session.getAttribute("userMf") %>';
	var userId 			= '<%=session.getAttribute("userId") %>';
	var userAgeChk	  	= "N";

	//alert("userDelFlag : " + userDelFlag + " , userBirthDay : " + userBirthDay + " , userSido : " + userSido + " , userMf : " + userMf);

function fncGetSurveyWebList(arg) {
	
	if(arg == "1"){
		document.frm.pageIndex.value = 1;
	}
	
   	document.frm.action="<c:url value='/listSurveyWeb.do'/>";
   	document.frm.submit();		
}

function lastday(calyear, calmonth){
	var monthDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

	if( ((calyear % 4 == 0) && (calyear % 100 != 0)) || (calyear % 400 == 0) ){
		monthDays[1] = 29;
	}

	var nDays = monthDays[calmonth - 1];
	return nDays;
}


//생년월일체크
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

function surveyPopup(num, str, NextQ, realCheck, pollRestrict, pollRestrictYn) {
	
	//var currentPage = document.location.href; //패널정보 확인이 잘 안됨 . 주석처리 : 2013.10.01. 하현숙 
	var currentPage = "http://research.seoul.go.kr/"; 

	if((userId == null) || (userId =="") || (userId == "null") || ('<%=session.getAttribute("userId") %>' == null)){
		alert("서울시 통합로그인후 참여가 가능합니다.");
		document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?refresh_url=http://research.seoul.go.kr&amp;SITE_GB=GB011"
		return;
	}
	
	if((realCheck == "") || (realCheck == null)){
		alert("실명인증이 안된 회원은 실명인증 후 참여가 가능합니다.");
		document.location.href="https://www.seoul.go.kr/seoul/servlet/seoul?div=mem&amp;a=UserInfoViewRenewalApp&amp;sNextVal=jsp%2Fmyseoul%2Fmyseoul_auth.jsp&amp;pageTitle=1"
		return;
	}


	if(userDelFlag != "N"){
		alert("패널가입 후 조사참여가 가능합니다.\n\n마이페이지에서 패널 가입 후 참여하여 주시기 바랍니다.");
		return;
	}


	
	var pollJoinChk1  = "N";
	var pollJoinChk2  = "N";
	var pollJoinChk3  = "N";
	var MfynChk 	 	= "MfynChk"+num;
	var pollMfyn	 	= "Mfyn"+num;
	var pollMfynCnt	 	= "MfynCnt"+num;
	
	var AgeynChk	 	= "AgeynChk"+num;
	var pollAgeyn	 	= "Ageyn"+num;
	var pollAgeynCnt 	= "AgeynCnt"+num;

	var RegionynChk	 		= "RegionynChk"+num;
	var pollRegionyn 		= "Regionyn"+num;
	var pollRegionynCnt	 	= "RegionynCnt"+num;
	
	//성별구분
	var MfynVal			= $("#"+MfynChk).val();
	var pollMfynVal		= $("#"+pollMfyn).val();

	if((userMf!="남") && (userMf!="여")){
		if((userMf == "1")||(userMf == "3")||(userMf == "5")||(userMf == "7")||(userMf == "9")){
			userMf = "남";
		}else if((userMf == "0")||(userMf == "2")||(userMf == "4")||(userMf == "6")||(userMf == "8")){
			userMf = "여";
		}else{
			userMf = "기타";
		}
	}

	if(MfynVal == "Y"){
		pollMfynVal = pollMfynVal.split("::");
		for(var i=0; i < pollMfynVal.length; i++){
			if(pollMfynVal[i] == userMf){
				pollJoinChk1 = "Y";
			}
		}
	}else{
		pollJoinChk1 = "Y";
	}
	
	//나이구분
	var AgeynVal		= $("#"+AgeynChk).val(); 
	var pollAgeynVal	= $("#"+pollAgeyn).val(); 

	//computerAge(userBirthDay);
	if((userBirthDay != "") ||(userBirthDay != null)){
		if(userBirthDay.length == "8"){
			age = computerAge(userBirthDay);	//만 나이
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
				alert("패널정보를 확인할 수 없습니다.\n패널정보를 수정후 다시 시도해주세요.(2)");
				return;
			}
		}
	}else{
		alert("패널정보를 확인할 수 없습니다.\n패널정보를 수정후 다시 시도해주세요.");
		return;
	}
	
	if(userAgeChk == "Y"){
		alert("정상적인 생년이 아닙니다.\n마이페이지 > 패널정보수정에서 수정후 다시 시도해주세요. (1)");
		return;
	}else if(userAgeChk == "M"){
		alert("정상적인 생월이 아닙니다.\n마이페이지 > 패널정보수정에서 수정후 다시 시도해주세요. (2)");
		return;
	}else if(userAgeChk == "D"){
		alert("정상적인 생일이 아닙니다.\n마이페이지 > 패널정보수정에서 수정후 다시 시도해주세요. (3)");
		return;
	}
	if(userAgeChk != "P"){
		alert("정상적인 생년월일이 아닙니다.\n마이페이지 > 패널정보수정에서 수정후 다시 시도해주세요. (4)");
		return;
	}
	
	if(AgeynVal == "Y"){
		pollAgeynVal = pollAgeynVal.split("::");
		for(var i=0; i < pollAgeynVal.length; i++){
			if(pollAgeynVal[i] == generation){
				pollJoinChk2 = "Y";
			}
		}
	}else{
		pollJoinChk2 = "Y";
	}
	

	//지역구분
	var RegionynVal		= $("#"+RegionynChk).val();
	var pollRegionynVal	= $("#"+pollRegionyn).val();
	if(RegionynVal == "Y"){
		pollRegionynVal = pollRegionynVal.split("::");
		for(var i=0; i < pollRegionynVal.length; i++){
			if(pollRegionynVal[i] == userSido){
				pollJoinChk3 = "Y";
			}
		}
	}else{
		pollJoinChk3 = "Y";
	}
	
	
	//alert("userMf : " + userMf + " , generation : " + generation + " , userSido : " + userSido);
	//alert("pollJoinChk1 : " + pollJoinChk1 + " , pollJoinChk2 : " + pollJoinChk2 + " , pollJoinChk3 : " +pollJoinChk3 + ", userDelFlag : " + userDelFlag);
	if(pollJoinChk1 != "Y"){
		alert("참여대상이 아닙니다.(성별)");
		return;
	}
	if(pollJoinChk2 != "Y"){
		alert("참여대상이 아닙니다.(나이)");
		return;
	}
	if(pollJoinChk3 != "Y"){
		alert("참여대상이 아닙니다.(지역)");
		return;
	}

	//시도:서울.경기.인천 이외의 지역은 제한됨 (2013.09.29.하현숙 추가)
	var sido = '<%=session.getAttribute("userSido")%>';
	if("서울" == sido || "경기" == sido || "인천" == sido ){
		
	}else{
		if("" != '<%=session.getAttribute("userId")%>'){
			alert("서울,경기,인천 지역 이외의 회원은 패널 활동이 제한됩니다.\n\n마이페이지 - 패널정보수정 화면에서 거주시도를 확인하여 주시기 바랍니다.");
			return;
		}
	}



	
	var PanelSexCnt_ 		= "PanelSexCnt"+num;
	var PanelGenerationCnt_	= "PanelGenerationCnt"+num;
	var PanelSidoCnt_		= "PanelSidoCnt"+num;

	var	PanelSexCnt			= $("#"+PanelSexCnt_).val();
	var	PanelGenerationCnt	= $("#"+PanelGenerationCnt_).val();
	var	PanelSidoCnt		= $("#"+PanelSidoCnt_).val();

	var PanelSexCntArray 		= PanelSexCnt.split("::");
	var PanelGenerationCntArray = PanelGenerationCnt.split("::");
	var PanelSidoCntArray 		= PanelSidoCnt.split("::");

	var mCnt	=	PanelSexCntArray[0];
	var fCnt	=	PanelSexCntArray[1];
		
	var Generation10Cnt	=	PanelGenerationCntArray[0];	
	var Generation20Cnt	=	PanelGenerationCntArray[1];
	var Generation30Cnt	=	PanelGenerationCntArray[2]; 
	var Generation40Cnt	=	PanelGenerationCntArray[3];
	var Generation50Cnt	=	PanelGenerationCntArray[4];
	var Generation60Cnt	=	PanelGenerationCntArray[5];

	var seoulCnt		= PanelSidoCntArray[0];
	var gyeonggiCnt		= PanelSidoCntArray[1];
	var incheonCnt		= PanelSidoCntArray[2];
	var othersCnt		= PanelSidoCntArray[3];

	pollMfynCnt_		=	$("#"+pollMfynCnt).val();
	pollAgeynCnt_		=	$("#"+pollAgeynCnt).val();
	pollRegionynCnt_	=	$("#"+pollRegionynCnt).val();


	
	//지역 표본수
	var pollRegionynArray			=	pollRegionynCnt_.split("::");
	for(var i=0; i < pollRegionynArray.length; i++){
		var pollRegionynPartCnt	= 	pollRegionynArray[i].split("||");
	}

	if(RegionynVal == "Y"){
		for(var i=0; i < pollRegionynArray.length; i++){
			for(var e=0; e < pollRegionynPartCnt.length; e++){
				if(pollRegionynPartCnt[e] == 'FF301'){
					//alert(pollRegionynPartCnt[e + 1] + " , " + seoulCnt);
					if(pollRegionynPartCnt[e + 1] <= Number(seoulCnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}else if(pollRegionynPartCnt[e] == 'FF302'){
					//alert(pollRegionynPartCnt[e + 1] + " , " + gyeonggiCnt);
					if(pollRegionynPartCnt[e + 1] <= Number(gyeonggiCnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}else if(pollRegionynPartCnt[e] == 'FF303'){
					//alert(pollRegionynPartCnt[e + 1] + " , " + incheonCnt);
					if(pollRegionynPartCnt[e + 1] <= Number(incheonCnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}else if(pollRegionynPartCnt[e] == 'FF304'){
					//alert(pollRegionynPartCnt[e + 1] + " , " + othersCnt);
					if(pollRegionynPartCnt[e + 1] <= Number(othersCnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}
			}
		}
	}

	
	//나이 표본수
	var pollAgeynArray			=	pollAgeynCnt_.split("::");
	for(var i=0; i < pollAgeynArray.length; i++){
		var pollAgeynPartCnt	= 	pollAgeynArray[i].split("||");
	}
	if(AgeynVal == "Y"){
		for(var i=0; i < pollAgeynArray.length; i++){
			for(var e=0; e < pollAgeynPartCnt.length; e++){
				if(pollAgeynPartCnt[e] == 'FF201'){
					//alert(pollAgeynPartCnt[e + 1] + " <= " + Generation10Cnt);
					if(pollAgeynPartCnt[e + 1] <= Number(Generation10Cnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}else if(pollAgeynPartCnt[e] == 'FF202'){
					//alert(pollAgeynPartCnt[e + 1] + " , " + Generation20Cnt);
					if(pollAgeynPartCnt[e + 1] <= Number(Generation20Cnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}else if(pollAgeynPartCnt[e] == 'FF203'){
					//alert(pollAgeynPartCnt[e + 1] + " , " + Generation30Cnt);
					if(pollAgeynPartCnt[e + 1] <= Number(Generation30Cnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}else if(pollAgeynPartCnt[e] == 'FF204'){
					//alert(pollAgeynPartCnt[e + 1] + " , " + Generation40Cnt);
					if(pollAgeynPartCnt[e + 1] <= Number(Generation40Cnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}else if(pollAgeynPartCnt[e] == 'FF205'){
					//alert(pollAgeynPartCnt[e + 1] + " , " + Generation50Cnt);
					if(pollAgeynPartCnt[e + 1] <= Number(Generation50Cnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}else if(pollAgeynPartCnt[e] == 'FF206'){
					//alert(pollAgeynPartCnt[e + 1] + " , " + Generation60Cnt);
					if(pollAgeynPartCnt[e + 1] <= Number(Generation60Cnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}
			}
		}
	}


	//성별표본수	userMf
	var pollMfynArray		=	pollMfynCnt_.split("::");
	for(var i=0; i < pollMfynArray.length; i++){
		var pollMfynPartCnt		= 	pollMfynArray[i].split("||");
	}
	if(MfynVal == "Y"){
		for(var i=0; i < pollMfynArray.length; i++){
			for(var e=0; e < pollMfynPartCnt.length; e++){
				if(pollMfynPartCnt[e] == 'FF101'){
					//alert(pollMfynPartCnt[e + 1] + " , " + mCnt);
					if(pollMfynPartCnt[e + 1] <= Number(mCnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}else if(pollMfynPartCnt[e] == 'FF102'){
					//alert(pollMfynPartCnt[e + 1] + " , " + fCnt);
					if(pollMfynPartCnt[e + 1] <= Number(fCnt)){
						alert("목표 표본수를 넘어 더 이상 참여하지 못합니다.");
						return;
					} 
				}
			}
		}
	}
	
	
	
	
	
	var url = "";
	var nowQ = NextQ - 1;
	//Y이미참여   N불참여
	if(str == "Y"){
		alert("이미 완료하신 설문조사입니다.");
		return;
	}
	
	if(NextQ > 0){
		if(confirm("현재 "+nowQ+"번 설문까지 답하셨습니다.\n"+NextQ+"번 설문으로 이동하시겠습니까?")== true){
			url = "/getSurveyQuest.do?pollIdx="+num+"&amp;nextQuestNo="+NextQ+"&amp;questNo=0";
		}else{
			return;
		}
	}else{
		url = "/getSurveyWeb.do?pollIdx="+num;
	}

	if (pollRestrictYn == "Y") {
		alert(pollRestrict + " 이전에 가입한 패널만 참여가능합니다.");
		return;
	}
	
	var name = "서울시여론조사";
	var left = 0;
	var top = 0;
	var width = 750;
	var height = 630;
	var toolbar = 0;
	var menubar = 0;
	var statusbar = 0;
	var scrollbar = 0;
	
	var resizable = 1;
	var location = 0;
	var selectionWindow = popup_window_common(url, name, left, top, width, height, toolbar, menubar, statusbar, scrollbar, resizable, location);

}
/*
 * 기능설명 : 팝업
 * 파라미터 : URL, 팝업이름, 왼쪽위치, 상단위치, 팝업가로사이즈, 팝업세로사이즈, 툴바, 메뉴바, 상태바, 스크롤바, 리사이징, 로케이션바
 */
function popup_window_common(url, name, left, top, width, height, toolbar, menubar, statusbar, scrollbar, resizable, location) {
	toolbar_str = toolbar ? 'yes' : 'no';
	menubar_str = menubar ? 'yes' : 'no';
	statusbar_str = statusbar ? 'yes' : 'no';
	scrollbar_str = scrollbar ? 'yes' : 'no';
	resizable_str = resizable ? 'yes' : 'no';
	location_str = location ? 'yes' : 'no';

	window.open(url, name, 'left='+left+',top='+top+',width='+width+',height='+height+',toolbar='+toolbar_str
				+',menubar='+menubar_str+',status='+statusbar_str+',scrollbars='+scrollbar_str 
				+',resizable='+resizable_str+',location='+location_str
	);

}
-->
</script>

</head>
<body>
<form id="frm" name="frm" method="post" action="<c:url value='/listSurveyWeb.do'/>">
<input type="hidden" name="pageIndex" value="<%=resultPage.getCurrentPage()%>"/>
<div id="wrap">
    <!-- header -->
    <%@ include file="/include/top.jsp" %>
    <!-- //header -->
    <!-- container -->
    <div id="container" class="introJoinPanel">
        <!-- snb -->
        <%@ include file="/include/panel/left.jsp" %>
        <!-- //snb -->
        <!-- content -->
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="/img/poll/tit_onSurvey.gif" alt="진행중인 여론조사" /></h2>
                <p><img src="/img/poll/txt_spotOnSurvey.gif" alt="참여 인원에 따라 조사기간보다 조기에 마감될 수 있습니다." /></p>
            </div>
            <div class="article">
                <div class="searchBox">
                    <select name="searchCondition" title="검색항목" value="">
                        <option value="0">선택</option>
                        <option value="1" <%= ("1".equals(condition) ? "selected" : "")%>>부서</option>
                        <option value="2" <%= ("2".equals(condition) ? "selected" : "")%>>제목</option>
                    </select>
                    <input type="text" name="searchKeyword" value="<%= keyword %>" title="검색어" />
                    <a href="javascript:fncGetSurveyWebList('1');"><img src="/img/poll/btn_search.gif" alt="검색" /></a>
                </div>                
                <div class="tbl_listNum">
                    <span>총 <em><%= resultPage.getTotalCount() %></em>건</span>
                    <select name="searchCount" title="목록 출력수">
                        <option value="15">출력수</option>
                        <option value="20">20</option>
                        <option value="30">30</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                    </select>
                </div>
                <table class="tbl_list" summary="">
                    <caption></caption>
                    <colgroup>
                        <col width="6%" />
                        <col width="9%" />
                        <col width="12%" />
                        <col width="" />
                        <col width="17%" />
                        <col width="18%" />
                        <col width="7%" />
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">구분</th>
                        <th scope="col">부서</th>
                        <th scope="col">조사제목</th>
                        <th scope="col">기간</th>
                        <th scope="col">조사대상</th>
                        <th scope="col" class="noLine">참여<br />인원</th>
                    </tr>
                    </thead>
                    <tbody>
                    	<%
						int currentPageIndex = resultPage.getCurrentPage();
						int no = (currentPageIndex - 1) * pagesize;
						
						if (resultList.size() > 0) {
							for (int i = 0; i < resultList.size(); i++) {
								SurveyWeb surveyWeb = (SurveyWeb)resultList.get(i);
						%>
                    <tr>
                        <td><%=++no%></td>
                        <td><%= surveyWeb.getGubunName()%></td>
                        <td><%= surveyWeb.getCodeName().replaceAll("\\|", "<br />").replaceAll(" ", "") %></td>
                        <td class="txtLeft"><a href="javascript:surveyPopup('<%= surveyWeb.getPollIdx()%>', '<%= surveyWeb.getProgYn()%>', '<%= surveyWeb.getNextQuestNo()%>', '<%= real_check_falg %>', '<%= surveyWeb.getPollRestrict()%>', '<%= surveyWeb.getPollRestrictYn()%>');" ><%= surveyWeb.getPollTitle()%></a></td>
                        <td><%= surveyWeb.getPollTermstartYmd()%>&nbsp;<%= surveyWeb.getPollTermstartHour()%>:<%= surveyWeb.getPollTermstartMinute()%>~<br /><%= surveyWeb.getPollTermendYmd()%>&nbsp;<%= surveyWeb.getPollTermendHour()%>:<%= surveyWeb.getPollTermendMinute()%></td>
                        <td><%= surveyWeb.getPollTarget()%></td>
                        <td class="noLine"><%= nf.format(surveyWeb.getPollSample()) %></td>
                    </tr>
	                    
	                    <input type="hidden" id="MfynChk<%= surveyWeb.getPollIdx()%>" 		value="<%= surveyWeb.getMfynChk() %>" />
	                    <input type="hidden" id="Mfyn<%= surveyWeb.getPollIdx()%>" 			value="<%= surveyWeb.getPollMfyn() %>" />
	                    <input type="hidden" id="MfynCnt<%= surveyWeb.getPollIdx()%>" 		value="<%= surveyWeb.getPollMfynNumber() %>" />
	                    <input type="hidden" id="PanelSexCnt<%= surveyWeb.getPollIdx()%>" 		value="<%= surveyWeb.getPanelSexCnt() %>" />
	                    
	                    <input type="hidden" id="AgeynChk<%= surveyWeb.getPollIdx()%>" 		value="<%= surveyWeb.getAgeynChk() %>" />
	                    <input type="hidden" id="Ageyn<%= surveyWeb.getPollIdx()%>" 			value="<%= surveyWeb.getPollAgeyn() %>" />
	                    <input type="hidden" id="AgeynCnt<%= surveyWeb.getPollIdx()%>" 		value="<%= surveyWeb.getPollAgeynNumber() %>" />
	                    <input type="hidden" id="PanelGenerationCnt<%= surveyWeb.getPollIdx()%>" 		value="<%= surveyWeb.getPanelGenerationCnt() %>" />
	    
	                    <input type="hidden" id="RegionynChk<%= surveyWeb.getPollIdx()%>" 	value="<%= surveyWeb.getRegionynChk() %>" />
	                    <input type="hidden" id="Regionyn<%= surveyWeb.getPollIdx()%>" 		value="<%= surveyWeb.getPollRegionyn() %>" />
	                    <input type="hidden" id="RegionynCnt<%= surveyWeb.getPollIdx()%>" 	value="<%= surveyWeb.getPollRegionynNumber() %>" />
	                    <input type="hidden" id="PanelSidoCnt<%= surveyWeb.getPollIdx()%>" 		value="<%= surveyWeb.getPanelSidoCnt() %>" />

	                    <% } %>
	            </tbody>
                </table>
                <div class="paging">
                	<div class="pageNum">
						<anyframe:pagenavigator linkUrl="javascript:fncGetSurveyWebList(2);" 
						pages="<%=resultPage%>" 
						formName="frm"
						firstImg="/img/poll/btn_fstList.gif" 
						prevImg="/img/poll/btn_prevList.gif" 
						lastImg="/img/poll/btn_lstList.gif" 
						nextImg="/img/poll/btn_nextList.gif" />
					</div>
                </div>
	                    <% }else{ %>
	                <tr>
                        <td align="center" colspan="7" class="noLine">조회 결과가 없습니다.</td>
                    </tr>
	            </tbody>
                </table>
	                    <% } %>
                    
            </div>
        </div>
        <!-- //content -->
    </div>
    <!-- //container -->
   
    <!-- footer -->
    <%@ include file="/include/footer.jsp" %>
    <!-- //footer -->
</div>
</form>
</body>
</html>