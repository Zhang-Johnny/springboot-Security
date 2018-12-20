package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxGroupUser;
import org.sang.bean.XhxUser;
import org.sang.bean.XhxUserinfo;
import org.sang.common.SnowFlake;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxGroupUserService;
import org.sang.service.XhxUserService;
import org.sang.service.XhxUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户
 *  Created by zhangjun on 2018/9/18.
 */
@RestController
@RequestMapping("/groupUser")
public class GroupUserController {
    @Autowired
    private XhxGroupUserService groupUserService;
    @Autowired
    private XhxUserinfoService userinfoService;
    @Autowired
    private XhxUserService userService;
    @RequestMapping("/findAllGroupUser")
    public @ResponseBody Result findAllGroupUser(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        int pageSize = 10;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if(map.containsKey("pageNo")){
            pageNo=Utils.toInteger(map.get("pageNo"));
        }
        int totalCount = groupUserService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxGroupUser> list = groupUserService.findAll(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    @RequestMapping("/findGroupUserById/{id}")
    public @ResponseBody Result findGroupUserById(@PathVariable("id") Long id){
        XhxGroupUser xhxGroupUser = null;
        if (id!=null){
            xhxGroupUser = groupUserService.findById(id);
        }
        if (xhxGroupUser == null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(xhxGroupUser);
    }

    @RequestMapping("/updateGroupUser")
    public @ResponseBody Result updateGroupUser(XhxGroupUser xhxGroupUser){
        int i = groupUserService.update(xhxGroupUser);
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }

    @RequestMapping("/deleteGroupUser/{id}")
    public @ResponseBody Result deleteFreeTrlalRecord(@PathVariable("id") Long id){
        int i = 0;
        if (id!=null){
            i = groupUserService.delete(id);
        }
        if (i<1){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }

    @RequestMapping("/groupReg")
    public @ResponseBody Result groupReg(XhxGroupUser xhxGroupUser){
        int i = groupUserService.xhxUserReg(xhxGroupUser);
        if (i<1){
            return ResultUtil.error(ResultEnum.UNKONW_ERROR);
        }
        return ResultUtil.success(ResultEnum.SUCCESS);
    }
    //添加子账号:
    @RequestMapping("/insertSonUser")
    public @ResponseBody Result insertSonUser(HttpServletRequest request){
        XhxUser user = new XhxUser();
        XhxUserinfo userinfo = new XhxUserinfo();
        String data = request.getParameter("data");
        Map map = new HashMap();
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        int i;
        int j;
        user.setUsername(Utils.toString(map.get("username")));
        String enpwd=Utils.toString(map.get("password"));
        if(enpwd!=""){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(enpwd);
            user.setPassword(encode);
        }
        user.setParentId(Utils.toLong(map.get("parentId")));
        user.setBindMobile(Utils.toString(map.get("mobilePhone")));
        i = userService.XhxUserReg(user);
        if(i==1) {
            userinfo.setParentId(Utils.toLong(map.get("parentId")));
            userinfo.setUserId(user.getId());
            userinfo.setDepartment(Utils.toString(map.get("department")));
            userinfo.setNameZh(Utils.toString(map.get("nameZh")));
            userinfo.setMobilePhone(Utils.toString(map.get("mobilePhone")));
            userinfo.setEmail(Utils.toString(map.get("email")));
            j = userinfoService.insertuserinfo(userinfo);
            if (j==1){
                return ResultUtil.error(ResultEnum.ADD_ERROR);
            }
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }


    //编辑子账号:
    @RequestMapping("/updateSonUser")
    public @ResponseBody Result updateSonUser(HttpServletRequest request){
        XhxUser user = new XhxUser();
        XhxUserinfo userinfo = new XhxUserinfo();
        String data = request.getParameter("data");
        Map map = new HashMap();
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        int i=0;
        int j=0;
        user.setId(Utils.toLong(map.get("id")));
        user.setUsername(Utils.toString(map.get("username")));
        String enpwd=Utils.toString(map.get("password"));
        if(enpwd!=""){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(enpwd);
            user.setPassword(encode);
        }
        user.setBindMobile(Utils.toString(map.get("mobilePhone")));
        i = userService.updateXhxUser(user);
        userinfo=userinfoService.findByUserId(Utils.toLong(map.get("id")));
        userinfo.setUserId(Utils.toLong(map.get("id")));
        userinfo.setDepartment(Utils.toString(map.get("department")));
        userinfo.setNameZh(Utils.toString(map.get("nameZh")));
        userinfo.setMobilePhone(Utils.toString(map.get("mobilePhone")));
        userinfo.setEmail(Utils.toString(map.get("email")));
        userinfo.setUpdateTime(new Date());
        if(userinfo.getId()==null){
            userinfo.setId(SnowFlake.getSnowFlakeId("XhxUserinfo"));
            userinfo.setCreateTime(new Date());
            j = userinfoService.insertuserinfo(userinfo);
        }else {
            j = userinfoService.updateUserinfo(userinfo);
        }
        if (i+j<2){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }
    //修改groupuser密码
    @RequestMapping("/updateGrouppassword")
    public @ResponseBody Result updateGrouppassword(HttpServletRequest request){
        XhxGroupUser groupuser = new XhxGroupUser();
        String data = request.getParameter("data");
        Map map = new HashMap();
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        //先判断输入的旧密码是否正确
        Long id = Utils.toLong(map.get("id"));
        String newPassword = Utils.toString(map.get("newPassword"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(newPassword);
        String oldPassword = Utils.toString(map.get("oldPassword"));
        groupuser.setId(id);
        groupuser.setPassword(encode);
        String password = groupUserService.selectpassword(id);
        if(password!=null && password!="" && newPassword!=null && newPassword!=""){
            if (encoder.matches(oldPassword,password)) {
                if (encoder.matches(newPassword,password)) {//新密码和旧密码不能相同
                    return ResultUtil.error(ResultEnum.UPDATE_ERROR);
                }
                //更新数据库中的密码
                int i = groupUserService.updateXhxUserPasswordByid(groupuser);
                if(i==1){
                    return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
                }
                return ResultUtil.error(ResultEnum.UPDATE_ERROR);
            }
        }
        return ResultUtil.error(ResultEnum.UPDATE_ERROR);
    }

    //注册groupUser账号，同时修改user表的parentId
//    传递的参数：username、password、用户表的用户id
    @RequestMapping("/insertGroupUserAndParentId")
    public @ResponseBody Result insertGroupUserAndParentId(HttpServletRequest request){
        String data = request.getParameter("data");
        Map map = new HashMap();
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        Integer idint = (Integer) map.get("id");
        long idl = idint.longValue();
        XhxUser user = userService.getXhxUserById(idl);
        //向groupUser表中增加一条数据
        XhxGroupUser groupUser = new XhxGroupUser();
        groupUser.setUsername((String) map.get("username"));
        groupUser.setPassword((String) map.get("password"));
        Long Id = SnowFlake.getSnowFlakeId("XhxGroupUser");
        groupUser.setId(Id);
        groupUser.setBindMobile(user.getBindMobile());
        groupUser.setLoginCount("0");
        groupUser.setEnable(true);
        groupUser.setCreateTime(new Date());
        groupUser.setUpdateTime(new Date());
        int i = groupUserService.xhxGroupUserReg(groupUser);
//       向user表中该条数据中添加parentid
        user.setParentId(Id);
        int j = userService.updateXhxUser(user);
        if (i<1||j<1){
                return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS,Id);
        //{
        //    "code": 200,
        //    "msg": "成功",
        //    "data": 1544606049554913
        //}
    }
}
