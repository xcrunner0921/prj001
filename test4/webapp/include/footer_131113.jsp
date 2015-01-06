<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="footer">
	<div class="inner_footer">
		<strong><img src="/img/poll/img_logoSeoul.gif" alt="희망서울 로고" /></strong>
		<p>
			<a href="http://www.seoul.go.kr/v2012/help/honor.html" target="_blank">홈페이지 도우미</a><span> | </span>
			<a href="http://www.seoul.go.kr/v2007/help/privacy.html" target="_blank">개인정보 처리방침</a><span> | </span>
			<a href="http://www.seoul.go.kr/v2007/help/media.html" target="_blank">영상정보처리기기 운영・관리방침</a><span> | </span>
			<a href="http://www.seoul.go.kr/v2010/app/index.html?app=1" onclick="window.open(this.href,'app','width=857,height=750,scrollbars=yes,resizable=yes');return false;" target="_blank">서울 APP안내</a><span> | </span>
			<a href="http://php-app1.seoul.go.kr/errorReport/v2007/write.php?Sid=401_00&amp;url=http://www.seoul.go.kr/" onclick="window.open(this.href,'','width=700,height=730,scrollbars=no');return false;" target="_blank">홈페이지 개선의견</a><span> | </span>
			<a href="http://www.seoul.go.kr/v2012/allSite.html" onclick="window.open(this.href,'allSite','width=585,height=600,scrollbars=yes');return false;" target="_blank">전체사이트</a>
		</p>
		<select name="sLink" id="sLink" onchange="sChange();">
			<option value="">:::관련사이트:::</option>
			<option value="1">서울시정모니터</option>
			<option value="2">서울톡톡(온라인 뉴스)</option>
			<option value="3">라이브 서울</option>
			<option value="4">WOW서울</option>
		</select>
		<address>서울특별시청 : 100-744 서울특별시 중구 세종대로 110 <a href="http://120dasan.seoul.go.kr/" target="_blank"><img src="/img/poll/img_logoDasan.gif" alt="120 다산콜센터 로고" /></a></address>
	</div>
</div>

<script type="text/javascript">
function sChange(){
	var obj = document.getElementById("sLink");
	if(obj.value == 1){
		var url = "http://www2.seoul.go.kr/monitor/";		
		window.open(url,"서울시정모니터");
		
	}else if(obj.value == 2){
		var url = "http://inews.seoul.go.kr";		
		window.open(url, "서울톡톡_온라인뉴스");
		
	}else if(obj.value == 3){
		var url = "http://tv.seoul.go.kr";		
		window.open(url, "라이브서울");
	}else {
		var url = "http://wow.seoul.go.kr";		
		window.open(url, "WOW서울");
	}
}
</script>