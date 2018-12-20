package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.XhxRole;

import java.util.List;
import java.util.Map;

public interface XhxRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxRole record);

    int insertSelective(XhxRole record);

    XhxRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxRole record);

    int updateByPrimaryKey(XhxRole record);

    int deleteRoleById(Long id);

    List<XhxRole> roles();

    int addNewRole(Map map);

    XhxRole selectByName(String name);

    List<String> selectByRoleId(Map map);

    List<XhxRole> selectAll();

    List<XhxRole> selectByUserId(Long userId);

    int count(Map map);

    List<XhxRole> selectRoleAll(Map map);
}