package com.example.dao;

import com.example.entity.QingjiaInfo;
import com.example.entity.XiujiaInfo;
import com.example.vo.XiujiaInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface XiujiaInfoDao extends Mapper<XiujiaInfo> {
    List<XiujiaInfoVo> findByUserIdAndLevel(@Param("name") String name,
                                           @Param("level") Integer level,
                                           @Param("status") Integer status);
    List<XiujiaInfoVo> findAll();
    public int selectNumber(String name,Long firthDayLongtime,Long lastDayLongtime);

}
