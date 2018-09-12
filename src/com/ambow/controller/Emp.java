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
import com.ambow.bean.EmpInfo;
import com.ambow.bean.PositionInfo;
import com.ambow.daoImp.EmpDAOImp;
import com.ambow.util.PageUtil;

/**
 * Servlet implementation class Emp
 */
@WebServlet("/Emp")
public class Emp extends HttpServlet {
	 PageUtil pageUtil=new PageUtil();
	EmpDAOImp dao=new EmpDAOImp();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Emp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PageUtil pageUtil=new PageUtil();
		
		if( request.getParameter("opt").equals("query") )
			
		{
			
		try {
			
			ArrayList<EmpInfo> list=dao.query(pageUtil);
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("Rows", list);
			map.put("Total", list.size());
			String EmpData=JSON.toJSONString(map);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(EmpData);
			out.flush();
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		}
		if( request.getParameter("opt").equals("querypositionname") ) {
			try {
				ArrayList<PositionInfo> list=dao.querypositionname();
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
				EmpInfo info= JSONObject.parseObject(js,EmpInfo.class);						    

				dao.add(info);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(request.getParameter("opt").equals("update")) {
			String js=request.getParameter("json");
			EmpInfo info= JSONObject.parseObject(js,EmpInfo.class);	
			try {
				dao.update(info);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(request.getParameter("opt").equals("delete")) {
			String data=request.getParameter("data");
			int empid=Integer.parseInt(data);
			try {
				dao.delete(empid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(request.getParameter("opt").equals("queryname")) {
			try {
				ArrayList<EmpInfo> list=dao.queryname();
				String name=JSON.toJSONString(list);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(name);
				out.flush();
				out.close();
			} catch (Exception e) {
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
