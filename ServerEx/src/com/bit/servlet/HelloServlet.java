package com.bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet은 HttpServlet을 상속 받아 필요한 메서드를 Override 한다
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	                     throws ServletException, IOException{
		//GET 요청을 처리하는 메서드 오버라이드
		//클라이언트에서 name 파라미터를 전송 받아서 환영 메시지 출력
		//JSP 에서는 사용자 요청을 자동으로 request라는 이름으로 전달
		// -> Servlet에서는 인수로 전달된 req
		String name=req.getParameter("name");
		if(name==null) {
			name="Anonymous";
		}
		//출력
		//응답 객체에서 Writer를 얻어온다
		PrintWriter out=resp.getWriter();
		out.println("<h1>Hello, Servlet</h1>");
		out.println("<p>Hello, "+name+"</p>");
	}

}
