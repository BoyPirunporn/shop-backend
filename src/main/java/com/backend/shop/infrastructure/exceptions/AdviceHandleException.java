package com.backend.shop.infrastructure.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.shop.infrastructure.jwt.exceptions.JwtException;

@RestControllerAdvice
public class AdviceHandleException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();

        Map<String, String> paths = new HashMap<>();

        // เพิ่มข้อมูล validation ที่ผิดจาก BindingResult
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            paths.put(error.getField(), error.getDefaultMessage());
        });
        errors.put("messages", paths);
        errors.put("status", 400);

        // ส่งเป็น ResponseEntity ที่มี error message พร้อม status code
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<IAdviceHandler> baseException(BaseException ex) {
        IAdviceHandler adviceHandler = new IAdviceHandler();
        ex.printStackTrace();
        adviceHandler.setMessage(ex.getMessage());
        adviceHandler.setStatus(ex.getStatus().value());
        return new ResponseEntity<>(adviceHandler, ex.getStatus());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<IAdviceHandler> baseException(JwtException ex) {
        IAdviceHandler adviceHandler = new IAdviceHandler();
        ex.printStackTrace();
        adviceHandler.setMessage(ex.getMessage());
        adviceHandler.setStatus(ex.getStatus().value());
        return new ResponseEntity<>(adviceHandler, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<IAdviceHandler> throwException(Exception ex){
        IAdviceHandler adviceHandler = new IAdviceHandler();
        ex.printStackTrace();
        adviceHandler.setMessage(ex.getMessage());
        adviceHandler.setStatus(500);
        return new ResponseEntity<>(adviceHandler, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<IAdviceHandler> throwException(Throwable ex){
        IAdviceHandler adviceHandler = new IAdviceHandler();
        ex.printStackTrace();
        adviceHandler.setMessage(ex.getMessage());
        adviceHandler.setStatus(500);
        return new ResponseEntity<>(adviceHandler, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public class IAdviceHandler {
        String message;
        int status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }
}
