package com.emsoft.riskwaring.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 15:53
 * @Description:
 */
@Data
public class ModelListDto {
    @ApiModelProperty(value = "模型编号" ,required = true)
    private String mxbh;

    @ApiModelProperty(value = "模型名称" ,required = true)
    private String mxmc;

    @ApiModelProperty(value = "任务参数" ,required = true)
    private Object rwcs;

    @ApiModelProperty(value = "结果字典" ,required = true)
    private Object jgzd;
}

