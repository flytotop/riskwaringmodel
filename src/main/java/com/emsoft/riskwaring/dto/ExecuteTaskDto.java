package com.emsoft.riskwaring.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 15:57
 * @Description:
 */
@Data
public class ExecuteTaskDto {

    @ApiModelProperty(value = "模型编号" ,required = true)
    private String mxbh;

    @ApiModelProperty(value = "任务编号" ,required = true)
    private String rwbh;

    @ApiModelProperty(value = "任务状态" ,required = true)
    private String rwzt;

    @ApiModelProperty(value = "任务参数" ,required = true)
    private Object rwcs;
}
