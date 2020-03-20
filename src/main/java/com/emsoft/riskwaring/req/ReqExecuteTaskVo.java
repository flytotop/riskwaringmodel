package com.emsoft.riskwaring.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 13:39
 * @Description: 查看任务详情
 */
@Data
@ApiModel("ReqExecuteTaskVo")
public class ReqExecuteTaskVo {

    @ApiModelProperty(value = "模型编号" ,required = true)
    @NotEmpty
    private String mxbh;

    @ApiModelProperty(value = "任务编号" ,required = true)
    @NotEmpty
    private String rwbh;

    @ApiModelProperty(value = "任务状态" ,required = true)
    @NotEmpty
    private String rwzt;

    @ApiModelProperty(value = "任务参数" ,required = true)
    private String rwcs;
}
