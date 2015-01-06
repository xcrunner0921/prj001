
















<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사(조사결과)</title>
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/content.css"/>

<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
<!--<script src="/include/jquery-latest.min.js"></script>-->
<!--<script src="/include/jquery-ui.js"></script>-->

<script type="text/javascript">
<!--
function fncGetSurveyResultList(arg) {
	
	var searchTxt = document.getElementById("searchPollTitle").value; //검색어
	var searchFile = document.getElementById("searchPollFieldName").selectedIndex; //분야
	var searchCond = document.getElementById("searchCondition").selectedIndex; //검색항목(부서 또는 제목)

	//검색어가 입력되면 분야 또는 검색항목 중 하나만 선택해야한다. 	 
	if(searchTxt != ""){
		if((searchFile != 0) && (searchCond != 0)){
			alert("선택값은 하나만 선택해 주세요.");
			return;
		}
	}
		 
	if(arg == "1"){
		document.frm.pageIndex.value = 1;
	}
	
   	document.frm.action="/listSurveyResult.do";
   	document.frm.submit();		
}

function openResult(pollIdx, openYn){
	if(openYn == 0){
		alert("결과보고서 준비중입니다.");
	}else{
		var openUrl = "/popSurveyResult.do?pollIdx="+pollIdx;
		window.open(openUrl,'결과보기','width=640,height=550,scrollbars=yes,resizable=no');
	}
}

function checkAgent(pollIdx, openYn){
	var uAgent = navigator.userAgent.match;
	if( navigator.userAgent.match(/Android/i)
			|| navigator.userAgent.match(/webOS/i)
			|| navigator.userAgent.match(/iPhone/i)
			|| navigator.userAgent.match(/iPad/i)
			|| navigator.userAgent.match(/iPod/i)
			|| navigator.userAgent.match(/BlackBerry/i)
			|| navigator.userAgent.match(/Windows Phone/i) 
			){
				if(openYn == 0){
					alert("결과보고서 준비중입니다.");
				}else{
					var theURL = "http://research.seoul.go.kr/MobileResult.do?pollIdx=" + pollIdx;
					window.open(theURL, "Example","scrollbars=no, menubar=false" );
				}
			}else {
				if(openYn == 0){
					alert("결과보고서 준비중입니다.");
				}else{
					var theURL = "http://research.seoul.go.kr/admincenter/applMng/admin_appl.jsp?pollIdx=" + pollIdx + "&user=lett";
					window.open(theURL, "Example", "scrollbars=no, width=900, height=700,resizable=yes, menubar=false");
				}	
			}
}
-->
</script>

</head>
<body>
<form name="frm" method="post" action="/listSurveyResult.do">
<input type="hidden" name="pollIdx" />
<input type="hidden" name="pageIndex" value="3"/>
<div id="wrap">
    <!-- header -->
    

 




 









  
 
<script type="text/javascript" src="/include/jquery-latest.min.js"></script> 
<script type="text/javascript" src="/include/jquery-ui.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$('.depth1Link').mouseenter(function(){
	    $('#header').height(222);
	    $(this).addClass('depth1_on');
	    $('.gnb').addClass('gnb_on');         
	});
	$('.depth1Link').mouseleave(function(){
	    $(this).removeClass('depth1_on');
	});
	$('#header').mouseleave(function(){	
	    $('#header').height(80);
	    $('.gnb').removeClass('gnb_on');
	});
    
}); 
</script>

<script type="text/javascript">
<!--
//로그인후 returnPage
function joinSeoulTOP(){
	var currentPage = document.location.href; 

	// 로그아웃이 아닌경우 
	var yChk = currentPage.indexOf("?");
	if(yChk == -1){
		//세션정보 생성 순서때문에 설문참여하기는 index로 이동시킨 후 설문참여하도록 한다. 
		if(currentPage == "http://research.seoul.go.kr/listSurveyWeb.do"){
			document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&amp;refresh_url=http://research.seoul.go.kr/";
		}else{
			document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&amp;refresh_url="+currentPage;
		}
	}else{
	// 로그아웃인경우 : ~~index.jsp?logout=Y
		var cLengh = currentPage.length;
		currentPage = currentPage.substring(0, yChk);
		document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&refresh_url="+currentPage;
	}
}
-->
</script>
<div id="header">
	<div class="inner_header">
		<h1 class="logo"><a href="/main.do"><img src="/img/poll/img_logo.gif" alt="서울시 여론조사 로고" /></a></h1>
		<div class="lnb">
			<a href="http://www.seoul.go.kr/seoul/servlet/seoul?a=UserLogOutApp&amp;div=mem&amp;SITE_GB=GB011&amp;sNextVal=http://research.seoul.go.kr/main.do?logout=Y"><img src="/img/poll/link_logout.gif" alt="로그아웃" /></a><span> | </span>
			<a href="http://www.seoul.go.kr/" target="_blank"><img src="/img/poll/btn_linkSeoul.gif" alt="서울시" /></a><span> | </span>
			<a href="/poll/sitemap.jsp"><img src="/img/poll/btn_sitemap.gif" alt="사이트맵" /></a>
		</div>
		<!-- gnb -->
		<div class="gnb">
			<ul>
				<li class="depth1">
					<a href="/poll/panel/p010101.jsp" class="depth1Link"><img src="/img/poll/gnb_introPanel.gif" alt="패널안내" /></a>
					<ul class="wrap_depth2">
						<li><a href="/poll/panel/p010101.jsp">패널가입안내</a></li>
						<!-- li><a href="/poll/panel/p010201.jsp">패널현황</a></li -->
		            </ul>
				</li>
				<li class="depth1">
					<a href="/listSurveyWeb.do" class="depth1Link"><img src="/img/poll/gnb_joinSurvey.gif" alt="조사참여" /></a>
					<ul class="wrap_depth2">
						<li><a href="/listSurveyWeb.do">진행중인 여론조사</a></li>
						<li><a href="/poll/survey/p020201.jsp">시정 여론조사 소개</a></li>
					</ul>
				</li>
					<li class="depth1">
						<a href="/listSurveyResult.do" class="depth1Link"><img src="/img/poll/gnb_surveyResult.gif" alt="조사결과" /></a>
						<ul class="wrap_depth2">
							<li><a href="/listSurveyResult.do">여론조사 결과</a></li>
						</ul>
				</li>
				<li class="depth1 gnb_mypage">
					<a href="/poll/mypage/p040101.jsp" class="depth1Link"><img src="/img/poll/gnb_myPage.gif" alt="마이페이지" /></a>
					<ul class="wrap_depth2">
						<li><a href="/poll/mypage/p040101.jsp">패널가입</a></li>
						<li><a href="/poll/mypage/p040201.jsp">마일리지 조회</a></li>
						<li><a href="/listMyPageJoin.do">내가 참여한 조사</a></li>
						<li><a href="/poll/mypage/p040401.jsp">패널정보수정</a></li>
						<!-- li><a href="/poll/mypage/p040501.jsp">패널탈퇴</a></li-->
					</ul>
				</li>
				<li class="depth1 gnb_board">
					<a href="/poll/board/p050101.jsp?bid=" class="depth1Link"><img src="/img/poll/gnb_board.gif" alt="게시판" /></a>
					<ul class="wrap_depth2">
						<li><a href="/poll/board/p050101.jsp?bid=">공지사항</a></li>
						<li><a href="/poll/board/p050201.jsp">자주하는 질문</a></li>
						<li><a href="/poll/board/p050301.jsp">질문과 답변</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- //gnb -->
    </div>
</div>

    <!-- //header -->

    <!-- container -->
    <div id="container" class="surveyResult">
        <!-- snb -->
        

<!-- S: MainMenu 1 -->
	
<!-- E: MainLv 1 -->

<!-- S: MainMenu 2 -->
	
<!-- E: MainLv 1 -->

<!-- S: MainMenu 3 -->
	
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_surveyResult.gif" alt="조사결과" /></strong>
			<ul>
				<li><a href="/listSurveyResult.do"><img src="/img/poll/snb_surveyResult_on.gif" alt="조사결과" /></a></li>
			</ul>
		</div>
	
<!-- E: MainLv 1 -->


<!-- S: MainMenu 4 -->
	
<!-- E: MainLv 4 -->

<!-- S: MainMenu 5 -->
	
	
	
<!-- E: MainLv 5 -->

        <!-- //snb -->
        <!-- content -->
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="/img/poll/tit_surveyResult.gif" alt="여론조사 결과" /></h2>
                 <p><img src="/img/poll/sub_surveyResult.gif" alt="여론조사결과 안내문구 " /></p> 
            </div>
            <div class="article">
                <div class="searchBox">
                    <span class="dateSet">
                        <label for="dateSet">조사기간</label>
                       <input type="text" name="searchRegDatestart" id="searchRegDatestart" value="2011-11-16" />~
                        <input type="text" name="searchRegDateend" id="searchRegDateend" value="2014-11-16"  />
                    </span>
                    <span>
                        <select id="searchPollFieldName" name="searchPollFieldName" style="width:100px" title="분야">
							<option value="">분야 전체</option>
                            
							<option value="BB001" >복지건강</option>
							
							<option value="BB002" >소방/안전</option>
							
							<option value="BB003" >환경</option>
							
							<option value="BB004" >물관리/상수도</option>
							
							<option value="BB005" >경제/일자리</option>
							
							<option value="BB006" >교통</option>
							
							<option value="BB007" >도시계획</option>
							
							<option value="BB008" >문화/관광</option>
							
							<option value="BB009" >주택/건축</option>
							
							<option value="BB010" >공원</option>
							
							<option value="BB011" >홍보</option>
							
							<option value="BB012" >체육</option>
							
							<option value="BB013" >부동산</option>
							
							<option value="BB014" >여성/보육</option>
							
							<option value="BB015" >아동/청소년</option>
							
							<option value="BB016" >시정일반</option>
							
							<option value="BB999" >기타</option>
							
                        </select>                        
	                    <select id="searchCondition" name="searchCondition" style="width:80px" title="검색항목" >
	                        <option value="">부서/제목</option>
	                        <option value="1" >부서</option>
	                        <option value="2" >제목</option>
	                    </select>
                        <input type="text" style="width:140px" id="searchPollTitle" name="searchPollTitle" value="" title="검색" maxlength="20" />
                        <a href="javascript:fncGetSurveyResultList('1');"><img src="/img/poll/btn_search.gif" alt="검색" /></a>
                    </span>
                </div>
                <div class="tbl_listNum">
                    <span>총&nbsp;<em>123</em>&nbsp;건</span>
                    <select name="searchCount" title="목록 출력수">
                        <option value="15" >출력수</option>
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
                        <col width="7%" />
                        <col width="13%" />
                        <col width="7%" />
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">구분</th>
                        <th scope="col">부서</th>
                        <th scope="col">조사제목</th>
                        <th scope="col">조사<br />방법</th>
                        <th scope="col">기간</th>
                        <th scope="col" class="noLine">참여<br />인원</th>
                    </tr>
                    </thead>
                    <tbody>
                    
                    <tr>
                        <td>11</td>
                        <td>환경</td>
                        <td>기후환경본부 <br /> 기후대기과 </td>
                        
                        <td class="txtLeft"><a href="javascript:checkAgent('431', '1');">2015 ICLEI 세계총회 관련 온라인 여론조사</a></td>   
                        <td><font style="color:red">온라인</font></td>
                        
                        <td>2014-08-20~<br />2014-08-24</td>
                        <td class="noLine">2,356</td>
                    </tr>
                    <tr>
                        <td>12</td>
                        <td>시정일반</td>
                        <td>교육협력국 <br /> 평생교육과 </td>
                        
                        <td class="txtLeft"><a href="javascript:checkAgent('430', '1');">부모교육 관련 온라인 조사</a></td>   
                        <td><font style="color:red">온라인</font></td>
                        
                        <td>2014-08-18~<br />2014-08-21</td>
                        <td class="noLine">2,642</td>
                    </tr>
                    <tr>
                        <td>13</td>
                        <td>환경</td>
                        <td>기후환경본부 <br /> 기후대기과 </td>
                        
                        <td class="txtLeft"><a href="javascript:checkAgent('429', '1');">대기오염 경보발령시 차량운행 제한에 대한 온라인 여론조사</a></td>   
                        <td><font style="color:red">온라인</font></td>
                        
                        <td>2014-08-07~<br />2014-08-11</td>
                        <td class="noLine">2,471</td>
                    </tr>
                    <tr>
                        <td>14</td>
                        <td>홍보</td>
                        <td>시민소통기획관 <br /> 시민소통담당관 </td>
                        
                        <td class="txtLeft"><a href="javascript:checkAgent('426', '1');">서울시 상징(브랜드) 시민인지도 조사</a></td>   
                        <td><font style="color:red">온라인</font></td>
                        
                        <td>2014-08-06~<br />2014-08-10</td>
                        <td class="noLine">2,165</td>
                    </tr>
                    <tr>
                        <td>15</td>
                        <td>문화/관광</td>
                        <td>서울시립미술관<br /> </td>
                        
                        <td class="txtLeft"><a href="javascript:checkAgent('427', '1');">서울시립미술관 인지도 및 이용에 대한 온라인 패널조사</a></td>   
                        <td><font style="color:red">온라인</font></td>
                        
                        <td>2014-07-30~<br />2014-08-04</td>
                        <td class="noLine">2,060</td>
                    </tr>
	            </tbody>
                </table>
                <div class="paging">
                	<div class="pageNum">
						<table border='0' cellspacing='0' cellpadding='0' style='width: 10%;' align='center'><tr><td width='20'><img diabled src='/img/poll/btn_fstList.gif' width='15' height='13' border='0'></td><td width='28'><a href='javascript:document.frm.pageIndex.value=2;javascript:fncGetSurveyResultList(2);'><img src='/img/poll/btn_prevList.gif' border='0'></a></td><td class='page'>&nbsp;<a href='javascript:document.frm.pageIndex.value=1;javascript:fncGetSurveyResultList(2);'>1</a>&nbsp;&nbsp;<a href='javascript:document.frm.pageIndex.value=2;javascript:fncGetSurveyResultList(2);'>2</a>&nbsp;&nbsp;<font class='page_s'>3</font>&nbsp;&nbsp;<a href='javascript:document.frm.pageIndex.value=4;javascript:fncGetSurveyResultList(2);'>4</a>&nbsp;&nbsp;<a href='javascript:document.frm.pageIndex.value=5;javascript:fncGetSurveyResultList(2);'>5</a>&nbsp;&nbsp;<a href='javascript:document.frm.pageIndex.value=6;javascript:fncGetSurveyResultList(2);'>6</a>&nbsp;&nbsp;<a href='javascript:document.frm.pageIndex.value=7;javascript:fncGetSurveyResultList(2);'>7</a>&nbsp;&nbsp;<a href='javascript:document.frm.pageIndex.value=8;javascript:fncGetSurveyResultList(2);'>8</a>&nbsp;&nbsp;<a href='javascript:document.frm.pageIndex.value=9;javascript:fncGetSurveyResultList(2);'>9</a>&nbsp;&nbsp;<a href='javascript:document.frm.pageIndex.value=10;javascript:fncGetSurveyResultList(2);'>10</a>&nbsp;</td><td width='28'><a href='javascript:document.frm.pageIndex.value=4;javascript:fncGetSurveyResultList(2);'><img src='/img/poll/btn_nextList.gif' border='0'></a></td><td width='20'><a href='javascript:document.frm.pageIndex.value=13;javascript:fncGetSurveyResultList(2);'><img src='/img/poll/btn_lstList.gif' width='15' height='13' border='0'></a></td></tr></table>
					</div>
                </div>
	                    
            </div>
        </div>
        <!-- //content -->
    </div>
    <!-- //container -->

    <!-- footer -->
    
<div id="footer">
	<div class="inner_footer">
		<strong><img src="/img/poll/img_logoSeoul.gif" alt="희망서울 로고" /></strong>
		<p>
			<a href="http://www.seoul.go.kr/v2012/help/honor.html" target="_blank">홈페이지 도우미</a><span> | </span>
			<a href="http://www.seoul.go.kr/v2007/help/privacy.html" target="_blank"><font color=blue>개인정보 처리방침</font></a><span> | </span>
			<a href="http://www.seoul.go.kr/v2007/help/media.html" target="_blank">영상정보처리기기 운영・관리방침</a><span> | </span>
			

			<a href="http://www.seoul.go.kr/v2012/allSite.html" onclick="window.open(this.href,'allSite','width=585,height=600,scrollbars=yes');return false;" target="_blank">전체사이트</a>
		</p>
		<select name="sLink" id="sLink" onchange="sChange();">
			<option value="">:::관련사이트:::</option>
			<option value="1">꼼꼼한 서울씨</option>
			<option value="2">내 손안에 서울</option>
			<option value="3">라이브 서울</option><br>
			
		</select> <br>
		<p> <address>서울특별시청 : 100-744 서울특별시 중구 세종대로 110,   뉴미디어담당관  02 - 2133 - 6513  <a href="http://120dasan.seoul.go.kr/" target="_blank"> <a href="http://120dasan.seoul.go.kr/" target="_blank"><img src="/img/poll/img_logoDasan.gif" alt="120 다산콜센터 로고" /></a></address></p>
	</div>
</div>

<script type="text/javascript">
function sChange(){
	var obj = document.getElementById("sLink");
	if(obj.value == 1){
		var url = "http://www2.seoul.go.kr/monitor/";		
		window.open(url,"꼼꼼한 서울씨");
		
	}else if(obj.value == 2){
		var url = "http://mediahub.seoul.go.kr";		
		window.open(url, "내 손안에 서울");
		
	}else if(obj.value == 3){
		var url = "http://tv.seoul.go.kr";		
		window.open(url, "라이브서울");
	}else {
		var url = "http://wow.seoul.go.kr";		
		window.open(url, "WOW서울");
	}
}
</script>
    <!-- //footer -->
</div>
</form>
<form name="popfrm" method="post" action="/popSurveyResult.do">
<input type="hidden" name="searchPollIdx" id="searchPollIdx" />
<input type="hidden" name="userId" id="userId" />
<input type="hidden" name="userSubject" id="userSubject" />
</form>
<script language="JavaScript">
document.frm.searchPollTitle.focus();
</script>
</body>
</html>
