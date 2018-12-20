package org.sang.controller;

import org.sang.bean.RespBean;
import org.sang.bean.XhxUser;
import org.sang.service.XhxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sang on 2017/12/29.
 */
@RestController
public class RegLoginController {

    @Autowired
    XhxUserService userService;


    @RequestMapping("/login_p")
    public void login(HttpServletResponse resp) throws IOException {
       resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("{\"status\":\"error\",\"msg\":\"尚未登录，请登录!\"}");
        out.flush();
        out.close();
        //        return new RespBean("error", "尚未登录，请登录!");
    }

    @RequestMapping("/checkPhone")
    @ResponseBody
    public Integer checkPhone(String phonum){
        int  i = userService.checkPhone(phonum);
        return  i;
    }

    @RequestMapping("/regSave")
    @ResponseBody
    public RespBean save(XhxUser xhxUser){
        int i = userService.XhxUserReg(xhxUser);
        if (i == 1) {
            return new RespBean("success", "注册成功!");
        } else if (i == -1) {
            return new RespBean("error", "用户名重复，注册失败!");
        }
        return new RespBean("error", "注册失败!");
    }
}
