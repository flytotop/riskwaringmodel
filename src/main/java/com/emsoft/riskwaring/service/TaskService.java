package com.emsoft.riskwaring.service;

import com.emsoft.riskwaring.dto.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 16:17
 * @Description:
 */
public interface  TaskService {
    //预警模型列表
    List<ModelDto> modelList();
    //任务信息详情
    ModelDto taskDetail(String modelname);
    //任务结果
    Map taskResult(TaskResultDto dto);
    //任务结果明细
    Map taskResultDetail(TaskResultDetailDto detailDto);
    //执行任务
    Map executeTask(ExecuteTaskDto dto);
    //任务结果明细
    byte[] resultDetailDownload();

}
