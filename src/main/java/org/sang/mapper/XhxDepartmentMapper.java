package org.sang.mapper;

import org.sang.bean.XhxDepartment;

import java.util.List;

public interface XhxDepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxDepartment record);

    int insertSelective(XhxDepartment record);

    XhxDepartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxDepartment record);

    int updateByPrimaryKey(XhxDepartment record);

    List<XhxDepartment> getAllDeps();

    List<XhxDepartment> getDepByPid(Long pid);
}