package org.sang.controller;

import org.sang.bean.Result;
import org.sang.bean.XhxUserinfo;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户详情
 *  Created by zhangjun on 2018/9/25.
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    XhxUserinfoService userinfoService;

    @RequestMapping("/updateUserInfo")
    public @ResponseBody Result updateUserInfo(XhxUserinfo xhxUserinfo){
        int i = userinfoService.update(xhxUserinfo);
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }
}
