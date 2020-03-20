package com.emsoft.riskwaring.dao.repository;

import com.emsoft.riskwaring.dto.ModelDto;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 22:35
 * @Description:
 */
public interface ModelRepo {
    //任务模型查看
    List<ModelDto> modelquery();
    //新增任务模型
    void addModel(ModelDto dto);
    //删除任务模型
    void delete(String modelCode);
    //任务模型查看
    ModelDto modelquery(String modelCode);
    //任务模型查看
    ModelDto modelQueryByModelname(String modelName);



}
