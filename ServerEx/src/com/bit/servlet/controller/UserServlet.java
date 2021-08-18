package com.bit.servlet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	                   throws ServletException, IOException{
		//사용자 요청 파라미터 처리:a
		String action=req.getParameter("a");
		
		if("joinform".equals(action)) {
			// a=joinform -> 가입 폼으로 FORWARD
			RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/users/joinform.jsp");
			rd.forward(req,resp);
		}else if("joinsuccess".equals(action)) {
			//a=joinsuccess->가입 성공 화면으로 FORWARD
			RequestDispatcher rd= req.getRequestDispatcher("WEB-INF/views/users/joinsuccess.jsp");
			rd.forward(req,resp);
		}else {
			//처리할 수 없는 요청->404 에러
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
