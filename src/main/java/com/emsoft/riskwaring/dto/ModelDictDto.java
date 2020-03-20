package com.emsoft.riskwaring.dto;

import lombok.Data;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 22:46
 * @Description:
 */
@Data
public class ModelDictDto {

    private String id;


    private String modelCode;


    private String parameterName;


    private String chineseExplain;


    private String type;


    private boolean required;

}
