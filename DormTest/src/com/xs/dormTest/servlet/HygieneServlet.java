package com.xs.dormTest.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xs.dormTest.bean.Hygiene;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.service.HygieneService;
import com.xs.dormTest.service.HygieneServiceImpl;
import com.xs.dormTest.service.UserService;
import com.xs.dormTest.service.UserServiceImpl;
import com.xs.dormTest.util.PageModel;

/**
 * Servlet implementation class HygieneServlet
 */
@WebServlet("/hygiene.action")
public class HygieneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HygieneServlet() {
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
		//获取用户的宿舍楼id
		Integer buildId = user.getDormBuildId(); 
		Integer roleId = user.getRoleId();
		HygieneService hygieneService = new HygieneServiceImpl();
		
		if (action != null && action.equals("list")) {			
			//宿舍管理员的情况，可以对卫生状况进行打分
			if (roleId == 1) {
				//获取搜索框内的值
				String keyword = request.getParameter("keyword");
				//获取所管辖的宿舍楼内的所有宿舍情况
				//根据宿管的宿舍楼号获取宿舍情况
				List<Hygiene> hygienes = hygieneService.findRoomHygiene(buildId,keyword);
				request.setAttribute("hygienes", hygienes);
				
			}
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/hygieneList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("preUpdate")) {
			//录入卫生分数
			//根据楼号和寝室号获取应该对哪个寝室的卫生分数进行录入
			//获取寝室id
			String roomId = request.getParameter("roomId");
			
			Hygiene hygiene = hygieneService.findByRoomId(buildId,Integer.parseInt(roomId));
			request.setAttribute("hygiene", hygiene);
				
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/hygieneAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}else if (action != null && action.equals("save")) {
			//获取录入的分数信息
			String room_id = request.getParameter("room_id");
			String grade_18 = request.getParameter("grade_18");
			String grade_19 = request.getParameter("grade_19");
			String grade_20 = request.getParameter("grade_20");
			String grade_21 = request.getParameter("grade_21");
			
			//找到要修改的寝室卫生信息
			Hygiene hygieneUpdate = hygieneService.findByRoomId(buildId,Integer.parseInt(room_id));
			
			//进行数据更新
			hygieneUpdate.setGrade_18(Float.parseFloat(grade_18));
			hygieneUpdate.setGrade_19(Float.parseFloat(grade_19));
			hygieneUpdate.setGrade_20(Float.parseFloat(grade_20));
			hygieneUpdate.setGrade_21(Float.parseFloat(grade_21));
			
			//进行数据保存
			hygieneService.saveHygiene(hygieneUpdate);
			
				
			response.sendRedirect(getServletContext().getContextPath()+"/hygiene.action?action=list");
		}
	}

}
