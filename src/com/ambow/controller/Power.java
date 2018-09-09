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
import javax.swing.plaf.synth.SynthSpinnerUI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ambow.bean.DeptInfo;
import com.ambow.bean.UserInfo;
import com.ambow.daoImp.PowerDAOImp;
import com.ambow.util.DBHelper;

/**
 * Servlet implementation class Power
 */
@WebServlet("/Power")
public class Power extends HttpServlet {
	PowerDAOImp dao=new PowerDAOImp();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Power() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if( request.getParameter("opt").equals("query") ) {
			ArrayList<DeptInfo> list;
			try {
				list = dao.query();
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
		if( request.getParameter("opt").equals("queryname") )
		{
		try {
			ArrayList<UserInfo> list = dao.queryname();
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
		if( request.getParameter("opt").equals("queryBypk") ) {
			try {
				String adminname=request.getParameter("data");
				ArrayList<DeptInfo> list = dao.queryBypk(adminname);
				String DeptData=JSON.toJSONString(list);
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
		if( request.getParameter("opt").equals("delThenAdd") ) {
			String name= request.getParameter("data");
			String id = request.getParameter("departmentid");
	        String[] str=id.split(",");
	        String sql2="delete from Att_AdminPopedom where adminid=(select adminid from Att_User where adminname=?)";
			try {
				DBHelper.execUpdate(sql2,name);
				DBHelper.closeAll();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        for(int i=0;i<str.length;i++) {
	        	
	        try {
				dao.add(name,str[i]);
				PrintWriter out = response.getWriter();
				out.print("success");
				out.flush();
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
