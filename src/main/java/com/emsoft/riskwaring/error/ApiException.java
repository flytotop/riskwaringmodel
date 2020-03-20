package com.emsoft.riskwaring.error;

import com.emsoft.riskwaring.util.result.ResultCode;
import org.springframework.http.HttpStatus;

/**
 * ApiException
 *
 * @author TangWeijie
 * @date 2019/12/31 11:34
 */

public class ApiException extends RuntimeException {
    private int status;
    private Object detail;
    private ResultCode resultCode = ResultCode.ERROR;

    public ApiException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public ApiException(Exception exception) {
        this(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    public ApiException(int status, Object detail) {
        super(detail.toString());
        this.status = status;
        this.detail = detail;

    }


    public static ApiException badRequest(String formatText, Object... parms) {
        return new ApiException(ResultCode.ERROR.getCode(), String.format(formatText, parms));
    }

    public static ApiException serviceException() {
        ApiException apiException = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR);
        apiException.setResultCode(ResultCode.SERVICE_EXCEPTION);
        return apiException;
    }

    public static ApiException serviceException(String formatText, Object... parms) {
        ApiException apiException = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, String.format(formatText, parms));
        apiException.setResultCode(ResultCode.SERVICE_EXCEPTION);
        return apiException;
    }

    public ApiException(HttpStatus status) {
        this(status, status.getReasonPhrase());
    }

    public ApiException(HttpStatus status, String detailFormat, Object... objects) {
        this(status.value(), String.format(detailFormat, objects));
    }

    public ApiExceptionResponse getResponse() {
        return new ApiExceptionResponse(this.getStatus(), this.getDetail().toString());
    }

    public int getStatus() {
        return status;
    }

    public Object getDetail() {
        return detail;
    }

    public ApiException setStatus(int status) {
        this.status = status;
        return this;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public ApiException setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public ApiException setDetail(Object detail) {
        this.detail = detail;
        return this;
    }
}


