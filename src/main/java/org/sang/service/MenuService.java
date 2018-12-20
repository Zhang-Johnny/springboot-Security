package org.sang.service;

import org.sang.bean.XhxMenu;
import org.sang.common.SnowFlake;
import org.sang.common.UserUtils;
import org.sang.mapper.XhxMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sang on 2017/12/28.
 */
@Service
@Transactional
public class MenuService {
    @Autowired
    XhxMenuMapper menuMapper;

    public List<XhxMenu> getAllMenu(){
        return menuMapper.getAllMenu();
    }

    public List<XhxMenu> getMenusByUserId() {
        return menuMapper.getMenusByUserId(UserUtils.getCurrentUser().getId());
    }

    public List<XhxMenu> menuTree() {
        return menuMapper.menuTree();
    }

    public List<Long> getMenusByRid(Long rid) {
        return menuMapper.getMenusByRid(rid);
    }

    public int deleteByPrimaryKey(Long id){return menuMapper.deleteByPrimaryKey(id);};

    public int insert(XhxMenu record){return menuMapper.insert(record);}

    public int insertSelective(Map menuMap){
        menuMap.put("id",SnowFlake.getSnowFlakeId("xhx_menu"));
        menuMap.put("createTime",new Date());
        menuMap.put("updateTime",new Date());
        return menuMapper.insertSelective(menuMap);
    }

    public int updateByPrimaryKeySelective(Map menuMap){
        menuMap.put("updateTime",new Date());
        return menuMapper.updateByPrimaryKeySelective(menuMap);
    }

    public int updateByPrimaryKey(XhxMenu record){
        record.setUpdateTime(new Date());
        return menuMapper.updateByPrimaryKey(record);}

    public List<XhxMenu> selectAllMenu() {
        return menuMapper.selectAllMenu();
    }


}
