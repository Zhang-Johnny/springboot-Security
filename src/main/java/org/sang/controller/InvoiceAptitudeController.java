package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxInvoiceAptitude;
import org.sang.common.Utils;
import org.sang.common.tool.PageUtil;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxInvoiceAptitudeService;
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

/**
 * 增票资质
 *  Created by zhangjun on 2018/9/17.
 */
@RestController
@RequestMapping("/invoiceAptitude")
public class InvoiceAptitudeController {

    @Autowired
    XhxInvoiceAptitudeService invoiceAptitudeService;
    @Autowired
    XhxUserService userService;

    @RequestMapping("/findAllInvoiceAptitude")
    public @ResponseBody Result findAllInvoiceAptitude(HttpServletRequest request){
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
        String userName= Utils.toString(map.get("userName"));
        if (StringUtils.isNotBlank(userName)){
            List<Integer> userIdList = userService.getUserIdByUserName(userName);
            if (userIdList.size()>0){
                map.put("userIdList","'"+StringUtils.join(userIdList,"','")+"'");
            }
        }
        int totalCount = invoiceAptitudeService.count(map);
        PageUtil page = new PageUtil(pageNo,pageSize,totalCount);
        map.put("page", page.getPage());
        map.put("pageSize", page.getPageSize());
        List<XhxInvoiceAptitude> list = invoiceAptitudeService.findAll(map);
        if (list.size() < 1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        page.setList(list);
        return ResultUtil.success(page);
    }

    @RequestMapping("/findInvoiceAptitudeByNumber/{taxNumber}")
    public @ResponseBody Result findInvoiceAptitudeByNumber(@PathVariable("taxNumber") Integer taxNumber){
        List<XhxInvoiceAptitude> list = invoiceAptitudeService.findByNumber(taxNumber);
        if (list.size() < 1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(list);
    }

    @RequestMapping("/deleteInvoiceAptitude/{id}")
    public @ResponseBody Result deleteInvoiceAptitude(@PathVariable("id") Long id){
        int i = 0;
        if (id!=null){
            i = invoiceAptitudeService.delete(id);
        }
        if (i<1){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }

    //在增票资质页面点击提交，向数据库中添加资质信息/修改资质信息
    @RequestMapping("/saveInvoiceAptitude")
    @ResponseBody
    public Result saveInvoiceAptitude(XhxInvoiceAptitude invoiceAptitude){
       //根据userId查询判断用户是否已经填写了增票资质
        int i,j;
        XhxInvoiceAptitude invoiceAptitudeSelect=
                invoiceAptitudeService.findByUserId(invoiceAptitude.getUserId());
        if(invoiceAptitudeSelect!=null){
            //执行更新操作
            invoiceAptitude.setId(invoiceAptitudeSelect.getId());
            j=invoiceAptitudeService.updateinvoiceAptitude(invoiceAptitude);
            if (j<1){
                return ResultUtil.error(ResultEnum.ADD_ERROR );
            }
            return ResultUtil.success(ResultEnum.ADD_SUCCESS);
        }else{
            i = invoiceAptitudeService.saveInvoiceAptitude(invoiceAptitude);
            if (i<1){
                return ResultUtil.error(ResultEnum.ADD_ERROR );
            }
            return ResultUtil.success(ResultEnum.ADD_SUCCESS);
        }
    }

    //通过userId查询增票资质
    @RequestMapping("/getUserInvoiceAptitude")
    public @ResponseBody Result getUserInvoiceAptitude(HttpServletRequest request){
        Map map = new HashMap();
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        int userId = 0;
        if(map.containsKey("userId")){
            userId= Utils.toInteger(map.get("userId"));
        }
        XhxInvoiceAptitude invoiceAptitude = invoiceAptitudeService.findByUserId(userId);
        if (invoiceAptitude == null){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(invoiceAptitude);
    }

}
