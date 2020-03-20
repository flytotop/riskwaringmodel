package com.emsoft.riskwaring.vo;

import com.emsoft.riskwaring.error.ApiExceptionResponse;
import com.emsoft.riskwaring.util.result.ErrorMessages;
import com.emsoft.riskwaring.util.result.ResultCode;

/**
 * DefaultRespVoBuild
 *
 * @author TangWeijie
 * @date 2019/2/27 11:48
 */
public class DefaultRespVoBuild implements RespVoBuild{
    private int status = ResultCode.OK.getCode();
    private String message = ResultCode.OK.getDesc();
    private int errorCode;
    private String errorInfo;

    private boolean isError=false;


    @Override
    public RespVoBuild status(ResultCode resultCode) {

        return status(resultCode.getCode()).message(resultCode.getDesc());
    }

    @Override
    public RespVoBuild status(int status) {
        this.status = status;
        return this;
    }

    @Override
    public RespVoBuild message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public RespVoBuild error(ErrorMessages errorMessages) {
        return error(errorMessages.getErrCode(),errorMessages.getErrInfo());
    }

    @Override
    public RespVoBuild error(ApiExceptionResponse apiExceptionResponse) {
        return error(apiExceptionResponse.getStatus(),apiExceptionResponse.getDetail());
    }

    @Override
    public RespVoBuild error(int status, String errorInfo) {
        isError = true;
        this.errorCode = status;
        this.errorInfo = errorInfo;
        return this;
    }

    @Override
    public <T> RespVo<T> body(T body) {
        if(isError) {
            return new RespVo<T>(body,status,message,errorCode,errorInfo);
        }
        return new RespVo<T>(body,status,message);
    }

    @Override
    public <T> RespVo<T> build() {
        if(isError){
            return new RespVo<T>(null,status,message,errorCode,errorInfo);
        }
        return new RespVo<T>(null,status,message);
    }
}
