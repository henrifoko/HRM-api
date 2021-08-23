package com.frsummit.HRM.api.generic.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frsummit.HRM.api.exception.ApiNullParamTypeException;
import com.frsummit.HRM.api.exception.ValidatorGenerationException;
import com.frsummit.HRM.api.generic.validator.AbstractValidator;
import com.frsummit.HRM.api.generic.validator.ApiValidator;
import com.frsummit.HRM.api.util.Util;

/**
 * 
 * @author hfoko
 *
 */
abstract public class EndpointParamAbstract implements CanParseParameter, CanValidateParameter, ContainValue {
    protected String                       clientType     = null;
    protected String                       serverType     = null;
    protected Object                       value          = null;;
    protected List<EndpointParamValidator> validators     = null;

    // compiled versions of type and validators for optimizing usage
    protected Class<?>                     realType       = null;
    protected List<ApiValidator>           realValidators = null;

    /**
     * 
     * @throws ClassNotFoundException
     * @throws ValidatorGenerationException
     * @throws ApiNullParamTypeException
     * @throws NullPointerException
     */
    public EndpointParamAbstract() throws ClassNotFoundException,
            ValidatorGenerationException,
            NullPointerException,
            ApiNullParamTypeException {
        super();

        generateRealType();
        this.realValidators = generateRealValidators( validators );
    }

    /**
     * 
     * @param clientType
     * @param serverType
     * @param validators
     * @throws ClassNotFoundException
     * @throws ValidatorGenerationException
     * @throws ApiNullParamTypeException
     * @throws NullPointerException
     */
    public EndpointParamAbstract(
            String clientType,
            String serverType,
            List<EndpointParamValidator> validators )
            throws ClassNotFoundException,
            ValidatorGenerationException,
            NullPointerException,
            ApiNullParamTypeException {
        super();
        this.clientType = clientType;
        this.serverType = serverType;
        this.validators = validators;

        generateRealType();
        this.realValidators = generateRealValidators( validators );
    }

    /**
     * 
     * @return
     */
    public String getServerType() {
        return serverType;
    }

    /**
     * 
     * @param serverType
     */
    public void setServerType( String serverType ) {
        this.serverType = serverType;
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
    public void setValue( Object value ) {
        this.value = value;
    }

    /**
     * 
     * @return
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * 
     * @param type
     * @throws ClassNotFoundException
     * @throws ValidatorGenerationException
     * @throws ApiNullParamTypeException
     * @throws NullPointerException
     */
    public void setClientType( String type ) throws ClassNotFoundException, ValidatorGenerationException,
            NullPointerException, ApiNullParamTypeException {
        this.clientType = type;
        generateRealType();
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
            throws ClassNotFoundException, ValidatorGenerationException, ApiNullParamTypeException {
        this.validators = validators;
        this.realValidators = generateRealValidators( validators );
    }

    /**
     * 
     * @return
     */
    public Class<?> getRealType() {
        return realType;
    }

    /**
     * 
     * @return
     */
    public List<ApiValidator> getRealValidators() {
        return realValidators;
    }

    /**
     * 
     * @throws ClassNotFoundException
     * @throws NullPointerException
     * @throws ApiNullParamTypeException
     */
    private void generateRealType() throws ClassNotFoundException, NullPointerException, ApiNullParamTypeException {
        if ( clientType != null ) {
            try {
                realType = Class.forName( clientType );
            } catch ( ClassNotFoundException e ) {
                e.printStackTrace();

                throw e;
            }
        } else {
            throw new ApiNullParamTypeException();
        }
    }

    /**
     * 
     * @param validators
     * @return
     * @throws ValidatorGenerationException
     * @throws ApiNullParamTypeException
     */
    public static List<ApiValidator> generateRealValidators( List<EndpointParamValidator> validators )
            throws ValidatorGenerationException, ApiNullParamTypeException {

        ObjectMapper mapper = new ObjectMapper();
        List<ApiValidator> realValidators = new ArrayList<ApiValidator>();

        if ( validators != null ) {
            for ( EndpointParamValidator endpointValidator : validators ) {
                String validatorClassName = endpointValidator.getClassName();
                List<String> constructorParamTypesName = endpointValidator.getConstructorParamTypes();
                List<String> constructorParams = endpointValidator.getConstructorParams();

                // First step: Get the validator class from his class name
                Class<?> validatorClass = null;
                try {
                    validatorClass = (Class<?>) Class.forName( validatorClassName );
                } catch ( ClassNotFoundException e ) {
                    e.printStackTrace();
                    throw new ValidatorGenerationException();
                }

                // Assert: validatorClass contain the class of a validator

                // Second step: Determine the parameter types list
                Class<?>[] constructorParamTypes = null;
                try {
                    constructorParamTypes = Arrays.copyOf( constructorParamTypesName
                            .stream()
                            .map( typeName -> {

                                return Util.parseType( typeName );
                                // try {
                                // return Class.forName( typeName );
                                // } catch ( ClassNotFoundException e ) {
                                // // TODO Auto-generated catch block
                                // e.printStackTrace();
                                // throw new RuntimeException();
                                // }
                            } )
                            .toArray(),
                            constructorParamTypesName.size(),
                            Class[].class );
                } catch ( Exception e ) {
                    e.printStackTrace();
                    throw new ValidatorGenerationException();
                }

                // Third step: Determine the parameter types list
                Object[] constructorParamConverted = new Object[constructorParams.size()];
                try {
                    for ( int i = 0, l = constructorParams.size(); i < l; i++ ) {
                        try {
                            constructorParamConverted[i] = mapper.convertValue(
                                    constructorParams.get( i ),
                                    (Class<?>) constructorParamTypes[i] );
                        } catch ( IllegalArgumentException e ) {
                            // TODO: handle exception
                            e.printStackTrace();
                            throw new ValidatorGenerationException();
                        }
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                    throw new ValidatorGenerationException();
                }

                // Fourth step: Get the constructor
                Constructor<?> constructor = null;
                try {
                    constructor = validatorClass.getConstructor( (Class<?>[]) constructorParamTypes );
                } catch ( NoSuchMethodException e ) {
                    e.printStackTrace();
                    throw new ValidatorGenerationException();
                } catch ( SecurityException e ) {
                    e.printStackTrace();
                    throw new ValidatorGenerationException();
                }

                // Construct the validator object
                AbstractValidator validator = null;
                try {
                    validator = (AbstractValidator) constructor.newInstance( constructorParamConverted );
                } catch ( InstantiationException e ) {
                    e.printStackTrace();
                    throw new ValidatorGenerationException();
                } catch ( IllegalAccessException e ) {
                    e.printStackTrace();
                    throw new ValidatorGenerationException();
                } catch ( IllegalArgumentException e ) {
                    e.printStackTrace();
                    throw new ValidatorGenerationException();
                } catch ( InvocationTargetException e ) {
                    e.printStackTrace();
                    throw new ValidatorGenerationException();
                }

                // Sixth step: Add the validator to the list of real validators
                realValidators.add( validator );
            }
        } else {
            throw new ApiNullParamTypeException();
        }
        return realValidators;
    }
}
