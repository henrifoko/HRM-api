package com.frsummit.HRM.api.generic.controller;

import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frsummit.HRM.api.HrmApiApplication;
import com.frsummit.HRM.api.exception.ApiNullParamTypeException;
import com.frsummit.HRM.api.exception.ApiParamFormatException;
import com.frsummit.HRM.api.exception.ApiRequestConformityException;
import com.frsummit.HRM.api.exception.ApiResourceNotFoundException;
import com.frsummit.HRM.api.exception.ApiServerException;
import com.frsummit.HRM.api.exception.ApiSubErrorConformity;
import com.frsummit.HRM.api.exception.ApiValidationException;
import com.frsummit.HRM.api.generic.model.ApiEpconfig;
import com.frsummit.HRM.api.generic.model.EndpointConfig;
import com.frsummit.HRM.api.generic.model.EndpointConfigInvocation;
import com.frsummit.HRM.api.generic.model.EndpointParam;
import com.frsummit.HRM.api.generic.model.EndpointParamBody;
import com.frsummit.HRM.api.generic.model.EndpointParamPath;
import com.frsummit.HRM.api.generic.model.EndpointParamQuery;
import com.frsummit.HRM.api.generic.service.EndpointService;
import com.frsummit.HRM.api.generic.service.InvocationService;
import com.frsummit.HRM.api.server.shared.command.Command;
import com.frsummit.HRM.api.util.Util;

/**
 * 
 * @author hfoko
 *
 */
@RestController
public class Controller {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Autowired
    private EndpointService              endpointService;

    @Autowired
    private InvocationService            invocationService;

    private static final Logger          LOGGER = LoggerFactory.getLogger( Controller.class );

    /***
     * Register controller methods to various URLs. To form
     * 
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    @PostConstruct
    public void init() throws NoSuchMethodException, SecurityException {
        ApiEpconfig epconfig = endpointService.getEpconfig();
        LOGGER.info( "Exposing {} ressource(s)", epconfig.getRessources().size() );
        epconfig.getRessources().stream().forEach( res -> {
            LOGGER.info( "Resource [{}]", res.getName() );
            res.getEndpoints().stream().forEach( e -> {
                String uri = endpointService.generateEndpointUri( e, res );

                try {
                    Method method = Controller.class.getDeclaredMethod(
                            "parametrizedHandler",
                            Map.class,
                            Map.class,
                            MultipartFile.class,
                            RequestEntity.class,
                            HttpServletRequest.class );

                    handlerMapping.registerMapping(
                            RequestMappingInfo
                                    .paths( Util.withoutEndingSlash( uri ) )
                                    .methods( e.getMethod().getRequestMethod() )
                                    .produces( MediaType.APPLICATION_JSON_VALUE ).build(),
                            this,
                            method );

                    LOGGER.info( "Mapped URL path [{}: {}] onto {}",
                            e.getMethod().getRequestMethod().toString(),
                            Util.withoutEndingSlash( uri ),
                            method.toGenericString() );
                } catch ( NoSuchMethodException e2 ) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();

                    HrmApiApplication.exit();
                } catch ( SecurityException e2 ) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();

                    HrmApiApplication.exit();
                }

                // /api/v1/resource/users/email/user@outlook.com/
                // must be equivalent to
                // /api/v1/resource/users/email/user@outlook.com
                System.out.println( "Registering uri: " + Util.withEndingSlash( uri ) );
                try {
                    Method method = Controller.class.getDeclaredMethod(
                            "parametrizedHandler",
                            Map.class,
                            Map.class,
                            MultipartFile.class,
                            RequestEntity.class,
                            HttpServletRequest.class );
                    handlerMapping.registerMapping(
                            RequestMappingInfo
                                    .paths( Util.withEndingSlash( uri ) )
                                    .methods( e.getMethod().getRequestMethod() )
                                    .produces( MediaType.APPLICATION_JSON_VALUE ).build(),
                            this,
                            method );

                    LOGGER.info( "Mapped URL path [{}: {}] onto {}", e.getMethod().getRequestMethod().toString(),
                            Util.withEndingSlash( uri ),
                            method.toGenericString() );
                } catch ( NoSuchMethodException e1 ) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                    HrmApiApplication.exit();

                } catch ( SecurityException e1 ) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                    HrmApiApplication.exit();

                }

            } );
        } );
    }

    /**
     * Unique method handling all requests.
     * 
     * @param queryParams
     * @param pathVariables
     * @param file
     * @param requestEntity
     * @param servletRequest
     * @return
     * @throws ApiResourceNotFoundException
     * @throws ApiRequestConformityException
     * @throws ApiParamFormatException
     * @throws ApiServerException
     * @throws URISyntaxException
     * @throws RemoteException
     * @throws ApiNullParamTypeException
     * @throws ApiValidationException
     */
    public ResponseEntity<?> parametrizedHandler(
            @RequestParam( ) Map<String, String> queryParams,
            @PathVariable( ) Map<String, String> pathVariables,
            @RequestParam( value = "file", required = false ) MultipartFile file,
            RequestEntity<String> requestEntity,
            HttpServletRequest servletRequest )
            throws ApiResourceNotFoundException,
            ApiRequestConformityException,
            ApiParamFormatException,
            ApiServerException,
            URISyntaxException,
            RemoteException,
            ApiNullParamTypeException,
            ApiValidationException {

        // System.out.println( "Starting generic controller" );
        LOGGER.debug( "Generic handler started" );
        LOGGER.info( "Generic handler catching new request [URI={},method={},client_ip={}]",
                requestEntity.getUrl().toString(),
                requestEntity.getMethod(),
                servletRequest.getHeader( "X-FORWARDED-FOR" ) );

        List<Object> paramList = null;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
            // No content here
        };

        // System.out.println( "Finding the end point" );
        LOGGER.debug( "Getting the endpoint associated to this uri [{}]", requestEntity.getUrl().toString() );

        // Get the End point
        EndpointConfig endpoint = endpointService.findEndpoint(
                requestEntity.getUrl().toString(),
                requestEntity.getMethod() );

        LOGGER.debug( "Endpoint was found [{}]", endpoint.getName() );
        LOGGER.debug( "Extracting the request body..." );

        // Extract body parameter
        String bodyJson = requestEntity.getBody();

        Map<String, Object> bodyMap = null;
        try {
            if ( bodyJson != null && !bodyJson.isBlank() ) {
                bodyMap = mapper.readValue( bodyJson, typeRef );
            }
        } catch ( JsonProcessingException e ) {
            e.printStackTrace();
        }

        LOGGER.debug( "Body parsed successfully" );
        LOGGER.debug( "Checking the conformity of the request..." );

        // Check conformity of parameters
        // boolean conform = checkConformity( end point, pathVariables,
        // queryParams, bodyMap );
        checkConformity( endpoint, pathVariables, queryParams, bodyMap );

        LOGGER.debug( "Request conform" );
        LOGGER.debug( "Converting parameters..." );

        // Converts parameters
        paramList = convertParams( endpoint, pathVariables, queryParams, bodyMap );

        LOGGER.debug( "Parameters converted with success" );
        LOGGER.debug( "Validating parameters..." );

        // Validates parameters
        // boolean valid = validateParameters( end point );
        validateParameters( endpoint );

        LOGGER.debug( "Parameters validated" );
        LOGGER.debug( "Appling endpoint preprocessing filter..." );

        Object[] params = endpoint.getPreprocessing().getFilter().process(
                paramList.toArray(),
                queryParams,
                pathVariables,
                file,
                requestEntity,
                servletRequest );

        LOGGER.debug( "Endpoint preprocessed with success" );
        LOGGER.debug( "Generating command..." );

        // Constructs command
        Command command = generateCommand( endpoint, params );

        LOGGER.debug( "Command generated" );
        LOGGER.debug( "Invocing the remote invoker..." );

        // Submits command
        Object result = null;
        try {
            result = invocationService.invoke( command );
        } catch ( RemoteException e ) {
            e.printStackTrace();

            throw e;
        }

        LOGGER.debug( "Invocation success" );
        LOGGER.info( "Returning result for request [URI={}, method={}]",
                requestEntity.getUrl().toString(),
                requestEntity.getMethod(),
                servletRequest.getHeader( "X-FORWARDED-FOR" ) );

        // Filters and format response result
        return endpoint.getPostprocessing().getFilter().process(
                result,
                queryParams,
                pathVariables,
                file,
                requestEntity,
                servletRequest );
    }

    /**
     * 
     * @param endpoint
     * @param pathVariables
     * @param queryParams
     * @param bodyMap
     * @return
     * @throws ApiRequestConformityException
     */
    public boolean checkConformity(
            EndpointConfig endpoint,
            Map<String, String> pathVariables,
            Map<String, String> queryParams,
            Map<String, Object> bodyMap )
            throws ApiRequestConformityException {

        LOGGER.debug( "Check conformity of the request [hash={}]", endpoint.hashCode() );

        EndpointConfigInvocation invocation = endpoint.getInvocation();
        List<EndpointParam> params = invocation.getParams();
        for ( EndpointParam param : params ) {
            if ( param.getParser() instanceof EndpointParamPath ) {
                EndpointParamPath paramPath = (EndpointParamPath) param.getParser();
                String name = paramPath.getName();
                if ( !pathVariables.containsKey( name ) ) {
                    throw new ApiRequestConformityException(
                            List.of(
                                    new ApiSubErrorConformity(
                                            name,
                                            "The url parameter "
                                                    + name
                                                    + " should be provided in the path uri" ) ) );
                }
            } else if ( param.getParser() instanceof EndpointParamQuery ) {
                EndpointParamQuery paramQuery = (EndpointParamQuery) param.getParser();
                boolean isRequired = paramQuery.isRequired();
                String name = paramQuery.getName();
                if ( isRequired ) {
                    if ( !queryParams.containsKey( name ) ) {
                        throw new ApiRequestConformityException(
                                List.of(
                                        new ApiSubErrorConformity(
                                                name,
                                                "The parameter "
                                                        + name
                                                        + " is required." ) ) );
                    } else {
                        // No error
                    }
                }
            } else { // param instance of EndpointParamBody
                EndpointParamBody paramBody = (EndpointParamBody) param.getParser();
                boolean isRequired = paramBody.isRequired();
                if ( isRequired && bodyMap == null ) {
                    throw new ApiRequestConformityException(
                            List.of(
                                    new ApiSubErrorConformity(
                                            "body",
                                            "The body of the request cannot be null or blank" ) ) );
                }
            }
        }

        LOGGER.debug( "Request conforms to the endpoint specifications", endpoint.hashCode() );

        return true;
    }

    /**
     * 
     * @param endpoint
     * @param pathVariables
     * @param queryParams
     * @param bodyMap
     * @return
     * @throws ApiParamFormatException
     * @throws ApiServerException
     */
    public List<Object> convertParams(
            EndpointConfig endpoint,
            Map<String, String> pathVariables,
            Map<String, String> queryParams,
            Map<String, Object> bodyMap )
            throws ApiParamFormatException,
            ApiServerException {

        LOGGER.debug( "Converting parameters of the request [{}] ...", endpoint.hashCode() );

        List<Object> result = new ArrayList<Object>();
        List<EndpointParam> params = endpoint.getInvocation().getParams();

        Object temp = null;
        for ( EndpointParam param : params ) {
            if ( param.getParser() instanceof EndpointParamBody ) {
                EndpointParamBody paramBody = (EndpointParamBody) param.getParser();
                LOGGER.debug( "Body parameter [type={}] ", paramBody.getClientType() );
                temp = paramBody.parse( bodyMap );
                paramBody.setValue( temp );
            } else if ( param.getParser() instanceof EndpointParamQuery ) {
                EndpointParamQuery paramQuery = (EndpointParamQuery) param.getParser();
                LOGGER.debug( "Query parameter [type={},name={}] ", paramQuery.getClientType(), paramQuery.getName() );
                String name = paramQuery.getName();
                boolean isRequired = paramQuery.isRequired();

                if ( isRequired && queryParams.containsKey( name ) ) {
                    // If the parameter is required and present
                    temp = paramQuery.parse( queryParams.get( name ) );

                } else if ( !isRequired && queryParams.containsKey( name ) ) {
                    // If the parameter is not required but present
                    temp = paramQuery.parse( queryParams.get( name ) );

                } else {
                    // If the parameter is neither required nor present
                    temp = null;
                }
                paramQuery.setValue( temp );
            } else { // param instance of EndpointParamPath
                EndpointParamPath paramPath = (EndpointParamPath) param.getParser();
                LOGGER.debug( "Path parameter [type={},name={}] ", paramPath.getClientType(), paramPath.getName() );
                String name = paramPath.getName();

                // Assert: The map pathVariables contains a key ${name}
                // ---
                // This condition was met by the compliance verification
                // stage
                temp = paramPath.parse( pathVariables.get( name ) );
                paramPath.setValue( temp );
            }
            result.add( temp );
        }

        LOGGER.debug( "Parameters converted successfully", endpoint.hashCode() );

        return result;
    }

    /**
     * 
     * @param endpoint
     * @return
     * @throws ApiRequestValidationException
     * @throws ApiServerException
     */
    public boolean validateParameters( EndpointConfig endpoint )
            throws ApiValidationException,
            ApiServerException {

        LOGGER.debug( "Validating parameters of the request [{}] ...", endpoint.hashCode() );

        List<EndpointParam> params = endpoint.getInvocation().getParams();

        for ( EndpointParam param : params ) {
            LOGGER.debug( "Validating [{}] ...", param.getValue() );
            if ( !param.validate( param.getValue() ) )
                return false;
            LOGGER.debug( "[{}] validated (result=valid)", param.getValue() );
        }

        LOGGER.debug( "Validation terminated [{}]", endpoint.hashCode() );

        return true;
    }

    /**
     * 
     * @param endpoint
     * @param params
     * @return
     * @throws ApiNullParamTypeException
     */
    public Command generateCommand( EndpointConfig endpoint, Object[] params ) throws ApiNullParamTypeException {

        LOGGER.debug( "Make a command for the endpoint [name={}, uri={}, hash={}]",
                endpoint.getName(),
                endpoint.getUriPattern(),
                endpoint.hashCode() );

        List<EndpointParam> paramList = endpoint.getInvocation().getParams();
        String[] paramTypes = new String[paramList.size()];
        try {

            for ( int i = 0, l = paramList.size(); i < l; i++ ) {
                paramTypes[i] = paramList.get( i ).getServerType();
            }
            // paramTypes = Arrays.copyOf( endpoint
            // .getInvocation()
            // .getParams()
            // .stream()
            // .map( p -> {
            //
            // return p.getServerType();
            // } )
            // .toArray(),
            // endpoint
            // .getInvocation()
            // .getParams()
            // .size(),
            // String[].class );
        } catch ( ApiNullParamTypeException e ) {
            throw e;
        }

        Command command = new Command(
                endpoint.getInvocation().getBean(),
                endpoint.getInvocation().getMethod(),
                paramTypes,
                params,
                endpoint.getInvocation().getInvokerPreprocessingStrategy(),
                endpoint.getInvocation().getInvokerPostprocessingStrategy() );

        LOGGER.debug( "Command made for the endpoint [{}]", endpoint.hashCode() );

        return command;
    }

}
