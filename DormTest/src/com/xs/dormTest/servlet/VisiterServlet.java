package com.xs.dormTest.servlet;

import com.xs.dormTest.bean.User;
import com.xs.dormTest.bean.Visiter;
import com.xs.dormTest.service.VisiterService;
import com.xs.dormTest.service.VisiterServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "VisiterServlet", value = "/visiter.action")
public class VisiterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String id = "" ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisiterServlet() {
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
        List<Visiter> visiters = new ArrayList<>() ;
        VisiterService visiterService = new VisiterServiceImpl() ;

        if (action != null && action.equals("list") ) {
            //查询所有的访客记录
            //学生只有查看通知和确认收到的权利
            String time = request.getParameter("time") ;
            if (time == null || time.equals("")) {
                //连接表查询，根据学工号将user表和notice表连接，同时根据build_id查询公告即可
                visiters = visiterService.findAll() ;

                //去掉小数点，用showTime代替time输出
                for (int i = 0; i < visiters.size(); i++) {
                    String curr = String.valueOf(visiters.get(i).getTime()) ;
                    String currTime = curr.substring(0 , curr.indexOf(".")) ;
                    visiters.get(i).setShowTime(currTime);
                }

                request.setAttribute("visiters" , visiters);
            }else{
//                String curr = time;
                String date = time.substring(0 , 10) ;
                System.out.println(date);
                visiters = visiterService.findByTime(date) ;
                //去掉小数点，用showTime代替time输出
                for (int i = 0; i < visiters.size(); i++) {
                    String curr = String.valueOf(visiters.get(i).getTime()) ;
                    String currTime = curr.substring(0 , curr.indexOf(".")) ;
                    visiters.get(i).setShowTime(currTime);
                }
                request.setAttribute("visiters" , visiters);
            }

            List<Visiter> selects = visiterService.findAll() ;
            System.out.println(selects);
            List<Timestamp> times = selects.stream().map(Visiter::getTime).collect(Collectors.toList());
            List<String> times_new = new ArrayList<>() ;
            for (int i = 0; i < times.size(); i++) {
                String curr = String.valueOf(times.get(i));
                String currTime = curr.substring(0 , 10) ;
                times_new.add(currTime) ;
            }
            //去掉重复的日期
            HashSet<String> set = new HashSet<String>(times_new) ;
            List<String> dates = new ArrayList<String>(set) ;
            System.out.println(dates);
            //List<String> Dates = new ArrayList<>() ;

            //Collections.reverse(dates);

            request.setAttribute("dates" , dates);
            request.setAttribute("mainRight" , "visiter.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }else if (action != null && action.equals("preAdd")) {
            //登记访客信息
            request.setAttribute("mainRight" , "visiter_add.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }else if (action != null && action.equals("save")){
            //提交访客数据到数据库中
            String name = request.getParameter("name") ;
            String tel = request.getParameter("tel") ;
            String reason = request.getParameter("reason") ;
            Visiter visiter = new Visiter() ;
            visiter.setTel(tel);
            visiter.setName(name);
            visiter.setReason(reason);

            Date dt = new Date() ;
            java.sql.Timestamp time = new java.sql.Timestamp(dt.getTime()) ;
            System.out.println(time);
            visiter.setTime(time);

            visiterService.addVisiter(visiter) ;

            visiters = visiterService.findAll() ;
            //去掉小数点，用showTime代替time输出
            for (int i = 0; i < visiters.size(); i++) {
                String curr = String.valueOf(visiters.get(i).getTime()) ;
                String currTime = curr.substring(0 , curr.indexOf(".")) ;
                visiters.get(i).setShowTime(currTime);
            }

            request.setAttribute("visiters" , visiters);
            List<Visiter> selects = visiterService.findAll() ;
            System.out.println(selects);
            List<Timestamp> times = selects.stream().map(Visiter::getTime).collect(Collectors.toList());
            List<String> times_new = new ArrayList<>() ;
            for (int i = 0; i < times.size(); i++) {
                String curr = String.valueOf(times.get(i));
                String currTime = curr.substring(0 , 10) ;
                times_new.add(currTime) ;
            }
            //去掉重复的日期
            HashSet<String> set = new HashSet<String>(times_new) ;
            List<String> dates = new ArrayList<String>(set) ;
            System.out.println(dates);
            //List<String> Dates = new ArrayList<>() ;

            //Collections.reverse(dates);

            request.setAttribute("dates" , dates);
            request.setAttribute("mainRight" , "visiter.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }else if (action != null && action.equals("delete")){
            //删除访客信息
            id = request.getParameter("id") ;

            visiterService.deleteVisiter(Integer.parseInt(id)) ;

            visiters = visiterService.findAll() ;
            //去掉小数点，用showTime代替time输出
            for (int i = 0; i < visiters.size(); i++) {
                String curr = String.valueOf(visiters.get(i).getTime()) ;
                String currTime = curr.substring(0 , curr.indexOf(".")) ;
                visiters.get(i).setShowTime(currTime);
            }

            request.setAttribute("visiters" , visiters);
            List<Visiter> selects = visiterService.findAll() ;
            System.out.println(selects);
            List<Timestamp> times = selects.stream().map(Visiter::getTime).collect(Collectors.toList());
            List<String> times_new = new ArrayList<>() ;
            for (int i = 0; i < times.size(); i++) {
                String curr = String.valueOf(times.get(i));
                String currTime = curr.substring(0 , 10) ;
                times_new.add(currTime) ;
            }
            //去掉重复的日期
            HashSet<String> set = new HashSet<String>(times_new) ;
            List<String> dates = new ArrayList<String>(set) ;
            System.out.println(dates);
            //List<String> Dates = new ArrayList<>() ;

            //Collections.reverse(dates);

            request.setAttribute("dates" , dates);
            request.setAttribute("mainRight" , "visiter.jsp");
            request.getRequestDispatcher("/WEB-INF/jsp/mainAdmin.jsp").forward(request , response);
        }
    }
}
