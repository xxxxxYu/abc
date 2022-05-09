package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.exception.CustomException;
import com.example.entity.QingjiaInfo;
import com.example.vo.QingjiaInfoVo;
import com.example.service.QingjiaInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/qingjiaInfo")
public class QingjiaInfoController {
    @Resource
    private QingjiaInfoService qingjiaInfoService;

    @PostMapping
    public Result<QingjiaInfo> add(@RequestBody QingjiaInfo info, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "session失效，请重新登录");
        }
        info.setUserName(user.getName());
        info.setLevel(user.getLevel());

        // 状态设置为未提交
        info.setStatus("未提交");
        qingjiaInfoService.add(info);
        return Result.success(info);
    }

    @PostMapping("/submit")
    public Result submit(@RequestBody QingjiaInfo info) {
        // 先更新状态为待审核
        info.setStatus("待审核");
        info.setReason("");
        info.setVerifyName("");
        qingjiaInfoService.update(info);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        qingjiaInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody QingjiaInfo info, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.error("1001", "session已失效，请重新登录");
        }
        if (user.getLevel() == 1) {
            info.setVerifyName(user.getName());
        } else {
            info.setStatus("未提交");
            info.setReason("");
            info.setVerifyName("");
        }
        qingjiaInfoService.update(info);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<QingjiaInfo> detail(@PathVariable Long id) {
        QingjiaInfo info = qingjiaInfoService.findById(id);
        return Result.success(info);
    }

    @GetMapping
    public Result<List<QingjiaInfoVo>> all() {
        return Result.success(qingjiaInfoService.findAll());
    }

    @GetMapping("/page")
    public Result<PageInfo<QingjiaInfoVo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(qingjiaInfoService.findPage(pageNum, pageSize, request));
    }

}
