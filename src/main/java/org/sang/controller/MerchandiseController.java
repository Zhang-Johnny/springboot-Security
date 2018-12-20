package org.sang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.sang.bean.Result;
import org.sang.bean.XhxGroupUser;
import org.sang.bean.XhxMerchandiseOrder;

import org.sang.bean.XhxUser;

import org.sang.common.Utils;

import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxGroupUserService;
import org.sang.service.XhxMerchandiseOrderService;
import org.sang.service.XhxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

/**
 * 业务订单
 *  Created by zhangjun on 2018/9/13.
 */
@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {

    @Autowired
    private XhxMerchandiseOrderService merchandiseOrderService;

    @Autowired
    private XhxUserService userService ;

    @Autowired
    XhxGroupUserService groupUserService;

    @RequestMapping("/findAllMerchandise")
    public @ResponseBody Result findAllMerchandise(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        int pageSize = 10;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if(map.containsKey("pageNo")){
            pageNo= Utils.toInteger(map.get("pageNo"));
        }
        int totalCount = merchandiseOrderService.findByParamQdCount(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxMerchandiseOrder> list = merchandiseOrderService.findByParamQd(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    @RequestMapping("/findMerchandiseById/{id}")
    public @ResponseBody Result findMerchandiseById(@PathVariable("id") Long id){
        XhxMerchandiseOrder xhxMerchandiseOrder = null;
        if (id!=null){
            xhxMerchandiseOrder = merchandiseOrderService.findById(id);
        }
        if (xhxMerchandiseOrder == null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(xhxMerchandiseOrder);
    }

    @RequestMapping("/findMerchandiseByObject")
    public @ResponseBody Result findMerchandiseByObject(HttpServletRequest request){
        String str = request.getParameter("data");
        Map map = new HashMap();
        if (StringUtils.isNotBlank(str)){
            map = JSON.parseObject(str,map.getClass());
        }
        List<XhxMerchandiseOrder> list = merchandiseOrderService.findByObject(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(list);
    }

    @RequestMapping("/updateMerchandise")
    public @ResponseBody Result updateMerchandise(XhxMerchandiseOrder merchandiseOrder){
        int i = merchandiseOrderService.update(merchandiseOrder);
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }

    @RequestMapping("/deleteMerchandise/{id}")
    public @ResponseBody Result deleteMerchandise(@PathVariable("id") Long id){
        int i = 0;
        if (id!=null){
            i = merchandiseOrderService.delete(id);
        }
        if (i<1){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }

    @RequestMapping("/findByParamHd")
    public @ResponseBody Result findByParamHd(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        int pageSize = 10;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if(map.containsKey("pageNo")){
            pageNo= Utils.toInteger(map.get("pageNo"));
        }
        int totalCount = merchandiseOrderService.findByParamHdCount(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxMerchandiseOrder> list = merchandiseOrderService.findByParamHd(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    //根据groupId查找业务订单
    @RequestMapping("/findMerchandiseByUserId")
    public @ResponseBody Result findMerchandiseByUserId(Long parentId){
        List<Long> userIdList = userService.getUserIdByParentId(parentId);
        List<XhxMerchandiseOrder> list = null;
        HashMap<Long, List<XhxMerchandiseOrder>> userMerchandiseOrderHashMap = new HashMap<>();
        for (Long userId : userIdList) {
            if (userId!=null){
                list = merchandiseOrderService.findByUserId(userId);
                userMerchandiseOrderHashMap.put(userId,list);
            }
        }
        if (userMerchandiseOrderHashMap == null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(userMerchandiseOrderHashMap);
    }

    @RequestMapping("/findMerchandiseByGroupId")
    public @ResponseBody Result findMerchandiseByGroupId(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        int pageSize = 10;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if(map.containsKey("pageNo")){
            pageNo= Utils.toInteger(map.get("pageNo"));
        }
        XhxGroupUser groupUser = groupUserService.findById(Utils.toLong(map.get("groupId")));
        if (groupUser == null){
            return ResultUtil.error(ResultEnum.LACK_PARAMETER);
        }
        Long groupId = groupUser.getId();
        List<Long> userId = userService.getUserIdByParentId(groupId);
        userId.add(groupId);
        map.put("userList","'"+StringUtils.join(userId,"','")+"'");
        int totalCount = merchandiseOrderService.findMerchandiseByGroupIdCount(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List list = merchandiseOrderService.findMerchandiseByGroupId(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    @RequestMapping("/insertMerchandise")
    public @ResponseBody Result insertMerchandise(XhxMerchandiseOrder xhxMerchandiseOrder){
        DateTime d = DateTime.now();
        String orderNostr = d.toString("yyyyMMdd");
        int v = (int) ((Math.random() * 9 + 1) * 1000000);
        orderNostr=orderNostr+xhxMerchandiseOrder.getUserId()+v;
        Long orderNo = parseLong(orderNostr);
        xhxMerchandiseOrder.setOrderNo(orderNo);
        int i = merchandiseOrderService.insert(xhxMerchandiseOrder);
        if (i<1){
            return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(orderNo);
    }
}
