package org.sang.controller;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxRiskMonitor;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxRiskMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 风险监控内容菜单表
 *  Created by zhangjun on 2018/9/20.
 */
@Controller
@RequestMapping("/riskMonitor")
public class RiskMonitorController {
    public final static Logger logger = LoggerFactory.getLogger(RiskMonitorController.class);
    @Autowired
    private XhxRiskMonitorService riskMonitorService;

    @RequestMapping("/findAllRiskMonitor")
    public @ResponseBody Result findAllRiskMonitor(HttpServletRequest request){
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
        int totalCount = riskMonitorService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxRiskMonitor> list = riskMonitorService.findAll(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    @RequestMapping("/findRiskMonitorById/{id}")
    public @ResponseBody Result findRiskMonitorById(@PathVariable("id") Long id){
        XhxRiskMonitor xhxRiskMonitor = null;
        if (id!=null){
            xhxRiskMonitor = riskMonitorService.findById(id);
        }
        if (xhxRiskMonitor == null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(xhxRiskMonitor);
    }

    @RequestMapping("/updateRiskMonitor")
    public @ResponseBody Result updateGroupUser(XhxRiskMonitor xhxRiskMonitor){
        int i = riskMonitorService.update(xhxRiskMonitor);
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }

    @RequestMapping("/deleteRiskMonitor/{id}")
    public @ResponseBody Result deleteFreeTrlalRecord(@PathVariable("id") Long id){
        int i = 0;
        if (id!=null){
            i = riskMonitorService.delete(id);
        }
        if (i<1){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }

    @RequestMapping("/insertRiskMonitor")
    public @ResponseBody Result insertRiskMonitor(XhxRiskMonitor xhxRiskMonitor){
        int i = riskMonitorService.insert(xhxRiskMonitor);
        if (i<1){
            return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }
}
