package com.emsoft.riskwaring.rep;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: xhf
 * @Date: 2020-03-18 16:49
 * @Description:
 */
@Data
@ApiModel("RepModelVo")
public class RepModelVo {
    //模型编码
    @ApiModelProperty(value = "模型编码",required = false)
    private String modelCode;

    //任务参数
    @ApiModelProperty(value = "任务参数",required = false)
    private String taskParm;

    //任务名称
    @ApiModelProperty(value = "模型名称",required = false)
    private String modelName;

    //结果字典
    @ApiModelProperty(value = "结果字典",required = false)
    private String resultDict;

}
