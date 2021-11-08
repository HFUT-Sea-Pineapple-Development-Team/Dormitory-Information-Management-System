package com.xs.dormTest.servlet;

import com.xs.dormTest.bean.DormBuild;
import com.xs.dormTest.bean.RepairInfo;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "RepairInfoServlet", value = "/repair.action")
public class RepairInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String id = "";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepairInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action") ;
        RepairService repairService = new RepairServiceImpl() ;
        User user = (User) request.getSession().getAttribute("session_user");
         if (action != null && action.equals("list")) {
            //寝室报修维修情况查询
             List<RepairInfo> repairs = repairService.findById(user.getDormBuildId(), user.getRoomId()) ;
             request.setAttribute("repairs" , repairs);

            request.setAttribute("mainRight" , "repairList.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        } else if (action != null && action.equals("preAdd")) {
            //跳转到申请维修页面，相当于添加报修记录
             request.setAttribute("mainRight" , "repairAdd.jsp");
             request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }
        else if (action != null && action.equals("save")){
            //保存数据。即保存提交用户输入的报修原因和时间等到数据库
             RepairInfo repairInfo = new RepairInfo() ;

             String name = request.getParameter("name") ;
             String stu_id = user.getStu_code();
             String tel = request.getParameter("tel" ) ;
             int build_id = user.getDormBuildId();  ;
             int room_id = user.getRoomId(); ;
             String reason = request.getParameter("reason") ;
             Date date = new Date() ;
             java.sql.Date report_time = new java.sql.Date(date.getTime()) ;

             repairInfo.setReason(reason);
             repairInfo.setName(name);
             repairInfo.setRoom_id(room_id);
             repairInfo.setBuild_id(build_id);
             repairInfo.setStu_id(stu_id);
             repairInfo.setTel(tel);
             repairInfo.setReport_time(report_time);

             //插入数据库
             System.out.println(repairInfo);
             RepairService repairService1 = new RepairServiceImpl() ;
             repairService1.addRepair(repairInfo);

             List<RepairInfo> repairs = repairService.findById(user.getDormBuildId(), user.getRoomId()) ;
             request.setAttribute("repairs" , repairs);
             request.setAttribute("mainRight" , "repairList.jsp");
             request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);

        }
        else if (action != null && action.equals("preUpdate")) {
             //提出意见按钮跳转
             id = request.getParameter("id") ;
             request.setAttribute("mainRight" , "repair_idea.jsp");
             request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }
        else if (action != null && action.equals("suggestion")) {
            //点击提交后将用户意见添加至数据库
             RepairInfo repairInfo = new RepairInfo() ;
             String is_satisfied = request.getParameter("is_satisfied");
             String idea = request.getParameter("idea") ;
             //System.out.println("sugg :" + id);
             repairInfo.setId(Integer.parseInt(id));
             repairInfo.setIs_satisfied(is_satisfied);
             repairInfo.setIdea(idea);
             System.out.println(repairInfo);

             RepairService repairService2 = new RepairServiceImpl();
             repairService2.updateIdea(repairInfo);

             List<RepairInfo> repairs = repairService.findById(user.getDormBuildId(), user.getRoomId()) ;
             request.setAttribute("repairs" , repairs);

             request.setAttribute("mainRight" , "repairList.jsp");
             request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }
        else if (action != null && action.equals("delete")){
             //删除记录
             id = request.getParameter("repairId") ;

             RepairService repairService3 = new RepairServiceImpl();
             repairService3.deleteRepair(Integer.parseInt(id));

             List<RepairInfo> repairs = repairService.findById(user.getDormBuildId(), user.getRoomId()) ;
             request.setAttribute("repairs" , repairs);

             request.setAttribute("mainRight" , "repairList.jsp");
             request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }
    }
}
