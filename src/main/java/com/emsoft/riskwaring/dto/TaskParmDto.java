package com.emsoft.riskwaring.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 15:23
 * @Description:
 */
@Data
public class TaskParmDto {

    private String id;

    private String modelCode;


    private String parameterName;


    private String chineseExplain;


    private String type;


    private boolean required;
}
