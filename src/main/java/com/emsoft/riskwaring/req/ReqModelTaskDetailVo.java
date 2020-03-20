package com.emsoft.riskwaring.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 11:30
 * @Description: 模型列表
 */
@Data
@ApiModel("ReqModelTaskDetailVo")
public class ReqModelTaskDetailVo {

    @ApiModelProperty(value = "模型编号" ,required = true)
    @NotEmpty
    private String mxbh;

    @ApiModelProperty(value = "模型编号" ,required = true)
    @NotEmpty
    private String mxmc;

    @ApiModelProperty(value = "模型编号" ,required = true)
    private Object rwcs;

    @ApiModelProperty(value = "模型编号" ,required = true)
    private Object jgzd;
}
