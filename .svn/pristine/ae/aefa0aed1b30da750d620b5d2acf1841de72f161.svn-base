package com.ntels.ccbs.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ntels.ccbs.system.domain.common.service.SelectedMenu;
import com.ntels.ccbs.system.domain.common.service.SessionUser;

public class CommonUtil {

	/**
	 * 세션조회
	 * @return
	 */
	public static HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession(false);
	}
	
	/**
	 * 세션조회 (없을경우 생성)
	 * @return
	 */
	public static HttpSession getCreateSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession(true);
	}
	
	/**
	 * 세션에서 SessionUser를 조회 한다
	 * @return
	 */
	public static SessionUser getSessionManager() {
		HttpSession session = getSession();

		if (session != null) {
			SessionUser sessionUser = (SessionUser) session.getAttribute("session_user");
			return sessionUser;
		}

		return null;
	}
	
	/**
	 * 세션에 SessionUser를 저장한다
	 * @param sessionUser
	 */
	public static void setSessionManager(SessionUser sessionUser) {
		HttpSession session = getCreateSession();

		if (sessionUser != null) {
			session.setAttribute("session_user", sessionUser);
		}
	}
	
	public static SelectedMenu getSessionSelectMenuInfo() {
		HttpSession session = getSession();

		if (session != null) {
			SelectedMenu menuInfo = (SelectedMenu) session.getAttribute("selectedMenu");
			return menuInfo;
		}

		return null;
	}

	public static void setSessionSelectMenuInfo(SelectedMenu menuInfo) {
		HttpSession session = getCreateSession();

		if (menuInfo != null) {
			session.setAttribute("selectedMenu", menuInfo);
		}
	}

    /**
     * 문자열을 원하는 길이만큼 지정한 문자로 left padding 처리한다.
     * 
     * @param origin padding 처리할 문자열
     * @param limit padding 처리할 size
     * @param pad padding 될 문자
     * @return padding 처리된 문자열
     */
    public static String leftPadding( String origin, int limit, String pad )
    {
        String temp = pad;

        int size = origin.length();

        if ( limit <= size )
        {
            return origin;
        }
        else
        {
            StringBuffer sb = new StringBuffer( temp );

            for ( int inx = size; inx < limit - 1; inx++ )
            {
                sb.append( pad );

            }

            return sb.toString() + origin;
        }
    }	
	
    public static String camelToDbStyle(String str){
		String regex = "([a-z])([A-Z])";
		String replacement = "$1_$2";
        String value = "";        
        value = str.replaceAll(regex, replacement).toUpperCase();
        return value;
	}

    public static String transVOValToDBColNm(String str){
    	
    	//----------------------------------------
		//정렬 기준이 되는 인덱스명을 DB컬럼명으로 변경
		//----------------------------------------
    	//String을 CharArray로 변경
		char[] sidxCh = str.toCharArray();
		String tranStr = str;	//변경된 String
		int idx = 0;			//'_' 넣을 위치 idx
		for(int i=0;i<sidxCh.length;i++){
			
			//char가 대문자 이면...
			if(Character.isUpperCase(sidxCh[i])){
				//대문자 앞에 '_' 추가
				tranStr = tranStr.substring(0,idx)+"_"+tranStr.substring(idx);
				idx++;
			}
			idx++;
		}
		
		//모두 대문자로 변경
		tranStr = tranStr.toUpperCase();	
        return tranStr;
	}
}
