import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frsummit.HRM.api.config.ServerFrontConig;
import com.frsummit.HRM.api.generic.model.ApiEpconfig;

public class Main_5 {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main( String[] args ) throws IOException {
        Path path = Paths.get( ServerFrontConig.ENDPOINT_CONFIG_FILE );

        String json = Files.readString( path );

        // System.out.println( json );

        ApiEpconfig epconfig = objectMapper.readValue( json, ApiEpconfig.class );

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
    }

}
