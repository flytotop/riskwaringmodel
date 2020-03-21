package com.emsoft.riskwaring.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Auther: xhf
 * @Date: 2020-03-20 16:45
 * @Description:
 */
@ApiModel("TaksParmVo")
@Data
public class TaksParmVo {
    @ApiModelProperty(value = "参数" ,required = true)
    @NotEmpty
    private String parm;

    @ApiModelProperty(value = "参数名称" ,required = true)
    @NotEmpty
    private String name;

    @ApiModelProperty(value = "参数类型" ,required = true)
    @NotEmpty
    private String type;

    @ApiModelProperty(value = "必填" ,required = true)
    private boolean required;
}

