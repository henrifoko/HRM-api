package com.frsummit.HRM.api.generic.validator;

import java.util.List;

import com.frsummit.HRM.api.exception.ApiSubErrorValidation;
import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public class NotNullValidator extends AbstractValidator {

    /**
     * 
     */
    public static final String VALIDATOR_NAME = "NOT_NULL";

    /**
     * 
     * @param param
     */
    public NotNullValidator( String param ) {
        super( param );
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiValidationException {
        if ( val == null )
            throw new ApiValidationException(
                    List.of(
                            new ApiSubErrorValidation(
                                    null,
                                    param,
                                    "null",
                                    param + " cannot be null" ) ) );
        return true;
    }

}