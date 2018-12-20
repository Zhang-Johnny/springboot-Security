package org.sang.service;

import org.sang.bean.XhxGroupUser;
import org.sang.bean.XhxReUserRole;
import org.sang.common.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sang.mapper.XhxGroupUserMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XhxGroupUserService{
    @Autowired
    XhxGroupUserMapper ptuser;
    @Autowired
    ReUserRoleService reUserRole;

    public XhxGroupUser loadUserByUsername(String s) {
        XhxGroupUser user = ptuser.QueryByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        return user;
    }

    public int xhxUserReg(XhxGroupUser user) {
        //如果用户名存在，返回错误
        if (ptuser.QueryByUsername(user.getUsername()) != null) {
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());
        Long Id = SnowFlake.getSnowFlakeId("XhxGroupUser");
        user.setPassword(encode);
        user.setId(Id);
        if(ptuser.insertSelective(user)==1){
            XhxReUserRole xhxReUserRole=new XhxReUserRole();
            xhxReUserRole.setUserId(user.getId());
            int re=reUserRole.insertSelectiveGroupUser(xhxReUserRole);
            if(re==1){
                return 1;
            }else {
                return 0;
            }
        }else {
            return -1;
        }
    }

    public int xhxGroupUserReg(XhxGroupUser user) {
        //如果用户名存在，返回错误
        if (ptuser.QueryByUsername(user.getUsername()) != null) {
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());
        user.setPassword(encode);
        if(ptuser.insertSelective(user)==1){
            XhxReUserRole xhxReUserRole=new XhxReUserRole();
            xhxReUserRole.setUserId(user.getId());
            int re=reUserRole.insertSelectiveGroupUser(xhxReUserRole);
            if(re==1){
                return 1;
            }else {
                return 0;
            }
        }else {
            return -1;
        }
    }
    public int count(Map map){
        return ptuser.count(map);
    };

    public List<XhxGroupUser> findAll(Map map){
        return ptuser.findAll(map);
    }

    public XhxGroupUser findById(Long id){
        return ptuser.findById(id);
    }

    public int update(XhxGroupUser xhxGroupUser){
        xhxGroupUser.setUpdateTime(new Date());
        return ptuser.updateByPrimaryKeySelective(xhxGroupUser);
    }

    public int delete(Long id){
        return ptuser.deleteByPrimaryKey(id);
    }

    public int updateXhxUserPassword(XhxGroupUser xhxGroupUser) {
        xhxGroupUser.setUpdateTime(new Date());
        return ptuser.updatePassword(xhxGroupUser);
    }

    public int checkGroupUsername(String username) {
        return ptuser.checkGroupUsername(username);
    }

    public int checkMobile(Map map) {
        return ptuser.checkMobile(map);
    }
    public int updateXhxUserPasswordByid(XhxGroupUser xhxGroupUser) {
        xhxGroupUser.setUpdateTime(new Date());
        return ptuser.updatePasswordByid(xhxGroupUser);
    }

    public String selectpassword(Long id) {
        return ptuser.findById(id).getPassword();
    }
}
