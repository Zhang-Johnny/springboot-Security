package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxMenu;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.MenuRoleService;
import org.sang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuRoleService menuRoleService;
    //查询已经分配的数据，进行菜单数据的回显
    @ResponseBody
    @RequestMapping("/loadAssignData")
    public Result loadAssignData( Long roleId ) {
        List<XhxMenu> roots = new ArrayList<XhxMenu>();
        // 查询所有菜单数据
        List<XhxMenu> menus = menuService.selectAllMenu();
        // 查询角色已经分配的菜单id
        List<Long> menuIds = menuRoleService.selectMenuIdByRoleId(roleId);
        Map<Long,XhxMenu> menuMap = new HashMap<Long,XhxMenu>();
        for ( XhxMenu menu : menus ) {
            if ( menuIds.contains(menu.getId()) ) {
                menu.setChecked(true);//判断回显菜单
            } else {
                menu.setChecked(false);
            }
            menuMap.put(menu.getId(), menu);
        }
        for ( XhxMenu menu : menus ) {
            // 子节点
            XhxMenu child = menu;
            if ( child.getParentId() == null ) {
                roots.add(menu);
            } else {
                // 父节点
                XhxMenu parent = menuMap.get(child.getParentId());
                // 组合父子节点的关系
                parent.getChildren().add(child);
            }
        }
        if (roots==null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(roots);
    }
    //查询所有的菜单数据
    @ResponseBody
    @RequestMapping("/loadAsyncData")
    public Result loadAsyncData() {
        List<XhxMenu> roots = new ArrayList<XhxMenu>();
        // 查询一次数据
        List<XhxMenu> menus = menuService.selectAllMenu();

        Map<Long, XhxMenu> menuMap = new HashMap<Long, XhxMenu>();
        for ( XhxMenu menu : menus ) {
            menuMap.put(menu.getId(), menu);
        }
        for ( XhxMenu menu : menus ) {
            // 子节点
            XhxMenu child = menu;
            if ( child.getParentId() == null ) {
                roots.add(menu);
            } else {
                // 父节点
                XhxMenu parent = menuMap.get(child.getParentId());
                // 组合父子节点的关系
                parent.getChildren().add(child);
            }
        }
        if (roots==null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(roots);
    }
    //删除菜单数据
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete( Long id ) {
        int i = menuService.deleteByPrimaryKey(id);
        if(i<0){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }
    //修改菜单数据
    @ResponseBody
    @RequestMapping("/update")
    public Result update(HttpServletRequest request) {
        String menustr = request.getParameter("data");
        Map<String,Object> menuMap = new HashMap();
        if (StringUtils.isNotBlank(menustr)){
            menuMap = JSON.parseObject(menustr,menuMap.getClass());
        }
        int i = menuService.updateByPrimaryKeySelective(menuMap);
        if (i<0){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }
    //添加菜单数据
    @ResponseBody
    @RequestMapping("/insert")
    public Result insert(HttpServletRequest request) {
        String menustr = request.getParameter("data");
        Map<String,Object> menuMap = new HashMap();
        if (StringUtils.isNotBlank(menustr)){
            menuMap = JSON.parseObject(menustr,menuMap.getClass());
        }
        int i = menuService.insertSelective(menuMap);
        if (i<0){
            return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }
}
