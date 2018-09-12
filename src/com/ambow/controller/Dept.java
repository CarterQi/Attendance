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
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ambow.bean.DeptInfo;
import com.ambow.daoImp.DeptDAOImp;

/**
 * Servlet implementation class Dept
 */
@WebServlet("/Dept")
public class Dept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DeptDAOImp dao=new DeptDAOImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dept() {
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
			ArrayList<DeptInfo> list = dao.query();
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("Rows", list);
			map.put("Total", list.size());
			String DeptData=JSON.toJSONString(map);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(DeptData);
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if( request.getParameter("opt").equals("queryonename") ) {
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session=req.getSession();	
			String adminname=(String) session.getAttribute("username");
			try {
				ArrayList<DeptInfo> list = dao.queryonename(adminname);
				String name=JSON.toJSONString(list);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(name);
				out.flush();
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if( request.getParameter("opt").equals("querytwoname") ) {
			try {
				String data=request.getParameter("data");
				ArrayList<DeptInfo> list = dao.querytwoname(data);
				String name=JSON.toJSONString(list);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(name);
				out.flush();
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		if( request.getParameter("opt").equals("queryname") ) {
		
			ArrayList<DeptInfo> list;
			try {
				list = dao.queryname();
				String name=JSON.toJSONString(list);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(name);
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
				DeptInfo info= JSONObject.parseObject(js,DeptInfo.class);						    

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
	DeptInfo info= JSONObject.parseObject(js,DeptInfo.class);	
	try {
		dao.update(info);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
if(request.getParameter("opt").equals("manage")) {
	String data=request.getParameter("data");
	try {
		ArrayList<DeptInfo> list = dao.querymg(data);
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("Rows", list);
		map.put("Total", list.size());
		String DeptData=JSON.toJSONString(map);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(DeptData);
		out.flush();
		out.close();
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
