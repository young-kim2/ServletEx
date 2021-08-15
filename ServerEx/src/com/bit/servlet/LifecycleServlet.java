package com.bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿의 생명 주기 확인
public class LifecycleServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException{
		//첫 호출시 init 메서드가 수행: 서블릿 초기화 담당
		
		System.out.println("Lifecycle:init()");
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	                       throws ServletException, IOException{
		//요청이 들어올 때 호출, 요청 방식에 따라 doGet or doPost
		//요청 메서드 방식에 관계 없이 호출된다
		
		System.out.println("Lifecycle: service()");
		                   super.service(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	                     throws ServletException, IOException{
		//요청이 GET 메서드일 때
		System.out.println("Lifecycle: doGet()");
		PrintWriter out=resp.getWriter();
		out.println("<h1>doGet call</h1>");
	}
	@Override
	public void destroy() {
		//서버 중단, 오랜 시간 서블릿 요청이 없을 때-> 서블릿의 자원 정리
		System.out.println("Lifecycle: destroy()");
	}

}
