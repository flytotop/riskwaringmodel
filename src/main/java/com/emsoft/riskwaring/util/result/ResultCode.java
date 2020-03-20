package com.emsoft.riskwaring.util.result;

import java.io.Serializable;

/**
 * ResultCode
 *
 * @author TangWeijie
 * @date 2019/1/31 17:12
 */

public enum ResultCode implements Serializable {

    /**
     * 返回数据字典
     */
    OK(0, "OK"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),
    SERVICE_EXCEPTION(3, "服务异常"),
    WARN(4, "服务正常,数据异常");

    private final Integer code;
    private final String desc;
    private static final long serialVersionUID = 1L;

    ResultCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}

