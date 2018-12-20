package org.sang.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxWalletRecord;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxUserService;
import org.sang.service.XhxWalletRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 充值消费
 * Created by zhangjun on 2018/9/19.
 */
@Controller
@RequestMapping("/walletRecord")
public class WalletRecordController {

    @Autowired
    private XhxWalletRecordService walletRecordService;
    @Autowired
    private XhxUserService userService;
    @RequestMapping("/findAllWalletRecord")
    public @ResponseBody Result findAllWalletRecord(HttpServletRequest request){
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
        int totalCount = walletRecordService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxWalletRecord> list = walletRecordService.findAll(map);
        if (list.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    //根据参数查找业务订单
    @RequestMapping("/findWalletRecordByParam")
    public @ResponseBody Result findWalletRecord(HttpServletRequest request){
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
        Long parentId = null;
        if(map.containsKey("parentId")){
            parentId=Utils.toLong(map.get("parentId"));
            List<Long> userIdList = userService.getUserIdByParentId(parentId);
            if (parentId!=null){
                userIdList.add(parentId);
                if(map.containsKey("userId")){
                    map.remove("userId");
                }
            }
            if (userIdList.size()>0){
                map.put("userIdList","'"+StringUtils.join(userIdList,"','")+"'");
            }
        }
        int totalCount = walletRecordService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxWalletRecord> list = walletRecordService.findByUserIdList(map);
        if (list == null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    //根据参数查找业务订单
    @RequestMapping("/insertRecord")
    public @ResponseBody Result insertRecord(XhxWalletRecord walletRecord){
        int i = walletRecordService.insertRecord(walletRecord);
        if (i<1){
            return ResultUtil.error(ResultEnum.ADD_ERROR );
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }
}
