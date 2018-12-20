package org.sang.mapper;

import org.sang.bean.XhxReRoleMenu;

import java.util.List;
import java.util.Map;

public interface XhxReRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxReRoleMenu record);

    int insertSelective(XhxReRoleMenu record);

    XhxReRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxReRoleMenu record);

    int updateByPrimaryKey(XhxReRoleMenu record);

    void deleteMenuByRid(Long rid);

    int addMenu(Map map);

    int insertMenuRole(Map<String, Object> paramMap);

    List<Long> selectMenuIdByRoleId(Long roleId);
}