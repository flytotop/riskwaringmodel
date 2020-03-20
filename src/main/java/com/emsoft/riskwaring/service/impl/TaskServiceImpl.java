package com.emsoft.riskwaring.service.impl;

import com.alibaba.fastjson.JSON;
import com.emsoft.riskwaring.dao.repository.ModelDictRepo;
import com.emsoft.riskwaring.dao.repository.ModelRepo;
import com.emsoft.riskwaring.dao.repository.TaskParmRepo;
import com.emsoft.riskwaring.dto.*;
import com.emsoft.riskwaring.hbase.HbaseClient;
import com.emsoft.riskwaring.service.ModelService;
import com.emsoft.riskwaring.service.TaskService;
import com.emsoft.riskwaring.util.cglib.ClassUtil;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 16:33
 * @Description:
 */
@Service
@Log4j2
public class TaskServiceImpl implements TaskService {
    @Autowired
    private ModelService modelService;
    @Autowired
    private ModelRepo modelRepo;
    @Autowired
    private ModelDictRepo modelDictRepo;
    @Autowired
    private TaskParmRepo taskParmRepo;
    /**
     * 预警模型列表
     * @param
     * @return
     */
    @Override
    public List<ModelDto> modelList() {
        List<ModelDto> modelDtos=modelRepo.modelquery();
        List<ModelDto> results=new ArrayList<>();
        modelDtos.forEach(modelDto -> {
            results.add(taskDetail(modelDto.getModelName()));
        });
        return results;
    }

    /**
     * 查看任务信息详情
     * @param modelname 模型名称
     * @return
     */
    @Override
    public ModelDto taskDetail(String modelname) {
        ModelDto modelDto= modelRepo.modelQueryByModelname(modelname);
        String modelCode=modelDto.getModelCode();
        List<ModelDictDto> modelDictDtos=modelDictRepo.query(modelCode);
        Map map=new HashMap();
        Map map1=new HashMap();
        modelDictDtos.stream().forEach(modelDicDto->{
            map1.put("name",modelDicDto.getChineseExplain());
            map1.put("type",modelDicDto.getType());
            map1.put("required",modelDicDto.isRequired());
            map.put(modelDicDto.getParameterName(),map1);
        });
        JSONObject bbb= JSONObject.fromObject(map);
        System.out.println(bbb.toString());
        modelDto.setResultDict(bbb);
        return modelDto;

    }

    /**
     * 任务结果查询
     * @param dto 请求信息里面包含请求的tablename 和 rowkey
     * @return
     */
    @Override
    public Map taskResult(TaskResultDto dto) {
        //TODO 暂时模拟有tablename和rowkey的情况
        String tablename="";
        String rowkey="";
        String modelCode="";

        HashMap addproperty=new HashMap();
        HashMap addValMap =new HashMap();
        //根据模型编号获取模型字典
        List<ModelDictDto> modelDictDtos=modelDictRepo.query(modelCode);
        modelDictDtos.forEach(dto1 -> {
            try {
                addproperty.put(dto1.getParameterName(),Class.forName("java.lang.String"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        HbaseClient hbaseClient=new HbaseClient();
        try {
            addValMap=hbaseClient.selectOneRow(tablename,rowkey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user=new User();
        try {
            Object obj= new ClassUtil().dynamicClass(user,addproperty,addValMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map=new HashMap();
        return map;
    }

    @Override
    public Map taskResultDetail(TaskResultDetailDto detailDto) {
        //TODO 暂时模拟有tablename和rowkey的情况
        String tablename="";
        String rowkey="";
        String modelCode="";

        HashMap addproperty=new HashMap();
        HashMap addValMap =new HashMap();
        //根据模型编号获取模型字典
        List<ModelDictDto> modelDictDtos=modelDictRepo.query(modelCode);
        modelDictDtos.forEach(dto1 -> {
            try {
                addproperty.put(dto1.getParameterName(),Class.forName("java.lang.String"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        HbaseClient hbaseClient=new HbaseClient();
        try {
            addValMap=hbaseClient.selectOneRow(tablename,rowkey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user=new User();
        try {
            Object obj= new ClassUtil().dynamicClass(user,addproperty,addValMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map=new HashMap();
        return map;
    }

    @Override
    public Map executeTask(ExecuteTaskDto dto) {
        return null;
    }

    /**
     * 任务结果明细下载
     * @return
     */
    @Override
    public byte[] resultDetailDownload() {
        //TODO 暂时模拟有tablename和rowkey的情况
        String tablename="";
        String rowkey="";
        String modelCode="";

        HashMap addproperty=new HashMap();
        HashMap addValMap =new HashMap();
        //根据模型编号获取模型字典
        List<ModelDictDto> modelDictDtos=modelDictRepo.query(modelCode);
        modelDictDtos.forEach(dto1 -> {
            try {
                addproperty.put(dto1.getParameterName(),Class.forName("java.lang.String"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        HbaseClient hbaseClient=new HbaseClient();
        try {
            addValMap=hbaseClient.selectOneRow(tablename,rowkey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user=new User();
        Object obj=new Object();
        try {
            obj= new ClassUtil().dynamicClass(user,addproperty,addValMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map=new HashMap();
        String result=JSON.toJSONString(obj);
        return result.getBytes();
    }
}
