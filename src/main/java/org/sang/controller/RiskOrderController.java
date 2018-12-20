package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxFreeTrlalRecord;
import org.sang.bean.XhxRiskOrder;
import org.sang.bean.XhxUser;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxRiskOrderService;
import org.sang.service.XhxUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户风险警戒
 * Created by zhangjun on 2018/9/19.
 */
@Controller
@RequestMapping("/riskOrder")
public class RiskOrderController {
    public final static Logger logger = LoggerFactory.getLogger(RiskOrderController.class);
    @Autowired
    private XhxRiskOrderService riskOrderService;

    @Autowired
    private XhxUserService userService;

    @RequestMapping("/findAllRiskOrder")
    public @ResponseBody Result findAllRiskOrder(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        String username = null;
        if(StringUtils.isNotBlank(Utils.toString(map.get("username")))){
            username=Utils.toString(map.get("username"));
        }
        if (StringUtils.isNotBlank(username)){
            List<XhxUser> userList = userService.findByName(username);
            List userId = new ArrayList();
            for (XhxUser ul : userList){
                userId.add(ul.getId());
            }
            if (userId.size()>0){
                map.put("id","'"+StringUtils.join(userId,"','")+"'");
            }
        }
        if(StringUtils.isNotBlank(Utils.toString(map.get("pageNo")))){
            pageNo=Integer.parseInt(Utils.toString(map.get("pageNo")));
        }
        int pageSize = 10;
        int totalCount = riskOrderService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxRiskOrder> list = riskOrderService.findAll(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    @RequestMapping("/findRiskOrderById/{id}")
    public @ResponseBody Result findRiskOrderById(@PathVariable("id") Long id){
        XhxRiskOrder xhxRiskOrder = null;
        if (id!=null){
            xhxRiskOrder = riskOrderService.findById(id);
        }
        if (xhxRiskOrder == null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(xhxRiskOrder);
    }

    @RequestMapping("/updateRiskOrder")
    public @ResponseBody Result updateRiskOrder(XhxRiskOrder xhxRiskOrder){
        int i = riskOrderService.update(xhxRiskOrder);
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }

    @RequestMapping("/deleteRiskOrder/{id}")
    public @ResponseBody Result deleteRiskOrder(@PathVariable("id") Long id){
        int i = 0;
        if (id!=null){
            i = riskOrderService.delete(id);
        }
        if (i<1){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }

    @RequestMapping("/addRiskOrder")
    public @ResponseBody Result addRiskOrder(XhxRiskOrder xhxRiskOrder){
        int i = riskOrderService.insert(xhxRiskOrder);
        if (i<1){
            return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }
    //根据userId查询表xhx_risk_order、并关联表xhx_risk_monitor
    @RequestMapping("/findRiskOrderByUserId")
    public @ResponseBody Result findRiskOrderByUserId(HttpServletRequest request){
        Map map = new HashMap();
        int pageNo = 1;
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if(StringUtils.isNotBlank(Utils.toString(map.get("pageNo")))){
            pageNo=Integer.parseInt(Utils.toString(map.get("pageNo")));
        }
        int pageSize = 10;
        int totalCount = riskOrderService.countByUserId(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxRiskOrder> list = riskOrderService.findRiskOrderByUserId(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }
}
