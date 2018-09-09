package com.ambow.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.ambow.bean.UserInfo;
import com.ambow.daoImp.UserDAOImp;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	UserDAOImp dao=new UserDAOImp();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if( request.getParameter("opt").equals("query") )
		{
		try {
			ArrayList<UserInfo> list = dao.query();
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("Rows", list);
			map.put("Total", list.size());
			String UserData=JSON.toJSONString(map);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(UserData);
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
if(request.getParameter("opt").equals("add")) {
			
			try {
				String js=request.getParameter("json");
				
				UserInfo info= JSONObject.parseObject(js,UserInfo.class);						    

				dao.add(info);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
if(request.getParameter("opt").equals("delete")) {
	     String data=request.getParameter("data");
		try {
			dao.delete(data);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	
}
if(request.getParameter("opt").equals("update")) {
	String js=request.getParameter("json");
	UserInfo info= JSONObject.parseObject(js,UserInfo.class);	
	try {
		dao.update(info);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
