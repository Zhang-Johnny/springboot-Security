package org.sang.service;

import org.sang.bean.XhxRole;
import org.sang.mapper.XhxRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class RoleService {
    @Autowired
    XhxRoleMapper roleMapper;


   public List<XhxRole> roles() {
        return roleMapper.roles();
    }

    public int addNewRole(String role, String roleZh) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        Map map =  new HashMap();
        map.put("name",role);
        map.put("role_zh",roleZh);
        return roleMapper.addNewRole(map);
    }
    public XhxRole selectByName(String name){
       return roleMapper.selectByName(name);
    };
    public int deleteRoleById(Long rid) {
        return roleMapper.deleteRoleById(rid);
    }

    public List<String> selectRoleNameZh(Map map) {
        return roleMapper.selectByRoleId(map);
    }

    public List<XhxRole> queryAll() {
        return roleMapper.selectAll();
    }

    public List<XhxRole> selectRoleByUserId(Long userId) {
        return roleMapper.selectByUserId(userId);
    }

    public int count(Map map) {
        return roleMapper.count(map);
    }

    public List<XhxRole> findAll(Map map) {
        return roleMapper.selectAll();
    }

    public List<XhxRole> selectRoleAll(Map map) {
        return roleMapper.selectRoleAll(map);
    }
}
