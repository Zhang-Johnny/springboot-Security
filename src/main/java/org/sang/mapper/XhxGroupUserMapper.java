package org.sang.mapper;

import org.sang.bean.XhxGroupUser;
import org.sang.bean.XhxRole;

import java.util.List;
import java.util.Map;

public interface XhxGroupUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxGroupUser record);

    int insertSelective(XhxGroupUser record);

    XhxGroupUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxGroupUser record);

    int updateByPrimaryKey(XhxGroupUser record);

    //function
    XhxGroupUser QueryByUsername(String username);

    List<XhxRole> getRolesByUserId(Long id);

    List<XhxGroupUser> findAll(Map map);

    int count(Map map);

    XhxGroupUser findById(Long id);

    int updatePassword(XhxGroupUser xhxGroupUser);

    int checkGroupUsername(String username);

    int checkMobile(Map map);
    int updatePasswordByid(XhxGroupUser xhxGroupUser);

    int updateByusernameSelective(XhxGroupUser groupUser);
}