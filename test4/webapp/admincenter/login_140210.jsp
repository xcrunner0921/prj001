<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>서울시 여론조사 시스템 관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />

<!--<link rel="stylesheet" href="<c:url value='/sample/css/admin.css'/>" type="text/css">-->
<script language="javascript" src="<c:url value='/sample/javascript/CommonScript.js'/>"></script>
<script language="JavaScript">
<!--
function fncLogin() {
	if (FormValidation(document.loginForm) != false) {
	    document.loginForm.action = "<c:url value='/login.do' />";
	    document.loginForm.submit();
    }
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000" >
<form name="loginForm" method="post">

<!--<div align="center">-->
<!--<TABLE WIDTH="100%" HEIGHT="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">-->
<!--<TR>-->
<!--<TD ALIGN="CENTER" VALIGN="MIDDLE">-->

	<div id="login">
		<h1><img src="/img/admincenter/logo.gif" alt="서울시 여론조사"/></h1>
		<div class="inner">
			<p>
				<label for="">아이디</label>
				<input type="text" name="userId" id="" />
				<br/>
				<label for="">비밀번호</label>
				<input type="password" name="password" id="" onKeyPress="if (event.keyCode == 13) fncLogin();" />
			</p>
		</div>
		<a href="javascript:fncLogin();" class="btnLogin"><img src="/img/admincenter/btn_login.gif" alt="Login"/></a>
	</div>
	<!-- // #login -->

<!--</TD>-->
<!--</TR>-->
<!--</TABLE>-->
<!--</div>-->

<!-- end of title -->
<input type="hidden" name="flag">
</form>
<script language="JavaScript">
document.loginForm.userId.focus();
</script>
</body>
</html>
