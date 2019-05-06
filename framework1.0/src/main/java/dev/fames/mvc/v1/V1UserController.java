package dev.fames.mvc.v1;

import dev.fames.mvc.annotation.Autowired;
import dev.fames.mvc.annotation.Controller;
import dev.fames.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class V1UserController {

    @Autowired("userService")
    private V1UserService userService;

    @RequestMapping("/getMsg")
    public void getMsg(HttpServletRequest request, HttpServletResponse resp, String userName) {
        String name = request.getParameter("name");
        System.out.printf("userName: /%s, name: %s ", userName, name);
        int age = userService.getAge(name);
        try {
            //  中文乱码处理
            resp.setContentType("text/html; charset=utf-8");
            resp.getWriter().write(name + "的年龄是： " + age);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
