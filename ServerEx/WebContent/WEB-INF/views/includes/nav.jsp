<%@page import="com.bit.servlet.dao.UserVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
    //사용자 정보 확인
    //UserVo authUser=(UserVo)session.getAttribute("authUser");
    %>
<!-- Navigation -->
<ul>
<c:choose>
<c:when test="${empty authUser}">
<!-- c:url은 자동으로 앞에 웹 앱의 컨텍스트 패스를 붙여준다 -->

<li><a href="<c:url value="/users?a=loginform"/>">로그인</a></li>
<li><a href="<c:url value="/users?a=joinform"/>">회원가입</a></li>
</c:when>
<c:otherwise>
<li>${authUser.name}님 환영합니다.</li>
<li><a href="<c:url value="/users?a=logout"/>">로그아웃</a></li>
</c:otherwise>
</c:choose>
</ul>