package com.emsoft.riskwaring.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 15:53
 * @Description:
 */
public class TaskResultDetailDto {

    @ApiModelProperty(value = "明细编号" ,required = true)
    private  String mxbh;

    @ApiModelProperty(value = "任务结果编号" ,required = true)
    private  String rwjgbh;

    @ApiModelProperty(value = "模型编号" ,required = true)
    private  String mxmc;

    @ApiModelProperty(value = "任务编号" ,required = true)
    private  String rwbh;

    @ApiModelProperty(value = "任务日期" ,required = true)
    private  String rwrq;

    @ApiModelProperty(value = "任务时间" ,required = true)
    private  String rwsj;

    @ApiModelProperty(value = "任务时间戳" ,required = true)
    private  String rwsjc;

    @ApiModelProperty(value = "明细信息" ,required = true)
    private  String mxxx;
}
