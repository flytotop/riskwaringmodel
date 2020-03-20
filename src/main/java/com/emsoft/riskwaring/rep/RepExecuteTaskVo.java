package com.emsoft.riskwaring.rep;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: xhf
 * @Date: 2020-03-20 10:22
 * @Description:
 */
@Data
@ApiModel("RepExecuteTaskVo")
public class RepExecuteTaskVo {

    @ApiModelProperty(value = "任务结果编号" ,required = true)
    private String rwjgbh;

    @ApiModelProperty(value = "模型编号" ,required = true)
    private String mxbh;

    @ApiModelProperty(value = "模型名称" ,required = true)
    private String mxmc;

    @ApiModelProperty(value = "任务编号" ,required = true)
    private String rwbh;

    @ApiModelProperty(value = "任务日期" ,required = true)
    private String rwrq;

    @ApiModelProperty(value = "任务时间" ,required = true)
    private String rwsj;

    @ApiModelProperty(value = "任务时间戳" ,required = true)
    private String rwsjc;

    @ApiModelProperty(value = "任务状态" ,required = true)
    private String rwzt;

    @ApiModelProperty(value = "明细数量" ,required = true)
    private Integer mxsl;

    @ApiModelProperty(value = "任务参数" ,required = true)
    private String rwcs;
}
