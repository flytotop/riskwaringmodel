package com.emsoft.riskwaring.controller;

import com.emsoft.riskwaring.dto.ModelDictDto;
import com.emsoft.riskwaring.dto.ModelDto;
import com.emsoft.riskwaring.dto.TaskResultDetailDto;
import com.emsoft.riskwaring.dto.TaskResultDto;
import com.emsoft.riskwaring.rep.ModelVo;
import com.emsoft.riskwaring.rep.RepExecuteTaskVo;
import com.emsoft.riskwaring.rep.RepModelVo;
import com.emsoft.riskwaring.req.*;
import com.emsoft.riskwaring.service.ModelService;
import com.emsoft.riskwaring.service.TaskService;
import com.emsoft.riskwaring.util.CopyBean;
import com.emsoft.riskwaring.util.result.ResultCode;
import com.emsoft.riskwaring.vo.RespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 14:55
 * @Description:
 */
@RestController
@Api(tags = {"任务"})
public class TaskController {

    @Autowired
    private ModelService modelService;
    @Autowired
    private TaskService taskService;
    @ApiOperation(value = "预警模型列表", tags = {"任务"})
    @RequestMapping(value = "/dsjfkpt/yjmx", method = RequestMethod.GET,produces = "application/json")
    public RespVo<List<ModelVo> > mxlb() {
        List<ModelDto> modelDtoList=taskService.modelList();
        List<ModelVo>  modelVos=new ArrayList<>();
        modelDtoList.stream().forEach(modelDto -> {
            ModelVo modelVo=new ModelVo();
            modelVo.setMxbh(modelDto.getModelCode());
            modelVo.setMxmc(modelDto.getModelName());
            List<TaksParmVo> taksParmVos =new ArrayList<>();
            taksParmVos =(List<TaksParmVo>)JSONArray.toCollection(JSONArray.fromObject(modelDto.getTaskParm()),TaksParmVo.class);
            Map parmMap=new HashMap();
            taksParmVos.forEach(taksParmVo -> {
                Map map1=new HashMap();
                map1.put("name",taksParmVo.getName());
                map1.put("type",taksParmVo.getType());
                map1.put("required",taksParmVo.isRequired());
                parmMap.put(taksParmVo.getParm(),map1);
            });
            List<ModelDictVo> resultDicts=new ArrayList<>();
            resultDicts =(List<ModelDictVo>)JSONArray.toCollection(JSONArray.fromObject(modelDto.getResultDict()),ModelDictVo.class);
            Map dictMap=new HashMap();
            resultDicts.forEach(modelDictVo -> {
                Map map1=new HashMap();
                map1.put("name",modelDictVo.getChineseExplain());
                map1.put("type",modelDictVo.getType());
                map1.put("required",modelDictVo.isRequired());
                dictMap.put(modelDictVo.getParameterName(),map1);
            });
            modelVo.setJgzd(dictMap);
            modelVo.setRwcs(parmMap);
            modelVos.add(modelVo);
        });
        return RespVo.ok(modelVos);
    }

    @ApiOperation(value = "任务模型详情", tags = {"任务"})
    @RequestMapping(value = "/dsjfkpt/yjmx/{mxmc}", method = RequestMethod.GET,produces = "application/json")
    public RespVo<ModelVo> rwmxxq(@PathVariable("mxmc") String mxmc) {


        ModelDto modelDto=taskService.taskDetail(mxmc);
        ModelVo modelVo=new ModelVo();
        modelVo.setMxbh(modelDto.getModelCode());
        modelVo.setMxmc(modelDto.getModelName());
        List<TaksParmVo> taksParmVos =new ArrayList<>();
        taksParmVos =(List<TaksParmVo>)JSONArray.toCollection(JSONArray.fromObject(modelDto.getTaskParm()),TaksParmVo.class);
        Map parmMap=new HashMap();
        taksParmVos.forEach(taksParmVo -> {
            Map map1=new HashMap();
            map1.put("name",taksParmVo.getName());
            map1.put("type",taksParmVo.getType());
            map1.put("required",taksParmVo.isRequired());
            parmMap.put(taksParmVo.getParm(),map1);
        });
        List<ModelDictVo> resultDicts=new ArrayList<>();
        resultDicts =(List<ModelDictVo>)JSONArray.toCollection(JSONArray.fromObject(modelDto.getResultDict()),ModelDictVo.class);
        Map dictMap=new HashMap();
        resultDicts.forEach(modelDictVo -> {
            Map map1=new HashMap();
            map1.put("name",modelDictVo.getChineseExplain());
            map1.put("type",modelDictVo.getType());
            map1.put("required",modelDictVo.isRequired());
            dictMap.put(modelDictVo.getParameterName(),map1);
        });
        modelVo.setJgzd(dictMap);
        modelVo.setRwcs(parmMap);
        return RespVo.ok(modelVo);
    }

    @ApiOperation(value = "任务结果", tags = {"任务"})
    @RequestMapping(value = "/dsjfkpt/yjmx/{mxmc}/rwjg", method = RequestMethod.GET)
    public RespVo<Object> rwjg(@PathVariable("mxmc") String mxmc) {
        //TODO 测试使用的模拟数据
        ReqTaskResultVo vo=new ReqTaskResultVo();
        vo.setRwbh("任务编号1241515459");
        vo.setMxbh("33");
        vo.setMxmc("你是我的小呀小苹果");
        vo.setRwjgbh("任务结果编号8588351");
        vo.setRwrq("20200312");
        vo.setRwlx(100);
        vo.setRwcs("任务参数");
        vo.setRwzt("o");
        vo.setRwsjc("任务时间戳");
        vo.setRwsj("任务时间");

//        map1.put("rwjg",vo);

//        TaskResultDto resultDto=new TaskResultDto();
//        Map map=taskService.taskResult(resultDto);
        return RespVo.ok(vo);
    }

    @ApiOperation(value = "任务结果明细", tags = {"任务"})
    @RequestMapping(value = "/dsjfkpt/yjmx/{mxmc}/rwjg/{rwjgbh}", method = RequestMethod.GET,produces = "application/json")
    public RespVo<Object> rwjgmxxz(@PathVariable("mxmc") String mxmc ,@PathVariable("rwjgbh") String rwjgbh) {
        ReqTaskResultDetailVo vo=new ReqTaskResultDetailVo();
        vo.setRwbh("任务编号1241515459");
        vo.setMxbh("33");
        vo.setMxmc("你是我的小呀小苹果");
        vo.setRwjgbh("任务结果编号8588351");
        vo.setRwrq("20200312");
        vo.setRwsjc("任务时间戳");
        vo.setRwsj("任务时间");
        vo.setMxxx("明细信息-更多字段");
//        Map map=taskService.taskResultDetail();
        return RespVo.ok(vo);
    }
    @ApiOperation(value = "任务明细下载", tags = {"任务"})
    @RequestMapping(value = "/dsjfkpt/yjmx/rwjg/rwjgmx", method = RequestMethod.GET,produces = "application/json")
    public RespVo<byte[]> rwjgmx(@RequestBody ReqTaskResultDetailVo vo) {
        byte[] bytes=taskService.resultDetailDownload();
        return RespVo.ok(bytes);
    }

    @ApiOperation(value = "执行任务", tags = {"任务"})
    @RequestMapping(value = "/dsjfkpt/yjmx/{mxmc}", method = RequestMethod.POST,produces = "application/json")
    public RespVo<RepExecuteTaskVo> zxrw(@PathVariable("mxmc") String mxmc ,@RequestBody ReqExecuteTaskVo reqExecuteTaskVo) {
        RepExecuteTaskVo vo=new RepExecuteTaskVo();
        vo.setMxbh("模型编号");
        vo.setMxmc("模型名称");
        vo.setRwbh("任务编号");
        vo.setRwsj("20200320");
        vo.setMxsl(1000);
        vo.setRwjgbh("任务结果编号");
        vo.setRwrq("任务日期");
        vo.setRwzt("任务状态");
        vo.setRwcs("任务参数");
        vo.setRwsjc("任务时间戳");
        return RespVo.ok(vo);
    }
    @ApiOperation(value = "添加模型", tags = {"任务"})
    @RequestMapping(value = "/dsjfkpt/yjmx/tjmx", method = RequestMethod.POST,produces = "application/json" )
    public RespVo<Void> tjmx(@RequestBody ReqAddModelVo vo) {
        ModelDto modelDto=new ModelDto();
        modelDto.setModelCode(vo.getMxbh());
        modelDto.setModelName(vo.getMxmc());
        modelDto.setTaskParm(vo.getRwcs());
        modelDto.setResultDict(vo.getJgzd());
        modelService.addModel(modelDto);
        return RespVo.status(ResultCode.OK).build();
    }
    @ApiOperation(value = "添加模型字典", tags = {"任务"})
    @RequestMapping(value = "/dsjfkpt/yjmx/tjmxzd", method = RequestMethod.POST,produces = "application/json")
    public RespVo<Void> tjmxzd(@RequestBody List<ModelDictVo> list) {
//        List<ModelDictVo> list=vo.getDict();
        List<ModelDictDto> dictDtos=new ArrayList<>();
        list.forEach(modelDictVo->{
            dictDtos.add(CopyBean.simpleCopy(modelDictVo,ModelDictDto.class));
        });
        modelService.addDict(dictDtos);
        return RespVo.status(ResultCode.OK).build();
    }
}
