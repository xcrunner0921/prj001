<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>eMarketPlace</title>
<script language="JavaScript">
function fncLogOut() {
	if(confirm("Do you want to log out?")) {
		document.logoutForm.target="_top";
	    document.logoutForm.action="<c:url value='/logout.do'/>";
	    document.logoutForm.submit();	
    }
}
</script>

<link href="<c:url value='/sample/css/left.css'/>" rel="stylesheet"
	type="text/css">

</head>

<body topmargin="0" leftmargin="0">
<form name="logoutForm"><input type="hidden" name="loflag"
	value="LO"></form>
<table width="100%" height="50" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="800" height="30" bgcolor="#8A8BE3"><font size="4"
			face="arial" color="#FFFFFF">
		<center>Anyframe Template</center>
		</font></td>
		<td height="30" bgcolor="#8A8BE3">&nbsp;</td>
	</tr>
	<tr>
		<td height="20" align="right"
			background="<c:url value='/sample/images/img_bg.gif'/>">
		<table width="200" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="115"></td>
				<td width="14">&nbsp;</td>
				<td width="56"><a href="javascript:fncLogOut();"> <img
					src="<c:url value='/sample/images/btn_logout.gif'/>" width="56"
					height="16"> </a></td>
			</tr>
		</table>
		</td>
		<td height="20"
			background="<c:url value='/sample/images/img_bg.gif'/>">&nbsp;</td>
	</tr>
</table>


</body>
</html>
