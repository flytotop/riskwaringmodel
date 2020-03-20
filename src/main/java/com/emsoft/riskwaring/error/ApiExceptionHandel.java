package com.emsoft.riskwaring.error;


import com.emsoft.riskwaring.error.ApiException;
import com.emsoft.riskwaring.vo.RespVo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.util.stream.Collectors;

/**
 * @author TangWeijie
 */
@RestControllerAdvice
@Data
@Slf4j
public class ApiExceptionHandel {

    private boolean apiExceptionPrint = true;

    public boolean isApiExceptionPrint() {
        return apiExceptionPrint;
    }

    public void setApiExceptionPrint(boolean apiExceptionPrint) {
        this.apiExceptionPrint = apiExceptionPrint;
    }

    @ExceptionHandler(value = ApiException.class)
    public RespVo<Object> allExceptionHandler(HttpServletRequest request, Exception exception) {
        if (log.isDebugEnabled()) {
            log.debug("错误异常",exception);
        }

        ApiException apiException = (ApiException) exception;
        log.error("-------------------");
        log.error("error {}-at{}", apiException.getStackTrace()[1], apiException.getDetail());
        log.error("-------------------");
        exception.printStackTrace();
        return RespVo.status(apiException.getResultCode()).error(apiException.getResponse()).build();
    }

    @ExceptionHandler(value = {BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public RespVo<Object> bindExceptionHandler(HttpServletRequest request, Exception exception) {
        if (log.isDebugEnabled()) {
            log.error("错误异常",exception);
        }
        log.error("-------------------");
        log.error("error {}-at{}", exception.getStackTrace()[1], exception.getMessage());
        log.error("-------------------");
        ApiException apiException = ApiException.badRequest("未知的错误;%s", exception.getMessage());
        try {
            throw exception;
        } catch (BindException | ConstraintViolationException e) {
            apiException = ApiException.badRequest(e.getMessage());
        } catch (MethodArgumentNotValidException e) {

            String exrrorMessage = StringUtils.join(e.getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(x -> (FieldError) x)
                    .map(x -> x.getField() + x.getDefaultMessage())
                    .collect(Collectors.toList()), ',');
            apiException = ApiException.badRequest(exrrorMessage);
        } catch (Exception e) {
            log.error("-------------------");
            log.error("error {}-at {}", apiException.getStackTrace()[1], apiException.getDetail());
            log.error("-------------------");
        }
        return RespVo.status(apiException.getResultCode()).error(apiException.getResponse()).build();
    }

    @ExceptionHandler(value = {Exception.class})
    public RespVo<Object> defaultExceptionHandler(HttpServletRequest request, Exception exception) {
        if (log.isDebugEnabled()) {
            log.debug("错误异常",exception);
        } else {
            log.error("错误异常",exception);

        }
        exception.printStackTrace();
        ApiException apiException = ApiException.badRequest("未知的错误:%s", exception.getMessage());

        log.error("-------------------");
        log.error("error {}-at {}", apiException.getStackTrace()[1], apiException.getDetail());
        log.error("-------------------");
        exception.printStackTrace();
        return RespVo.status(apiException.getResultCode()).error(apiException.getResponse()).build();
    }

}
