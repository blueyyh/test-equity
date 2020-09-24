package com.trafigura.poc.equity.controller.exceptionhandler;

import com.trafigura.poc.equity.controller.TradeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by JACK YANG on 2020/9/24.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    public static class ErrorInfo{

        public ErrorInfo(String url, String error) {
            this.url = url;
            this.error = error;
        }

        private String url;

        private String error;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Object handleActionException(Exception ex, HttpServletRequest request) {
        LOGGER.error("An error occurred during process url:" + request.getRequestURL().toString(), ex);
        return new ErrorInfo(request.getRequestURL().toString(), ex.getMessage());
    }
}
