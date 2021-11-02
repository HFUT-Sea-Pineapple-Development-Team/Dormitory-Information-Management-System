package com.xs.dormTest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import com.sun.accessibility.internal.resources.accessibility;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.service.DormBuildService;
import com.xs.dormTest.service.DormBuildServiceImpl;

/**
 * Servlet implementation class DormBuildServlet
 */
@WebServlet("/dormBuild.action")
public class DormBuildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DormBuildServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action") ;
		//通过request.getParameter("id")
		String id = request.getParameter("id") ;
		
		DormBuildService dormBuildService = new DormBuildServiceImpl( ) ;
		//根据楼号查询宿舍楼信息
		if (action != null && action.equals("list")) {
			List<DormBuild> builds = new ArrayList<DormBuild>() ;
			if (id == null || id.equals("")) {
				//查询所有宿舍楼信息并添加至dormList
				builds = dormBuildService.find() ;
				
			} else if (id != null && !id.equals("")) {
				//点击右侧搜索框进行搜索某个宿舍楼信息
				DormBuild build = dormBuildService.findById(Integer.parseInt(id)) ;
				builds.add(build) ;
			}
			//查询所有的宿舍楼，在select中遍历
			List<DormBuild> buildSelects = dormBuildService.find() ;
			
			request.setAttribute("buildSelects", buildSelects);
			request.setAttribute("id", id);
			
			
			request.setAttribute("builds", builds);
			request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response); 
		}
		else if (action != null && action.equals("preAdd")) {
			//添加宿舍楼信息
			request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildAdd.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}
		else if (action != null && action.equals("save")) {
			//保存数据
			String name = request.getParameter("dormBuildName") ;
			String detail = request.getParameter("detail") ;
			
			//宿舍楼名称不能重复，查询输入的宿舍楼是否已经存在
			DormBuild dormBuild = dormBuildService.findByName(name) ;
			if (id != null && !id.equals("")) {
				//更新宿舍楼信息
				if (dormBuild != null && dormBuild.getId()!=Integer.parseInt(id)) {
					//当前宿舍楼已存在
					//提示信息
					request.setAttribute("error", "当前宿舍楼已存在，请重新输入！");
					//根据宿舍楼id，查询宿舍楼
					DormBuild build = dormBuildService.findById(Integer.parseInt(id) ) ;
					//保存宿舍楼信息到前端页面
					request.setAttribute("build", build);
					request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildAdd.jsp");
					request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
				}else {
					dormBuild = dormBuildService.findById(Integer.parseInt(id)) ;
					
					dormBuild.setDormBuildName(name);
					dormBuild.setDetail(detail);
					
					dormBuildService.update(dormBuild) ;
					//更新完成，跑到宿舍楼管理页表也查询所有宿舍楼
					List<DormBuild> builds = dormBuildService.find() ;
					request.setAttribute("builds", builds);
					request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildList.jsp");
					request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response); 
					
				}
			}
			else {
				//保存(就是添加新的宿舍楼信息)
				if (dormBuild != null) {
					//当前宿舍楼已存在
					//提示信息
					request.setAttribute("error", "当前宿舍楼已存在，请重新输入！");
					request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildAdd.jsp");
					request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
				}
				else {
					//不存在则保存用户输入的信息到数据库
					DormBuild build = new DormBuild() ;
					build.setDormBuildName(name);
					build.setDetail(detail);
					dormBuildService.save(build) ;
					request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildList.jsp");
					request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
				}
			}
			
		}else if (action != null && action.equals("preUpdate")) {
			
			//根据宿舍楼id，查询宿舍楼
			DormBuild build = dormBuildService.findById(Integer.parseInt(id) ) ;
			//保存宿舍楼信息到前端页面
			request.setAttribute("build", build);
			//跳转到宿舍楼的修改页面 
			request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildAdd.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request, response);
		}
	}

}
