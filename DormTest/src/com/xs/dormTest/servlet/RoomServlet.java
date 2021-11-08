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
import com.xs.dormTest.service.UserService;
import com.xs.dormTest.service.UserServiceImpl;

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
		//获取当前的寝室楼id
		String buildId = request.getParameter("buildId");
		
		RoomService roomService = new RoomServiceImpl();
		DormBuildService dormBuildService = new DormBuildServiceImpl();
		UserService userService = new UserServiceImpl();
		
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
				List<Room> rooms = roomService.findByBuildId(Integer.parseInt(buildToSelect),keyword);
				request.setAttribute("rooms", rooms);
				request.setAttribute("buildToSelect", buildToSelect);
			}else if (roleId == 1) {
				//获取搜索框内的值
				String keyword = request.getParameter("keyword");
				
				//宿舍管理员的情况，可以对寝室成员进行添加和删除
				//首先确定寝室管理员管理的寝室楼号
				
				Integer buildManager = user.getDormBuildId();
				
				//根据寝室楼号显示对应的寝室楼信息
				List<Room> rooms = roomService.findByBuildId(buildManager,keyword);
				request.setAttribute("rooms", rooms);
			}
			
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/roomList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("detail")) {
			//查询某寝室的详细信息
			//当前的楼号及寝室号(楼号在最前面已经获取)
			System.out.println("hello world");
			String roomId = request.getParameter("roomId");
			
			System.out.println("hello world");
			//需要获取的学生信息
			List<User> students = userService.findByBuildAndRoom(Integer.parseInt(buildId),Integer.parseInt(roomId));
			
			request.setAttribute("roomId", roomId);
			request.setAttribute("buildId", buildId);
			request.setAttribute("students", students);
			request.setAttribute("mainRight", "/WEB-INF/jsp/roomDetail.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("preAdd")) {
			if (roleId == 0) {
				
				String rooms = request.getParameter("rooms");
				request.setAttribute("rooms", rooms);
				request.setAttribute("buildId", buildId);
				//在相应的寝室楼加入寝室
				
			}
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/roomAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("save")) {
			//保存寝室信息
			if (roleId == 0) {
				//超级管理员的页面
				String room_id = request.getParameter("room_id");
				
				//先判断有无这个寝室
				System.out.println(buildId);
				System.out.println(room_id);
				Room roomExist = roomService.findByBuildAndRoom(Integer.parseInt(buildId), Integer.parseInt(room_id));
				
				if (roomExist != null) {
					//已存在，则跳转到添加页面
					response.sendRedirect(getServletContext().getContextPath()+"/room.action?action=preAdd");
				}else {
					//未存在，可以保存
					Room roomsave = new Room();
					roomsave.setBuild_num(Integer.parseInt(buildId));
					roomsave.setRoom_id(Integer.parseInt(room_id));
					roomService.saveRoom(roomsave);
					response.sendRedirect(getServletContext().getContextPath()+"/room.action?action=list");
				}
			}
		}else if (action != null && action.equals("deleteRoom")) {
			//删除整个寝室信息
			
			//首先是删除整个寝室
			//根据寝室楼号和寝室号来确定是哪个寝室
			String build_id = request.getParameter("buildId");
			String room_id = request.getParameter("roomId");
			System.out.println(build_id);
			Room roomExist = roomService.findByBuildAndRoom(Integer.parseInt(build_id), Integer.parseInt(room_id));
			
			//删除对应寝室(若有人居住则不可以删除)
			roomService.deleteRoom(roomExist);
			response.sendRedirect(getServletContext().getContextPath()+"/room.action?action=list");

		}else if (action != null && action.equals("preAddUser")) {
			//获取当前的寝室楼号与寝室号	
			String build_id = request.getParameter("buildId");
			String room_id = request.getParameter("roomId");
			
			//列出当前寝室楼内所有未分配寝室的学生
			
			List<User> studentsNotRoom = userService.findNotRoom(Integer.parseInt(build_id));
			request.setAttribute("build_id", build_id);
			request.setAttribute("room_id", room_id);
			request.setAttribute("studentsNotRoom", studentsNotRoom);
			//在相应的寝室楼加入寝室
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/roomAddUser.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("addUser")) {
			//获取当前的寝室楼号与寝室号	
			String build_id = request.getParameter("buildId");
			String room_id = request.getParameter("roomId");
			
			//先进行添加操作
			//判断人数是否小于4
			Room room = roomService.findByBuildAndRoom(Integer.parseInt(build_id), Integer.parseInt(room_id));
			
			if (room.getPerson_num() < 4) {
				//说明寝室人数仍有空余，可以增加
				//获取当前的学生号码
				String id = request.getParameter("studentId");
				
				User userAdd = userService.findById(Integer.parseInt(id));
				userAdd.setRoomId(Integer.parseInt(room_id));
				userService.addUser(userAdd);
			}
			//列出当前寝室楼内所有未分配寝室的学生
			
			List<User> studentsNotRoom = userService.findNotRoom(Integer.parseInt(build_id));
			request.setAttribute("build_id", build_id);
			request.setAttribute("room_id", room_id);
			request.setAttribute("studentsNotRoom", studentsNotRoom);
			//在相应的寝室楼加入寝室
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/roomAddUser.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("deleteUser")) {
			//删除寝室用户(就是将学生的房间id修改为空)
			
			String id = request.getParameter("studentId"); 
			//获取当前的寝室楼号与寝室号	
			String room_id = request.getParameter("roomId");
			//将学生的房间号修改为空
			userService.updateRoom(Integer.parseInt(id));
			//需要获取的学生信息
			System.out.println(room_id);
			List<User> students = userService.findByBuildAndRoom(Integer.parseInt(buildId),Integer.parseInt(room_id));
			
			request.setAttribute("students", students);
			request.setAttribute("buildId", buildId);
			request.setAttribute("roomId", room_id);
			request.setAttribute("mainRight", "/WEB-INF/jsp/roomDetail.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);

		}
	}
}
