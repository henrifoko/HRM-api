package com.frsummit.HRM.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author hfoko
 *
 */
public class ApiError {

    private ApiErrorCode      code;
    private HttpStatus        status;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss" )
    private LocalDateTime     timestamp;
    private String            message;
    private String            debugMessage;
    private List<ApiSubError> subErrors;

    /**
     * 
     * @param code
     * @param status
     * @param message
     * @param debugMessage
     * @param subErrors
     */
    public ApiError(
            ApiErrorCode code,
            HttpStatus status,
            String message,
            String debugMessage,
            List<ApiSubError> subErrors ) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
        this.subErrors = subErrors;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 
     * @param code
     * @param status
     * @param timestamp
     * @param message
     * @param debugMessage
     * @param subErrors
     */
    public ApiError(
            ApiErrorCode code,
            HttpStatus status,
            LocalDateTime timestamp,
            String message,
            String debugMessage,
            List<ApiSubError> subErrors ) {
        this.code = code;
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.debugMessage = debugMessage;
        this.subErrors = subErrors;
    }

    /**
     * 
     * @return
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     */
    public void setStatus( HttpStatus status ) {
        this.status = status;
    }

    /**
     * 
     * @return
     */
    public String getDebugMessage() {
        return debugMessage;
    }

    /**
     * 
     * @param debugMessage
     */
    public void setDebugMessage( String debugMessage ) {
        this.debugMessage = debugMessage;
    }

    /**
     * 
     * @return
     */
    public List<ApiSubError> getSubErrors() {
        return subErrors;
    }

    /**
     * 
     * @param subErrors
     */
    public void setSubErrors( List<ApiSubError> subErrors ) {
        this.subErrors = subErrors;
    }

    /**
     * 
     * @param timestamp
     */
    public void setTimestamp( LocalDateTime timestamp ) {
        this.timestamp = timestamp;
    }

    /**
     * 
     * @return
     */
    public ApiErrorCode getCode() {
        return code;
    }

    /**
     * 
     * @param code
     */
    public void setCode( ApiErrorCode code ) {
        this.code = code;
    }

    /**
     * 
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     */
    public void setMessage( String message ) {
        this.message = message;
    }

    /**
     * 
     * @return
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
