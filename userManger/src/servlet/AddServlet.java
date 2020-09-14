package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entiy.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2020-07-20
 * Time: 20:25
 */
@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String ageString = req.getParameter("age");
        int age = Integer.parseInt(ageString);
        String address = req.getParameter("address");
        String qq = req.getParameter("qq");
        String email = req.getParameter("email");

        User addUser = new User();
        addUser.setName(name);
        addUser.setGender(gender);
        addUser.setAge(age);
        addUser.setAddress(address);
        addUser.setQq(qq);
        addUser.setEmail(email);

        UserService userService = new UserService();
        int ret = userService.add(addUser);

        Map<String,Object> returnMap = new HashMap<>();
        if(ret == 1) {
            returnMap.put("msg",true);
        }else {
            returnMap.put("msg",false);
        }
        //把登录成功的map返回给前端。json      : 便于前端进行处理。
        ObjectMapper objectMapper = new ObjectMapper();
        //就是将returnMap，转换为json字符串
        objectMapper.writeValue(resp.getWriter(),returnMap);
    }
}
