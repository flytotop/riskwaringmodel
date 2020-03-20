package com.emsoft.riskwaring.error;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author TangWeijie
 */
public class ApiExceptionResponse {
    @JsonProperty("status")
    private int status;
    @JsonProperty("detail")
    private String detail;

    public ApiExceptionResponse(int status, String detail) {
        this.status = status;
        this.detail = detail;
    }
    public ApiExceptionResponse() {

    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
