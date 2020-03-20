package com.emsoft.riskwaring.util.result;

import com.emsoft.riskwaring.error.ApiExceptionResponse;


/**
 * RespVoBuild
 *
 * @author TangWeijie
 * @date 2019/2/27 11:47
 */
public interface RespVoBuild {
   RespVoBuild status(ResultCode resultCode);
   RespVoBuild status(int status);
   RespVoBuild message(String string);
   RespVoBuild error(ErrorMessages errorMessages);
   RespVoBuild error(int status, String detail);
   RespVoBuild error(ApiExceptionResponse apiExceptionResponse);
   <T> RespVo<T> body(T body);
   <T> RespVo<T> build();
}
