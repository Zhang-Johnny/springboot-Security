package org.sang.controller;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxGroupUser;
import org.sang.bean.XhxUser;
import org.sang.common.SendMobile;
import org.sang.common.Utils;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.mapper.XhxUserMapper;
import org.sang.service.XhxGroupUserService;
import org.sang.service.XhxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ResetPassController {
    @Autowired
    XhxUserMapper ptuser;
    @Autowired
    SendMobile sendMobile;
    @Autowired
    XhxUserService userService;
    @Autowired
    XhxGroupUserService groupUserService;
    //在忘记密码页面用户填写用户名（验证用户名是否存在）和手机号（根据用户名验证手机号是否正确）
    @RequestMapping("/checkUsername")
    @ResponseBody
    public Result checkUserName(String username){

        int i = userService.checkUsername(username);
        if (i<1){
            return ResultUtil.error(ResultEnum.CHECK_ERROR);
        }
        return ResultUtil.success(ResultEnum.CHECK_SUCCESS);
    }
    //根据用户名验证手机号是否正确
    @RequestMapping("/checkBindMobile")
    @ResponseBody
    public Result checkMobile(HttpServletRequest request){
        Map map = new HashMap();
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        int i = userService.checkMobile(map);
        if (i<1){
            return ResultUtil.error(ResultEnum.CHECK_ERROR);
        }
        return ResultUtil.success(ResultEnum.CHECK_SUCCESS);
    }

    //发送验证码短信
    @RequestMapping(value="/sendVerificationCode")
    @ResponseBody
    public Result sendVerificationCode(HttpServletRequest request){
        boolean result = sendMobile.sendcode(request);//发送短信,返回值为result
        if(result){
            return ResultUtil.success(ResultEnum.SUCCESS);
        }
        return ResultUtil.error(ResultEnum.NOT_FIND);
    }

    //获取用户输入的验证码与session中的验证码进行匹配验证
    @RequestMapping("/checkVerificationCode")
    @ResponseBody
    public Result checkVerificationCode(HttpServletRequest request, String verificationCodeFront){

        HttpSession session = request.getSession();
        String verificationCode = (String) session.getAttribute("verificationCode");//session中的验证码
        //verificationCode="11";//测试用
        if(verificationCode!=null || verificationCode!=""){
            if (verificationCode.equals(verificationCodeFront)){
                return ResultUtil.success(ResultEnum.CHECK_SUCCESS);//验证成功
            }else {
                return ResultUtil.error(ResultEnum.CHECK_ERROR);//提示用户验证码不正确或验证码超时
            }
        }
            return ResultUtil.error(ResultEnum.CHECK_ERROR);//提示用户请输入验证码
    }

    //用户点击重置密码按钮，提交数据封装为一个XhxUser xhxUser对象，更新数据库
    @RequestMapping("/resetXhxUserPassword")
    @ResponseBody
    public Result resetPassword(XhxUser xhxUser) throws IOException {
        //加密用户的密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(xhxUser.getPassword());
        xhxUser.setPassword(encode);
        //更新user表中的密码
        int i = userService.updateXhxUserPassword(xhxUser);
        //response.sendRedirect("URL");//重定向到登陆页面redirect:/order/confirm
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);//修改失败
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);//成功
    }

    //用户点击重置密码按钮，提交数据封装为一个XhxGroupUser xhxGroupUser对象，更新数据库
    @RequestMapping("/resetXhxGroupUserPassword")
    @ResponseBody
    public Result resetGroupPassword(XhxGroupUser xhxGroupUser) throws IOException {
        //加密用户的密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(xhxGroupUser.getPassword());
        xhxGroupUser.setPassword(encode);
        //更新xhxGroupUser表中的密码
        int i = groupUserService.updateXhxUserPassword(xhxGroupUser);
        //response.sendRedirect("URL");//重定向到登陆页面redirect:/order/confirm
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);//修改失败
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);//成功
    }

    //在忘记密码页面用户填写用户名（验证用户名是否存在）
    @RequestMapping("/checkGroupUsername")
    @ResponseBody
    public Result checkGroupUserName(String username){

        int i = groupUserService.checkGroupUsername(username);
        if (i<1){
            return ResultUtil.error(ResultEnum.CHECK_ERROR);
        }
        return ResultUtil.success(ResultEnum.CHECK_SUCCESS);
    }
    //根据用户名验证手机号是否正确
    @RequestMapping("/checkGroupBindMobile")
    @ResponseBody
    public Result checkGroupMobile(HttpServletRequest request){
        Map map = new HashMap();
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        int i = groupUserService.checkMobile(map);
        System.out.println(i);
        if (i<1){
            return ResultUtil.error(ResultEnum.CHECK_ERROR);
        }
        return ResultUtil.success(ResultEnum.CHECK_SUCCESS);
    }
}
