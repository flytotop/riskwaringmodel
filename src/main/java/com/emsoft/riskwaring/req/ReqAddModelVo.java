package com.emsoft.riskwaring.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 10:19
 * @Description: 添加模型
 */
@Data
@ApiModel("ReqAddModelVo")
public class ReqAddModelVo {


    //模型编码
    @ApiModelProperty(value = "模型编码" ,required = true)
    private String mxbh;

    @ApiModelProperty(value = "模型名称" ,required = true)
    private String mxmc;

    //任务参数
    @ApiModelProperty(value = "任务参数" ,required = false)
    private List<TaksParmVo> rwcs;

    //结果字典
    @ApiModelProperty(value = "结果字典" ,required = false)
    private List<ModelDictVo> jgzd;

}
