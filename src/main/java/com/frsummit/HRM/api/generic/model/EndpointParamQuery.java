package com.frsummit.HRM.api.generic.model;

import java.util.List;

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
public class EndpointParamQuery extends EndpointParamAbstract {

    private String  name;
    private boolean required;

    /**
     * 
     * @throws ClassNotFoundException
     * @throws ValidatorGenerationException
     * @throws NullPointerException
     * @throws ApiNullParamTypeException
     */
    public EndpointParamQuery() throws ClassNotFoundException,
            ValidatorGenerationException,
            NullPointerException,
            ApiNullParamTypeException {
        super();
    }

    /**
     * 
     * @param name
     * @param required
     * @param clientType
     * @param serverType
     * @param validators
     * @throws ClassNotFoundException
     * @throws ValidatorGenerationException
     * @throws NullPointerException
     * @throws ApiNullParamTypeException
     */
    public EndpointParamQuery(
            String name,
            boolean required,
            String clientType,
            String serverType,
            List<EndpointParamValidator> validators )
            throws ClassNotFoundException,
            ValidatorGenerationException,
            NullPointerException,
            ApiNullParamTypeException {
        super( clientType, serverType, validators );
        this.name = name;
        this.required = required;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName( String name ) {
        this.name = name;
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
    @Override
    public Object parse( Object val ) throws ApiServerException, ApiParamFormatException {
        ObjectMapper mapper = new ObjectMapper();
        if ( realType != null ) {
            try {
                return mapper.convertValue( val, realType );
            } catch ( IllegalArgumentException e ) {
                e.printStackTrace();
                throw new ApiParamFormatException(
                        List.of(
                                new ApiSubErrorBadParamFormat(
                                        val.toString(),
                                        "Refers to doc",
                                        "The param '" + name + "' is invalid or bad formatted" ) ) );
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
        if ( realValidators != null ) {
            for ( ApiValidator validator : realValidators ) {
                if ( !validator.validate( val ) )
                    return false;
            }
            return true;
        } else {
            throw new ApiServerException();
        }
    }

}
