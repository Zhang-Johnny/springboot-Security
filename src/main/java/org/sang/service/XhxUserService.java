package org.sang.service;

import org.sang.bean.XhxGroupUser;
import org.sang.bean.XhxReUserRole;
import org.sang.bean.XhxUser;
import org.sang.common.SnowFlake;
import org.sang.common.UserUtils;
import org.sang.mapper.XhxGroupUserMapper;
import org.sang.mapper.XhxReUserRoleMapper;
import org.sang.mapper.XhxUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XhxUserService implements UserDetailsService {
    @Autowired
    XhxUserMapper ptuser;

    @Autowired
    XhxGroupUserMapper gpuser;

    @Autowired
    ReUserRoleService reUserRole;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        XhxUser user = ptuser.QueryByUsername(s);
        if (user == null) {
            XhxGroupUser groupuser=gpuser.QueryByUsername(s);
            if(groupuser== null){
                throw new UsernameNotFoundException("用户名不对");
            }
            return groupuser;
        }
        return user;
    }

   public int XhxUserReg(XhxUser user) {
        //如果用户名存在，返回错误
        if (ptuser.QueryByUsername(user.getUsername()) != null) {
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());
        user.setPassword(encode);
        Long Id = SnowFlake.getSnowFlakeId("XhxUser");
        user.setId(Id);
        user.setLoginCount(0);
        user.setUserType(2);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        XhxReUserRole xhxReUserRole=new XhxReUserRole();
        xhxReUserRole.setUserId(user.getId());
        int i=ptuser.insertSelective(user);
        if(i==1){
          int re=reUserRole.insertSelective(xhxReUserRole);
          if(re==1){
              return 1;
          }else {
              return 0;
          }
        }else {
            return -1;
        }
    }

    public List<XhxUser> getXhxUserByKeywords(String keywords) {
        return ptuser.getUsersByKeywords(keywords);
    }

    public int updateXhxUser(XhxUser xhxuser) {
        xhxuser.setUpdateTime(new Date());
        return ptuser.updateByPrimaryKeySelective(xhxuser);
    }

    public int updateXhxUserIp(String ipAddress,String username) {
        XhxUser xhxuserold = new XhxUser();
        xhxuserold.setUsername(username);
        xhxuserold=ptuser.QueryByUsernameandpassword(xhxuserold);
        XhxGroupUser xhxGroupUser = gpuser.QueryByUsername(username);
        if(xhxuserold==null&&xhxGroupUser==null){
            return 0;
        }else if(xhxuserold!=null&&xhxGroupUser==null){
            XhxUser user = new XhxUser();
            Integer loginCount = xhxuserold.getLoginCount();
            user.setLoginCount((loginCount+1));
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(ipAddress);
            user.setUsername(username);
            user.setUpdateTime(new Date());
            return ptuser.updateUserIpSelective(user);
        }else if(xhxuserold==null&&xhxGroupUser!=null){
            XhxGroupUser groupUser = new XhxGroupUser();
            String loginCount = xhxGroupUser.getLoginCount();
            int loginCountInt = Integer.parseInt(loginCount);
            loginCount=String.valueOf(loginCountInt+1);
            groupUser.setLoginCount(loginCount);
            groupUser.setLastLoginTime(new Date());
            groupUser.setLastLoginIp(ipAddress);
            groupUser.setUsername(username);
            groupUser.setUpdateTime(new Date());
            return gpuser.updateByusernameSelective(groupUser);
        }
        return 0;
    }

    public int updateXhxUserRoles(Long userId, Long[] rids) {
        int i = ptuser.deleteRoleByUserId(userId);
        return ptuser.addRolesForUser(userId, rids);
    }

    public XhxUser getXhxUserById(Long id) {
        return ptuser.getUserById(id);
    }

   public int deleteXhxUser(Long id) {
        return ptuser.deleteUser(id);
    }

    public List<XhxUser> getAllXhxUserExceptAdmin() {
        return ptuser.getAllUser(UserUtils.getCurrentUser().getId());
    }
    public List<XhxUser> getAllXhxUser() {
        return ptuser.getAllUser(null);
    }

    public int checkPhone(String phonum) {
        return ptuser.checkPhone(phonum);
    }

    /*
    根据用户名验证该用户是否存在:数量为0代表不存在，否则存在。
     */
    public int checkUsername(String username) {
        return  ptuser.checkUsername(username);
    }

    /*
    根据用户名和手机号去数据库查询，判断有误结果
     */
    public int checkMobile(Map map) {
        return ptuser.checkMobile(map);
    }

    public int updateXhxUserPassword(XhxUser xhxUser) {
        return ptuser.updatePassword(xhxUser);
    }
    public int count(Map map){
        return ptuser.count(map);
    }

    public List<XhxUser> findAll(Map map){
        return ptuser.findAll(map);
    }

    public List<XhxUser> findByName(String username){
        return ptuser.findByName(username);
    }

    public List<Long> getUserIdByParentId(Long parentId) {
        return ptuser.selectByParentId(parentId);
    }

    public List<Integer> getUserIdByUserName(String userName) {
        return ptuser.selectUserIdByUserName(userName);
    }

    public int insertUser(XhxUser user) {
        return ptuser.insertSelective(user);
    }

    public String selectpassword(Long id) {
        return ptuser.selectByPrimaryKey(id).getPassword();
    }

    public List<String> findAllNameZh(Map map) {
        return ptuser.selectNameZhByParentId(map);
    }
    public List<XhxUser> findAllRoles(Map map) {
        return ptuser.selectRolesByParentId(map);
    }
}
