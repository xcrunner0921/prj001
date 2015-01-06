<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.net.URL" %>
<%
	int gMenuIndex		= 5;
	int gSubMenuIndex	= 1;
	
	//공지사항
	String boardid = request.getParameter("bid");
	String isrc = "http://spp.seoul.go.kr/silguk/research/research_announce.jsp";
	if (!boardid.equals("")){
		isrc += "?search_boardId="+boardid;
	}
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/content.css"/>

<script type="text/javascript">
//로그인후 returnPage
function joinSeoul(){
	var currentPage = document.location.href; 
	document.location.href="http://www.seoul.go.kr/seoul/jsp/member/UserLogin.jsp?SITE_GB=GB011&refresh_url=" + currentPage;
}
</script>

</head>
<body>
<div id="wrap">
    <!-- header -->
    <%@ include file="/include/top.jsp" %>
    <!-- //header -->

    <!-- container -->
    <div id="container" class="notice">
        <!-- snb -->
        <%@ include file="/include/panel/left.jsp" %>
        <!-- //snb -->
        <!-- content -->
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="/img/poll/tit_notice.gif" alt="공지사항" /></h2>
            </div>
            <div class="article">
				<iframe title="공지사항" width="710" height="440" src="<%=isrc%>" frameborder="0"></iframe>
            </div>
        </div>
        <!-- //content -->
    </div>
    <!-- //container -->

    <!-- footer -->
    <%@ include file="/include/footer.jsp" %>
    <!-- //footer -->
</div>
</body>
</html>