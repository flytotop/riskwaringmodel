package com.emsoft.riskwaring.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 13:36
 * @Description: 查看任务结果
 */
@Data
@ApiModel("ReqTaskResultVo")
public class ReqTaskResultVo {

    @ApiModelProperty(value = "任务结果编号" ,required = true)
    @NotEmpty
    private String rwjgbh;

    @ApiModelProperty(value = "模型编号" ,required = true)
    @NotEmpty
    private String mxbh;

    @ApiModelProperty(value = "模型名称" ,required = true)
    @NotEmpty
    private String mxmc;

    @ApiModelProperty(value = "任务编号" ,required = true)
    @NotEmpty
    private String rwbh;

    @ApiModelProperty(value = "任务日期" ,required = true)
    @NotEmpty
    private String rwrq;
    @ApiModelProperty(value = "任务时间" ,required = true)
    @NotEmpty
    private String rwsj;

    @ApiModelProperty(value = "任务时间戳" ,required = true)
    @NotEmpty
    private String rwsjc;

    @ApiModelProperty(value = "任务状态" ,required = true)
    @NotEmpty
    private String rwzt;

    @ApiModelProperty(value = "明细数量" ,required = true)
    @NotNull
    private Integer rwlx;

    @ApiModelProperty(value = "任务参数" ,required = true)
    private Object rwcs;
}
