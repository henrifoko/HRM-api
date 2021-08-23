package com.frsummit.HRM.api.generic.validator;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Future;

import com.frsummit.HRM.api.exception.ApiSubErrorValidation;
import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public class FutureValidator extends AbstractValidator {

    /**
     * 
     */
    public static final String VALIDATOR_NAME = "FUTURE_DATE";

    /**
     * 
     * @author hfoko
     *
     */
    class TestValidation {
        @Future
        private Date value;

        public TestValidation( Date value ) {
            this.value = value;
        }

        public Date getValue() {
            return value;
        }

        public void setValue( Date value ) {
            this.value = value;
        }
    }

    /**
     * 
     * @param param
     */
    public FutureValidator( String param ) {
        super( param );
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiValidationException {
        Date testDate = (Date) val;
        TestValidation test = this.new TestValidation( testDate );
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = (Validator) factory.getValidator();
        Set<ConstraintViolation<TestValidation>> constraintViolation = validator.validate( test );
        if ( !constraintViolation.isEmpty() )
            throw new ApiValidationException(
                    List.of(
                            new ApiSubErrorValidation(
                                    null,
                                    param,
                                    val.toString(),
                                    param + " must be located in the future" ) ) );
        return true;
    }

}