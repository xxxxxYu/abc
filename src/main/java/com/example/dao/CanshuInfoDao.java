package com.example.dao;

import com.example.entity.CanshuInfo;
import com.example.vo.CanshuInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CanshuInfoDao extends Mapper<CanshuInfo> {
    List<CanshuInfoVo> findByName(@Param("name") String name);
    
    
    
    Integer count();
}
