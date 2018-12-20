package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.XhxRole;
import org.sang.bean.XhxUser;

import java.util.List;
import java.util.Map;

public interface XhxUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxUser record);

    int insertSelective(XhxUser record);

    XhxUser selectByPrimaryKey(Long id);

   int updateByPrimaryKeySelective(XhxUser record);

    int updateByPrimaryKey(XhxUser record);

    //function
    XhxUser QueryByUsername(String username);

    List<XhxRole> getRolesByUserId(Long id);

    int UserReg(@Param("username") String username, @Param("password") String password);

    List<XhxUser> getUsersByKeywords(@Param("keywords") String keywords);

    int deleteRoleByUserId(Long hrId);

    int addRolesForUser(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

     XhxUser getUserById(Long hrId);

   int deleteUser(Long hrId);

    List<XhxUser> getAllUser(@Param("currentId") Long currentId);

    int checkPhone(String bindMobile);

    int checkUsername(String username);//验证用户是否存在

    int checkMobile(Map map);//根据用户名和手机号去数据库查询，判断有无结果

    int updatePassword(XhxUser xhxUser);

    List<XhxUser> findAll(Map map);

    int count(Map map);

    List<XhxUser> findByName(String username);

    List<Long> selectByParentId(Long parentId);

    List<Integer> selectUserIdByUserName(String userName);

    List<String> selectNameZhByParentId(Map map);

    List<XhxUser> selectRolesByParentId(Map map);

    int updateUserIpSelective(XhxUser xhxuser);

    XhxUser QueryByUsernameandpassword(XhxUser xhxuser);
}