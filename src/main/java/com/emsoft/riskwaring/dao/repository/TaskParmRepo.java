package com.emsoft.riskwaring.dao.repository;

import com.emsoft.riskwaring.dto.TaskParmDto;

import java.util.List;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 15:22
 * @Description:
 */
public interface TaskParmRepo {
    //删除结果字典
    void delete(String idList);
    //增加结果字典
    void add(List<TaskParmDto> dtoList);
    //查询字典
    List<TaskParmDto> query(String modelCode);
}
