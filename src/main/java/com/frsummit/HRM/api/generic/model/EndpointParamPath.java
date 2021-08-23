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
public class EndpointParamPath extends EndpointParamAbstract {

    private String name;

    /**
     * 
     * @throws ClassNotFoundException
     * @throws ValidatorGenerationException
     * @throws NullPointerException
     * @throws ApiNullParamTypeException
     */
    public EndpointParamPath() throws ClassNotFoundException,
            ValidatorGenerationException,
            NullPointerException,
            ApiNullParamTypeException {
        super();
    }

    /**
     * 
     * @param name
     * @param clientType
     * @param serverType
     * @param validators
     * @throws ClassNotFoundException
     * @throws ValidatorGenerationException
     * @throws NullPointerException
     * @throws ApiNullParamTypeException
     */
    public EndpointParamPath(
            String name,
            String clientType,
            String serverType,
            List<EndpointParamValidator> validators )
            throws ClassNotFoundException,
            ValidatorGenerationException,
            NullPointerException,
            ApiNullParamTypeException {
        super( clientType, serverType, validators );
        this.setName( name );
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
                                        name,
                                        "Refers to the doc",
                                        "The param '" + name + "' is invalid or bad formatted" ) ) );
                // TODO Please check the documentation
            }
        } else {
            throw new ApiServerException();
            // TODO Please try to contact an administrator
        }
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiServerException, ApiValidationException {
        if ( realValidators != null ) {
            for ( ApiValidator validator : realValidators ) {
                if ( !validator.validate( val ) )
                    return false;
            }
            return true;
        } else {
            throw new ApiServerException();
            // TODO Please try to contact an administrator
        }
    }

}
