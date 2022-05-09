package com.example.service;

import com.example.dao.XiujiaInfoDao;
import com.example.entity.Account;
import com.example.entity.QingjiaInfo;
import com.example.entity.XiujiaInfo;
import com.example.vo.XiujiaInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class XiujiaInfoService {

    @Resource
    private XiujiaInfoDao xiujiaInfoDao;

    public XiujiaInfo add(XiujiaInfo info) {
    xiujiaInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        xiujiaInfoDao.deleteByPrimaryKey(id);
    }

    public void update(XiujiaInfo info) {
        xiujiaInfoDao.updateByPrimaryKeySelective(info);
    }

    public XiujiaInfo findById(Long id) {
        return xiujiaInfoDao.selectByPrimaryKey(id);
    }
    public List<XiujiaInfoVo> findAll() {
        return xiujiaInfoDao.findAll();
    }

    public PageInfo<XiujiaInfoVo> findPage(Integer pageNum, Integer pageSize, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        if (account == null) {
            return PageInfo.of(new ArrayList<>());
        }
        String name = account.getName();
        Integer level = account.getLevel();
        PageHelper.startPage(pageNum, pageSize);
        List<XiujiaInfoVo> info;
        if (1 == level) {
            info = xiujiaInfoDao.findByUserIdAndLevel(null, null, 1);
        } else {
            info = xiujiaInfoDao.findByUserIdAndLevel(name, level, 0);
        }
        return PageInfo.of(info);
    }


    public int selectNumber(XiujiaInfo xiujiaInfo){
        return xiujiaInfoDao.selectNumber(xiujiaInfo);
    }
}
