package com.xs.dormTest.servlet;

import com.xs.dormTest.bean.Notice;
import com.xs.dormTest.bean.User;
import com.xs.dormTest.service.NoticeService;
import com.xs.dormTest.service.NoticeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.dnd.DropTarget;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "NoticeServlet", value = "/notice.action")
public class NoticeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String id = "" ;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
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

        if (action != null && action.equals("list")){
            String time = request.getParameter("time") ;
            //未进行搜索，展示所有通知信息
            if (time == null || time.equals("")) {
                //展示通知公告的信息，从视图中查看
                List<Notice> notices = noticeService.findByStuId(user.getStu_code()) ;
                request.setAttribute("notices" ,notices);
            }else {
                //搜索某个日期的通知公告
                java.sql.Date date = java.sql.Date.valueOf(time) ;
                System.out.println(date);
                List<Notice> notices = noticeService.findByDate(date) ;
                request.setAttribute("notices" ,notices);
            }
            List<Notice> noticesSelect = noticeService.findByStuId(user.getStu_code()) ;
            List<Date> times = noticesSelect.stream().map(Notice::getTime).collect(Collectors.toList());
            //去掉重复的日期
            HashSet<Date> set = new HashSet<Date>(times) ;
            List<Date> dates = new ArrayList<Date>(set) ;
            Collections.reverse(dates);
            request.setAttribute("dates" ,dates);
            request.setAttribute("mainRight" , "notice_keeper.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }else if (action != null && action.equals("preAdd")) {
            //发布新的公告 加入数据库
            request.setAttribute("mainRight" , "notice_add.jsp");

            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }else if (action != null && action.equals("save")) {
            //发布新的公告 加入数据库
            String title = request.getParameter("title") ;
            String tel = request.getParameter("tel") ;
            String content = request.getParameter("content") ;
            Date date = new Date() ;
            java.sql.Date time = new java.sql.Date(date.getTime()) ;

            noticeService.insertNotice(title, tel , content , time, user.getStu_code()) ;

            List<Notice> notices = noticeService.findByStuId(user.getStu_code()) ;
            request.setAttribute("notices" ,notices);

            request.setAttribute("mainRight" , "notice_keeper.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }else if (action != null && action.equals("Check")){
            //查看每一则通知的具体内容 主要三个字段 ttle ,tel ,content
            id = request.getParameter("id") ;

            Notice notice = noticeService.findById(Integer.parseInt(id)) ;
            request.setAttribute("notice" , notice);
            System.out.println(notice);
            request.setAttribute("mainRight" , "notice_content.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }else if (action != null && action.equals("delete")){
            //删除通知
            id = request.getParameter("id") ;
            noticeService.deleteNotice(Integer.parseInt(id)) ;

            List<Notice> notices = noticeService.findByStuId(user.getStu_code()) ;
            request.setAttribute("notices" ,notices);

            request.setAttribute("mainRight" , "notice_keeper.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }else if  (action != null && action.equals("search")){
            //根据点击日期查询通知公告

        }
    }
}
