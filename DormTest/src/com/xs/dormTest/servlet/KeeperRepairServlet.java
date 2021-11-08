package com.xs.dormTest.servlet;

import com.xs.dormTest.bean.RepairInfo;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.service.KeeperRepairService;
import com.xs.dormTest.service.KeeperRepairServiceImpl;
import com.xs.dormTest.service.RepairService;
import com.xs.dormTest.service.RepairServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "KeeperRepairServlet", value = "/keeperRepair.action")
public class KeeperRepairServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String id = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KeeperRepairServlet() {
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
        KeeperRepairService keeperRepairService = new KeeperRepairServiceImpl() ;
        User user = (User) request.getSession().getAttribute("session_user");
        if (action != null && action.equals("list")) {
            String room_id = request.getParameter("room_id") ;
            //System.out.println(room_id);
            List<RepairInfo> repairs = new ArrayList<>() ;
            //根据搜索框里的room_id是空还是有数字来判断是显示所有信息还是某个寝室的
            if (room_id == null || room_id.equals("")) {
                //该栋宿舍楼所有报修寝室查询
                repairs = keeperRepairService.findByBuildId(user.getDormBuildId()) ;
            }else {
                repairs = keeperRepairService.findByRoomId(Integer.parseInt(room_id));
            }

            request.setAttribute("repairs" , repairs);
            //System.out.println(repairs);

            request.setAttribute("mainRight" , "keeperRepairList.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        } else if (action != null && action.equals("save")){
            // 提交维修情况保存至数据库
            String is_finished = request.getParameter("is_finished") ;
            String repair_report = request.getParameter("repair_report") ;

            Date date = new Date() ;
            java.sql.Date repair_time = new java.sql.Date(date.getTime()) ;

            keeperRepairService.saveReport(is_finished , repair_report ,Integer.parseInt(id),repair_time) ;

            List<RepairInfo> repairs = keeperRepairService.findByBuildId(user.getDormBuildId()) ;
            request.setAttribute("repairs" , repairs);
            //System.out.println(repairs);

            request.setAttribute("mainRight" , "keeperRepairList.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }
        else if (action != null && action.equals("preUpdate")) {
            //进行维修情况登记 维修时间 维修完成情况等
            id = request.getParameter("id") ;

            request.setAttribute("mainRight" , "keeperRepairUpdate.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }
        else if (action != null && action.equals("preCheck")) {
            //此处可以查看维修报告
            id = request.getParameter("id") ;
            RepairInfo repairInfo = keeperRepairService.findById(Integer.parseInt(id)) ;

            request.setAttribute("reports" , repairInfo);
            //System.out.println(repairInfo);
            request.setAttribute("mainRight" , "repairReport.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }
        else if (action != null && action.equals("delete")){
            id = request.getParameter("id") ;

            RepairService repairService = new RepairServiceImpl() ;
            repairService.deleteRepair(Integer.parseInt(id));

            List<RepairInfo> repairs = keeperRepairService.findByBuildId(user.getDormBuildId()) ;
            request.setAttribute("repairs" , repairs);
            //System.out.println(repairs);
            request.setAttribute("mainRight" , "keeperRepairList.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);

        }else if (action != null && action.equals("selectRoom")){
            //根据宿管输入的寝室号进行搜索相关报修信息
            int room_id = Integer.parseInt(request.getParameter("room_id")) ;
            //System.out.println(room_id);
            List<RepairInfo> repairInfos = keeperRepairService.findByRoomId(room_id);

            request.setAttribute("repairs" , repairInfos);
            //System.out.println(repairInfos);

            request.setAttribute("mainRight" , "keeperRepairList.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }
    }
}
