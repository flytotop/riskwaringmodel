package com.emsoft.riskwaring.dao.impl;

import com.emsoft.riskwaring.dao.jpa.ModelJpa;
import com.emsoft.riskwaring.dao.po.ModelPo;
import com.emsoft.riskwaring.dao.repository.ModelRepo;
import com.emsoft.riskwaring.dto.ModelDto;
import com.emsoft.riskwaring.util.CopyBean;
import io.swagger.models.Model;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 08:39
 * @Description:
 */
@Repository
public class ModelRepompl implements ModelRepo {
    @Autowired
    private ModelJpa jpa;

    @Override
    public List<ModelDto> modelquery() {
        List<ModelDto> list=new ArrayList<>();
        jpa.findAll().forEach(modelPo -> {
            ModelDto modelDto=new ModelDto();
            modelDto.setResultDict(JSONArray.fromObject(modelPo.getResultDict()));
            modelDto.setTaskParm(JSONArray.fromObject(modelPo.getTaskParm()));
            modelDto.setModelName(modelPo.getModelName());
            modelDto.setModelCode(modelPo.getModelCode());
            list.add(modelDto);

        });
        return list;
    }

    @Override
    public void addModel(ModelDto dto) {
        ModelPo po=CopyBean.simpleCopy(dto,ModelPo.class);
        po.setResultDict(JSONArray.fromObject(dto.getResultDict()).toString());
        po.setTaskParm(JSONArray.fromObject(dto.getTaskParm()).toString());
        jpa.save(po);
    }

    @Override
    public void delete(String modelCode) {
        jpa.deleteByModelCode(modelCode);
    }

    @Override
    public ModelDto modelquery(String modelCode) {
        ModelPo po=new ModelPo();
        po.setModelCode(modelCode);
        List<ModelPo> modelPos=jpa.findAll(Example.of(po));
        if (modelPos.size()==0){
            return null;
        }
        ModelDto modelDto=CopyBean.simpleCopy(modelPos.get(0),ModelDto.class);
        return modelDto;
    }

    @Override
    public ModelDto modelQueryByModelname(String modelName) {
        ModelPo po=new ModelPo();
        po.setModelName(modelName);
        List<ModelPo> modelPos=jpa.findAll(Example.of(po));
        if (modelPos.size()==0){
            return null;
        }
        ModelDto modelDto=new ModelDto();
        modelDto.setModelCode(modelPos.get(0).getModelCode());
        modelDto.setModelName(modelPos.get(0).getModelName());
        modelDto.setResultDict(JSONArray.fromObject(modelPos.get(0).getResultDict()));
        modelDto.setTaskParm(JSONArray.fromObject(modelPos.get(0).getTaskParm()));
        return modelDto;
    }
}
