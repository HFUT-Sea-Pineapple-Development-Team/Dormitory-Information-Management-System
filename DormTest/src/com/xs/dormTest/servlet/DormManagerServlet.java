package com.xs.dormTest.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xs.dormTest.bean.DormBuild;

/**
 * Servlet implementation class DormManagerServlet
 */
@WebServlet("/dormManager.action")
public class DormManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DormManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action") ;
		
		if (action != null && action.equals("list")) {
			//宿舍管理员查询
			request.setAttribute("mainRight", "dormManagerList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("preAdd")) {
			//添加宿舍管理员信息
			request.setAttribute("mainRight", "dormManagerAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("save")) {
		}
	}

}
