package qim.netty.server.service;

import qim.netty.server.mapper.WtsChatMapper;
import qim.netty.sdk.model.WtsChat;

public class WtsChatService{


    private WtsChatMapper wtsChatMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return wtsChatMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(WtsChat record) {
        return wtsChatMapper.insert(record);
    }

    
    public int insertSelective(WtsChat record) {
        return wtsChatMapper.insertSelective(record);
    }

    
    public WtsChat selectByPrimaryKey(Integer id) {
        return wtsChatMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(WtsChat record) {
        return wtsChatMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(WtsChat record) {
        return wtsChatMapper.updateByPrimaryKey(record);
    }

}
