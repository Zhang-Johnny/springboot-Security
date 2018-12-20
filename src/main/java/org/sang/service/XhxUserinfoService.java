package org.sang.service;

import org.sang.bean.XhxUserinfo;
import org.sang.common.SnowFlake;
import org.sang.mapper.XhxUserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class XhxUserinfoService {

    @Autowired
    XhxUserinfoMapper userinfoMapper;

    public XhxUserinfo findById(Long id){
        return userinfoMapper.selectByPrimaryKey(id);
    }
    public XhxUserinfo findByUserId(Long userId){
        return userinfoMapper.findByUserId(userId);
    }

    public int insertuserinfo(XhxUserinfo userinfo) {
        Long infoid = SnowFlake.getSnowFlakeId("XhxUserinfo");
        userinfo.setId(infoid);
        userinfo.setCreateTime(new Date());
        userinfo.setUpdateTime(new Date());
        return userinfoMapper.insertSelective(userinfo);
    }
    public int update(XhxUserinfo xhxUserinfo){
        return userinfoMapper.updateByPrimaryKeySelective(xhxUserinfo);
    }

    public int updateUserinfo(XhxUserinfo userinfo) {
        userinfo.setUpdateTime(new Date());
        return userinfoMapper.updateByUserId(userinfo);
    }

    public String selectEmail(Long userid) {
        return userinfoMapper.selectByUserid(userid);
    }
}
