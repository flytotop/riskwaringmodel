package com.emsoft.riskwaring.service.impl;

import com.alibaba.fastjson.JSON;
import com.emsoft.riskwaring.dao.repository.ModelDictRepo;
import com.emsoft.riskwaring.dao.repository.ModelRepo;
import com.emsoft.riskwaring.dao.repository.TaskParmRepo;
import com.emsoft.riskwaring.dto.ModelDictDto;
import com.emsoft.riskwaring.dto.ModelDto;
import com.emsoft.riskwaring.dto.TaskParmDto;
import com.emsoft.riskwaring.service.ModelService;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.json.JsonObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 11:18
 * @Description:
 */
@Service
@Log4j2
public class ModelServiceImpl implements ModelService {
    private final ModelRepo modelRepo;
    private final ModelDictRepo modelDictRepo;
    private final TaskParmRepo taskParmRepo;

    public ModelServiceImpl(ModelRepo modelRepo, ModelDictRepo modelDictRepo, TaskParmRepo taskParmRepo) {
        this.modelRepo = modelRepo;
        this.modelDictRepo = modelDictRepo;
        this.taskParmRepo = taskParmRepo;
    }

    @Override
    public List<ModelDto> queryModel() {
        return modelRepo.modelquery();
    }

    @Override
    public ModelDto queryModel(String modelCode) {
        ModelDto modelDto=modelRepo.modelquery(modelCode);
        return modelDto;

    }

    @Override
    public void addModel(ModelDto dto) {
         modelRepo.addModel(dto);
    }

    @Override
    public void delete(String modelCode) {
        modelRepo.delete(modelCode);
    }

    @Override
    public List<ModelDictDto> querDict(String modelCode) {
        return modelDictRepo.query(modelCode);
    }

    @Override
    public void addDict(List<ModelDictDto> dtoList) {
        modelDictRepo.add(dtoList);
    }

    @Override
    public void deleteDict(String idList) {
        modelDictRepo.delete(idList);
    }

    @Override
    public List<TaskParmDto> querParm(String modelCode) {
        return taskParmRepo.query(modelCode);
    }

    @Override
    public void deleteParm(String idList) {
        taskParmRepo.delete(idList);
    }

    @Override
    public void addParm(List<TaskParmDto> dtoList) {
        taskParmRepo.add(dtoList);
    }
}
