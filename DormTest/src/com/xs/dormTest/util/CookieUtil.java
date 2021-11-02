package com.xs.dormTest.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static void addCookie(String cookieName, int time, HttpServletRequest request,HttpServletResponse response, String stu_code, String password) {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		
		//遍历所有cookie
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					cookie.setValue(stu_code+"#"+password);
				}
			}
		}
		
		Cookie cookie = new Cookie(cookieName,stu_code+"#"+password);
		cookie.setMaxAge(time);
		cookie.setPath(request.getContextPath());
		
		response.addCookie(cookie);
	}
	
}
