package seoul.research.poll.common;

import java.security.MessageDigest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 2013.09.10.  
 *  
 */
public class LoginCheck {
	
	long startTime =System.currentTimeMillis();

	HttpServletRequest request; // 2013.09.10.  
	HttpSession oSession;
	
	String ADMIN_ID; // 
	String user_id;  //  
	String guest_key;  //  

	String NOW_IP;						
	String sNoPrint;
	String NOW_PAGE;

	public LoginCheck(HttpServletRequest request){
		
		this.request =request; 
		
		oSession = request.getSession();

		ADMIN_ID 	= NVL((String)oSession.getAttribute("ADMIN_ID")); // 
		user_id		= NVL((String)oSession.getAttribute("user_id"));  //  
		guest_key	= NVL((String)oSession.getAttribute("guest_key"));  // 

		NOW_IP		= NVL(request.getRemoteAddr());						
		sNoPrint	= NVL(request.getParameter("sNoPrint"));
		NOW_PAGE	= NVL(request.getRequestURI());
		
		//Debug.println("===>> ADMIN_ID ::: ", ADMIN_ID);
		//Debug.println("===>> user_id  ::: ", user_id);
		//Debug.println("===>> guest_key ::: ", guest_key);
		//Debug.println("===>> NOW_IP    ::: ", NOW_IP);
		
	}
	
	
	public static String makeMd5(String word) throws Exception {

		//Debug.println("===>> makeMd5 :::  ", "");
	    byte[] bNoti = word.toString().getBytes();
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] digest = md.digest(bNoti);

	    StringBuffer strBuf = new StringBuffer();
	    for (int i=0 ; i < digest.length ; i++) {
	        int c = digest[i] & 0xff;
	        if (c <= 15){
	            strBuf.append("0");
	        }
	        strBuf.append(Integer.toHexString(c));
	    }
	    String HASHDATA = strBuf.toString();

		return HASHDATA;
	}

	public static String getUserId(String p_enc_str, String p_key_value) throws Exception {
		
		String enc_key = makeMd5("seoul"+ p_key_value +"city");		
		
		String key_head = enc_key.substring(0, 16);
		String key_tail = enc_key.substring(16, 32);
		String enc_tail = "";
		String enc_head = "";
		String user_id = "";      
		for (int i = 0; i < p_enc_str.length()/2 ; i++ ) {
			enc_tail= enc_tail + p_enc_str.substring(i*2, i*2+1);
			enc_head= enc_head + p_enc_str.substring(i*2+1, i*2+2);
        }
        String temp_str = enc_head + enc_tail;
		String temp_str1 = temp_str.substring(16, temp_str.length());
		String temp_str2 = temp_str1.substring(0, temp_str1.indexOf(key_tail));

		if( temp_str.indexOf(key_head) > -1 && temp_str.indexOf(key_tail) > -1) {
			user_id = new String(hex2byte(temp_str2));
		}
		
		return user_id;

	}

	public String getUserIdForUTF(String p_enc_str, String p_key_value) throws Exception {

		String enc_key = makeMd5("seoul"+ p_key_value +"city");

		String key_head = enc_key.substring(0, 16);
		String key_tail = enc_key.substring(16, 32);

		String enc_tail = "";
		String enc_head = "";
		String user_id = "";

		for (int i = 0; i < p_enc_str.length()/2 ; i++ ) {
			enc_tail= enc_tail + p_enc_str.substring(i*2, i*2+1);
			enc_head= enc_head + p_enc_str.substring(i*2+1, i*2+2);
		}
		
		String temp_str = enc_head + enc_tail;
		String temp_str1 = temp_str.substring(16, temp_str.length());
		String temp_str2 = temp_str1.substring(0, temp_str1.indexOf(key_tail));
		if( temp_str.indexOf(key_head) > -1 && temp_str.indexOf(key_tail) > -1) {
			user_id = new String(hex2byte(temp_str2), "KSC5601" );
		}
		return user_id;
	}
    
	private static byte[] hex2byte(String s){
	   if(s == null) return null;
	   int l = s.length();
	   if(l%2 == 1) return null;
	     byte[] b = new byte[l/2];
             for(int i = 0 ; i < l/2 ;i++) {
		b[i] = (byte)Integer.parseInt(s.substring(i*2,i*2+2),16);
	   }
	   return b;
	}
    

	private static String byte2Hex(byte b) {
		String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
		int nb = b & 0xFF;
		int i_1 = (nb >> 4) & 0xF;
		int i_2 = nb & 0xF;
		return HEX_DIGITS[i_1] + HEX_DIGITS[i_2];
	}

	private static String bytes2Hex(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int x = 0; x < b.length; x++) {
			sb.append(byte2Hex(b[x]));
		}
		return sb.toString();
	}
    
	
	/**
	 *  
	 * @param request
	 * @param cookieName
	 * @return
	 * @throws Exception
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) throws Exception {
		
		System.out.println("=== LoginCheck getCookie  : " + request.toString());
		
		Cookie [] cookies = request.getCookies();
        String value = "";
        if(cookies==null){
            return null;
        }
        
        for(int i=0;i<cookies.length;i++) {
            if(cookieName.equals(cookies[i].getName())) {
                value = cookies[i].getValue();
                break;
            }
        }
        return value;
    }

	public String Uni2Ksc( String KscStr ) throws java.io.UnsupportedEncodingException {
	    if ( KscStr == null ) {
	      return null;
	    } else {
	      return new String( KscStr.getBytes( "8859_1" ), "KSC5601" );
	    }
	}
	
		
	
	////===========================================================================
	
	/// ******************************************************
	///  _common.jsp 
	/// ******************************************************	

	// DEBUG FLAG
	//boolean DEBUG_FLAG = ConfigClass.DEBUG_FLAG;

	int LIST_LINES = 10;
	int LIST_PAGES = 10;
	int CUR_PAGE = 0;

	// ISO_8859_1 => KSC5601
	public static String tokr(String str) throws java.io.UnsupportedEncodingException {
		return  new String(str.getBytes("8859_1"), "KSC5601");
	}

	// KSC5601 => ISO_8859_1
	public String toeng(String str) throws java.io.UnsupportedEncodingException {
		return new String(str.getBytes("KSC5601"), "8859_1");
	}

	public String URLEncoder(String str)  {
		return java.net.URLEncoder.encode(str);
	}
	
	public String URLDecoder(String str)  {
		return java.net.URLDecoder.decode(str);
	}


			public static String ToString(int str)
		    {
				return Integer.toString(str);
		    }

			public static int ToInt(String str)
		    {
				if(str.equals("")){
					return 0;
				}else{
					return Integer.parseInt(str);
				}
		    }

			public static long ToLong(String str)
		    {
				if(str.equals("")){
					return 0;
				}else{
					return Long.parseLong(str);
				}
				
		    }

			static public String NVL(String str){
	      		if(str == null)
	         		return "";
	      		else 
	         		return str;
	   	}

			static public String NVL(String str,String val){
	      		if(str == null)
	         		return val;
	      		else 
	         		return str;
	   	}
		static public boolean isPost(HttpServletRequest req) {
			if("POST".equals(req.getMethod())) {
				return true;
			} else {
				return false;
			}
		}

		static public String getStat(String str) {
			if("M".equals(str)) {
				return "ȸ��";
			}else if("N".equals(str)){
				return "��ȸ��";
			}else if("S".equals(str)){
				return "����Ʈ��";
			}else if("W".equals(str)){
				return "��";
			}else if("A".equals(str)){
				return "WAP";
			}else{
				return "";
			}
		}

	public String getRandom(){
		String rnd_char = "";
		for (int i = 1; i <= 8; i++){
		      int rnd_val = 1 + ( int ) ( Math.random() * 52 );
		      //int rnd_val = i;
		      if (rnd_val > 26){
		            rnd_val = rnd_val + 70;
		      }else{
		            rnd_val = rnd_val + 64;
		      }
		      rnd_char = rnd_char + (char)(rnd_val);
		      }
			  return rnd_char;
		}
		
}

