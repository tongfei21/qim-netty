package qim.netty.server.mapper;

import qim.netty.sdk.model.WtsChat;

public interface WtsChatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WtsChat record);

    int insertSelective(WtsChat record);

    WtsChat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WtsChat record);

    int updateByPrimaryKey(WtsChat record);
}