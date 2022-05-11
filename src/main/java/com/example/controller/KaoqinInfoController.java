package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.example.service.*;
import com.example.vo.CanshuInfoVo;
import com.example.vo.KaoqinInfoVo;
import com.example.vo.KaoqinInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/kaoqinInfo")
public class KaoqinInfoController {
    @Resource
    private KaoqinInfoService kaoqinInfoService;

    @Resource
    private CanshuInfoService canshuInfoService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private QingjiaInfoService qingjiaInfoService;
    @Resource
    private XiujiaInfoService xiujiaInfoService;

    @GetMapping("/shangban")
    public Result<KaoqinInfo> shangbanDaka(HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录");
        }
        Long userId = user.getId();
        List<KaoqinInfoVo> all = kaoqinInfoService.findByUserId(userId);

        String shangban = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String today = shangban.split(" ")[0];
        String time = shangban.split(" ")[1];

        System.out.println("s:" + shangban);
        System.out.println("t:" + today);
        System.out.println("time:" + time);

//        s:2022-03-30 09:56:51
//        t:2022-03-30
//        time:09:56:51


        List<KaoqinInfoVo> list = all.stream().filter(x -> x.getShangban().contains(today)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(list)) {
//            throw new CustomException("1001", "请勿重复打卡");
        }
        KaoqinInfo info = new KaoqinInfo();

        CanshuInfoVo canshu = canshuInfoService.findAll().get(0);
        if (time.compareTo(canshu.getShangban()) < 0) {
            info.setShangbanStatus("上班迟到");
        } else {
            info.setShangbanStatus("上班正常");
        }

        info.setName(user.getName());
        info.setShangban(shangban);
        info.setUserId(userId);
        kaoqinInfoService.add(info);
        return Result.success(info);
    }

    @GetMapping("/xiaban")
    public Result<KaoqinInfo> xiabandaka(HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录");
        }
        Long userId = user.getId();
        List<KaoqinInfoVo> all = kaoqinInfoService.findByUserId(userId);
        String xiaban = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String today = xiaban.split(" ")[0];
        String time = xiaban.split(" ")[1];
        List<KaoqinInfoVo> shangban = all.stream().filter(x -> x.getShangban().contains(today)).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(shangban)) {
            throw new CustomException("1001", "请先上班打卡");
        }
        KaoqinInfoVo info = shangban.get(0);

        CanshuInfoVo canshu = canshuInfoService.findAll().get(0);
        if (time.compareTo(canshu.getXiaban()) < 0) {
            info.setXiabanStatus("下班早退");
        } else {
            info.setXiabanStatus("下班正常");
        }

        info.setXiaban(xiaban);
        kaoqinInfoService.update(info);
        return Result.success(info);
    }

    @PostMapping
    public Result<KaoqinInfo> add(@RequestBody KaoqinInfoVo kaoqinInfo) {
        kaoqinInfoService.add(kaoqinInfo);
        return Result.success(kaoqinInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        kaoqinInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody KaoqinInfoVo kaoqinInfo) {
        kaoqinInfoService.update(kaoqinInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<KaoqinInfo> detail(@PathVariable Long id) {
        KaoqinInfo kaoqinInfo = kaoqinInfoService.findById(id);
        return Result.success(kaoqinInfo);
    }

    @GetMapping
    public Result<List<KaoqinInfoVo>> all() {
        return Result.success(kaoqinInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<KaoqinInfoVo>> page(@PathVariable String name,
                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "8") Integer pageSize,
                                               HttpServletRequest request) {
        return Result.success(kaoqinInfoService.findPage(name, pageNum, pageSize, request));
    }

    /**
     * 批量通过excel添加信息
     *
     * @param file excel文件
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {

        List<KaoqinInfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(KaoqinInfo.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            // 处理一下空数据
            List<KaoqinInfo> resultList = infoList.stream().filter(x -> ObjectUtil.isNotEmpty(x.getName())).collect(Collectors.toList());
            for (KaoqinInfo info : resultList) {
                kaoqinInfoService.add(info);
            }
        }
        return Result.success();
    }

    @GetMapping("/getExcelModel")
    public void getExcelModel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("name", "");
        row.put("shangban", "");
        row.put("xiaban", "");
        row.put("shangbanStatus", "");
        row.put("xiabanStatus", "");
        row.put("userId", 1);

        List<Map<String, Object>> list = CollUtil.newArrayList(row);

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=kaoqinInfoModel.xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }


    /**
     * 查询考勤记录
     *
     * @param
     * @throws
     */
    @GetMapping("/select_by_userId")
    public Result findInfoByUserId(HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        Long userId = user.getId();
        String dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        kaoqinInfoService.findInfoByUserId(userId, dayTime);
        return Result.success();
    }

    @GetMapping("/shangbanbuka")
    public Result<KaoqinInfo> shangbanbuka(HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录");
        }
        Long userId = user.getId();
        List<KaoqinInfoVo> all = kaoqinInfoService.findByUserId(userId);

        String shangban = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String today = shangban.split(" ")[0];
        String time = shangban.split(" ")[1];
        System.out.println("s:" + shangban);
        System.out.println("t:" + today);
        System.out.println("time:" + time);


        List<KaoqinInfoVo> list = all.stream().filter(x -> x.getShangban().contains(today)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(list)) {
//            throw new CustomException("1001", "请勿重复打卡");
        }
        KaoqinInfo info = new KaoqinInfo();

        CanshuInfoVo canshu = canshuInfoService.findAll().get(0);
        if (time.compareTo(canshu.getShangban()) < 0) {
            info.setShangbanStatus("上班迟到");
        } else {
            info.setShangbanStatus("上班正常");
        }

        info.setName(user.getName());
        info.setShangban(shangban);
        info.setUserId(userId);
        kaoqinInfoService.add(info);
        return Result.success(info);
    }


    @PostMapping("/xiabanbuka")
    public Result<KaoqinInfo> xiabanbuka(HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录");
        }
        Long userId = user.getId();
        List<KaoqinInfoVo> all = kaoqinInfoService.findByUserId(userId);

        String xiaban = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String today = xiaban.split(" ")[0];
        String time = xiaban.split(" ")[1];


        List<KaoqinInfoVo> shangban = all.stream().filter(x -> x.getShangban().contains(today)).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(shangban)) {
            throw new CustomException("1001", "请先上班打卡");
        }
        KaoqinInfoVo info = shangban.get(0);

        CanshuInfoVo canshu = canshuInfoService.findAll().get(0);
        if (time.compareTo(canshu.getXiaban()) < 0) {
            info.setXiabanStatus("下班早退");
        } else {
            info.setXiabanStatus("下班正常");
        }

        info.setXiaban(xiaban);
        kaoqinInfoService.update(info);
        return Result.success(info);

    }

    @GetMapping("/selectKaoQin")
    public UserInfo selectKaoQin(HttpServletRequest request) {

        Account user = (Account) request.getSession().getAttribute("user");
        Long userId = user.getId();
//        处理时间问题
        LocalDate date = LocalDate.now();
        LocalDate localDateMonth = date.minusMonths(0);
        LocalDate firstDay = localDateMonth.with(TemporalAdjusters.firstDayOfMonth()); // 获取当前月的第一天
        LocalDate lastDateMonth = date.minusMonths(-1);
        LocalDate lastDay = lastDateMonth.with(TemporalAdjusters.firstDayOfMonth()); // 获取下个月的第一天
        Long firthDayLongtime = firstDay.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli() / 1000;
        Long lastDayLongtime = lastDay.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli() / 1000;
//        查询出勤天数以及迟到早退天数
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setFirstDay(firthDayLongtime);
        userInfo.setLastDay(lastDayLongtime);
        UserInfo userInfo1 = userInfoService.selectKaoQinInfo(userInfo);
//查询请假天数

        int qingJiaNumber = qingjiaInfoService.selectNumber(user.getName(),firthDayLongtime,lastDayLongtime);

//查询休假天数

        int xiuJiaNumber = xiujiaInfoService.selectNumber(user.getName(),firthDayLongtime,lastDayLongtime);
        userInfo1.setQingJiaNumber(qingJiaNumber);
        userInfo1.setXiuJiaNumber(xiuJiaNumber);
        return userInfo1;
    }


}
