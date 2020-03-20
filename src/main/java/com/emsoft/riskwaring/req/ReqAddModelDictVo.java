package com.emsoft.riskwaring.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 10:46
 * @Description:
 */
@Data
@ApiModel("ReqAddModelDictVo")
public class ReqAddModelDictVo {

    private List<ModelDictVo> dict;
}
