package com.frsummit.HRM.api.generic.validator;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Past;

import com.frsummit.HRM.api.exception.ApiSubErrorValidation;
import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public class PastValidator extends AbstractValidator {

    /**
     * 
     */
    public static final String VALIDATOR_NAME = "PAST_DATE";

    /**
     * 
     * @author hfoko
     *
     */
    class TestValidation {
        @Past
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
    public PastValidator( String param ) {
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
                                    param + " must be located in the past" ) ) );
        return true;
    }

}