package org.sang.mapper;

import org.apache.ibatis.annotations.*;
import org.sang.bean.XhxFeedback;

import java.util.List;
import java.util.Map;

public interface XhxFeedbackMapper {
    @Delete("delete from xhx_feed_back where id = #{id}")
    int deleteByPrimaryKey(Long id);

    @Insert("INSERT INTO xhx_feed_back (id, name,iphone,title,context,email,createTime,updateTime) " +
            "VALUES (#{id}, #{name}, #{iphone}, #{title}, #{context}, #{email}, #{createTime}, #{updateTime})")
    int insert(XhxFeedback record);

/*    @Results(id="feedbackMap",value = {

    })*/
    @Select("select * from xhx_feed_back where id=#{id}")
    XhxFeedback selectByPrimaryKey(Long id);

    @Update("UPDATE xhx_feed_back set name=#{name},iphone=#{iphone},title=#{title},context=#{context},email=#{email},updateTime=#{updateTime} where id=#{id}")
    int updateByPrimaryKey(XhxFeedback record);

    @Select("select * from xhx_feed_back")
    List<XhxFeedback> getAllFeedBack();

    List<XhxFeedback> getFeedBackParam(Map map);

    int count(Map map);
}