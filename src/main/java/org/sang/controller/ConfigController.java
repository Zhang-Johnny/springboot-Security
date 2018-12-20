package org.sang.controller;

import org.sang.bean.XhxMenu;
import org.sang.bean.XhxUser;
import org.sang.common.UserUtils;
import org.sang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 这是一个只要登录就能访问的Controller
 * 主要用来获取一些配置信息
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    MenuService menuService;
    @RequestMapping("/sysmenu")
    public List<XhxMenu> sysmenu() {
        return menuService.getMenusByUserId();
    }

    @RequestMapping("/user")
    public XhxUser currentUser() {
        return (XhxUser)UserUtils.getCurrentUser();
    }
}
