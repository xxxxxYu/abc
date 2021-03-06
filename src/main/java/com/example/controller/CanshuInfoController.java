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
import com.example.entity.CanshuInfo;
import com.example.service.*;
import com.example.vo.CanshuInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/canshuInfo")
public class CanshuInfoController {
    @Resource
    private CanshuInfoService canshuInfoService;

    @PostMapping
    public Result<CanshuInfo> add(@RequestBody CanshuInfoVo canshuInfo) {
        canshuInfoService.add(canshuInfo);
        return Result.success(canshuInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        canshuInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody CanshuInfoVo canshuInfo) {
        canshuInfoService.update(canshuInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<CanshuInfo> detail(@PathVariable Long id) {
        CanshuInfo canshuInfo = canshuInfoService.findById(id);
        return Result.success(canshuInfo);
    }

    @GetMapping
    public Result<List<CanshuInfoVo>> all() {
        return Result.success(canshuInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<CanshuInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(canshuInfoService.findPage(name, pageNum, pageSize, request));
    }

    /**
    * ????????????excel????????????
    * @param file excel??????
    * @throws IOException
    */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {

        List<CanshuInfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(CanshuInfo.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            // ?????????????????????
            List<CanshuInfo> resultList = infoList.stream().filter(x -> ObjectUtil.isNotEmpty(x.getName())).collect(Collectors.toList());
            for (CanshuInfo info : resultList) {
                canshuInfoService.add(info);
            }
        }
        return Result.success();
    }

    @GetMapping("/getExcelModel")
    public void getExcelModel(HttpServletResponse response) throws IOException {
        // 1. ??????excel
        Map<String, Object> row = new LinkedHashMap<>();
		row.put("name", "");
		row.put("shangban", "");
		row.put("xiaban", "");

        List<Map<String, Object>> list = CollUtil.newArrayList(row);

        // 2. ???excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=canshuInfoModel.xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }
}
