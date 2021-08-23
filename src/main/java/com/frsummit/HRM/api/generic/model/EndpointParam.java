package com.frsummit.HRM.api.generic.model;

import com.frsummit.HRM.api.exception.ApiNullParamTypeException;
import com.frsummit.HRM.api.exception.ApiParamFormatException;
import com.frsummit.HRM.api.exception.ApiServerException;
import com.frsummit.HRM.api.exception.ApiValidationException;
import com.frsummit.HRM.api.exception.EndpointParameterException;

/**
 * 
 * @author hfoko
 *
 */
public class EndpointParam implements CanParseParameter, CanValidateParameter, ContainValue {

    private EndpointParamQuery   query;
    private EndpointParamPath    path;
    private EndpointParamBody    body;
    private EndpointParamFile    file;

    private CanParseParameter    parser;
    private CanValidateParameter validator;
    private ContainValue         container;

    /**
     * 
     * @throws EndpointParameterException
     */
    public EndpointParam() throws EndpointParameterException {
        super();

        checkConformity();
    }

    /**
     * 
     * @param body
     * @param path
     * @param query
     * @param file
     * @throws EndpointParameterException
     */
    public EndpointParam(
            EndpointParamBody body,
            EndpointParamPath path,
            EndpointParamQuery query,
            EndpointParamFile file )
            throws EndpointParameterException {
        this.body = body;
        this.path = path;
        this.query = query;
        this.file = file;

        checkConformity();

    }

    /**
     * 
     * @return
     * @throws ApiNullParamTypeException
     */
    public String getClientType() throws ApiNullParamTypeException {
        if ( file != null ) {
            return byte[].class.getName();
        } else {
            if ( query != null )
                return query.getClientType();
            else if ( path != null )
                return path.getClientType();
            else if ( body != null )
                return body.getClientType();
            else
                throw new ApiNullParamTypeException();
        }
    }

    /**
     * 
     * @return
     * @throws ApiServerException
     * @throws ApiNullParamTypeException
     */
    public String getServerType() throws ApiNullParamTypeException {
        if ( file != null ) {
            return byte[].class.getName();
        } else {
            if ( query != null )
                return query.getServerType();
            else if ( path != null )
                return path.getServerType();
            else if ( body != null )
                return body.getServerType();
            else
                throw new ApiNullParamTypeException();
        }
    }

    /**
     * 
     * @return
     */
    public CanParseParameter getParser() {
        return parser;
    }

    /**
     * 
     * @return
     */
    public EndpointParamBody getBody() {
        return body;
    }

    /**
     * 
     * @param body
     * @throws EndpointParameterException
     */
    public void setBody( EndpointParamBody body ) throws EndpointParameterException {
        this.body = body;
        checkConformity();
    }

    /**
     * 
     * @return
     */
    public EndpointParamPath getPath() {
        return path;
    }

    /**
     * 
     * @param path
     * @throws EndpointParameterException
     */
    public void setPath( EndpointParamPath path ) throws EndpointParameterException {
        this.path = path;
        checkConformity();
    }

    /**
     * 
     * @return
     */
    public EndpointParamQuery getQuery() {
        return query;
    }

    /**
     * 
     * @param query
     * @throws EndpointParameterException
     */
    public void setQuery( EndpointParamQuery query ) throws EndpointParameterException {
        this.query = query;
        checkConformity();
    }

    /**
     * 
     * @return
     */
    public EndpointParamFile getFile() {
        return file;
    }

    /**
     * 
     * @param file
     * @throws EndpointParameterException
     */
    public void setFile( EndpointParamFile file ) throws EndpointParameterException {
        this.file = file;
        checkConformity();
    }

    /**
     * 
     * @return
     * @throws EndpointParameterException
     */
    public boolean checkConformity() throws EndpointParameterException {
        Object[] params = new Object[] { query, path, body, file };
        Object temp;
        for ( int i = 0, j = 0; i < params.length; i++ ) {
            temp = params[i];
            if ( temp != null && j < 1 ) {
                this.parser = (CanParseParameter) temp;
                this.validator = (CanValidateParameter) temp;
                this.container = (ContainValue) temp;
                j++;
            } else if ( temp != null && j != 0 ) {
                throw new EndpointParameterException();
            }
        }
        return true;
    }

    /**
     * 
     */
    @Override
    public Object getValue() {
        return container.getValue();
    }

    /**
     * 
     */
    @Override
    public void setValue( Object val ) {
        container.setValue( val );

    }

    /**
     * 
     */
    @Override
    public boolean validate( Object val ) throws ApiServerException, ApiValidationException {
        return this.validator.validate( val );
    }

    /**
     * 
     */
    @Override
    public Object parse( Object val ) throws ApiParamFormatException, ApiServerException {
        return this.parser.parse( val );
    }

}
