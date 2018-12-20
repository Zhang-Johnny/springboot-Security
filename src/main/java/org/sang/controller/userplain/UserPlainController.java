package org.sang.controller.userplain;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.*;
import org.sang.common.SendMobile;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userplain/user")
public class UserPlainController {
    @Autowired
    XhxUserService hrService;

    @Autowired
    XhxUserinfoService infoService;

    @Autowired
    XhxUserService userService;

    @Autowired
    XhxGroupUserService groupUserService;
    @Autowired
    ReUserRoleService userRoleService;
    @Autowired
    RoleService roleService;
    @RequestMapping("/findById/{Id}")
    public XhxUser getfindById(@PathVariable Long Id) {
        return hrService.getXhxUserById(Id);
    }

    @RequestMapping(value = "/{hrId}", method = RequestMethod.DELETE)
    public RespBean deleteHr(@PathVariable Long hrId) {
        if (hrService.deleteXhxUser(hrId) == 1) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/updateUser")
    public RespBean updateHr(XhxUser hr) {
        if (hrService.updateXhxUser(hr) == 1) {
            return new RespBean("success", "更新成功!");
        }
        return new RespBean("error", "更新失败!");
    }

    @RequestMapping(value = "/roles", method = RequestMethod.PUT)
    public RespBean updateHrRoles(Long hrId, Long[] rids) {
        if (hrService.updateXhxUserRoles(hrId, rids) == rids.length) {
            return new RespBean("success", "更新成功!");
        }
        return new RespBean("error", "更新失败!");
    }

    @RequestMapping("/{keywords}")
    public List<XhxUser> getHrsByKeywords(@PathVariable(required = false) String keywords) {
        List<XhxUser> hrs = hrService.getXhxUserByKeywords(keywords);
        return hrs;
    }

    @RequestMapping("/infoByUserId/{userId}")
    public XhxUserinfo getInfoByUserId(@PathVariable(required = false) Long userId) {
        XhxUserinfo info = infoService.findByUserId(userId);
        return info;
    }
    @RequestMapping("/userInfoByUserId")
    public @ResponseBody Result getUserInfoByUserId(HttpServletRequest request) {
        Long userId=Utils.toLong(request.getParameter("userId"));
        XhxUserinfo info = infoService.findByUserId(userId);
        return ResultUtil.success(info);
    }

    @RequestMapping(value = "/hr/reg", method = RequestMethod.POST)
    public RespBean hrReg(String username, String password) {
        XhxUser user=new XhxUser();
        user.setUsername(username);
        user.setPassword(password);
        int i = hrService.XhxUserReg(user);
        if (i == 1) {
            return new RespBean("success", "注册成功!");
        } else if (i == -1) {
            return new RespBean("error", "用户名重复，注册失败!");
        }
        return new RespBean("error", "注册失败!");
    }

    @RequestMapping("/findAllUser")
    public @ResponseBody Result findAllUser(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        int pageSize = 10;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if(StringUtils.isNotBlank(Utils.toString(map.get("pageNo")))){
            pageNo=Integer.parseInt(Utils.toString(map.get("pageNo")));
        }
        int totalCount = hrService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxUser> list = hrService.findAll(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    @RequestMapping("/deleteUser/{id}")
    public @ResponseBody Result deleteUser(@PathVariable("id") Long id){
        int i = 0;
        if (id!=null){
            i = hrService.deleteXhxUser(id);
        }
        if (i<1){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }

    @RequestMapping("/findSonUserByParam")
    public @ResponseBody Result findSonUserByParam(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        int pageSize = 10;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if (!map.containsKey("groupId")){
            return ResultUtil.error(ResultEnum.LACK_PARAMETER);
        }
        if(map.containsKey("pageNo")){
            pageNo= Utils.toInteger(map.get("pageNo"));
        }

        int totalCount = hrService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxUser> list = hrService.findAll(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }


    @RequestMapping("/updateUserEnabled")
    public @ResponseBody Result updateUserEnabled(Long id,int enabled){
        boolean flag = false;
        if (enabled == 1){
            flag = true;
        }
        XhxUser xhxUser = new XhxUser();
        xhxUser.setId(id);
        xhxUser.setEnabled(flag);
        int i = hrService.updateXhxUser(xhxUser);
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }


    //修改user密码
    @RequestMapping("/updatepassword")
    public @ResponseBody Result updatepassword(HttpServletRequest request) {
        XhxUser user = new XhxUser();
        String data = request.getParameter("data");
        Map map = new HashMap();
        if (StringUtils.isNotBlank(data)) {
            map = JSON.parseObject(data, map.getClass());
        }
        //先判断输入的旧密码是否正确
        Long id = Utils.toLong(map.get("id"));
        String newPassword = Utils.toString(map.get("newPassword"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(newPassword);
        String oldPassword = Utils.toString(map.get("oldPassword"));
        user.setId(id);
        user.setPassword(encode);
        String password = userService.selectpassword(id);
        if (password != null && password != "" && newPassword != null && newPassword != "") {
            if (encoder.matches(oldPassword,password)) {
                if (encoder.matches(newPassword,password)) {//新密码和旧密码不能相同
                    return ResultUtil.error(ResultEnum.UPDATE_ERROR);
                }
                /*HttpSession session = request.getSession();
                String verificationCode = (String) session.getAttribute("verificationCode");//session中的验证码
                String code = request.getParameter("verificationCode");//前台输入的验证码
                if (verificationCode.equals(code)){*/
                    //更新数据库中的密码
                    int i = userService.updateXhxUser(user);
                    if (i == 1) {
                        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
                    }
                    return ResultUtil.error(ResultEnum.UPDATE_ERROR);
            }
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.error(ResultEnum.UPDATE_ERROR);
    }
    @RequestMapping("/updateMobilePhone")
    public @ResponseBody Result updateMobilePhone(HttpServletRequest request){
        int i = 0;
        Map map = new HashMap();
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        String userType = Utils.toString(map.get("userType"));
        if ("2".equalsIgnoreCase(userType)){
            XhxUser xhxUser = new XhxUser();
            xhxUser.setBindMobile(Utils.toString(map.get("bindPhone")));
            xhxUser.setId(Utils.toLong(map.get("userId")));
            i = hrService.updateXhxUser(xhxUser);
        }else if("1".equalsIgnoreCase(userType)){
            XhxGroupUser xhxGroupUser = new XhxGroupUser();
            xhxGroupUser.setBindMobile(Utils.toString(map.get("bindPhone")));
            xhxGroupUser.setId(Utils.toLong(map.get("userId")));
            i = groupUserService.update(xhxGroupUser);
        }else {
            return ResultUtil.error(ResultEnum.LACK_PARAMETER);
        }
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }
    //根据parentId查找用户角色role的集合
    @RequestMapping("/findSonUserRoleByParent")
    public @ResponseBody Result findSonUserRoleByParent(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        int pageSize = 10;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if (!map.containsKey("groupId")){
            return ResultUtil.error(ResultEnum.LACK_PARAMETER);
        }
        if(map.containsKey("pageNo")){
            pageNo= Utils.toInteger(map.get("pageNo"));
        }
        map.put("userType",2);
        int totalCount = hrService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxUser> list = hrService.findAllRoles(map);
        System.out.println(list);

        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    //修改user邮箱
    @RequestMapping("/updateemail")
    public @ResponseBody Result updateemail(HttpServletRequest request) {
        XhxUserinfo xhxUserinfo = new XhxUserinfo();
        String data = request.getParameter("data");
        Map map = new HashMap();
        if (StringUtils.isNotBlank(data)) {
            map = JSON.parseObject(data, map.getClass());
        }
        Long userid = Utils.toLong(map.get("id"));
        xhxUserinfo.setUserId(userid);
        String email = Utils.toString(map.get("email"));
        xhxUserinfo.setEmail(email);
        String bindMobile = Utils.toString(map.get("bindMobile"));
        String oldEmail = infoService.selectEmail(userid);
        if(email.equals(oldEmail)){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }

        /*HttpSession session = request.getSession();
        String verificationCode = (String) session.getAttribute("verificationCode"+bindMobile);//session中的验证码
        String code = request.getParameter("verificationCode");//前台输入的验证码
        if (verificationCode.equals(code)){*/
            //更新数据库中的邮箱
        int i = infoService.updateUserinfo(xhxUserinfo);
        if (i == 1) {
            return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
        }
        return ResultUtil.error(ResultEnum.UPDATE_ERROR);

    }

}
