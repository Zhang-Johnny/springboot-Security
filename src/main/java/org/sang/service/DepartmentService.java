package org.sang.service;

import org.sang.bean.XhxDepartment;
import org.sang.mapper.XhxDepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {
    @Autowired
    XhxDepartmentMapper departmentMapper;
    public int addDep(XhxDepartment department) {
        /* department.setEnabled(true);
       departmentMapper.addDep(department);
        return department.getResult();*/
       return 1;
    }

    public int deleteDep(Long did) {
        XhxDepartment department = new XhxDepartment();
        department.setId(did);
       /* departmentMapper.deleteDep(department);
        return department.getResult();*/
       return 1;
    }

   public List<XhxDepartment> getDepByPid(Long pid) {
        return departmentMapper.getDepByPid(pid);
    }

    public List<XhxDepartment> getAllDeps() {
        return departmentMapper.getAllDeps();
    }
}
