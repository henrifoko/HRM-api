import java.net.URI;
import java.net.URISyntaxException;

public class Main_6 {

    public static void main( String[] args ) {
        try {
            URI uri = new URI(
                    "https://www.google.com/search?q=spring+boot+create+my+own+handle+mapping&rlz=1C1GCEU_frCM947CM947&oq=spring+boot+create+my+own+handle+mapping+&aqs=chrome..69i57j33i10i160.16661j0j7&sourceid=chrome&ie=UTF-8" );
            System.out.println( uri.toString() );
            System.out.println( uri.toASCIIString() );
            System.out.println( uri.getHost() );
            System.out.println( uri.getPort() );
            System.out.println( uri.getPath() );
            System.out.println( uri.getQuery() );
        } catch ( URISyntaxException e ) {
            e.printStackTrace();
        }

    }

}
