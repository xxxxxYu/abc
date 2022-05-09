package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.CanshuInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.CanshuInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.CanshuInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class CanshuInfoService {

    @Resource
    private CanshuInfoDao canshuInfoDao;

    public CanshuInfo add(CanshuInfo canshuInfo) {
        canshuInfoDao.insertSelective(canshuInfo);
        return canshuInfo;
    }

    public void delete(Long id) {
        canshuInfoDao.deleteByPrimaryKey(id);
    }

    public void update(CanshuInfo canshuInfo) {
        canshuInfoDao.updateByPrimaryKeySelective(canshuInfo);
    }

    public CanshuInfo findById(Long id) {
        return canshuInfoDao.selectByPrimaryKey(id);
    }

    public List<CanshuInfoVo> findAll() {
        return canshuInfoDao.findByName("all");
    }

    public PageInfo<CanshuInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<CanshuInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<CanshuInfoVo> findAllPage(HttpServletRequest request, String name) {
		return canshuInfoDao.findByName(name);
    }

}
