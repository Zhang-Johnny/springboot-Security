package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxInvoiceAptitude;
import org.sang.bean.XhxInvoiceOrder;
import org.sang.common.SnowFlake;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxInvoiceAptitudeService;
import org.sang.service.XhxInvoiceOrderService;
import org.sang.service.XhxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发票管理
 *  Created by zhangjun on 2018/9/11.
 */
@RestController
@RequestMapping("/invoiceOrder")
public class InvoiceOrderController {

    @Autowired
    private XhxInvoiceOrderService invoiceOrderService;
    @Autowired
    XhxInvoiceAptitudeService invoiceAptitudeService;
    @Autowired
    private XhxUserService userService;
    @RequestMapping("/findAllInvoiceOrder")
    public @ResponseBody Result findAllInvoiceOrder(HttpServletRequest request){
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
        int totalCount = invoiceOrderService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxInvoiceOrder> list = invoiceOrderService.findAll(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    @RequestMapping(value = "/findInvoiceOrderById/{id}")
    public @ResponseBody Result findInvoiceOrderById(@PathVariable("id") Long id){
        XhxInvoiceOrder xhxInvoiceOrder = null;
        if (id!=null){
            xhxInvoiceOrder = invoiceOrderService.findById(id);
        }
        if (xhxInvoiceOrder == null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(xhxInvoiceOrder);
    }

    @RequestMapping(value = "/findInvoiceOrderByNumber/{invoiceNo}")
    public Result findInvoiceByInvoiceNo(@PathVariable("invoiceNo") String invoiceNo){
        XhxInvoiceOrder xhxInvoiceOrder = null;
        if (StringUtils.isNotBlank(invoiceNo)){
            xhxInvoiceOrder = invoiceOrderService.findByInvoiceNo(invoiceNo);
        }
        if (xhxInvoiceOrder == null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(xhxInvoiceOrder);
    }

    @RequestMapping("/updateInvoiceOrder")
    public @ResponseBody Result updateInvoiceOrder(XhxInvoiceOrder xhxInvoiceOrder){
        int i = invoiceOrderService.update(xhxInvoiceOrder);
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }

    @RequestMapping("/deleteInvoiceOrder/{id}")
    public @ResponseBody Result deleteInvoiceOrder(@PathVariable("id") Long id){
        int i = 0;
        if (id!=null){
            i = invoiceOrderService.delete(id);
        }
        if (i<1){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }

    //保存发票订单信息，XhxInvoiceOrder中需要包含userId
    @RequestMapping("/saveInvoiceOrder")
    @ResponseBody
    public Result saveInvoiceOrder(XhxInvoiceOrder xhxInvoiceOrder){
        //根据发票订单对象信息保存发票订单
        Long id = SnowFlake.getSnowFlakeId("XhxInvoiceOrder");//先生成发票订单id
        xhxInvoiceOrder.setId(id);
        xhxInvoiceOrder.setCreateTime(new Date());
        xhxInvoiceOrder.setUpdateTime(new Date());
        int i=invoiceOrderService.saveInvoiceOrder(xhxInvoiceOrder);
        if (i<1){
            return ResultUtil.error(ResultEnum.ADD_ERROR );
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }
    //用户选择增值税发票类型，异步请求，请求参数为userId,返回值为XhxInvoiceAptitude对象数据
    @RequestMapping("/getInvoiceAptitude")
    @ResponseBody
    public Result getInvoiceAptitude(int userId){
        XhxInvoiceAptitude invoiceAptitude = invoiceAptitudeService.getInvoiceAptitude(userId);
        return ResultUtil.success(invoiceAptitude);
    }

    //根据groupId查找发票订单
    @RequestMapping("/findAllUserInvoiceOrder")
    public @ResponseBody Result findWalletRecord(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if(map.containsKey("pageNo")){
            pageNo=Utils.toInteger(map.get("pageNo"));
        }
        int pageSize = 10;
        Long parentId=0L;
        if(map.containsKey("parentId")){
            parentId=Utils.toLong(map.get("parentId"));
            List<Long> userIdList = userService.getUserIdByParentId(parentId);
            userIdList.add(parentId);
            map.put("userIdList","'"+StringUtils.join(userIdList,"','")+"'");
        }
        int totalCount = invoiceOrderService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxInvoiceOrder> invoiceOrderList = invoiceOrderService.findInvoiceOrderByUserIdList(map);
        if (invoiceOrderList == null && invoiceOrderList.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(invoiceOrderList);
        return ResultUtil.success(page);
    }


}