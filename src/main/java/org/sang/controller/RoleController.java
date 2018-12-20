package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxRole;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.MenuRoleService;
import org.sang.service.ReUserRoleService;
import org.sang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private MenuRoleService menuRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ReUserRoleService userRoleService;
    //为每个角色分配菜单
    @ResponseBody
    @RequestMapping("/assignMenu")
    public Result assignMenu(Long roleId, String mIds) {
        List<Long> mIdList = Arrays.asList(mIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleId", roleId);
        paramMap.put("mIds", mIdList);
        int i = menuRoleService.insertSelective(paramMap);
        if (i<0){
            return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }
    //查询用户所有角色信息列表
    @RequestMapping("/selectRoleByUserId")
    public @ResponseBody Result selectRoleByUserId(Long userId) {
        // 根据用户id查询关系表、角色表关联查询角色的数据
        List<XhxRole> xhxRoles = roleService.selectRoleByUserId(userId);
        if (xhxRoles==null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(xhxRoles);
    }
    //分页查询所有角色列表
    @RequestMapping("/findAllRole")
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
        int totalCount = roleService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxRole> list = roleService.selectRoleAll(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }
}
