package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxRole;
import org.sang.bean.XhxUser;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.ReUserRoleService;
import org.sang.service.RoleService;
import org.sang.service.XhxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ReUserRoleService userRoleService;
    @Autowired
    private XhxUserService userService;
    @Autowired
    private RoleService roleService;
    //删除已分配的角色
    @RequestMapping("/unAssignRole")
    public @ResponseBody Result unassignRole( Integer userId, String roleIds) {
        List<Long> roleIdList = Arrays.asList(roleIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("roleIds", roleIdList);
        int i = userRoleService.deleteUserRolesSelective(paramMap);
        if(i<0){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }
    //将选择的角色集合分配给该用户
    @ResponseBody
    @RequestMapping("/assignRole")
    public Result assignRole(Long userId, String roleIds) {
        List<Long> roleIdList = Arrays.asList(roleIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("roleIds",roleIdList);
        int i = userRoleService.insertSelective(paramMap);
        if (i<0){
            return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }
    /*点击页面的分配角色按钮，跳转到分配角色页面，并加载数据，
     页面中左右两侧的角色（分别代表未分配、已分配角色），
    点击分配或取消按钮时左右内容进行增减动态展示
    （获取用户的角色信息）
    */
    @RequestMapping("/assign")
    public @ResponseBody Result assign(Long userId) {
        List<XhxRole> roles = roleService.queryAll();
        // 左边列表
        List<XhxRole> leftList = new ArrayList<XhxRole>();
        // 右边列表
        List<XhxRole> rightList = new ArrayList<XhxRole>();
        // 根据用户id查询关系表的数据
        List<Long> roleIds = userRoleService.selectRoleIdsByUserId(userId);
        for ( XhxRole role : roles ) {
            if ( roleIds.contains(role.getId()) ) {
                rightList.add(role);
            } else {
                leftList.add(role);
            }
        }
        HashMap<String, List> map = new HashMap<>();
        map.put("roles", roles);
        map.put("leftList",leftList);
        map.put("rightList",rightList);
        if (map==null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(map);
    }
    //分页查询所有列表
    @RequestMapping("/findAllUser")
    public @ResponseBody Result findAllWalletRecord(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if(map.containsKey("pageNo")){
            pageNo= Utils.toInteger(map.get("pageNo"));
        }
        int pageSize = 10;
        int totalCount = userService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxUser> list = userService.findAll(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }
}
