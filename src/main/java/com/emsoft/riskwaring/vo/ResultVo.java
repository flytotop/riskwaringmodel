package com.emsoft.riskwaring.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * ResultVo
 * 弃用ResultVo 统一使用RespVo
 * @author TangWeijie
 * @date 2019/1/24 14:07
 */
@ApiModel("通用错误接口")
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Deprecated
public class ResultVo<T> extends RespVo<T>{


}
