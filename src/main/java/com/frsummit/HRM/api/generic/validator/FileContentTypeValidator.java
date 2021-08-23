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
public class FileContentTypeValidator extends AbstractValidator {

    public static final String VALIDATOR_NAME = "FILE_CONTENT_TYPE";
    private String             contentType;

    /**
     * 
     * @param param
     * @param contentType
     */
    public FileContentTypeValidator( String param, String contentType ) {
        super( param );
        this.contentType = contentType;
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
    public String getContentType() {
        return contentType;
    }

    /**
     * 
     * @param contentType
     */
    public void setContentType( String contentType ) {
        this.contentType = contentType;
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiValidationException {
        MultipartFile file = (MultipartFile) val;
        if ( getContentType().equals( file.getContentType() ) )
            throw new ApiValidationException(
                    List.of(
                            new ApiSubErrorValidation(
                                    null,
                                    param,
                                    file.getContentType(),
                                    "Content type of the file is invalid" ) ) );
        return true;
    }

}
