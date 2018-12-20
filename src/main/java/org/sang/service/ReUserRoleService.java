package org.sang.service;

import org.sang.bean.XhxReUserRole;
import org.sang.bean.XhxRole;
import org.sang.common.SnowFlake;
import org.sang.common.Utils;
import org.sang.mapper.XhxReUserRoleMapper;
import org.sang.mapper.XhxRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReUserRoleService {
    @Autowired
    XhxReUserRoleMapper mapper;

    @Autowired
    XhxRoleMapper roleMapper;

    public int insertSelective(XhxReUserRole record) {
        if(Utils.isBlankEmpty(record.getId())){
            XhxRole xhxRole=roleMapper.selectByName("NODE_ordi"); //普通会员 默认
            record.setId(SnowFlake.getSnowFlakeId("xhx_re_user_role"));
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            record.setEndTime(new Date());
            record.setStartTime(record.getEndTime());
            record.setRoleId(xhxRole.getId());
        }
        return mapper.insertSelective(record);
    }
    public int insertSelectiveGroupUser(XhxReUserRole record) {
        if(Utils.isBlankEmpty(record.getId())){
            XhxRole xhxRole=roleMapper.selectByName("NODE_ordi_client"); //普通会员 默认
            record.setId(SnowFlake.getSnowFlakeId("xhx_re_user_role"));
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            record.setEndTime(new Date());
            record.setStartTime(record.getEndTime());
            record.setRoleId(xhxRole.getId());
        }
        return mapper.insertSelective(record);
    }
    //复制主账户的权限到子账户
    public int insertSelective(XhxReUserRole record,Long parentid) {
        if(Utils.isBlankEmpty(record.getId())){
            XhxReUserRole xhxReUserRole=mapper.selectByUserId(parentid);
            String name=roleMapper.selectByPrimaryKey(xhxReUserRole.getRoleId()).getName().replaceAll("_client","");
            XhxRole xhxRole=roleMapper.selectByName(name); //克隆权限
            record.setId(SnowFlake.getSnowFlakeId("xhx_re_user_role"));
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            record.setEndTime(xhxReUserRole.getEndTime());
            record.setStartTime(xhxReUserRole.getStartTime());
            record.setRoleId(xhxRole.getId());
        }
        return mapper.insertSelective(record);
    }

    //复制主账户的权限到子账户
    public int updateSelective(XhxReUserRole record,Long parentid) {
        if(Utils.isBlankEmpty(record.getId())){
            XhxReUserRole xhxReUserRole=mapper.selectByUserId(parentid);
            String name=roleMapper.selectByPrimaryKey(xhxReUserRole.getRoleId()).getName().replaceAll("_client","");
            XhxRole xhxRole=roleMapper.selectByName(name); //克隆权限
            record.setId(SnowFlake.getSnowFlakeId("xhx_re_user_role"));
            record.setUpdateTime(new Date());
            record.setEndTime(xhxReUserRole.getEndTime());
            record.setStartTime(xhxReUserRole.getStartTime());
            record.setRoleId(xhxRole.getId());
        }
        return mapper.updateByPrimaryKeySelective(record);
    }

    public List<XhxReUserRole> selectRoleId(Long userId) {
        return mapper.selectByroleUserList(userId);
    }

    public int insertSelective(Map<String, Object> paramMap) {
        List<Long> roleIdList = (List<Long>) paramMap.get("roleIds");
        int i=0;
        int j=roleIdList.size();
        for (Long roleId : roleIdList) {
            paramMap.put("roleId",roleId);
            paramMap.put("id",SnowFlake.getSnowFlakeId("xhx_re_user_role"));
            paramMap.put("createTime",new Date());
            paramMap.put("updateTime",new Date());
            paramMap.put("endTime",new Date());
            paramMap.put("startTime",new Date());
            i += mapper.insertUserRoleSelective(paramMap);
        }
        if(i==j){
            return 1;
        }
        return -1;
    }

    public XhxReUserRole queryRoleIdsByUserId(Long id) {
        return mapper.selectByUserId(id);
    }

    public int deleteUserRolesSelective(Map<String, Object> paramMap) {
        return mapper.deleteUserRolesSelective(paramMap);
    }

    public List<Long> selectRoleIdsByUserId(Long userId) {
        return mapper.selectRoleIdsByUserId(userId);
    }
}
