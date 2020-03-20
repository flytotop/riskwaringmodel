//package com.emsoft.riskwaring.controller;
//
//import com.emsoft.riskwaring.dto.ModelDictDto;
//import com.emsoft.riskwaring.dto.ModelDto;
//import com.emsoft.riskwaring.dto.TaskParmDto;
//import com.emsoft.riskwaring.rep.RepModelVo;
//import com.emsoft.riskwaring.req.*;
//import com.emsoft.riskwaring.service.ModelService;
//import com.emsoft.riskwaring.util.CopyBean;
//import com.emsoft.riskwaring.util.result.ResultCode;
//import com.emsoft.riskwaring.vo.RespVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Auther: xhf
// * @Date: 2020-03-17 09:58
// * @Description:
// */
//@RestController
//@Api(tags = {"模型"})
//public class ModelController {
//    @Autowired
//    private ModelService modelService;
//
//    @ApiOperation(value = "查看模型", tags = {"模型"})
//    @RequestMapping(value = "/dsjfkpt/yjmx/ckmx", method = RequestMethod.GET,produces = "application/json" )
//    public RespVo<RepModelVo> ckmx(@RequestParam(value = "modelCode") String modelCode) {
//        ModelDto modelDto=modelService.queryModel(modelCode);
//
//        return RespVo.ok(CopyBean.simpleCopy(modelDto,RepModelVo.class));
//    }
//
//    @ApiOperation(value = "添加模型", tags = {"模型"})
//    @RequestMapping(value = "/dsjfkpt/yjmx/tjmx", method = RequestMethod.POST,produces = "application/json" )
//    public RespVo<Void> tjmx(@RequestBody ReqAddModelVo vo) {
//        ModelDto dto= CopyBean.simpleCopy(vo,ModelDto.class);
//        modelService.addModel(dto);
//        return RespVo.status(ResultCode.OK).build();
//    }
//
//    @ApiOperation(value = "删除模型", tags = {"模型"})
//    @RequestMapping(value = "/dsjfkpt/yjmx/scmx", method = RequestMethod.GET,produces = "application/json")
//    public RespVo<Void> scmx(@RequestParam(value = "modelCode")  String modelCode) {
//        modelService.delete(modelCode);
//        return RespVo.status(ResultCode.OK).build();
//    }
//
//    @ApiOperation(value = "添加模型字典", tags = {"模型"})
//    @RequestMapping(value = "/dsjfkpt/yjmx/tjmxzd", method = RequestMethod.POST,produces = "application/json")
//    public RespVo<Void> tjmxzd(@RequestBody List<ModelDictVo> list) {
////        List<ModelDictVo> list=vo.getDict();
//        List<ModelDictDto> dictDtos=new ArrayList<>();
//        list.forEach(modelDictVo->{
//            dictDtos.add(CopyBean.simpleCopy(modelDictVo,ModelDictDto.class));
//        });
//        modelService.addDict(dictDtos);
//        return RespVo.status(ResultCode.OK).build();
//    }
//    @ApiOperation(value = "删除模型字典", tags = {"模型"})
//    @RequestMapping(value = "/dsjfkpt/yjmx/scmxzd", method = RequestMethod.GET,produces = "application/json")
//    public RespVo<Void> scmxzd(@RequestParam(value = "idList")  String idList) {
//        modelService.deleteDict(idList);
//        return RespVo.status(ResultCode.OK).build();
//    }
//    @ApiOperation(value = "查看模型字典", tags = {"模型"})
//    @RequestMapping(value = "/dsjfkpt/yjmx/ckmxzd", method = RequestMethod.GET,produces = "application/json")
//    public RespVo<List<ModelDictVo>> ckmxzd(@RequestParam(value = "modelCode") String modelCode) {
//        List<ModelDictDto> dictDtos=modelService.querDict(modelCode);
//        List<ModelDictVo> results=new ArrayList<>();
//        dictDtos.forEach(dto -> {
//            results.add(CopyBean.simpleCopy(dto,ModelDictVo.class));
//        });
//        return RespVo.ok(results);
//    }
//
//    @ApiOperation(value = "添加模型任务参数", tags = {"模型"})
//    @RequestMapping(value = "/dsjfkpt/yjmx/tjmxrwcs", method = RequestMethod.POST,produces = "application/json")
//    public RespVo<Void> tjmxrwcs(@RequestBody ReqAddModelParmVo vo) {
//        List<ModelParmVo> list=vo.getParmVos();
//        List<TaskParmDto> dictDtos=new ArrayList<>();
//        list.forEach(modelDictVo->{
//            dictDtos.add(CopyBean.simpleCopy(modelDictVo,TaskParmDto.class));
//        });
//        modelService.addParm(dictDtos);
//        return RespVo.status(ResultCode.OK).build();
//    }
//    @ApiOperation(value = "删除模型任务参数", tags = {"模型"})
//    @RequestMapping(value = "/dsjfkpt/yjmx/scmxrwcs", method = RequestMethod.GET,produces = "application/json")
//    public RespVo<Void> scmxrwcs(@RequestParam(value = "idList")  String idList) {
//        modelService.deleteParm(idList);
//        return RespVo.status(ResultCode.OK).build();
//    }
//    @ApiOperation(value = "查看模型任务参数", tags = {"模型"})
//    @RequestMapping(value = "/dsjfkpt/yjmx/ckmxrwcs", method = RequestMethod.GET,produces = "application/json")
//    public RespVo<List<ModelParmVo>> ckmxrwcs(@RequestParam(value = "modelCode")  String modelCode) {
//        List<TaskParmDto> dictDtos=modelService.querParm(modelCode);
//        List<ModelParmVo> results=new ArrayList<>();
//        dictDtos.forEach(dto -> {
//            results.add(CopyBean.simpleCopy(dto,ModelParmVo.class));
//        });
//        return RespVo.ok(results);
//    }
//
//}
