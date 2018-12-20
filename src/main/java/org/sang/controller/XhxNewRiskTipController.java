package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxNewRiskTip;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxNewRiskTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/newRiskTip")
public class XhxNewRiskTipController {
    @Autowired
    private XhxNewRiskTipService newRiskTipService;
    //根据用户id查询最新风险提示
    @RequestMapping("/findNewRiskTipByUserId")
    public @ResponseBody Result findNewRiskTipByUserId(HttpServletRequest request){
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
        int totalCount = newRiskTipService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxNewRiskTip> newRiskTipList=null;
        if(map.containsKey("userId")){
           newRiskTipList = newRiskTipService.getNewRiskTipByUserId(map);
        }
        if (newRiskTipList.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(newRiskTipList);
    }
}
