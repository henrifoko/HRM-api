package com.frsummit.HRM.api.generic.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frsummit.HRM.api.exception.ApiNullParamTypeException;
import com.frsummit.HRM.api.exception.ApiParamFormatException;
import com.frsummit.HRM.api.exception.ApiServerException;
import com.frsummit.HRM.api.exception.ApiSubErrorBadParamFormat;
import com.frsummit.HRM.api.exception.ApiValidationException;
import com.frsummit.HRM.api.exception.ValidatorGenerationException;
import com.frsummit.HRM.api.generic.validator.ApiValidator;

/**
 * 
 * @author hfoko
 *
 */
public class EndpointParamBody extends EndpointParamAbstract {

    private boolean required;

    /**
     * 
     * @throws ClassNotFoundException
     * @throws ValidatorGenerationException
     * @throws ApiNullParamTypeException
     * @throws NullPointerException
     */
    public EndpointParamBody() throws ClassNotFoundException, ValidatorGenerationException, NullPointerException,
            ApiNullParamTypeException {
        super();
    }

    /**
     * 
     * @param clientType
     * @param serverType
     * @param required
     * @param validators
     * @throws ClassNotFoundException
     * @throws ValidatorGenerationException
     * @throws ApiNullParamTypeException
     * @throws NullPointerException
     */
    public EndpointParamBody(
            String clientType,
            String serverType,
            boolean required,
            List<EndpointParamValidator> validators )
            throws ClassNotFoundException,
            ValidatorGenerationException,
            NullPointerException,
            ApiNullParamTypeException {
        super( clientType, serverType, validators );
        this.required = required;
    }

    /**
     * 
     * @return
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * 
     * @param required
     */
    public void setRequired( boolean required ) {
        this.required = required;
    }

    /**
     * 
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public Object parse( Object val ) throws ApiParamFormatException, ApiServerException {
        Map<String, Object> bodyMap = null;
        if ( val instanceof Map ) {
            bodyMap = (Map<String, Object>) val;
        } else {
            throw new ApiServerException();
        }

        // Assert: bodyMap contain a Map<String, String>

        ObjectMapper mapper = new ObjectMapper();
        if ( realType != null ) {
            try {
                Object pojo = mapper.convertValue( bodyMap, realType );
                return pojo;
            } catch ( IllegalArgumentException e ) {
                e.printStackTrace();

                throw new ApiParamFormatException(
                        List.of(
                                new ApiSubErrorBadParamFormat(
                                        "body",
                                        "Refers to doc",
                                        "The body of the request is bad formatted" ) ) );
            }
        } else {
            throw new ApiServerException();
        }
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiValidationException, ApiServerException {
        try {
            if ( realValidators != null ) {
                for ( ApiValidator validator : realValidators ) {
                    if ( !validator.validate( val ) )
                        return false;
                }
                return true;
            } else {
                throw new ApiServerException();
            }
        } catch ( ApiValidationException e ) {
            e.printStackTrace();
            throw e;
        } catch ( ApiServerException e ) {
            e.printStackTrace();
            throw e;
        }
    }

}
