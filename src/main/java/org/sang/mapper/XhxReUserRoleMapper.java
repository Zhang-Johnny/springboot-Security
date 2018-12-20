package org.sang.mapper;

import org.sang.bean.XhxReUserRole;

import java.util.List;
import java.util.Map;

public interface XhxReUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxReUserRole record);

    int insertSelective(XhxReUserRole record);

    XhxReUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxReUserRole record);

    int updateByPrimaryKey(XhxReUserRole record);

    XhxReUserRole selectByUserId(Long userId);

    List<XhxReUserRole> selectByroleUserList(Long userId);

    int insertUserRoleSelective(Map<String, Object> paramMap);

    int deleteUserRolesSelective(Map<String, Object> paramMap);

    List<Long> selectRoleIdsByUserId(Long userId);
}