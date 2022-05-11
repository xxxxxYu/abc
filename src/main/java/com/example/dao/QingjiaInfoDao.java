package com.example.dao;

import com.example.entity.QingjiaInfo;
import com.example.vo.QingjiaInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface QingjiaInfoDao extends Mapper<QingjiaInfo> {
    List<QingjiaInfoVo> findByUserIdAndLevel(@Param("name") String name,
                                           @Param("level") Integer level,
                                           @Param("status") Integer status);
    List<QingjiaInfoVo> findAll();

    public int selectNumber(String name,Long firthDayLongtime,Long lastDayLongtime);
}
