package com.yuxiang.guli.service.base.handler;

import com.yuxiang.guli.common.base.result.R;
import com.yuxiang.guli.common.base.result.ResultCodeEnum;
import com.yuxiang.guli.common.base.util.ExceptionUtils;
import com.yuxiang.guli.service.base.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = IOException.class)
    public R httpIOExceptionHandler(IOException ex) {
        log.error(ExceptionUtils.getMessage(ex));
        return R.setResult(ResultCodeEnum.FILE_UPLOAD_ERROR);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public R httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        log.error(ExceptionUtils.getMessage(ex));
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    public R badSqlGrammarExceptionHandler(BadSqlGrammarException ex) {
        log.error(ExceptionUtils.getMessage(ex));
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(value = GuliException.class)
    public R guliExceptionHandler(GuliException ex) {
        log.error(ExceptionUtils.getMessage(ex));
        return R.error().code(ex.getCode()).message(ex.getMessage());
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public R sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ExceptionUtils.getMessage(ex));
        return R.setResult(ResultCodeEnum.UNIQUE_NAME);
    }
    @ExceptionHandler(value = Exception.class)
    public R errorHandler(Exception ex) {
        log.error(ExceptionUtils.getMessage(ex));
        return R.error();
    }

}
