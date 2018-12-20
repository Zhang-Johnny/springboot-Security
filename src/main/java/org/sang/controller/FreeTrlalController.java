package org.sang.controller;

import org.sang.bean.Result;
import org.sang.bean.XhxFreeTrlalRecord;
import org.sang.common.tool.ResultUtil;
import org.sang.enums.ResultEnum;
import org.sang.service.XhxFreeTrlalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
/*
宋志林20181120
 */
@Controller
@RequestMapping("/freeTrlal")
public class FreeTrlalController {
    @Autowired
    XhxFreeTrlalRecordService freeTrlalRecordService;
//添加一个免费用户
    @RequestMapping("/addFreeTrlalRecord")
    public @ResponseBody Result addFreeTrlalRecord(Long userId, String trialDate) {
        XhxFreeTrlalRecord freeTrlalRecord = new XhxFreeTrlalRecord();
        freeTrlalRecord.setUserId(userId);
        freeTrlalRecord.setTrialDate(trialDate);
        freeTrlalRecord.setTrialType(1);
        freeTrlalRecord.setStartTime(new Date());
        Date startTime = freeTrlalRecord.getStartTime();
        freeTrlalRecord.setEndTime(new Date(startTime.getTime()+(Integer.parseInt(trialDate) * 24 * 60 * 60 * 1000)));
        int i=freeTrlalRecordService.inset(freeTrlalRecord);
        if(i<0){
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success(ResultEnum.ADD_SUCCESS);
    }
//修改免费用户的类型：试用中-->已试用
    @RequestMapping("/updateFreeTrlalRecord")
    public @ResponseBody Result updateFreeTrlalRecord(Long userId){
        XhxFreeTrlalRecord freeTrlalRecord = freeTrlalRecordService.selectByUserId(userId);
        int i=0;
        if((freeTrlalRecord.getEndTime().getTime()-(new Date()).getTime())<0){
            freeTrlalRecord.setTrialType(2);
            i = freeTrlalRecordService.update(freeTrlalRecord);
            if (i<1){
                return ResultUtil.error(ResultEnum.UPDATE_ERROR);
            }
            return ResultUtil.success(ResultEnum.UPDATE_SUCCESS);
        }
        return ResultUtil.error(ResultEnum.NOTHING);
    }
}
