package org.sang.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sang.bean.Result;
import org.sang.bean.XhxRiskSetUp;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxRiskSetUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/riskSetUp")
public class XhxRiskSetUpController {
    @Autowired
    private XhxRiskSetUpService riskSetUpService;
    //根据userId查询XhxRiskSetUp表
    @RequestMapping("/findAllRiskSetUp")
    public @ResponseBody Result findAllRiskSetUp(Long userId){
        List<XhxRiskSetUp> riskSetUpList = riskSetUpService.getModelByUserId(userId);
        if (riskSetUpList.size()<1){
            return ResultUtil.error(ResultEnum.NOT_FIND);
        }
        return ResultUtil.success(riskSetUpList);
    }
    //新增用户地址信息
    @RequestMapping("/addRiskSetUp")
    public @ResponseBody
    Result addRiskSetUp(HttpServletRequest request){
        Map map = new HashMap();
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if (!map.containsKey("userId")&&!map.containsKey("receive_mode")
                &&!map.containsKey("mails")&&!map.containsKey("send_cycle")){
            return ResultUtil.error(ResultEnum.LACK_PARAMETER);
        }
        int i = riskSetUpService.addRiskSetUp(map);
        if (i<1){
            return ResultUtil.error(ResultEnum.ADD_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }
    //根据id删除一条
    @RequestMapping("/deleteRiskSetUp")
    public @ResponseBody Result deleteRiskSetUp(Long id){
        int i = riskSetUpService.deleteModule(id);
        if (i<1){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.DELETE_SUCCESS);
    }
    //根据id修改
    @RequestMapping("/updateRiskSetUp")
    public @ResponseBody Result updateRiskSetUp(HttpServletRequest request){
        Map map = new HashMap();
        String data=request.getParameter("data");
        if (StringUtils.isNotBlank(data)){
            map = JSON.parseObject(data,map.getClass());
        }
        if (!map.containsKey("id")){
            return ResultUtil.error(ResultEnum.LACK_PARAMETER);
        }
        int i = riskSetUpService.updateRiskSetUp(map);
        if (i<1){
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
    }
}
