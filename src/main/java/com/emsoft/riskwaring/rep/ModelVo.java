package com.emsoft.riskwaring.rep;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Auther: xhf
 * @Date: 2020-03-20 08:33
 * @Description:
 */
@ApiModel("ModelVo")
@Data
public class ModelVo {
    @ApiModelProperty(value = "模型编号" ,required = true)
    private String mxbh;

    @ApiModelProperty(value = "模型名称" ,required = true)
    private String mxmc;

    @ApiModelProperty(value = "任务参数" ,required = true)
    private Object rwcs;

    @ApiModelProperty(value = "结果字典" ,required = true)
    private Object jgzd;


}
