package org.sang.service;

import org.sang.bean.XhxFeedback;
import org.sang.common.SnowFlake;
import org.sang.mapper.XhxFeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackServer {
    @Autowired
    XhxFeedbackMapper feedbackMapper;
    public int insertFeedback(XhxFeedback feedback) {
        feedback.setId(SnowFlake.getSnowFlakeId("xhx_feed_back"));
        feedback.setCreateTime(new Date());
        feedback.setUpdateTime(new Date());
        return feedbackMapper.insert(feedback);
    }

    public int eleteFeedback(long id) {
        return feedbackMapper.deleteByPrimaryKey(id);
    }

    public XhxFeedback findFeedback(long id) {
        return feedbackMapper.selectByPrimaryKey(id);
    }

    public int updateFeedback(XhxFeedback feedback) {
        XhxFeedback feedbackold=null;
        if(feedback.getId()!=null){
        feedbackold = feedbackMapper.selectByPrimaryKey(feedback.getId());
        }
        feedback.setUpdateTime(new Date());
        if(feedback.getContext()!=null){
            feedbackold.setContext(feedback.getContext());
        }
        if(feedback.getEmail()!=null){
            feedbackold.setEmail(feedback.getEmail());
        }
        if(feedback.getIphone()!=null){
            feedbackold.setIphone(feedback.getIphone());
        }
        if(feedback.getName()!=null){
            feedbackold.setName(feedback.getName());
        }
        if(feedback.getTitle()!=null){
            feedbackold.setTitle(feedback.getTitle());
        }
        return feedbackMapper.updateByPrimaryKey(feedbackold);
    }

    public List<XhxFeedback> findAllFeedback() {
        return feedbackMapper.getAllFeedBack();
    }

    public int count(Map map) {
        return feedbackMapper.count(map);
    }

    public List<XhxFeedback> findByParam(Map map) {
        return feedbackMapper.getFeedBackParam(map);
    }

}
