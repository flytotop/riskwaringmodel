package com.emsoft.riskwaring.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 16:32
 * @Description:
 */
@Data
public class ModelParmVo {
    @ApiModelProperty(value = "模型编号" ,required = true)
    private String modelCode;

    @ApiModelProperty(value = "参数名称" ,required = true)
    private String parameterName;

    @ApiModelProperty(value = "中文说明" ,required = true)
    private String chineseExplain;

    @ApiModelProperty(value = "类型" ,required = true)
    private String type;

    @ApiModelProperty(value = "必填" ,required = true)
    private boolean required;
}
