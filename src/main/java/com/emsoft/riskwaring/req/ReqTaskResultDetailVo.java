package com.emsoft.riskwaring.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 13:37
 * @Description: 查看任务结果明细
 */
@Data
@ApiModel("ReqTaskResultDetailVo")
public class ReqTaskResultDetailVo {

    @ApiModelProperty(value = "明细编号" ,required = true)
    @NotEmpty
    private  String mxbh;

    @ApiModelProperty(value = "任务结果编号" ,required = true)
    @NotEmpty
    private  String rwjgbh;

    @ApiModelProperty(value = "模型编号" ,required = true)
    @NotEmpty
    private  String mxmc;

    @ApiModelProperty(value = "任务编号" ,required = true)
    @NotEmpty
    private  String rwbh;

    @ApiModelProperty(value = "任务日期" ,required = true)
    @NotEmpty
    private  String rwrq;

    @ApiModelProperty(value = "任务时间" ,required = true)
    @NotEmpty
    private  String rwsj;

    @ApiModelProperty(value = "任务时间戳" ,required = true)
    @NotEmpty
    private  String rwsjc;

    @ApiModelProperty(value = "明细信息" ,required = true)
    @NotEmpty
    private  String mxxx;

}
