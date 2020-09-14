package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entiy.PageBean;
import entiy.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2020-07-20
 * Time: 18:58
 */
@WebServlet("/findByPageServlet")
public class FindByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");

        //获取前端发送给后端的所有数据
        //这个map是不能直接进行修改
        Map<String, String[]> parMap =  req.getParameterMap();

        Map<String,String[]> map = new HashMap<>(parMap);
        map.remove("currentPage");
        map.remove("rows");

        System.out.println("=======================");
        for (Map.Entry<String,String[]> entry  : parMap.entrySet()) {
            System.out.println("key: "+ entry.getKey()+" value "+Arrays.toString(entry.getValue()));
        }

        int curtPage = Integer.parseInt(currentPage);
        int rowsInt = Integer.parseInt(rows);
        UserService userService = new UserService();
        PageBean<User> pageBean =  userService.findAllByPage(curtPage,rowsInt,map);

        //把登录成功的map返回给前端。json      : 便于前端进行处理。
        ObjectMapper objectMapper = new ObjectMapper();
        //就是将returnMap，转换为json字符串
        objectMapper.writeValue(resp.getWriter(),pageBean);

    }
}
