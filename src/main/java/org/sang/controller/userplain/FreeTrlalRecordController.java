package org.sang.controller.userplain;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxFreeTrlalRecord;
import org.sang.bean.XhxGroupUser;
import org.sang.bean.XhxUser;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxFreeTrlalRecordService;
import org.sang.service.XhxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 免费试用
 *  Created by zhangjun on 2018/9/18.
 */
@RestController
@RequestMapping("/freeTrlalRecord")
public class FreeTrlalRecordController {

    @Autowired
    private XhxFreeTrlalRecordService freeTrlalRecordService;

    @Autowired
    private XhxUserService userService;

    @RequestMapping("/findAllFreeTrlalRecord")
    public @ResponseBody Result findAllFreeTrlalRecord(HttpServletRequest request){
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
        int totalCount = freeTrlalRecordService.countNo(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxFreeTrlalRecord> list = freeTrlalRecordService.findAllByParam(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    @RequestMapping("/findFreeTrlalRecordById/{id}")
    public @ResponseBody Result findFreeTrlalRecordById(@PathVariable("id") Long id){
        XhxFreeTrlalRecord xhxFreeTrlalRecord = null;
        if (id!=null){
            xhxFreeTrlalRecord = freeTrlalRecordService.findById(id);
        }
        if (xhxFreeTrlalRecord == null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(xhxFreeTrlalRecord);
    }

    @RequestMapping("/updateFreeTrlalRecord")
    public @ResponseBody Result updateFreeTrlalRecord(XhxFreeTrlalRecord xhxFreeTrlalRecord){
        int i = freeTrlalRecordService.update(xhxFreeTrlalRecord);
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }

    @RequestMapping("/deleteFreeTrlalRecord/{id}")
    public @ResponseBody Result deleteFreeTrlalRecord(@PathVariable("id") Long id){
        int i = 0;
        if (id!=null){
            i = freeTrlalRecordService.delete(id);
        }
        if (i<1){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }

    @RequestMapping("/addFreeTrlal")
    public @ResponseBody Result addFreeTrlal(XhxFreeTrlalRecord xhxFreeTrlalRecord){
        int i = freeTrlalRecordService.inset(xhxFreeTrlalRecord);
        if (i<1){
            return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }

    @RequestMapping("/findIp")
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("Proxy-Client-IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("WL-Proxy-Client-IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getRemoteAddr();
         }
         if (ip.equals("0:0:0:0:0:0:0:1")) {
             ip = "本地";
         }
        return ip;
    }
}
