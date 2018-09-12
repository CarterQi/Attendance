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
import com.ambow.bean.CheckInfo;
import com.ambow.daoImp.CheckDAOImp;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Check")
public class Check extends HttpServlet {
	CheckDAOImp dao=new CheckDAOImp();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if( request.getParameter("opt").equals("query") ) {
			String data=request.getParameter("data");
			try {
				ArrayList<CheckInfo> list=dao.query(data);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if( request.getParameter("opt").equals("update") ) {
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session=req.getSession();	
			String adminname=(String) session.getAttribute("username");
			String startdate=request.getParameter("startdate");
			String attendanceflag=request.getParameter("attendanceflag");
			String employeename=request.getParameter("employeename");
			String cardnumber=request.getParameter("cardnumber");
			String typename=request.getParameter("typename");
			String noteid=request.getParameter("noteid");
			String attendancememo=request.getParameter("attendanceMemo");
            CheckInfo info=new CheckInfo(employeename,cardnumber,"",attendancememo,adminname,startdate,typename,noteid,attendanceflag);

			try {
				dao.update(info,adminname);
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
