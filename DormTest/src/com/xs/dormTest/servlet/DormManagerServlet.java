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
import com.xs.dormTest.bean.User;
import com.xs.dormTest.service.DormBuildService;
import com.xs.dormTest.service.DormBuildServiceImpl;
import com.xs.dormTest.service.UserService;
import com.xs.dormTest.service.UserServiceImpl;

/**
 * Servlet implementation class DormManagerServlet
 */
@WebServlet("/dormManager.action")
public class DormManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DormManagerServlet(){
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
		
		UserService userService = new UserServiceImpl();
		DormBuildService buildService = new DormBuildServiceImpl();
		List<DormBuild> builds = new ArrayList<DormBuild>();
		builds = buildService.findAll();
		
		if (action != null && action.equals("list")) {
			//宿舍管理员查询
			//获取当前所有的宿舍管理员
			List<User> dormManagerList = userService.findManager();
			
			request.setAttribute("dormManagerList", dormManagerList);
			request.setAttribute("mainRight", "/WEB-INF/jsp/dormManagerList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("preAdd")) {
			//添加宿舍管理员信息
			//获得所有的宿舍楼
			request.setAttribute("builds", builds);
			System.out.println(builds);
			request.setAttribute("mainRight", "/WEB-INF/jsp/dormManagerAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);

		}else if (action != null && action.equals("save")) {
			//获得当前的工号
			String jobCode = request.getParameter("jobCode");
			
			//获取当前的管理者id，根据有无这个id来判断这是修改还是添加
			String id = request.getParameter("dormManagerId");
			
			//判断当前的工号是否已存在
			User dormManager = userService.findById(Integer.parseInt(jobCode));
			
			if(id != null && !id.equals("")) {
				//更新之前，先查询数据库是否已存在当前工号的管理员
				if (dormManager != null && !dormManager.getId().equals(Integer.parseInt(id))) {
					//当前学号的学生已存在，请重新修改
					request.getRequestDispatcher("/dormManager.action?action=preUpdate&id" + id).forward(request, response);
				}else {
					//更新
					//不存在，需要保存
					String password = request.getParameter("password");
					String name = request.getParameter("name");
					String tel = request.getParameter("tel");
					String sex = request.getParameter("sex");
					String dormBuildId = request.getParameter("dormBuildId");
					
					User manager = new User();
					manager.setId(Integer.parseInt("id"));
					manager.setStu_code(jobCode);
					manager.setPassword(password);
					manager.setName(name);
					manager.setTel(tel);
					manager.setSex(Integer.parseInt(sex));
					manager.setDormBuildId(Integer.parseInt(dormBuildId));
					
					userService.updateManager(manager);
					response.sendRedirect(getServletContext().getContextPath()+"/dormManager.action?action=list");
				}
				
			}else {
				if (dormManager != null) {
					//已存在，则返回该页面
					response.sendRedirect(getServletContext().getContextPath()+"/dormManager.action?action=preAdd");
				}else {
					//不存在，需要保存
					String password = request.getParameter("password");
					String name = request.getParameter("name");
					String tel = request.getParameter("tel");
					String sex = request.getParameter("sex");
					String dormBuildId = request.getParameter("dormBuildId");
					
					User manager = new User();
					manager.setStu_code(jobCode);
					manager.setPassword(password);
					manager.setName(name);
					manager.setTel(tel);
					manager.setSex(Integer.parseInt(sex));
					manager.setDormBuildId(Integer.parseInt(dormBuildId));
					
					userService.saveManager(manager);
					response.sendRedirect(getServletContext().getContextPath()+"/dormManager.action?action=list");
				}
			}
		}else if (action != null && action.equals("preUpdate")) {
			String id = request.getParameter("dormManagerId");
			//通过学号查找学生
			User dormManager = userService.findById(Integer.parseInt(id));
			
			request.setAttribute("dormManager", dormManager);
			request.setAttribute("builds", builds);
			//跳转到修改页面
			request.setAttribute("mainRight", "/WEB-INF/jsp/dormManagerAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
			
		}else if (action != null && action.equals("delete")) {
			String id = request.getParameter("dormManagerId");
			String dormBuildId = request.getParameter("dormBuildId");
			//通过学号查找学生
			User userDelete = userService.findById(Integer.parseInt(id));
			userService.deleteStudent(userDelete);
			request.setAttribute("dormBuildId", dormBuildId);
			//跳转到修改页面
			response.sendRedirect(getServletContext().getContextPath()+"/dormManager.action?action=list");
			
		}
		
	}

}
