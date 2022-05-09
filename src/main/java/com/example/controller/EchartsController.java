package com.example.controller;

import cn.hutool.json.JSONObject;
import com.example.common.Result;
import com.example.entity.*;
import com.example.service.*;
import com.example.vo.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

	@Resource
	private AdminInfoService adminInfoService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private CanshuInfoService canshuInfoService;
	@Resource
	private KaoqinInfoService kaoqinInfoService;


    @GetMapping("/get/{modelName}")
    Result<List<EchartsData>> getEchartsData(@PathVariable String modelName) {
        List<EchartsData> list = new ArrayList<>();
        switch (modelName) {
			case "adminInfo":
				List<AdminInfoVo> adminInfoList = adminInfoService.findAll();
				List<AdminInfo> adminInfoMaleList = adminInfoList.stream().filter(x -> "男".equals(x.getSex())).collect(Collectors.toList());
				Map<String, Integer> adminInfoMap = new HashMap<>(2);
				adminInfoMap.put("男", adminInfoMaleList.size());
				adminInfoMap.put("女", adminInfoList.size() - adminInfoMaleList.size());
				getPieData("管理员", list, adminInfoMap);
				getBarData("管理员", list, adminInfoMap);

				break;
			case "userInfo":
				List<UserInfoVo> userInfoList = userInfoService.findAll();
				List<UserInfo> userInfoMaleList = userInfoList.stream().filter(x -> "男".equals(x.getSex())).collect(Collectors.toList());
				Map<String, Integer> userInfoMap = new HashMap<>(2);
				userInfoMap.put("男", userInfoMaleList.size());
				userInfoMap.put("女", userInfoList.size() - userInfoMaleList.size());
				getPieData("员工", list, userInfoMap);
				getBarData("员工", list, userInfoMap);

				break;

			case "canshuInfo":
				List<CanshuInfoVo> canshuInfoList = canshuInfoService.findAll();
				Map<String, Integer> canshuInfoMap = new HashMap<>(2);
				for (CanshuInfo canshuInfo : canshuInfoList) {
					Integer value = canshuInfoMap.get(canshuInfo.getName());
					if (value != null && value != 0) {
						canshuInfoMap.put(canshuInfo.getName(), value + 1);
					} else {
						canshuInfoMap.put(canshuInfo.getName(), 1);
					}
				}
				getPieData("考勤参数", list, canshuInfoMap);
				getBarData("考勤参数", list, canshuInfoMap);

				break;
			case "kaoqinInfo":
				List<KaoqinInfoVo> kaoqinInfoList = kaoqinInfoService.findAll();
				Map<String, Integer> kaoqinInfoMap = new HashMap<>(2);
				for (KaoqinInfo kaoqinInfo : kaoqinInfoList) {
					Integer value = kaoqinInfoMap.get(kaoqinInfo.getName());
					if (value != null && value != 0) {
						kaoqinInfoMap.put(kaoqinInfo.getName(), value + 1);
					} else {
						kaoqinInfoMap.put(kaoqinInfo.getName(), 1);
					}
				}
				getPieData("考勤", list, kaoqinInfoMap);
				getBarData("考勤", list, kaoqinInfoMap);

				break;

            default:
                break;
        }
        return Result.success(list);
    }

    @GetMapping("/options")
        Result<List<Map<String, String>>> getOptions() {
        List<Map<String, String>> options = new ArrayList<>();

		Map<String, String> optionMap1 = new HashMap<>();
		optionMap1.put("value", "adminInfo");
		optionMap1.put("label", "管理员信息");
		options.add(optionMap1);
		Map<String, String> optionMap2 = new HashMap<>();
		optionMap2.put("value", "userInfo");
		optionMap2.put("label", "员工信息");
		options.add(optionMap2);
		Map<String, String> optionMap3 = new HashMap<>();
		optionMap3.put("value", "canshuInfo");
		optionMap3.put("label", "考勤参数信息");
		options.add(optionMap3);
		Map<String, String> optionMap4 = new HashMap<>();
		optionMap4.put("value", "kaoqinInfo");
		optionMap4.put("label", "考勤信息");
		options.add(optionMap4);

        return Result.success(options);
    }

    private void getPieData(String name, List<EchartsData> pieList, Map<String, Integer> dataMap) {
        EchartsData pieData = new EchartsData();
        EchartsData.Series series = new EchartsData.Series();

        Map<String, String> titleMap = new HashMap<>(2);
        titleMap.put("text", name + "信息");
        pieData.setTitle(titleMap);

        series.setName(name + "比例");
        series.setType("pie");
        series.setRadius("55%");

        List<Object> objects = new ArrayList<>();
        List<Object> legendList = new ArrayList<>();
        for (String key : dataMap.keySet()) {
            Integer value = dataMap.get(key);
            objects.add(new JSONObject().putOpt("name", key).putOpt("value", value));
            legendList.add(key);
        }
        series.setData(objects);

        pieData.setSeries(Collections.singletonList(series));
        Map<String, Boolean> map = new HashMap<>();
        map.put("show", true);
        pieData.setTooltip(map);

        Map<String, Object> legendMap = new HashMap<>(4);
        legendMap.put("orient", "vertical");
        legendMap.put("x", "left");
        legendMap.put("y", "center");
        legendMap.put("data", legendList);
        pieData.setLegend(legendMap);

        pieList.add(pieData);
    }

    private void getBarData(String name, List<EchartsData> barList, Map<String, Integer> dataMap) {
        EchartsData barData = new EchartsData();
        EchartsData.Series series = new EchartsData.Series();

        List<Object> seriesObjs = new ArrayList<>();
        List<Object> xAxisObjs = new ArrayList<>();
            for (String key : dataMap.keySet()) {
            Integer value = dataMap.get(key);
            xAxisObjs.add(key);
            seriesObjs.add(value);
        }

        series.setType("bar");
        series.setName(name);
        series.setData(seriesObjs);
        barData.setSeries(Collections.singletonList(series));

        Map<String, Object> xAxisMap = new HashMap<>(1);
        xAxisMap.put("data", xAxisObjs);
        barData.setxAxis(xAxisMap);

        barData.setyAxis(new HashMap<>());

        Map<String, Object> legendMap = new HashMap<>(1);
        legendMap.put("data", Collections.singletonList(name));
        barData.setLegend(legendMap);

        Map<String, Boolean> map = new HashMap<>(1);
        map.put("show", true);
        barData.setTooltip(map);

        Map<String, String> titleMap = new HashMap<>(1);
        titleMap.put("text", name + "信息");
        barData.setTitle(titleMap);

        barList.add(barData);
    }
}
