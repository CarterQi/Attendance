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
import com.ambow.bean.NotesInfo;
import com.ambow.daoImp.TravelDAOImp;

/**
 * Servlet implementation class Travel
 */
@WebServlet("/Travel")
public class Travel extends HttpServlet {
	TravelDAOImp dao=new TravelDAOImp();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Travel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("opt").equals("query")) {
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session=req.getSession();	
			String adminname=(String) session.getAttribute("username");
			try {
				ArrayList<NotesInfo> list=dao.query(adminname);
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("Rows", list);
				map.put("Total", list.size());
				String Data=JSON.toJSONString(map);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(Data);
				out.flush();
				out.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		if(request.getParameter("opt").equals("add")) {
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session=req.getSession();	
			String adminname=(String) session.getAttribute("username");
			String application=request.getParameter("application");
			String date=request.getParameter("date");
			String startdate=request.getParameter("startdate");
			String enddate=request.getParameter("enddate");
			String why=request.getParameter("text1");
			String pjname=request.getParameter("text2");
			String sign=request.getParameter("text3");
			String typeid="22";
			String typename="³ö²î";
			String operatorid="1";
			NotesInfo info=new NotesInfo(typeid,operatorid,adminname,application,typename,date,startdate,startdate,enddate,why,pjname,sign);
			try {
				dao.add(info);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("opt").equals("update")) {
			String js=request.getParameter("json");
			String noteid=request.getParameter("noteid");
			NotesInfo info=JSONObject.parseObject(js,NotesInfo.class);
			try {
				dao.update(info,noteid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("opt").equals("delete")) {
			String noteid=request.getParameter("data");
			try {
				dao.delete(noteid);
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
