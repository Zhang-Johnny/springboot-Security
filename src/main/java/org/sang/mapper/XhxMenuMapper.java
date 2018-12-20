package org.sang.mapper;

import org.sang.bean.XhxMenu;

import java.util.List;
import java.util.Map;

public interface XhxMenuMapper {
    List<XhxMenu> getAllMenu();

    List<XhxMenu> getMenusByUserId(Long hrId);

    List<XhxMenu> menuTree();

    List<Long> getMenusByRid(Long rid);
//新增
    int deleteByPrimaryKey(Long id);

    int insert(XhxMenu record);

    int insertSelective(Map menuMap);

    int updateByPrimaryKeySelective(Map menuMap);

    int updateByPrimaryKey(XhxMenu record);

    List<XhxMenu> selectAllMenu();
}