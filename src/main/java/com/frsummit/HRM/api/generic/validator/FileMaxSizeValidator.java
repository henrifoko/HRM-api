package com.frsummit.HRM.api.generic.validator;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.frsummit.HRM.api.exception.ApiSubErrorValidation;
import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public class FileMaxSizeValidator extends AbstractValidator {

    public static final String VALIDATOR_NAME = "FILE_MAX_SIZE";
    private long               maxSize;

    /**
     * 
     * @param param
     * @param maxSize
     */
    public FileMaxSizeValidator( String param, long maxSize ) {
        super( param );
        this.maxSize = maxSize;
    }

    /**
     * 
     */
    public String getParam() {
        return param;
    }

    /**
     * 
     */
    public void setParam( String param ) {
        this.param = param;
    }

    /**
     * 
     * @return
     */
    public long getMaxSize() {
        return maxSize;
    }

    /**
     * 
     * @param maxSize
     */
    public void setMaxSize( long maxSize ) {
        this.maxSize = maxSize;
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiValidationException {
        MultipartFile file = (MultipartFile) val;
        if ( file.getSize() < maxSize )
            throw new ApiValidationException(
                    List.of(
                            new ApiSubErrorValidation(
                                    null,
                                    param,
                                    file.getSize(),
                                    "File is too large" ) ) );

        return true;
    }

}
