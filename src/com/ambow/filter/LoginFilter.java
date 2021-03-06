package com.ambow.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		String url = req.getRequestURI().toString(); 
			HttpSession session=req.getSession();			
			System.out.println( "拦截到了！" +url + "id:"+ session.getId() + (session.getAttribute("username")==null));
			
			if(session.getAttribute("username")==null){
				 request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				HttpServletResponse rep = (HttpServletResponse)response;
			    rep.sendRedirect("/Attendance/Login.jsp");
			   
			}else{
				
				chain.doFilter(request, response);
			}
		

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("初始化成功");
		
	}

}
