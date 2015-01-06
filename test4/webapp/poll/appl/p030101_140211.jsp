<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri='/WEB-INF/anyframe-page.tld' prefix='anyframe' %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.*" %>
<%@ page import="anyframe.common.Page" %>
<%@ page import="com.sds.emp.common.EmpUtil" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.poll.service.SurveyResultSearchVO" %>
<%@ page import="seoul.research.domain.SurveyResult" %>

<%
	int gMenuIndex		= 3;
	int gSubMenuIndex	= 1;
%>
<%
	SurveyResultSearchVO surveyResultSearchVO = (SurveyResultSearchVO)request.getAttribute("SurveyResultSearchVO");
	
	String regDatestart = surveyResultSearchVO.getSearchRegDatestart(); 	//시작일
	String regDateend = surveyResultSearchVO.getSearchRegDateend();		//종료일
	String condition = EmpUtil.null2str(surveyResultSearchVO.getSearchCondition()); // 조건(부서/제목)
	String searchPollFieldName = EmpUtil.null2str(surveyResultSearchVO.getSearchPollFieldName());	//조사분야
	String searchPollTitle = EmpUtil.null2str(surveyResultSearchVO.getSearchPollTitle());			//검색어

	Page resultPage = (Page)request.getAttribute("resultPage");
	ArrayList resultList = (ArrayList)resultPage.getList();	
	int pagesize = resultPage.getPagesize();
	
	ArrayList fieldCodeList = (ArrayList)request.getAttribute("fieldCodeList");
	NumberFormat nf = NumberFormat.getInstance(); // 
	nf.setMaximumIntegerDigits(5); //
%>

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
	
   	document.frm.action="<c:url value='/listSurveyResult.do'/>";
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
					window.open(theURL, "Example", "scrollbars=no, width=900, height=700, resizable=yes, menubar=false");
				}	
			}
}
-->
</script>

</head>
<body>
<form name="frm" method="post" action="<c:url value='/listSurveyResult.do'/>">
<input type="hidden" name="pollIdx" />
<input type="hidden" name="pageIndex" value="<%=resultPage.getCurrentPage()%>"/>
<div id="wrap">
    <!-- header -->
    <%@ include file="/include/top.jsp" %>
    <!-- //header -->

    <!-- container -->
    <div id="container" class="surveyResult">
        <!-- snb -->
        <%@ include file="/include/panel/left.jsp" %>
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
                       <input type="text" name="searchRegDatestart" id="searchRegDatestart" value="<%= regDatestart %>" />~
                        <input type="text" name="searchRegDateend" id="searchRegDateend" value="<%= regDateend %>"  />
                    </span>
                    <span>
                        <select id="searchPollFieldName" name="searchPollFieldName" style="width:100px" title="분야">
							<option value="">-구분선택-</option>
                            <%
								for (int i = 0; i < fieldCodeList.size(); i++) {
									Code fieldCode = (Code)fieldCodeList.get(i);
							%>
							<option value="<%= fieldCode.getCodeId() %>" <%= (fieldCode.getCodeId().equals(searchPollFieldName) ? "selected" : "")%>><%= fieldCode.getCodeName() %></option>
							<%
								}
							%>
                        </select>                        
	                    <select id="searchCondition" name="searchCondition" style="width:80px" title="검색항목" >
	                        <option value="">-검색선택-</option>
	                        <option value="1" <%= ("1".equals(condition) ? "selected" : "")%>>부서</option>
	                        <option value="2" <%= ("2".equals(condition) ? "selected" : "")%>>제목</option>
	                    </select>
                        <input type="text" style="width:140px" id="searchPollTitle" name="searchPollTitle" value="<%=searchPollTitle %>" title="검색" maxlength="20" />
                        <a href="javascript:fncGetSurveyResultList('1');"><img src="/img/poll/btn_search.gif" alt="검색" /></a>
                    </span>
                </div>
                <div class="tbl_listNum">
                    <span>총&nbsp;<em><%= resultPage.getTotalCount() %></em>&nbsp;건</span>
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
                    <%
						int currentPageIndex = resultPage.getCurrentPage();
						int no = (currentPageIndex - 1) * pagesize;
						
						if (resultList.size() > 0) {
							for (int i = 0; i < resultList.size(); i++) {
								SurveyResult surveyResult = (SurveyResult)resultList.get(i);
						%>
                    <tr>
                        <td><%= ++no %></td>
                        <td><%= surveyResult.getPollFieldName() %></td>
                        <td><%= surveyResult.getPollAskName().replaceAll("\\|", "<br />")  %></td>
                    <%if ("LL101".equals(surveyResult.getPollGubun())){ %>    
                        <td class="txtLeft"><a href="javascript:checkAgent('<%= surveyResult.getPollIdx() %>', '<%= surveyResult.getPollHit() %>');"><%= surveyResult.getPollTitle() %></a></td>   
                        <td><font style="color:red">온라인</font></td>
                    <%}else{ %>
                    	<td class="txtLeft"><a href="javascript:openResult('<%= surveyResult.getPollIdx() %>', '<%= surveyResult.getPollHit() %>');"><%= surveyResult.getPollTitle() %></a></td>
                    	<td>기타</td>
                    <%} %>    
                        <td><%= surveyResult.getPollTermstart() %>~<br /><%= surveyResult.getPollTermend() %></td>
                        <td class="noLine"><%= nf.format(surveyResult.getPollSample()) %></td>
                    </tr><% } %>
	            </tbody>
                </table>
                <div class="paging">
                	<div class="pageNum">
						<anyframe:pagenavigator linkUrl="javascript:fncGetSurveyResultList(2);"
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
<form name="popfrm" method="post" action="<c:url value='/popSurveyResult.do'/>">
<input type="hidden" name="searchPollIdx" id="searchPollIdx" />
<input type="hidden" name="userId" id="userId" />
<input type="hidden" name="userSubject" id="userSubject" />
</form>
<script language="JavaScript">
document.frm.searchPollTitle.focus();
</script>
</body>
</html>
