package org.sang.service;

import org.sang.common.SnowFlake;
import org.sang.mapper.XhxReRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MenuRoleService {
    @Autowired
    XhxReRoleMenuMapper menuRoleMapper;

   public int updateMenuRole(Long rid, Long[] mids) {
        menuRoleMapper.deleteMenuByRid(rid);
        if (mids.length == 0) {
            return 0;
        }
        Map map = new HashMap();
        map.put("roleId",rid);
        map.put("mId",mids);
        return menuRoleMapper.addMenu(map);
    }

    public int insertSelective(Map<String, Object> paramMap) {
        List<Long> mIdList = (List<Long>) paramMap.get("mIds");
        int i = 0;
        int j = mIdList.size();
        for (Long mId : mIdList) {
            paramMap.put("mId",mId);
            paramMap.put("id", SnowFlake.getSnowFlakeId("xhx_re_role_menu"));
            paramMap.put("createTime",new Date());
            paramMap.put("updateTime",new Date());
            i += menuRoleMapper.insertMenuRole(paramMap);
        }
        if(i==j){
            return 1;
        }
        return -1;
    }

    public List<Long> selectMenuIdByRoleId(Long roleId) {
        return menuRoleMapper.selectMenuIdByRoleId(roleId);
    }
}
