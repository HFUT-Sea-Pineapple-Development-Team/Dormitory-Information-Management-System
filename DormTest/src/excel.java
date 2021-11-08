import com.xs.dormTest.bean.User;
import com.xs.dormTest.service.UserService;
import com.xs.dormTest.service.UserServiceImpl;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class excel {
    public static void main(String[] args) throws IOException, BiffException {
        File xlsFile = new File("F:\\java_workspace\\Dormitory-Information-Management-System\\DormTest\\out\\artifacts\\DormTest_Web_exploded\\upload\\0-学生信息表.xls");
        // 工作表
        Workbook wb = null;
        try {
            wb = Workbook.getWorkbook(xlsFile);
        } catch (BiffException e) {
            e.printStackTrace();
        }
        // 表个数。
        int numberOfSheets = wb.getNumberOfSheets();
        List<User> stus = new ArrayList<>() ;
        // 遍历表。
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = wb.getSheet(i);
            // 读数据。
            for (int row = 1; row < sheet.getRows(); row++) {
                User user = new User() ;
                user.setStu_code(sheet.getCell(0 ,row).getContents());
                user.setName(sheet.getCell(1 ,row).getContents());
                user.setSex(Integer.parseInt(sheet.getCell(2 ,row).getContents().trim()));
                user.setTel(sheet.getCell(3 ,row).getContents());
                user.setDormBuildId(Integer.parseInt(sheet.getCell(4 ,row).getContents()));
                user.setRoomId(Integer.parseInt(sheet.getCell(5 ,row).getContents()));
                user.setMajor(sheet.getCell(6 ,row).getContents());
                user.setClassName(Integer.parseInt(sheet.getCell(7 ,row).getContents()));

                stus.add(user) ;
            }
        }
        System.out.println(stus);
        //获得数据后插入数据库 利用存储过程
        UserService userService = new UserServiceImpl();
        try {
            userService.insertMuch(stus) ;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("导入成功");
        }

    }
}
