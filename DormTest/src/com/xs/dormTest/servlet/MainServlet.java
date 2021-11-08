package com.xs.dormTest.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DormManagerServlet
 */
@WebServlet("/blank.action")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action") ;
		
		if (action != null && action.equals("list")) {

		request.setAttribute("mainRight", "/WEB-INF/jsp/blank.jsp");
		request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
			
		}
		
	}

}

 