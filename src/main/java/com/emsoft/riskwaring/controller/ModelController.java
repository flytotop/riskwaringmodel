package com.emsoft.riskwaring.controller;

import com.emsoft.riskwaring.config.EsBlog;
import com.emsoft.riskwaring.dto.ModelDictDto;
import com.emsoft.riskwaring.dto.ModelDto;
import com.emsoft.riskwaring.dto.TaskParmDto;
import com.emsoft.riskwaring.rep.RepModelVo;
import com.emsoft.riskwaring.req.*;
import com.emsoft.riskwaring.service.ModelService;
import com.emsoft.riskwaring.util.CopyBean;
import com.emsoft.riskwaring.util.result.ResultCode;
import com.emsoft.riskwaring.vo.RespVo;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 09:58
 * @Description:
 */
@RestController
@Api(tags = {"模型"})
public class ModelController {
    @Autowired
    private ModelService modelService;
    @Autowired
    JestClient jestClient;

    @ApiOperation(value = "es插入", tags = {"模型"})
    @RequestMapping(value = "/dsjfkpt/yjmx/ckmx", method = RequestMethod.GET,produces = "application/json" )
    public RespVo<Void> ckmx() {
        //1.给es中索引（保存）一个文档
        EsBlog esBlog=new EsBlog();
        esBlog.setBlogId(10002l);
        esBlog.setId("10002");
        esBlog.setContent("我是一只小呀小苹果");
        esBlog.setSummary("summary");
        esBlog.setTitle("想吃却怎么也吃不到");
        //构建一个索引功能
        Index index=new Index.Builder(esBlog).index("thblog").type("blog").build();
        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RespVo.status(ResultCode.OK).build();
    }

    @ApiOperation(value = "es查询", tags = {"模型"})
    @RequestMapping(value = "/dsjfkpt/yjmx/ckmxx", method = RequestMethod.GET,produces = "application/json" )
    public RespVo<Void> cx() {
        //查询表达式
        String json = "{\"query\":{\"match_all\":{}}}";
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("thblog").addType("blog").build();

        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RespVo.status(ResultCode.OK).build();
    }

}
