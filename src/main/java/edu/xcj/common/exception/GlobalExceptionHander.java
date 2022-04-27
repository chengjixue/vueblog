package edu.xcj.common.exception;

import edu.xcj.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/4/27 21:50
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHander {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handle(ShiroException e){
        log.error("系统异常：{}",e);
        return Result.fail(401, e.getMessage(),null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handle(RuntimeException e){
        log.error("系统异常：{}",e);
        return Result.fail(e.getMessage());
    }



}
