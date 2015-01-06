<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="seoul.research.poll.service.MyPageJoinSearchVO" %>
<%@ page import="seoul.research.domain.MyPageJoin" %>
<%@ page import="com.sds.emp.common.*" %>
<%@ page import="java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int gMenuIndex		= 4;
	int gSubMenuIndex	= 3; 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/content.css"/>

<%
	MyPageJoinSearchVO myPageJoinSearchVO = (MyPageJoinSearchVO)request.getAttribute("myPageJoinSearchVO");
	String searchGubun = EmpUtil.null2str(myPageJoinSearchVO.getPollIdx());
	String searchName = EmpUtil.null2str(myPageJoinSearchVO.getSearchName());
	String rSearchCount = EmpUtil.null2str(myPageJoinSearchVO.getSearchCount());
	String condition = EmpUtil.null2str(myPageJoinSearchVO.getSearchCondition());
	String keyword = EmpUtil.null2str(myPageJoinSearchVO.getSearchKeyword());
	
	Page resultPage = (Page)request.getAttribute("resultPage");
	
	ArrayList resultList = (ArrayList)resultPage.getList();	
	int pagesize = resultPage.getPagesize();
	
	//String SeoulTest = "SeoulTest";
	//session.setAttribute("userId", SeoulTest);
	
	NumberFormat nf = NumberFormat.getInstance(); // 숫자에 콤마 표시
	nf.setMaximumIntegerDigits(5); //최대 5자리까지
	
//	ArrayList askCodeList = (ArrayList)request.getAttribute("askCodeList");	
%>
<script type="text/javascript">
<!--
//로그인후 returnPage
function joinSeoul(){
	var currentPage = document.location.href; 
	document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&amp;refresh_url=" + currentPage;
}

function loginChk(){
	var currentPage = document.location.href; 
	var uSession = "<%=session.getAttribute("userId") %>";
	if(uSession == null || uSession =="" || uSession == "null"){
		alert("서울시 통합로그인후 참여가 가능합니다.");
//		document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&refresh_url=" + currentPage;
		return;
	}
}

function fncGetMyPageJoinList(arg) {
	
	if(arg == "1"){
		document.frm.pageIndex.value = 1;
	}
	
   	document.frm.action="<c:url value='/listMyPageJoin.do'/>";
   	document.frm.submit();		
}

function resultPopup(pollIdx) {
	//alert('결과보기 준비중입니다.');
	//var theURL = "http://research.seoul.go.kr/oz60/ozrviewer/admin_answer.jsp?pollIdx=" + pollIdx;
	//window.open(theURL, "Example", "scrollbars=no, width=900, height=700, menubar=false");
}
-->

</script>

</head>
<body>
<form name="frm" id="frm" action="">
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
                 <h2><img src="/img/poll/tit_mySurvey.gif" alt="내가 참여한 조사" /></h2>
            </div>
            <div class="article">
<% if (userid.equals("")) { %>
				<p>
                    <em class="emph">서울시 온라인 여론조사 패널의 정보는 서울시 홈페이지 회원 정보와 통합 관리</em>됩니다.<br />
                    정보를 확인 하시려면 먼저 서울시 통합회원 로그인 해주시기 바랍니다.<br />아래 로그인 버튼을 눌러주세요. 
                </p>
                <br /><br />
				<p><a href="javascript:joinSeoul();" class="btn_modifyMyInfo"><img src="/img/poll/btn_mypageJoinSeoul.gif" alt="통합회원 로그인" /></a></p>
                
 <%}else{ %>             
                <div class="searchBox">
                    <select name="searchCondition" title="검색항목" value="">
                        <option value="0">선택</option>
                        <option value="1" <%= ("1".equals(condition) ? "selected" : "")%>>부서</option>
                        <option value="2" <%= ("2".equals(condition) ? "selected" : "")%>>제목</option>
                    </select>
                    <input type="text" name="searchKeyword" value="<%= keyword %>" title="검색어" />
                    <a href="javascript:fncGetMyPageJoinList('1');"><img src="/img/poll/btn_search.gif" alt="검색" /></a>
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
                        <col width="16%" />
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
								MyPageJoin myPageJoin = (MyPageJoin)resultList.get(i);
								String filterYn = myPageJoin.getFilterYn(); //필터여부
								String progYn = myPageJoin.getProgYn();		//완료여부
								String stopYn = myPageJoin.getPollStopyn(); //일시중지여부
								String openYn = myPageJoin.getPollOpen(); //홈페이지공개여부
								System.out.println("===>>  filterYn  : " + filterYn + "     ==>> progYn : " + progYn + "     ==>> openYn : " +openYn);
						%>
                    <tr>
                        <td><%=++no%></td>
                        <td><%= myPageJoin.getGubunName()%></td>
                        <td><%= myPageJoin.getCodeName().replaceAll("\\|", "<br />").replaceAll(" ", "") %></td>
                        
                        <!-- 홈페이지 공개여부가 'N' :  결과보기를 제한 한다. -->
                        <%if("N".equals(openYn)) {%>
                        
	                        <%if("Y".equals(filterYn) && "Y".equals(progYn)) {%>
	                        <td class="txtLeft"><%= myPageJoin.getPollTitle()%></td>
	                        
	                        <%}else if("N".equals(filterYn) && "Y".equals(progYn)) {%>
	                        <td class="txtLeft"><%= myPageJoin.getPollTitle()%>&nbsp;<font style="color:orange;">(참여대상에서 제외되었습니다.)</font></td>
	                        <%} %>
	                    
	                     <!--   -->
                        <%}else{ %>
                        
	                        <%if("Y".equals(filterYn) && "Y".equals(progYn)) {%>
	                        <td class="txtLeft"><%= myPageJoin.getPollTitle()%></td>
	                        
	                        <%}else if("N".equals(filterYn) && "Y".equals(progYn)) {%>
	                        <td class="txtLeft"><%= myPageJoin.getPollTitle()%>&nbsp;<font style="color:orange;">(참여대상에서 제외되었습니다.)</font></td>
	                        <%} %>
                        
                        
                        <%} %>
                        <td><%= myPageJoin.getPollTermstartYmd()%>&nbsp;<%= myPageJoin.getPollTermstartHour()%>:<%= myPageJoin.getPollTermstartMinute()%>&nbsp;~<br /><%= myPageJoin.getPollTermendYmd()%>&nbsp;<%= myPageJoin.getPollTermendHour()%>:<%= myPageJoin.getPollTermendMinute()%></td>
                        <td><%= myPageJoin.getPollTarget()%></td>
                        <td class="noLine"><%= nf.format(myPageJoin.getPollSample()) %></td>
                    </tr>
	                    <% } %>
	            </tbody>
                </table>
                <div class="paging">
                	<div class="pageNum">
						<anyframe:pagenavigator linkUrl="javascript:fncGetMyPageJoinList(2);" 
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
                        <td align="center" colspan="7">조회 결과가 없습니다.</td>
                    </tr>
	            </tbody>
                </table>
	                    <% } %>
                    
            </div>
<%} %>            
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