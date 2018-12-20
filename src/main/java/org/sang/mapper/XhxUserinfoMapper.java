package org.sang.mapper;

import org.sang.bean.XhxUserinfo;

public interface XhxUserinfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxUserinfo record);

    int insertSelective(XhxUserinfo record);

    XhxUserinfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxUserinfo record);

    int updateByPrimaryKey(XhxUserinfo record);

    XhxUserinfo findByUserId(Long userId);

    int updateByUserId(XhxUserinfo userinfo);

    String selectByUserid(Long userId);
}