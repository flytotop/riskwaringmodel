package com.emsoft.riskwaring.service;

import com.emsoft.riskwaring.dto.ModelDictDto;
import com.emsoft.riskwaring.dto.ModelDto;
import com.emsoft.riskwaring.dto.TaskParmDto;

import java.util.List;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 10:59
 * @Description:
 */
public interface ModelService {
    //查看模型
    List<ModelDto> queryModel();
    //查看模型
    ModelDto queryModel(String modelCode);
    //添加模型
    void addModel(ModelDto dto);
    //删除模型
    void delete(String modelCode);
    //查看结果字典
    List<ModelDictDto> querDict(String modelCode);
    //添加结果字典
    void addDict(List<ModelDictDto> dtoList);
    //删除结果字典
    void deleteDict(String idList);
    //查看模型任务参数
    List<TaskParmDto> querParm(String modelCode);
    //删除模型任务参数
    void deleteParm(String idList);
    //添加模型任务参数
    void addParm(List<TaskParmDto> dtoList);


}
