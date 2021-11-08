package com.xs.dormTest.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(value="/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    public int index = 0;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");//更改文件编码格式
        request.setCharacterEncoding("utf-8");//更改文件编码格式
        PrintWriter out = response.getWriter();
        String filePath = "" ;
        UserService userService = new UserServiceImpl() ;

        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> IstForms = upload.parseRequest(request) ;//在Myeclipse中需将导入的包更改为import java.util.List;，默认导入的是import java.awt.List;
            for(FileItem fileItem:IstForms){
                if(fileItem.isFormField()){
                    System.out.println(fileItem.getString("utf-8"));
                }else{
                    String path = request.getServletContext().getRealPath("/upload");
                    String fileName = fileItem.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
                    fileItem.write(new File(path+"/"+ String.valueOf(index)+"-"+fileName ));//不添加“/”文件上传会失败
                    filePath = path+"/"+ String.valueOf(index)+"-"+fileName ;
                    index++ ;
                    //out.write(fileName+"文件上传成功");

                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            List<User> stus = new ArrayList<>() ;
            File xlsFile = new File(filePath);
            // 工作表
            Workbook wb = null;
            try {
                wb = Workbook.getWorkbook(xlsFile);
            } catch (BiffException e) {
                e.printStackTrace();
            }
            // 表个数。
            int numberOfSheets = wb.getNumberOfSheets();

            // 遍历表。
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = wb.getSheet(i);
                // 读数据。
                for (int row = 1; row < sheet.getRows(); row++) {
                    User userr = new User() ;

                    userr.setStu_code(sheet.getCell(0 ,row).getContents());
                    userr.setName(sheet.getCell(1 ,row).getContents());
                    userr.setSex(Integer.parseInt(sheet.getCell(2 ,row).getContents()));
                    userr.setTel(sheet.getCell(3 ,row).getContents());
                    userr.setDormBuildId(Integer.parseInt(sheet.getCell(4 ,row).getContents().trim()));
                    userr.setRoomId(Integer.parseInt(sheet.getCell(5 ,row).getContents()));
                    userr.setMajor(sheet.getCell(6 ,row).getContents());
                    userr.setClassName(Integer.parseInt(sheet.getCell(7 ,row).getContents()));

                    stus.add(userr) ;
                }
            }
            System.out.println(stus);
            //获得数据后插入数据库 利用存储过程
            try {
                userService.insertMuch(stus) ;

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                System.out.println("导入成功");
                response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
            }



        }

    }


}