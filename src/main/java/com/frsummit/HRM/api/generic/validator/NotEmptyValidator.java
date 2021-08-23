package com.frsummit.HRM.api.generic.validator;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;

import com.frsummit.HRM.api.exception.ApiSubErrorValidation;
import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public class NotEmptyValidator extends AbstractValidator {
    /**
     * 
     */
    public static final String VALIDATOR_NAME = "NOT_EMPTY";

    /**
     * 
     */
    class TestValidation {
        @NotEmpty
        private String value;

        public TestValidation( String value ) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue( String value ) {
            this.value = value;
        }
    }

    /**
     * 
     * @param param
     */
    public NotEmptyValidator( String param ) {
        super( param );
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiValidationException {
        String testNotEmpty = (String) val;
        TestValidation test = this.new TestValidation( testNotEmpty );
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
                                    param + " cannot be a empty string" ) ) );
        return true;
    }

}