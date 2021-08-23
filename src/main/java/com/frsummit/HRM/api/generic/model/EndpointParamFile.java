package com.frsummit.HRM.api.generic.model;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
public class EndpointParamFile implements CanParseParameter, CanValidateParameter, ContainValue {

    private String                       contentType;
    private Long                         maxSize;
    private Object                       value;
    private List<EndpointParamValidator> validators     = null;

    // compiled versions of type and validators for optimizing usage
    private List<ApiValidator>           realValidators = null;

    /**
     * 
     */
    public EndpointParamFile() {
        super();
    }

    /**
     * 
     * @param contentType
     * @param maxSize
     * @throws ValidatorGenerationException
     * @throws ApiNullParamTypeException
     */
    public EndpointParamFile( String contentType, Long maxSize ) throws ValidatorGenerationException,
            ApiNullParamTypeException {
        super();
        this.contentType = contentType;
        this.maxSize = maxSize;
        realValidators = EndpointParamAbstract.generateRealValidators( validators );
    }

    /**
     * 
     * @return
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 
     * @param contentType
     */
    public void setContentType( String contentType ) {
        this.contentType = contentType;
    }

    /**
     * 
     * @return
     */
    public Long getMaxSize() {
        return maxSize;
    }

    /**
     * 
     * @param maxSize
     */
    public void setMaxSize( Long maxSize ) {
        this.maxSize = maxSize;
    }

    /**
     * 
     * @return
     */
    public List<EndpointParamValidator> getValidators() {
        return validators;
    }

    /**
     * @throws ApiNullParamTypeException
     * 
     */
    public void setValidators( List<EndpointParamValidator> validators )
            throws ValidatorGenerationException,
            ApiNullParamTypeException {
        this.validators = validators;
        realValidators = EndpointParamAbstract.generateRealValidators( validators );
    }

    /**
     * 
     */
    @Override
    public void setValue( Object val ) {
        this.value = val;
    }

    /**
     * 
     */
    @Override
    public Object getValue() {
        return value;
    }

    /**
     * 
     */
    @Override
    public Object parse( Object val ) throws ApiParamFormatException, ApiServerException {
        MultipartFile file = (MultipartFile) val;
        try {
            return file.getBytes();
        } catch ( IOException e ) {
            e.printStackTrace();
            throw new ApiParamFormatException(
                    List.of( new ApiSubErrorBadParamFormat(
                            "file",
                            "Refers to doc",
                            "Impossible to load the file" ) ) );
        }
    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiServerException, ApiValidationException {
        for ( ApiValidator validator : realValidators ) {
            if ( !validator.validate( val ) )
                return false;
        }
        return true;
    }

}
