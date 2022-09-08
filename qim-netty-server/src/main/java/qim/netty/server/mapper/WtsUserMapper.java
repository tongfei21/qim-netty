package qim.netty.server.mapper;

import qim.netty.server.model.WtsUser;

public interface WtsUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WtsUser record);

    int insertSelective(WtsUser record);

    WtsUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WtsUser record);

    int updateByPrimaryKey(WtsUser record);

    WtsUser selectBynameandPwd(WtsUser user);
}