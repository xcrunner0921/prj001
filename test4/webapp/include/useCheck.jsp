<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="
    	java.lang.*,
    	java.io.*,  
    	java.util.*,
    	java.sql.*,
    	java.net.*,
    	java.text.*, 
		java.util.Date,
		java.util.zip.*,
		javax.imageio.ImageIO,
		java.awt.image.BufferedImage,
		java.awt.Image,
		javax.swing.ImageIcon,
    	javax.servlet.http.*,
		org.apache.commons.lang.StringUtils,
		org.apache.commons.lang.StringEscapeUtils,
		java.io.BufferedReader, 
		java.io.InputStreamReader, 
		java.net.URL"
%>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="seoul.research.poll.common.LoginCheck" %>
<%@ page import="seoul.research.domain.Panel" %>
<%@ page import="seoul.research.poll.service.PanelService" %>
<%@ page import="seoul.research.poll.service.impl.PanelServiceImpl" %>
<%@ page import="seoul.research.poll.web.PanelController" %>
<%
	Panel panel = new Panel(); 
	PanelController panelController = new PanelController(); 
	PanelServiceImpl  panelServiceImpl = new PanelServiceImpl();
	//로그인 정보 받아오기
	String temp_addr = ""; //로그인 키
	String mem001_id = ""; //암호화된 로그인 아이디
	String mem001_nm = ""; //암호화된 로그인 성명

 	String userid = ""; //복호화 된 로그인 아이디
	String mem_nm = ""; //복호화된 로그인 성명
	 
	String real_check_falg = ""; //복호화된 실명인증 플래그 N=비실명인증회원,Y=실명인증회원
	String lgOut = ""; // 로그아웃하면 세션 삭제
	String dFlag = ""; // 로그아웃하면 세션 삭제

	LoginCheck logChk = new LoginCheck(request);
	
	try {
		lgOut = request.getParameter("logout");
		dFlag = request.getParameter("delFlag");
		
		if("Y".equals(lgOut)){
			session.invalidate();	//로그아웃 후 세션 삭제 
			real_check_falg = "";
			userid = "";
			mem_nm = "";
		}
		
		// 로그인 관련 쿠키 추출		
		temp_addr = logChk.getCookie(request, "temp_addr");
			
		mem001_id = logChk.getCookie(request, "MEM001_ID");
		mem001_nm = logChk.getCookie(request, "MEM001_NM");
		    
	    real_check_falg = logChk.getCookie(request, "REALNAME_YN");
		userid = logChk.getUserId(mem001_id, temp_addr); // 아이디 복호화
		mem_nm = logChk.getUserId(mem001_nm, temp_addr); // 성명 복호화
		
		//out.println("<script>alert('11114 : " + userid + "');</script>");	
		//real_check_falg = getUserId(real_check_falg, temp_addr);
	    // 한글이 깨질 경우
		//mem_nm =  Uni2Ksc(getUserId(mem001_nm, temp_addr));
		//톰캣이 UTF8로 설치된 경우
	    //mem_nm = getUserIdForUTF(mem001_nm, temp_addr); 
	    if("".equals(userid) || "".equals(mem_nm)  ) {
	         // 로그인 체크 실패 시 처리 로직 부분 -- 로그인페이지 이동등 사이트에 맞게 로직 추가하세요.
	         //out.println("<script>alert('로그인 하세요');</script>");
	         //return;
		} else {
			// 로그인 체크 성공 시 처리 로직 부분 -- 사이트에 맞게 로직 추가하세요
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {

				//2013.09.27. connection  
				String driver = "net.sf.log4jdbc.DriverSpy"; 
				String url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(LOAD_BALANCE=ON)(FAILOVER=ON)(ADDRESS=(PROTOCOL=TCP)(HOST=115.84.160.105) (PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=115.84.160.110) (PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=FRONTDB2)(failover_mode=(type=session)(method=basic))))"; 
				Class.forName(driver);
				conn = DriverManager.getConnection(url, "hisip","xhd623gkq");
				String sql = "SELECT * FROM hisip.vw_mem001_m_research where m001_id = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid); 
			
				rs = pstmt.executeQuery();
	
				if (rs.next()) {
					session.setAttribute("userId", userid);
					session.setAttribute("userNm", rs.getString(2));
					session.setAttribute("userEmail", rs.getString(3));
					session.setAttribute("userMf", rs.getString(4));
					session.setAttribute("userBirthDay", rs.getString(5));
					session.setAttribute("userSido", rs.getString(6));
					//session.setAttribute("userDelFlag", rs.getString(7));
					/*
					System.out.println(" =====>>>  session.getAttribute(userId)       : " + session.getAttribute("userId"));	
					System.out.println(" =====>>>  session.getAttribute(userNm)       : " + session.getAttribute("userNm"));	
					System.out.println(" =====>>>  session.getAttribute(userEmail)    : " + session.getAttribute("userEmail"));	
					System.out.println(" =====>>>  session.getAttribute(userMf)       : " + session.getAttribute("userMf"));	
					System.out.println(" =====>>>  session.getAttribute(userBirthDay) : " + session.getAttribute("userBirthDay"));		
					System.out.println(" =====>>>  session.getAttribute(userSido)     : " + session.getAttribute("userSido"));*/
					//System.out.println(" =====>>>  session.getAttribute(userDelFlag)  : " + session.getAttribute("userDelFlag"));	
				}
				rs.close();
				pstmt.close();

	
			} catch (Exception se) {
				System.out.println("============>> Exception :  " + se.toString());
				out.print("<br /><br /><br />");
				out.print("<hr><br /><h1>일시 적으로 DB연결이 불안정 합니다. 잠시후 다시 시도해주시기 바랍니다.</h1><br /><hr>");
				
				//out.print("<script>alert(일시 적으로 DB연결이 불안정 합니다. 잠시후 다시 시도해주시기 바랍니다.(1));</script>");
				se.printStackTrace();
				return;
			} finally {
				if (rs != null)try {rs.close();} catch (Exception e) {}	
				if (pstmt != null)try {pstmt.close();} catch (Exception e) {}
				if (conn != null) try {conn.close();} catch (Exception e) {}
			} 
		}
	    	    
	    //---------------------------------------------------------- start 
	    //패널탈퇴 또는 패널가입 처리 후 delFlag 값을 조회해 온다. 
	    if(!"".equals(userid)){

			Connection conn2 = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			
	    	try {
				//System.out.println("===>>  패널탈퇴 또는 패널가입 여부 delFlag 값을 조회  시작 ~~~~~~  " );		
				// Server Connection 
				String driver = "net.sf.log4jdbc.DriverSpy"; 
				String url = "log4jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(LOAD_BALANCE=ON)(FAILOVER=ON)(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.160.160)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.160.170)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=PRIDB01)(failover_mode=(type=session)(method=basic))))"; 
				conn2 = DriverManager.getConnection(url, "research","sch!201306");
 
				String sql = "SELECT A.PNL_ID, A.PNL_NAME , A.PNL_SUBJECT , A.DELFLAG ";
				sql += "FROM PANEL A  ";
				sql += "WHERE A.PNL_ID = ? ";
				
				pstmt2 = conn2.prepareStatement(sql);
				pstmt2.setString(1, userid); 
			
				rs2 = pstmt2.executeQuery();
	
				if (rs2.next()) {
					session.setAttribute("userSubject", rs2.getString(3));//일반(전체),시정모니터,자문위원
					session.setAttribute("userDelFlag", rs2.getString(4));
					//System.out.println(" =====>>>  session.getAttribute(userSubject)  : " + session.getAttribute("userSubject"));	
					//System.out.println(" =====>>>  session.getAttribute(userDelFlag)  : " + session.getAttribute("userDelFlag"));
				}
				rs2.close();
				pstmt2.close();
				
	
			} catch (Exception se) {
				System.out.println("============>> Exception :  " + se.toString());
				out.print("<br /><br /><br />");
				out.print("<hr><br /><h1>일시 적으로 DB연결이 불안정 합니다. 잠시후 다시 시도해주시기 바랍니다.(9901)</h1><br /><hr>");
				se.printStackTrace();
				return;
			} finally {
				if (rs2 != null)try {rs2.close();} catch (Exception e) {}	
				if (pstmt2 != null)try {pstmt2.close();} catch (Exception e) {}
				if (conn2 != null) try {conn2.close();} catch (Exception e) {}
			}
	    }
		//-------------------------------------------------------- end
		
		
	} catch (Exception ee) {
		//out.println("<script>alert('" + ee.getMessage() + "!!!!!!!!!!!!!!!!!!');</script>");
	     // Exception 발생시 복호화 과정 오류로 로그인 체크 실패입니다. -- 로그인페이지 이동등 사이트에 맞게 로직 추가하세요
	     //return;
	} 
%>
