package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxDeliveryMailingAddress;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxDeliveryMailingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mail")
public class XhxDeliveryMailingAddressController {
    @Autowired
    private XhxDeliveryMailingAddressService deliveryMailingAddressService;
    //根据userId查询XhxDeliveryMailingAddress表
    @RequestMapping("/findAllMailingAddress")
    public @ResponseBody Result findAllMailingAddress(Long userId){
        List<XhxDeliveryMailingAddress> deliveryMailingAddressList = deliveryMailingAddressService.getModelByUserId(userId);
        if (deliveryMailingAddressList.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(deliveryMailingAddressList);
    }
    //新增用户地址信息
    @RequestMapping("/addMailingAddress")
    public @ResponseBody Result addMailingAddress(HttpServletRequest request){
        Map map = new HashMap();
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if (!map.containsKey("userId")&&!map.containsKey("personName")&&!map.containsKey("area")&&!map.containsKey("detailedAdd")
                &&!map.containsKey("zipCode")&&!map.containsKey("iphone")&&!map.containsKey("defaultStatus")){
            return ResultUtil.error(ResultEnum.LACK_PARAMETER);
        }
        int i = deliveryMailingAddressService.addMailingAddress(map);
        if (i<1){
            return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }
    //根据id删除一条
    @RequestMapping("/deleteMailingAddress")
    public @ResponseBody Result deleteMailingAddress(Long id){
        int i = deliveryMailingAddressService.deleteMailingAddress(id);
        if (i<1){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }
    //根据id修改
    @RequestMapping("/updateMailingAddress")
    public @ResponseBody Result updateMailingAddress(HttpServletRequest request){
        Map map = new HashMap();
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if (!map.containsKey("id")){
            return ResultUtil.error(ResultEnum.LACK_PARAMETER);
        }
        int i = deliveryMailingAddressService.updateMailingAddress(map);
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }
}
