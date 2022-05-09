package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.KaoqinInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.KaoqinInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.KaoqinInfoVo;
import com.example.entity.UserInfo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class KaoqinInfoService {

    @Resource
    private KaoqinInfoDao kaoqinInfoDao;

    public KaoqinInfo add(KaoqinInfo kaoqinInfo) {
        kaoqinInfoDao.insertSelective(kaoqinInfo);
        return kaoqinInfo;
    }

    public void delete(Long id) {
        kaoqinInfoDao.updateStatusById(id);
    }

    public void update(KaoqinInfo kaoqinInfo) {
        kaoqinInfoDao.updateByPrimaryKeySelective(kaoqinInfo);
    }

    public KaoqinInfo findById(Long id) {
        return kaoqinInfoDao.selectByPrimaryKey(id);
    }

    public List<KaoqinInfoVo> findAll() {
        return kaoqinInfoDao.findByName("all");
    }

    public PageInfo<KaoqinInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<KaoqinInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<KaoqinInfoVo> findAllPage(HttpServletRequest request, String name) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (2 == user.getLevel()) {
            return kaoqinInfoDao.findVoByCondition(user.getId(), name);
        } else {
            return kaoqinInfoDao.findVoByCondition(null, name);
        }

    }

    public List<KaoqinInfoVo> findByUserId(Long userId) {
        return kaoqinInfoDao.findByUserIdAndTime(userId);
    }

    public Integer findInfoByUserId(Long userId, String date) {
        return kaoqinInfoDao.findInfoByUserId(userId, date);
    }
    public List<KaoqinInfo> findByKaoQinInfo(KaoqinInfo kaoqinInfo) {
        return kaoqinInfoDao.findByKaoQinInfo(kaoqinInfo);
    }


}
