package com.example.dao;

import com.example.entity.KaoqinInfo;
import com.example.vo.KaoqinInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

@Repository
public interface KaoqinInfoDao extends Mapper<KaoqinInfo> {
    public List<KaoqinInfoVo> findByName(@Param("name") String name);

    public List<KaoqinInfoVo> findVoByCondition(@Param("id") Long id, @Param("name") String name);


    public Integer count();


    public List<KaoqinInfoVo> findByUserIdAndTime(Long userId);

    public Integer findInfoByUserId(Long userId, String date);


    public int updateStatusById(Long id);

    public List<KaoqinInfo> findByKaoQinInfo(KaoqinInfo kaoqinInfo);





}
