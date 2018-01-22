package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;


@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("servelet 진입");
		
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		if ("list".equals(actionName)) {
			System.out.println("list 진입");
	
			GuestbookDao dao = new GuestbookDao();
           List<GuestbookVo> list = dao.getList();
 			
 			request.setAttribute("list", list);
 			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
 			rd.forward(request, response);
			
		}else if("add".equals(actionName)) {
			System.out.println("add 진입");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo vo;
			vo = new GuestbookVo (1, name, password, content, "");
	
			GuestbookDao dao = new GuestbookDao();
			dao.insert(vo);
			
			response.sendRedirect("gb?a=list");
			
 		} else if("delete".equals(actionName)) {
 			System.out.println("delete 진입");
 			GuestbookDao dao = new GuestbookDao();
 			
 			int no = Integer.valueOf(request.getParameter("no"));
 			String password = request.getParameter("password");
 	
 			dao.delete(no, password);
 			response.sendRedirect("gb?a=list");
 			
 		} 
 		else if("deleteform".equals(actionName)) {
 			System.out.println("deleteform 진입");
 			
 			int no = Integer.valueOf(request.getParameter("no"));
		
 			request.setAttribute("no", no);
 			
 			RequestDispatcher rd;
			rd = request.getRequestDispatcher("deleteform.jsp");
			rd.forward(request, response);
 	
 			
 		}else {
 			System.out.println("잘못된 a값 처리");
 		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
