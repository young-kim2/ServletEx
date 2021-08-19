package com.bit.servlet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit.servlet.dao.UserDao;
import com.bit.servlet.dao.UserDaoOrclImpl;
import com.bit.servlet.dao.UserVo;

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
		}else if("loginform".equals(action)) {
			//a=loginform
			RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/views/users/loginform.jsp");
			rd.forward(req, resp);
		}else if("joinsuccess".equals(action)) {
			//a=joinsuccess->가입 성공 화면으로 FORWARD
			RequestDispatcher rd= req.getRequestDispatcher("WEB-INF/views/users/joinsuccess.jsp");
			rd.forward(req,resp);
		}else if("logout".equals(action)) {
			//a=logout->세션을 무효화하고 ->/로 리다이렉트
			HttpSession session=req.getSession();
			//session.removeAttribute("authUser");
			//개별 속성 삭제
			session.invalidate();
			//세션 전체를 무효화
			resp.sendRedirect(req.getContextPath()+"/");
		}else {
			//처리할 수 없는 요청->404 에러
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	                    throws ServletException, IOException{
		//액션 파라미터 받아오기
		String action=req.getParameter("a");
		if("join".equals(action)) {
			//a hidden 필드가 join일 때
			//파라미터 받아오기
			String name=req.getParameter("name");
			String password=req.getParameter("password");
			String email=req.getParameter("email");
			String gender=req.getParameter("gender");
			
			//사용자 객체 생성
			UserVo vo=new UserVo(name, password, email, gender);
			
			//Dao 생성
			UserDao dao=new UserDaoOrclImpl();
			
			int insertedCount=dao.insert(vo);
			//1이어야 성공
			
			if(insertedCount==1) {
				//성공->joinsuccess로 리다이렉트
				resp.sendRedirect(req.getContextPath()+"/users?a=joinsuccess");
			}else {
				//실패
				//다시 가입 폼으로 출력
				RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/views/users/joinform.jsp");
				
				rd.forward(req, resp);
			}
		}else if("login".equals(action)) {
			//a=login
			UserDao dao=new UserDaoOrclImpl();
			//파라미터
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			//사용자 검색
			UserVo vo=dao.getUserByEmailAndPassword(email, password);
			
			if(vo != null) {
				//사용자를 찾음
				//세션 객체 확보
				HttpSession session=req.getSession(true);
				//세션 객체에 사용자 정보를 속성으로 추가
				session.setAttribute("authUser", vo);
				//홈페이지로 리다이렉트
				resp.sendRedirect(req.getContextPath()+"/");
			}else {
				//그런 사용자 없음->로그인 폼으로 리다이렉트
				resp.sendRedirect(req.getContextPath()+"/users?a=loginform");
			}
		}else {
			//에러 페이지 전송
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
