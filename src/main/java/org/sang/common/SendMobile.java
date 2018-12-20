package org.sang.common;

import org.sang.bean.Result;
import org.sang.bean.XhxUser;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class SendMobile {
    @Autowired
    XhxUserService userService;
    //发送短信的方法，返回true/false“发送成功”或“发送失败”
    public Boolean sendSM(String bindMobile,String mes) {
            return true;
        }

    //生成验证码并发送
    public boolean sendcode(HttpServletRequest request){
        Map map = new HashMap();
        HttpSession session = request.getSession();
        String bindMobile = request.getParameter("bindMobile");//获取前端传送过来的电话号码
        Long id = Utils.toLong(request.getParameter("id"));
        map.put("id",id);
        map.put("bindMobile",bindMobile);
        if (bindMobile != null && bindMobile != "") {
            //验证手机号是否正确

            int i = userService.checkMobile(map);
            if (i==0){
                return false;
            }
            int Random = (int) ((Math.random() * 9 + 1) * 100000);//随机生成的6位数（验证码）
            String mes = Random + "";//验证码
            boolean result = sendSM(bindMobile,mes);
            if(result){
                //把验证码与电话号码存入session中，并设置120秒有效期限
                session.setAttribute("verificationCode"+bindMobile, mes);
                session.setMaxInactiveInterval(2*60);
                return true;
            }
            return false;
        }
        return false;
    }

}
