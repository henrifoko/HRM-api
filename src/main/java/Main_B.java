import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frsummit.HRM.api.generic.service.EndpointService;

public class Main_B {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main( String[] args ) {

        try {
            EndpointService epservice = new EndpointService();
        } catch ( IOException e ) {
            System.err.println( "Erreur de chargement du fichier de config" );
            e.printStackTrace();
        }
    }
}
