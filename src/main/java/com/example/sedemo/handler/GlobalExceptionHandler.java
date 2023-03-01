package com.example.sedemo.handler;

import com.example.sedemo.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    private static final String DUPLICATE_KEY_WARNING = "Duplicate entry";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException exception) {
        String errorMsg = exception.getMessage();
        log.error(errorMsg);

        if(errorMsg.contains(DUPLICATE_KEY_WARNING)) {
            String[] split = errorMsg.split(" ");
            String msg = split[2] + " 已存在";
            return Result.error().msg(msg);
        }

        return Result.error().msg("出现错误");
    }

    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public Result<String> handleValidatedException(BindException e) {
        return Result.error().msg(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}