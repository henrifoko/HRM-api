package com.frsummit.HRM.api.generic.validator;

import java.util.List;

import com.frsummit.HRM.api.exception.ApiSubErrorValidation;
import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public class NegativeValidator extends AbstractValidator {

    /**
     * 
     */
    public static final String VALIDATOR_NAME = "NEGATIVE_VALUE";

    /**
     * 
     * @param param
     */
    public NegativeValidator( String param ) {
        super( param );
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiValidationException {
        Double test = Double.valueOf( val.toString() );
        if ( test >= 0.0 )
            throw new ApiValidationException(
                    List.of(
                            new ApiSubErrorValidation(
                                    null,
                                    param,
                                    val.toString(),
                                    param + " must be negative" ) ) );

        return true;
    }

}