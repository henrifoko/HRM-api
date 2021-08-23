package com.frsummit.HRM.api.generic.model;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.frsummit.HRM.api.exception.ApiServerException;

/**
 * 
 * @author hfoko
 *
 */
public class FilterPostprocessingPojoList implements FilterPostprocessing {

    /**
     * 
     */
    @Override
    public ResponseEntity<?> process(
            Object result,
            Map<String, String> queryParams,
            Map<String, String> pathVariables,
            MultipartFile file,
            RequestEntity<String> requestEntity,
            HttpServletRequest servletRequest )
            throws ApiServerException {

        return ResponseEntity.ok( result );
    }

}
