package qim.netty.server.service;

import qim.netty.server.mapper.WtsUserMapper;
import qim.netty.server.model.WtsUser;

public class WtsUserService{


    private WtsUserMapper wtsUserMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return wtsUserMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(WtsUser record) {
        return wtsUserMapper.insert(record);
    }

    
    public int insertSelective(WtsUser record) {
        return wtsUserMapper.insertSelective(record);
    }

    
    public WtsUser selectByPrimaryKey(Integer id) {
        return wtsUserMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(WtsUser record) {
        return wtsUserMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(WtsUser record) {
        return wtsUserMapper.updateByPrimaryKey(record);
    }

}
