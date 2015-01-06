<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- S: MainMenu 1 -->
	<% if(gMenuIndex == 1 && gSubMenuIndex == 1){ %>
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_introPanel.gif" alt="패널안내" /></strong>
			<ul>
				<li><a href="/poll/panel/p010101.jsp"><img src="/img/poll/snb_introJoinPanel_on.gif" alt="패널가입안내" /></a></li>
				<!-- li><a href="/poll/panel/p010201.jsp"><img src="/img/poll/snb_panelState_off.gif" alt="패널현황" /></a></li -->
			</ul>
		</div>
	<% }else if(gMenuIndex == 1 && gSubMenuIndex == 2){ %>
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_introPanel.gif" alt="패널안내" /></strong>
			<ul>
				<li><a href="/poll/panel/p010101.jsp"><img src="/img/poll/snb_introJoinPanel_off.gif" alt="패널가입안내" /></a></li>
				<!-- li><a href="/poll/panel/p010201.jsp"><img src="/img/poll/snb_panelState_on.gif" alt="패널현황" /></a></li -->
			</ul>
		</div>
	<% } %>
<!-- E: MainLv 1 -->

<!-- S: MainMenu 2 -->
	<% if(gMenuIndex == 2 && gSubMenuIndex == 1){ %>
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_joinSurvey.gif" alt="조사참여" /></strong>
			<ul>
				<li><a href="/listSurveyWeb.do"><img src="/img/poll/snb_onSurvey_on.gif" alt="진행중인여론조사" /></a></li>
				<li><a href="/poll/survey/p020201.jsp"><img src="/img/poll/snb_introSurvey_off.gif" alt="시정여론조사소개" /></a></li>
			</ul>
		</div>
	<% }else if(gMenuIndex == 2 && gSubMenuIndex == 2){ %>
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_joinSurvey.gif" alt="조사참여" /></strong>
			<ul>
				<li><a href="/listSurveyWeb.do"><img src="/img/poll/snb_onSurvey_off.gif" alt="진행중인여론조사" /></a></li>
				<li><a href="/poll/survey/p020201.jsp"><img src="/img/poll/snb_introSurvey_on.gif" alt="시정여론조사소개" /></a></li>
			</ul>
		</div>
	<% } %>
<!-- E: MainLv 1 -->

<!-- S: MainMenu 3 -->
	<% if(gMenuIndex == 3 && gSubMenuIndex == 1){ %>
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_surveyResult.gif" alt="조사결과" /></strong>
			<ul>
				<li><a href="/listSurveyResult.do"><img src="/img/poll/snb_surveyResult_on.gif" alt="조사결과" /></a></li>
			</ul>
		</div>
	<% } %>
<!-- E: MainLv 1 -->


<!-- S: MainMenu 4 -->
	<% if(gMenuIndex == 4 && gSubMenuIndex == 1){ %>
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_myPage.gif" alt="마이페이지" /></strong>
			<ul>
				<li><a href="/poll/mypage/p040101.jsp"><img src="/img/poll/snb_introJoinPanel_on.gif" alt="패널가입" /></a></li>
				<li><a href="/poll/mypage/p040201.jsp"><img src="/img/poll/snb_myMileage_off.gif" alt="마일리지조회" /></a></li>
				<li><a href="/listMyPageJoin.do"><img src="/img/poll/snb_mySurvey_off.gif" alt="내가 참여한 조사" /></a></li>
				<li><a href="/poll/mypage/p040401.jsp"><img src="/img/poll/snb_modifyMyInfo_off.gif" alt="패널정보수정" /></a></li>
				<!-- li><a href="/poll/mypage/p040501.jsp"><img src="/img/poll/snb_deleteMyInfo_off.gif" alt="패널탈퇴" /></a></li-->
			</ul>
            <div class="box_mileageGuide">
                <p><img src="/img/poll/txt_mileageBox.gif" alt="마일리지 안내 : 마일리지 적립안내, 마일리지 사용 등 자세한 사항은 서울시 홈페이지 ‘마이서울’에서 확인 가능합니다." /></p>
                <a href="http://mileage.seoul.go.kr/main/NoticeList.do" target="_blank"><img src="/img/poll/btn_more.gif" alt="자세히 보기" /></a>
            </div>
		</div>
	<% }else if(gMenuIndex == 4 && gSubMenuIndex == 2){ %>
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_myPage.gif" alt="마이페이지" /></strong>
			<ul>
				<li><a href="/poll/mypage/p040101.jsp"><img src="/img/poll/snb_introJoinPanel_off.gif" alt="패널가입" /></a></li>
				<li><a href="/poll/mypage/p040201.jsp"><img src="/img/poll/snb_myMileage_on.gif" alt="마일리지조회" /></a></li>
				<li><a href="/listMyPageJoin.do"><img src="/img/poll/snb_mySurvey_off.gif" alt="내가 참여한 조사" /></a></li>
				<li><a href="/poll/mypage/p040401.jsp"><img src="/img/poll/snb_modifyMyInfo_off.gif" alt="패널정보수정" /></a></li>
				<!-- li><a href="/poll/mypage/p040501.jsp"><img src="/img/poll/snb_deleteMyInfo_off.gif" alt="패널탈퇴" /></a></li-->
			</ul>
            <div class="box_mileageGuide">
                <p><img src="/img/poll/txt_mileageBox.gif" alt="마일리지 안내 : 마일리지 적립안내, 마일리지 사용 등 자세한 사항은 서울시 홈페이지 ‘마이서울’에서 확인 가능합니다." /></p>
                <a href="http://mileage.seoul.go.kr/main/NoticeList.do" target="_blank"><img src="/img/poll/btn_more.gif" alt="자세히 보기" /></a>
            </div>
		</div>
	<% }else if(gMenuIndex == 4 && gSubMenuIndex == 3){ %>
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_myPage.gif" alt="마이페이지" /></strong>
			<ul>
				<li><a href="/poll/mypage/p040101.jsp"><img src="/img/poll/snb_introJoinPanel_off.gif" alt="패널가입" /></a></li>
				<li><a href="/poll/mypage/p040201.jsp"><img src="/img/poll/snb_myMileage_off.gif" alt="마일리지조회" /></a></li>
				<li><a href="/listMyPageJoin.do"><img src="/img/poll/snb_mySurvey_on.gif" alt="내가 참여한 조사" /></a></li>
				<li><a href="/poll/mypage/p040401.jsp"><img src="/img/poll/snb_modifyMyInfo_off.gif" alt="패널정보수정" /></a></li>
				<!-- li><a href="/poll/mypage/p040501.jsp"><img src="/img/poll/snb_deleteMyInfo_off.gif" alt="패널탈퇴" /></a></li-->
			</ul>
            <div class="box_mileageGuide">
                <p><img src="/img/poll/txt_mileageBox.gif" alt="마일리지 안내 : 마일리지 적립안내, 마일리지 사용 등 자세한 사항은 서울시 홈페이지 ‘마이서울’에서 확인 가능합니다." /></p>
                <a href="http://mileage.seoul.go.kr/main/NoticeList.do" target="_blank"><img src="/img/poll/btn_more.gif" alt="자세히 보기" /></a>
            </div>
		</div>
	<% }else if(gMenuIndex == 4 && gSubMenuIndex == 4){ %>
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_myPage.gif" alt="마이페이지" /></strong>
			<ul>
				<li><a href="/poll/mypage/p040101.jsp"><img src="/img/poll/snb_introJoinPanel_off.gif" alt="패널가입" /></a></li>
				<li><a href="/poll/mypage/p040201.jsp"><img src="/img/poll/snb_myMileage_off.gif" alt="마일리지조회" /></a></li>
				<li><a href="/listMyPageJoin.do"><img src="/img/poll/snb_mySurvey_off.gif" alt="내가 참여한 조사" /></a></li>
				<li><a href="/poll/mypage/p040401.jsp"><img src="/img/poll/snb_modifyMyInfo_on.gif" alt="패널정보수정" /></a></li>
				<!-- li><a href="/poll/mypage/p040501.jsp"><img src="/img/poll/snb_deleteMyInfo_off.gif" alt="패널탈퇴" /></a></li-->
			</ul>
            <div class="box_mileageGuide">
                <p><img src="/img/poll/txt_mileageBox.gif" alt="마일리지 안내 : 마일리지 적립안내, 마일리지 사용 등 자세한 사항은 서울시 홈페이지 ‘마이서울’에서 확인 가능합니다." /></p>
                <a href="http://mileage.seoul.go.kr/main/NoticeList.do" target="_blank"><img src="/img/poll/btn_more.gif" alt="자세히 보기" /></a>
            </div>
		</div>
	<% }else if(gMenuIndex == 4 && gSubMenuIndex == 5){ %>
		<div id="snb" class="snb">
			<strong><img src="/img/poll/snb_myPage.gif" alt="마이페이지" /></strong>
			<ul>
				<li><a href="/poll/mypage/p040101.jsp"><img src="/img/poll/snb_introJoinPanel_off.gif" alt="패널가입" /></a></li>
				<li><a href="/poll/mypage/p040201.jsp"><img src="/img/poll/snb_myMileage_off.gif" alt="마일리지조회" /></a></li>
				<li><a href="/listMyPageJoin.do"><img src="/img/poll/snb_mySurvey_off.gif" alt="내가 참여한 조사" /></a></li>
				<li><a href="/poll/mypage/p040401.jsp"><img src="/img/poll/snb_modifyMyInfo_off.gif" alt="패널정보수정" /></a></li>
				<!-- li><a href="/poll/mypage/p040501.jsp"><img src="/img/poll/snb_deleteMyInfo_off.gif" alt="패널탈퇴" /></a></li-->
			</ul>
            <div class="box_mileageGuide">
                <p><img src="/img/poll/txt_mileageBox.gif" alt="마일리지 안내 : 마일리지 적립안내, 마일리지 사용 등 자세한 사항은 서울시 홈페이지 ‘마이서울’에서 확인 가능합니다." /></p>
                <a href="http://mileage.seoul.go.kr/main/NoticeList.do" target="_blank"><img src="/img/poll/btn_more.gif" alt="자세히 보기" /></a>
            </div>
		</div>
	<% } %>
<!-- E: MainLv 4 -->

<!-- S: MainMenu 5 -->
	<% if(gMenuIndex == 5 && gSubMenuIndex == 1){ %>
		<div id="snb" class="snb">
            <strong><img src="/img/poll/snb_board.gif" alt="게시판" /></strong>
            <ul>
                <li><a href="/poll/board/p050101.jsp?bid="><img src="/img/poll/snb_notice_on.gif" alt="공지사항" /></a></li>
                <li><a href="/poll/board/p050201.jsp"><img src="/img/poll/snb_faq_off.gif" alt="자주하는 질문" /></a></li>
                <li><a href="/poll/board/p050301.jsp"><img src="/img/poll/snb_qna_off.gif" alt="질문과 답" /></a></li>
            </ul>
        </div>
	<% } %>
	<% if(gMenuIndex == 5 && gSubMenuIndex == 2){ %>
		<div id="snb" class="snb">
            <strong><img src="/img/poll/snb_board.gif" alt="게시판" /></strong>
            <ul>
                <li><a href="/poll/board/p050101.jsp?bid="><img src="/img/poll/snb_notice_off.gif" alt="공지사항" /></a></li>
                <li><a href="/poll/board/p050201.jsp"><img src="/img/poll/snb_faq_on.gif" alt="자주하는 질문" /></a></li>
                <li><a href="/poll/board/p050301.jsp"><img src="/img/poll/snb_qna_off.gif" alt="질문과 답" /></a></li>
            </ul>
        </div>
	<% } %>
	<% if(gMenuIndex == 5 && gSubMenuIndex == 3){ %>
		<div id="snb" class="snb">
            <strong><img src="/img/poll/snb_board.gif" alt="게시판" /></strong>
            <ul>
                <li><a href="/poll/board/p050101.jsp?bid="><img src="/img/poll/snb_notice_off.gif" alt="공지사항" /></a></li>
                <li><a href="/poll/board/p050201.jsp"><img src="/img/poll/snb_faq_off.gif" alt="자주하는 질문" /></a></li>
                <li><a href="/poll/board/p050301.jsp"><img src="/img/poll/snb_qna_on.gif" alt="질문과 답" /></a></li>
            </ul>
        </div>
	<% } %>
<!-- E: MainLv 5 -->
