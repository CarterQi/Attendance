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
import com.ambow.bean.LeaveInfo;
import com.ambow.bean.NotesInfo;
import com.ambow.daoImp.LeaveDAOImp;

/**
 * Servlet implementation class Leave
 */
@WebServlet("/Leave")
public class Leave extends HttpServlet {
	LeaveDAOImp dao=new LeaveDAOImp();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Leave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("opt").equals("queryname")) {
			try {
				ArrayList<LeaveInfo> list=dao.queryname();
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
			String application=request.getParameter("application");
			String daili=request.getParameter("daili");
			String typename=request.getParameter("cktype");
			String date=request.getParameter("date");
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
			String why=request.getParameter("text1");
			String opnion=request.getParameter("text2");
			String sign=request.getParameter("text3");
			String chk=request.getParameter("text4");
			System.out.println(application+""+daili+""+typename+""+date+""+startdate+""+enddate+""+why+""+opnion+""+sign+""+chk);
		}
		if(request.getParameter("opt").equals("query")) {
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session=req.getSession();	
			String adminname=(String) session.getAttribute("username");
		}
//			try {
//				ArrayList<NotesInfo> list=dao.query(adminname);
//				HashMap<String,Object> map=new HashMap<String,Object>();
//				map.put("Rows", list);
//				map.put("Total", list.size());
//				String Data=JSON.toJSONString(map);
//				System.out.println(Data);
//				response.setContentType("text/html;charset=utf-8");
//				PrintWriter out = response.getWriter();
//				out.print(Data);
//				out.flush();
//				out.close();
//			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
