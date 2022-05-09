package com.example.service;

import com.example.dao.QingjiaInfoDao;
import com.example.entity.Account;
import com.example.entity.QingjiaInfo;
import com.example.vo.QingjiaInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class QingjiaInfoService {

    @Resource
    private QingjiaInfoDao qingjiaInfoDao;

    public QingjiaInfo add(QingjiaInfo info) {
    qingjiaInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        qingjiaInfoDao.deleteByPrimaryKey(id);
    }

    public void update(QingjiaInfo info) {
        qingjiaInfoDao.updateByPrimaryKeySelective(info);
    }

    public QingjiaInfo findById(Long id) {
        return qingjiaInfoDao.selectByPrimaryKey(id);
    }
    public List<QingjiaInfoVo> findAll() {
        return qingjiaInfoDao.findAll();
    }

    public PageInfo<QingjiaInfoVo> findPage(Integer pageNum, Integer pageSize, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        if (account == null) {
            return PageInfo.of(new ArrayList<>());
        }
        String name = account.getName();
        Integer level = account.getLevel();
        PageHelper.startPage(pageNum, pageSize);
        List<QingjiaInfoVo> info;
        if (1 == level) {
            info = qingjiaInfoDao.findByUserIdAndLevel(null, null, 1);
        } else {
            info = qingjiaInfoDao.findByUserIdAndLevel(name, level, 0);
        }
        return PageInfo.of(info);
    }
    public int selectNumber(QingjiaInfo qingjiaInfo){
        return qingjiaInfoDao.selectNumber(qingjiaInfo);
    }

}
