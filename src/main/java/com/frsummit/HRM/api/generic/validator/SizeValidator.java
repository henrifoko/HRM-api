package com.frsummit.HRM.api.generic.validator;

import java.util.Collection;
import java.util.List;

import com.frsummit.HRM.api.exception.ApiSubErrorValidation;
import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public class SizeValidator extends AbstractValidator {

    /**
     * 
     */
    public static final String VALIDATOR_NAME = "SIZE_RANGE";
    private Integer            min;
    private Integer            max;

    /**
     * 
     * @param param
     * @param min
     * @param max
     */
    public SizeValidator( String param, Integer min, Integer max ) {
        super( param );
        this.min = min;
        this.max = max;
    }

    /**
     * @throws ApiValidationException
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiValidationException {
        boolean valid = true;

        if ( val instanceof String ) {
            String test = (String) val;
            if ( min != null && test.length() < min ) {
                valid = false;
            } else if ( max != null && test.length() > max ) {
                valid = false;
            }
        } else if ( val.getClass().isArray() ) {
            Object[] test = (Object[]) val;
            if ( min != null && test.length < min ) {
                valid = false;
            } else if ( max != null && test.length > max ) {
                valid = false;
            }
        } else if ( val instanceof Collection<?> ) {
            Collection<?> test = (Collection<?>) val;
            if ( min != null && test.size() < min ) {
                valid = false;
            } else if ( max != null && test.size() > max ) {
                valid = false;
            }
        }

        if ( !valid ) {
            String message = "";
            if ( min == null && max == null ) {
                // This case normally never occurs
            } else if ( min == null && max != null ) {
                message = "The size of " + param + " must be less than " + max;
            } else if ( min != null && max == null ) {
                message = "The size of " + param + " must be greater than " + min;
            } else { // min != null && max != null
                message = "The size of " + param + " must be between " + min + " and " + max;
            }

            throw new ApiValidationException( List.of(
                    new ApiSubErrorValidation(
                            null,
                            param,
                            val.toString(),
                            message ) ) );
        }

        return true;
    }

}