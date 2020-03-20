package com.emsoft.riskwaring.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 11:29
 * @Description: 模型列表查询
 */
@Data
@ApiModel("ReqModelListVo")
public class ReqModelListVo {

    @ApiModelProperty(value = "模型编号" ,required = true)
    @NotEmpty
    private String mxbh;

    @ApiModelProperty(value = "模型名称" ,required = true)
    @NotEmpty
    private String mxmc;

    @ApiModelProperty(value = "任务参数" ,required = true)
    @NotEmpty
    private Object rwcs;

    @ApiModelProperty(value = "结果字典" ,required = true)
    @NotEmpty
    private Object jgzd;
}
