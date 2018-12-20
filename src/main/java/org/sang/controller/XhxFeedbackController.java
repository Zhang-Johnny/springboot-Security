package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxFeedback;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.FeedbackServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Feedback")
@Controller
public class XhxFeedbackController {
    @Autowired
    FeedbackServer feedbackServer;
    //添加表数据的结构
    @RequestMapping("/insertFeedback")
    public @ResponseBody Result insertFeedback(XhxFeedback feedback) {
        int i = feedbackServer.insertFeedback(feedback);
        if(i<0){
            return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }

    //根据id删除一条数据
    @RequestMapping("/deleteFeedback")
    public @ResponseBody Result deleteFeedback(long id) {
        int i = feedbackServer.eleteFeedback(id);
        if(i<0){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }
//根据id查询一条数据
@RequestMapping("/findFeedback")
public @ResponseBody Result findFeedback(long id) {
    XhxFeedback feedback = feedbackServer.findFeedback(id);
    if(feedback==null){
        return ResultUtil.error(ResultEnum.NOT_FIND);
    }
    return ResultUtil.success(feedback);
}
//根据id修改
@RequestMapping("/updateFeedback")
public @ResponseBody Result updateFeedback(XhxFeedback feedback) {
    int i = feedbackServer.updateFeedback(feedback);
    if(i<0){
        return ResultUtil.error(ResultEnum.UPDATE_ERROR);
    }
    return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
}
//查询所有
@RequestMapping("/findAllFeedback")
public @ResponseBody Result findAllFeedback() {
    List<XhxFeedback> allFeedback = feedbackServer.findAllFeedback();
    if(allFeedback==null){
        return ResultUtil.error(ResultEnum.NOT_FIND);
    }
    return ResultUtil.success(allFeedback);
}
//根据可变参数查询
@RequestMapping("/findFeedbackBypara")
public @ResponseBody Result findAllFreeTrlalRecord(HttpServletRequest request){
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
    int totalCount = feedbackServer.count(map);
    PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
    map.put("page", page.getPage());
    map.put("pageSize", page.getPageSize());
    List<XhxFeedback> list = feedbackServer.findByParam(map);
    if (list.size()<1){
        return ResultUtil.error(ResultEnum.NOT_FIND);
    }
    page.setList(list);
    return ResultUtil.success(page);
}
}
