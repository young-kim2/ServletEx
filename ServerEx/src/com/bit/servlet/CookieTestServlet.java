package com.bit.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/cookies")
public class CookieTestServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	                    throws ServletException, IOException{
		//쿠키로 설정할 파라미터 읽어오기
		String value=req.getParameter("example");
		value=URLEncoder.encode(value, "UTF-8");
		//Cookie 생성
		Cookie cookie=new Cookie("example", value);
		//"example" 키에 value 저장
		cookie.setMaxAge(3600);
		//지속 시간 3600초
		//사용자 브라우저에 쿠키 저장 위해 응답 헤더에 적재
		resp.addCookie(cookie);
		resp.sendRedirect(req.getContextPath()+"/cookies");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	                 throws ServletException, IOException{
		RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/cookies/cookie_form.jsp");
		rd.forward(req, resp);
	}
}
