package controller.statemanagement;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StateManage {

	public Boolean checkUserSession(String userSession){
    	if(userSession != null && !userSession.isEmpty()) return true;
    	else return false;
    }
	
	public void getUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
	}
	
	public Boolean checkUserCookie() {
		// code statement
		return true;
	}
	
	public void getUserCookie(HttpServletRequest request, HttpServletResponse response) {
		// code statement
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie: cookies) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}
}
