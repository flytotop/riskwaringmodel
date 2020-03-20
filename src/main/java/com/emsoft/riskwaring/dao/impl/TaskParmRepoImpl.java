package com.emsoft.riskwaring.dao.impl;

import com.emsoft.riskwaring.dao.jpa.ModelJpa;
import com.emsoft.riskwaring.dao.jpa.TaskParmJpa;
import com.emsoft.riskwaring.dao.po.ModelDictPo;
import com.emsoft.riskwaring.dao.po.ModelPo;
import com.emsoft.riskwaring.dao.po.TaskParmPo;
import com.emsoft.riskwaring.dao.repository.TaskParmRepo;
import com.emsoft.riskwaring.dto.TaskParmDto;
import com.emsoft.riskwaring.error.ApiException;
import com.emsoft.riskwaring.util.CopyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 15:27
 * @Description:
 */
@Repository
public class TaskParmRepoImpl implements TaskParmRepo {
    @Autowired
    private TaskParmJpa taskParmJpa;
    @Autowired
    private ModelJpa modelJpa;

    @Override
    public void delete(String idList) {
        String[] ids=idList.split(",");
        String firstId=ids[0];
        Optional<TaskParmPo> po=taskParmJpa.findById(firstId);
        if (!po.isPresent()){
            throw ApiException.badRequest("结果字典无对应模型编号");
        }
        Arrays.stream(ids).forEach(id->{
            taskParmJpa.deleteById(id);
        });
        String modelCode =po.get().getModelCode();
        //更改模型下的结果字典
        List<TaskParmPo> list=taskParmJpa.queryByModelCode(modelCode);
        String resultdict="";
        Map<String ,String> map=new HashMap<>();
        map.put("resultdict",resultdict);
        list.forEach(modelDictPo -> {
            String dict=map.get("resultdict");
            dict+=(","+modelDictPo.getParameterName());
            map.put("resultdict",dict);
        });
        //重新设置模型下的结果字典
        ModelPo modelPo=new ModelPo();
        modelPo.setModelCode(modelCode);
        Optional<ModelPo> optionalModelPo=modelJpa.findOne(Example.of(modelPo));
        if (optionalModelPo.isPresent()){
            modelPo=optionalModelPo.get();
            modelPo.setTaskParm(map.get("resultdict"));
            modelJpa.save(modelPo);
        }
    }

    @Override
    public void add(List<TaskParmDto> dtoList) {
        dtoList.stream().forEach(taskParmDto -> {
            taskParmJpa.save(CopyBean.simpleCopy(taskParmDto, TaskParmPo.class));
        });
//更改模型下的结果字典
        String modelCode=dtoList.get(0).getModelCode();
        List<TaskParmPo> list=taskParmJpa.queryByModelCode(modelCode);
        String resultdict="";
        Map<String ,String> map=new HashMap<>();
        map.put("resultdict",resultdict);
        list.forEach(modelDictPo -> {
            String dict=map.get("resultdict");
            dict+=(","+modelDictPo.getParameterName());
            map.put("resultdict",dict);
        });
        //重新设置模型下的结果字典
        ModelPo modelPo=new ModelPo();
        modelPo.setModelCode(modelCode);
        Optional<ModelPo> optionalModelPo=modelJpa.findOne(Example.of(modelPo));
        if (optionalModelPo.isPresent()){
            modelPo=optionalModelPo.get();
            modelPo.setTaskParm(map.get("resultdict"));
            modelJpa.save(modelPo);
        }
    }

    @Override
    public List<TaskParmDto> query(String modelCode) {
        TaskParmPo po=new TaskParmPo();
        po.setModelCode(modelCode);
        List<TaskParmPo> taskParmPos=taskParmJpa.findAll(Example.of(po));
        List<TaskParmDto> dtoList=new ArrayList<>();
        taskParmPos.stream().forEach(taskParmPo -> {
            dtoList.add(CopyBean.simpleCopy(taskParmPo,TaskParmDto.class));
        });
        return dtoList;
    }
}
