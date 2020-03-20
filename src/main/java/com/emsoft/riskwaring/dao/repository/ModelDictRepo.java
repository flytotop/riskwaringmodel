package com.emsoft.riskwaring.dao.repository;

import com.emsoft.riskwaring.dto.ModelDictDto;

import java.util.List;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 22:35
 * @Description:
 */
public interface ModelDictRepo {
    //删除结果字典
    void delete(String idList);
    //增加结果字典
    void add(List<ModelDictDto> dtoList);
    //查询字典
    List<ModelDictDto> query(String modelCode);
}
