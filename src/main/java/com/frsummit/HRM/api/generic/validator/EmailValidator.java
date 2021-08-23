package com.frsummit.HRM.api.generic.validator;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Email;

import com.frsummit.HRM.api.exception.ApiSubErrorValidation;
import com.frsummit.HRM.api.exception.ApiValidationException;

/**
 * 
 * @author hfoko
 *
 */
public class EmailValidator extends AbstractValidator {
    public static final String VALIDATOR_NAME = "EMAIL";

    /**
     * 
     * @author hfoko
     *
     */
    class TestValidation {
        @Email
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
    public EmailValidator( String param ) {
        super( param );
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiValidationException {
        String testEmail = (String) val;
        TestValidation test = this.new TestValidation( testEmail );
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
                                    testEmail + " must be a valid email" ) ) );

        return true;
    }

}
