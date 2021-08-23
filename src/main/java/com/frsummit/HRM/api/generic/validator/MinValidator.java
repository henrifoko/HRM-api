package com.frsummit.HRM.api.generic.validator;

import java.util.List;

import com.frsummit.HRM.api.exception.ApiSubErrorValidation;
import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public class MinValidator extends AbstractValidator {

    /**
     * 
     */
    public static final String VALIDATOR_NAME = "MIN_VALUE";
    private int                value;

    /**
     * 
     * @param param
     * @param value
     */
    public MinValidator( String param, int value ) {
        super( param );
        this.value = value;
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiValidationException {
        Integer test = Integer.valueOf( val.toString() );
        if ( test < value )
            throw new ApiValidationException(
                    List.of(
                            new ApiSubErrorValidation(
                                    null,
                                    param,
                                    val.toString(),
                                    "Minimum value for the parameter " + param + " is " + value ) ) );
        return true;
    }

}
