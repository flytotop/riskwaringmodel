package com.emsoft.riskwaring.dto;

import lombok.Data;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 22:46
 * @Description:
 */
@Data
public class ModelDto {
    private String id;

    //模型编码
    private String modelCode;

    //任务参数
    private Object taskParm;

    //任务名称
    private String modelName;

    //结果字典
    private Object resultDict;
}
