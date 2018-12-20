package org.sang.mapper;

import org.sang.bean.XhxWalletRecord;
import java.util.List;
import java.util.Map;

public interface XhxWalletRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XhxWalletRecord record);

    int insertSelective(XhxWalletRecord record);

    XhxWalletRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XhxWalletRecord record);

    int updateByPrimaryKey(XhxWalletRecord record);

    int count(Map map);

    List<XhxWalletRecord> findAll(Map map);

    List<XhxWalletRecord> selectByUserIdList(Map map);

    List<XhxWalletRecord> selectByParam(Map map);
}