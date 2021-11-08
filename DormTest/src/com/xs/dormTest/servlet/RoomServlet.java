package com.xs.dormTest.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.bean.Hygiene;
import com.xs.dormTest.bean.Room;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.service.DormBuildService;
import com.xs.dormTest.service.DormBuildServiceImpl;
import com.xs.dormTest.service.HygieneService;
import com.xs.dormTest.service.HygieneServiceImpl;
import com.xs.dormTest.service.RoomService;
import com.xs.dormTest.service.RoomServiceImpl;

/**
 * Servlet implementation class RoomServlet
 */
@WebServlet("/room.action")
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决传递中文乱码的问题
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action") ;
		
		//获取当前登录的用户
		User user = (User) request.getSession().getAttribute("session_user");
		Integer roleId = user.getRoleId();
		
		RoomService roomService = new RoomServiceImpl();
		DormBuildService dormBuildService = new DormBuildServiceImpl();
		
		if (action != null && action.equals("list")) {			
			//超级管理员的情况，可以对寝室进行创建和删除
			if (roleId == 0) {
				//获取搜索框内的值
				String keyword = request.getParameter("keyword");
				
				//获取所有的寝室楼信息
				List<DormBuild> builds = dormBuildService.findAll();
				request.setAttribute("builds", builds);
				
				//获取当前寝室楼所有寝室的信息
				String buildToSelect = request.getParameter("buildToSelect");
				
				if (buildToSelect == null) {
					buildToSelect = "1";
				}
				
				List<Room> rooms = roomService.findByBuildId(Integer.parseInt(buildToSelect));
				request.setAttribute("rooms", rooms);
				
			}
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/roomList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}
	}

}
