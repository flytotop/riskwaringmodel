package com.emsoft.riskwaring.dao.impl;

import com.emsoft.riskwaring.dao.jpa.ModelDictJpa;
import com.emsoft.riskwaring.dao.jpa.ModelJpa;
import com.emsoft.riskwaring.dao.po.ModelDictPo;
import com.emsoft.riskwaring.dao.po.ModelPo;
import com.emsoft.riskwaring.dao.repository.ModelDictRepo;
import com.emsoft.riskwaring.dto.ModelDictDto;
import com.emsoft.riskwaring.error.ApiException;
import com.emsoft.riskwaring.util.CopyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 08:38
 * @Description:
 */
@Repository
public class ModelDictRepoImpl implements ModelDictRepo {
    @Autowired
    private ModelDictJpa jpa;

    @Autowired
    private ModelJpa modelJpa;

    @Override
    public void delete(String idList) {
        String[] ids =idList.split(",");
        String firstId=ids[0];
        Optional<ModelDictPo> po=jpa.findById(firstId);
        if (!po.isPresent()){
            throw ApiException.badRequest("结果字典无对应模型编号");
        }
        String modelCode =po.get().getModelCode();
        Arrays.stream(ids).forEach(id->{
            jpa.deleteById(id);
        });
        //更改模型下的结果字典
        List<ModelDictPo> list=jpa.queryByModelCode(modelCode);
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
            modelPo.setResultDict(map.get("resultdict"));
            modelJpa.save(modelPo);
        }
    }

    @Override
    public void add(List<ModelDictDto> dtoList) {

        dtoList.forEach(dto -> {
            ModelDictPo po= CopyBean.simpleCopy(dto,ModelDictPo.class);
            jpa.save(po);
        });

        //更改模型下的结果字典
        String modelCode=dtoList.get(0).getModelCode();
        List<ModelDictPo> list=jpa.queryByModelCode(modelCode);
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
            modelPo.setResultDict(map.get("resultdict"));
            modelJpa.save(modelPo);
        }
    }

    @Override
    public List<ModelDictDto> query(String modelCode) {
        List<ModelDictPo> list=jpa.queryByModelCode(modelCode);
        List<ModelDictDto> list1=new ArrayList<>();
        list.forEach(modelDictPo -> {
            list1.add(CopyBean.simpleCopy(modelDictPo,ModelDictDto.class));
        });
        return list1;
    }
}
