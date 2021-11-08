package com.xs.dormTest.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.bean.UserAndRoom;
import com.xs.dormTest.service.DormBuildService;
import com.xs.dormTest.service.DormBuildServiceImpl;
import com.xs.dormTest.service.UserService;
import com.xs.dormTest.service.UserServiceImpl;
import com.xs.dormTest.util.PageModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/student.action")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
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
		//获取学生id
		String id = request.getParameter("id");
		
		//获取当前登录的用户
		User user = (User) request.getSession().getAttribute("session_user");
		Integer roleId = user.getRoleId();
		
		DormBuildService buildService = new DormBuildServiceImpl();
		UserService userService = new UserServiceImpl();
		
		List<DormBuild> builds = new ArrayList<DormBuild>();
		
		String buildToSelect = null;
		if (roleId.equals(0)) {
			//超级管理员
			builds = buildService.findAll();
			buildToSelect= request.getParameter("buildToSelect");
			
		}else if (roleId.equals(1)) {
			//宿舍管理员
			builds = buildService.findByManager(user.getDormBuildId());
			buildToSelect = user.getDormBuildId().toString();
		}else if (roleId.equals(2)) {
			//学生
			Integer studentId = user.getId();
			Integer roomId = user.getRoomId();
			DormBuild studentDormBuild = buildService.findById(user.getDormBuildId());
			UserAndRoom userAndRoom = userService.findByRoomId(studentId);
			
			request.setAttribute("studentDormBuild", studentDormBuild);
			request.setAttribute("roomId", roomId);
			request.setAttribute("userAndRoom", userAndRoom);
		}
		
		request.setAttribute("builds", builds);
		
		if (action != null && action.equals("list")) {
			//查询学生在右侧展示
			String searchType = request.getParameter("searchType");
			String keyword = request.getParameter("keyword");
			
			//当前要查询的页码
			String pageIndex = request.getParameter("pageIndex");
			
			//默认查询第一页，需两个参数，当前页码及每页展示的条数
			PageModel pageModel = new PageModel();
			if (pageIndex != null && !pageIndex.contentEquals("")) {
				pageModel.setPageIndex(Integer.parseInt(pageIndex));
			}
			List<User> students = userService.findStudent(buildToSelect,searchType,keyword,user,pageModel);
			
			//获取查询处理的总数量
			Integer totalNum = userService.findTotalNum(buildToSelect,searchType,keyword,user);
			
			System.out.println(searchType);
			
			System.out.println(buildToSelect);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("keyword", keyword);
			request.setAttribute("searchType", searchType);
			request.setAttribute("students", students);
			request.setAttribute("buildToSelect", buildToSelect);
			request.setAttribute("pageIndex", pageModel.getPageIndex());
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/studentList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("preAdd")) {
			//根据用户角色查询宿舍楼进行添加学生
			//如当前用户为宿舍管理员，他只能添加学生到其管理的宿舍楼
			//如当前用户为超级管理员，他可以添加学生到所有宿舍楼
			System.out.println("hello world");
			System.out.println(builds);
			request.setAttribute("builds", builds);
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/studentAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if(action != null && action.equals("addList")) {
			//批量导入学生信息

			request.setAttribute("mainRight", "/WEB-INF/jsp/selectFile.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("save")) {
			//保存学生
			System.out.println("hello world");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String major = request.getParameter("major");
			String className = request.getParameter("className");
			String dormBuildId = request.getParameter("dormBuildId");
			String dormName = request.getParameter("dormName");
			String tel = request.getParameter("tel");
			String leaveSchool = request.getParameter("leaveSchool");
			
			//未存在，保存
			User student = userService.findByStuCode(userName);
			if(id != null && !id.equals("")) {
				//更新之前，先查询数据库是否已存在当前学号的学生
				if (student != null && !student.getId().equals(Integer.parseInt(id))) {
					//当前学号的学生已存在，请重新修改
					request.getRequestDispatcher("/student.action?action=preUpdate&id" + id).forward(request, response);
				}else {
					//更新
					User studentUpdate = userService.findById(Integer.parseInt(id));
					studentUpdate.setStu_code(userName);
					studentUpdate.setPassword(password);
					studentUpdate.setName(name);
					studentUpdate.setSex(Integer.parseInt(sex));
					studentUpdate.setMajor(major);
					studentUpdate.setClassName(Integer.parseInt(className));
					studentUpdate.setDormBuildId(Integer.parseInt(dormBuildId));
					studentUpdate.setRoomId(Integer.parseInt(dormName));
					studentUpdate.setTel(tel);
					studentUpdate.setLeaveSchool(Integer.parseInt(leaveSchool));
					
					userService.updateStudent(studentUpdate);
					response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
				}
				
			}else {
				//在保存之前，先查询数据库是否已存在当前学号的学生
				if (student != null) {
					//已存在，则跳转到添加页面
					response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=preAdd");
				}else {
					User user2 = new User();
					user2.setStu_code(userName);
					user2.setPassword(password);
					user2.setName(name);
					user2.setSex(Integer.parseInt(sex));
					user2.setMajor(major);
					user2.setClassName(Integer.parseInt(className));
					user2.setDormBuildId(Integer.parseInt(dormBuildId));
					user2.setRoomId(Integer.parseInt(dormName));
					user2.setTel(tel);
					userService.saveStudent(user2);
					response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
				}
			}
			
		}else if (action != null && action.equals("preUpdate")) {
			//通过学号查找学生
			User userUpdate = userService.findById(Integer.parseInt(id));
			
			request.setAttribute("userUpdate", userUpdate);
			//跳转到修改页面
			request.setAttribute("mainRight", "/WEB-INF/jsp/studentAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
			
		}else if (action != null && action.equals("delete")) {
			//通过学号查找学生
			User userDelete = userService.findById(Integer.parseInt(id));
			userService.deleteStudent(userDelete);
			//跳转到修改页面
			response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
			
		}
		
	}

}
