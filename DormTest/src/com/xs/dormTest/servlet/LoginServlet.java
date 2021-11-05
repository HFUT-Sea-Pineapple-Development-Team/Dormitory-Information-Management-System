package com.xs.dormTest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xs.dormTest.bean.User;
import com.xs.dormTest.service.UserService;
import com.xs.dormTest.service.UserServiceImpl;
import com.xs.dormTest.util.CookieUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=登录=");
		
		//get用户名和密码以及用户类型
		String stu_code = request.getParameter("stu_code");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		System.out.println(stu_code+" "+password + remember);
		
		UserService userService = new UserServiceImpl();
		//去查询用户输入的登录名和密码是否正确
		User user = userService.findByNameAndPass(stu_code,password);
//		System.out.println(user);
		
		if (user == null) {
			//输入的学号或密码错误
			request.setAttribute("error", "您输入的学号和密码错误");
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}else {
			//输入的学号和密码正确，跳转到主页面
			//30分钟内有效
			request.getSession().setAttribute("session_user", user);
			
			if (remember != null && remember.equals("remember-me")) {
				//记住密码一周
				CookieUtil.addCookie("cookie_name_pass",7*24*60*60,request,response, stu_code, password);
			}

			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
