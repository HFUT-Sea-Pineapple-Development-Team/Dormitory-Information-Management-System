package com.xs.dormTest.servlet;

import com.xs.dormTest.bean.Notice;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.service.NoticeService;
import com.xs.dormTest.service.NoticeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "NoticeStuServlet", value = "/stuNotice.action")
public class NoticeStuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String id = "" ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeStuServlet() {
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
        User user = (User) request.getSession().getAttribute("session_user");
        NoticeService noticeService = new NoticeServiceImpl() ;

        if (action != null && action.equals("list")) {
            //学生只有查看通知和确认收到的权利
            String time = request.getParameter("time") ;
            if (time == null || time.equals("")) {
                //连接表查询，根据学工号将user表和notice表连接，同时根据build_id查询公告即可
                List<Notice> notices = noticeService.findByBuildId(user.getDormBuildId()) ;
                request.setAttribute("notices" , notices);
            }else{
                java.sql.Date date = java.sql.Date.valueOf(time) ;
                List<Notice> notices = noticeService.findByTime(date) ;
                request.setAttribute("notices" , notices);
            }

            List<Notice> noticesSelect = noticeService.findByBuildId(user.getDormBuildId()) ;
            List<Date> times = noticesSelect.stream().map(Notice::getTime).collect(Collectors.toList());
            //去掉重复的日期
            HashSet<Date> set = new HashSet<Date>(times) ;
            List<Date> dates = new ArrayList<Date>(set) ;
//            System.out.println(dates);
            Collections.reverse(dates);
            request.setAttribute("dates" , dates);

            request.setAttribute("mainRight" , "notice_student.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }else if ((action != null && action.equals("detail"))){
            //查看每一条通知的具体内容
            id = request.getParameter("id") ;
            Notice notice = noticeService.findById(Integer.parseInt(id)) ;

            request.setAttribute("notice" , notice);

            request.setAttribute("mainRight" , "notice_content.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }else if ((action != null && action.equals("check"))){
            //学生点击一次确认收到，次数+1
            id = request.getParameter("id") ;
            noticeService.updateCounts(Integer.parseInt(id)) ;

            List<Notice> notices = noticeService.findByBuildId(user.getDormBuildId()) ;
            request.setAttribute("notices" , notices);

            request.setAttribute("mainRight" , "notice_student.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }
    }
}
