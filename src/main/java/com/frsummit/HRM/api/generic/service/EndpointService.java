package com.frsummit.HRM.api.generic.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frsummit.HRM.api.HrmApiApplication;
import com.frsummit.HRM.api.config.ServerFrontConig;
import com.frsummit.HRM.api.exception.ApiResourceNotFoundException;
import com.frsummit.HRM.api.exception.ApiSubErrorRessource;
import com.frsummit.HRM.api.generic.model.ApiEpconfig;
import com.frsummit.HRM.api.generic.model.ApiResource;
import com.frsummit.HRM.api.generic.model.EndpointConfig;
import com.frsummit.HRM.api.util.Util;

/**
 * 
 * @author hfoko
 *
 */
@Service
public class EndpointService {

    private static final ObjectMapper   OBJECT_MAPPER = new ObjectMapper();
    private static ApiEpconfig          epconfig;
    private static List<EndpointConfig> endpoints;

    /**
     * 
     * @throws IOException
     */
    public EndpointService() throws IOException {

        // Loading End points configuration while the service is being
        // constructed
        try {
            loadConfig();
        } catch ( IOException e ) {
            System.err.println( "Error on the loading of the endpoint config file" );
            e.printStackTrace();

            HrmApiApplication.exit();
        }
    }

    /**
     * 
     * @throws IOException
     */
    public static void loadConfig() throws IOException {

        Path path = Paths.get( ServerFrontConig.ENDPOINT_CONFIG_FILE );

        String json = null;
        try {
            json = Files.readString( path );
            epconfig = OBJECT_MAPPER.readValue( json, ApiEpconfig.class );

            // TODO : Delete this test code
            System.out.println( "ressource name   : "
                    + epconfig.getRessources().get( 0 ).getName() );
            System.out.println( "ressource uri    : "
                    + epconfig.getRessources().get( 0 ).getUri() );
            System.out.println( "endpoint name    : "
                    + epconfig.getRessources().get( 0 ).getEndpoints().get( 0 ).getName() );
            System.out.println( "endpoint version : "
                    + epconfig.getRessources().get( 0 ).getEndpoints().get( 0 ).getVersion() );
            System.out.println( "endpoint uri     : "
                    + epconfig.getRessources().get( 0 ).getEndpoints().get( 0 ).getUriPattern() );

            // Initialize the end point list
            initEndpoints();

        } catch ( IOException e ) {
            e.printStackTrace();

            throw e;
        }

    }

    /**
     * 
     * @return
     */
    public ApiEpconfig getEpconfig() {
        return epconfig;
    }

    /**
     * 
     */
    private static void initEndpoints() {
        endpoints = new ArrayList<EndpointConfig>();
        epconfig.getRessources()
                .stream()
                .map( r -> r.getEndpoints() )
                .forEach( endpointList -> endpointList
                        .forEach( e -> endpoints
                                .add( e ) ) );
    }

    /**
     * 
     * @return
     */
    public List<EndpointConfig> findAll() {
        List<EndpointConfig> endoints = new ArrayList<EndpointConfig>();
        for ( ApiResource res : epconfig.getRessources() ) {
            for ( EndpointConfig e : res.getEndpoints() ) {
                endoints.add( e );
            }
        }
        return endpoints;
    }

    /**
     * 
     * @param uri
     * @param method
     * @return
     * @throws ApiResourceNotFoundException
     * @throws URISyntaxException
     */
    public EndpointConfig findEndpoint(
            String uri,
            HttpMethod method )
            throws ApiResourceNotFoundException,
            URISyntaxException {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        URI uriObj = new URI( uri );
        String path = uriObj.getRawPath();
        for ( ApiResource res : epconfig.getRessources() ) {
            for ( EndpointConfig e : res.getEndpoints() ) {
                String uriPattern = generateEndpointUri( e, res );
                // System.out.println( "p : " + uriPattern );
                // System.out.println( "u : " + uri );
                // System.out.println( pathMatcher.match( uriPattern, path ) );
                if ( ( pathMatcher.match( Util.withoutEndingSlash( uriPattern ), path )
                        || pathMatcher.match( Util.withEndingSlash( uriPattern ), path ) )
                        && e.getMethod().toString().equals( method.toString() ) ) {
                    return e;
                }
            }
        }

        throw new ApiResourceNotFoundException(
                List.of(
                        new ApiSubErrorRessource(
                                uri,
                                method.toString() ) ) );
    }

    /**
     * 
     * @return
     */
    public List<String> getAllUris() {
        List<String> uris = new ArrayList<String>();
        List<ApiResource> resources = epconfig.getRessources();
        String resourceUri = "";
        String uri = "";
        for ( ApiResource res : resources ) {
            resourceUri = res.getUri();
            for ( EndpointConfig e : res.getEndpoints() ) {
                uri = ServerFrontConig.BASE_URL + resourceUri + "v" + e.getVersion();
                uri = "/" + String.join( "/",
                        ServerFrontConig.BASE_URL,
                        "v" + e.getVersion(),
                        resourceUri,
                        e.getUriPattern() );
                uris.add( uri );
            }
        }

        return uris;
    }

    /**
     * 
     * @param endpoint
     * @param resource
     * @return
     */
    public String generateEndpointUri( EndpointConfig endpoint, ApiResource resource ) {
        String resourceUri = resource.getUri();
        String uri = "/" + String.join( "/",
                ServerFrontConig.BASE_URL,
                "v" + endpoint.getVersion(),
                resourceUri,
                endpoint.getUriPattern() );

        return uri;
    }

}
