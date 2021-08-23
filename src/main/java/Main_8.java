import java.util.ArrayList;
import java.util.List;

public class Main_8 {

    public static void main( String[] args ) {
        test2();
    }

    public static void test1() {
        try {
            System.out.println( Class.forName( "java.lang.Integer" ) );
            Class<?> test = Class.forName(
                    "com.frsummit.HRM.api.generic.validator.EmailValidator" );
            System.out.println( test );
            System.out.println( Class.forName( "com.frsummit.HRM.api.generic.validator.ApiValidator" ) );
            // System.out.println( Class.forName( "int" ) ); // will raise an
            // Exception
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        List<String> names = new ArrayList<String>();
        names.addAll( List.of( "java.lang.Integer", "java.lang.Double", "java.lang.String" ) );
        Object[] classes = names
                .stream()
                .map( s -> {
                    try {
                        return (Class<?>) Class.forName( s );
                    } catch ( Exception e ) {
                        throw new RuntimeException();
                        // return null;
                    }
                } ).toArray();
        for ( Object c : classes ) {
            System.out.println( (Class<?>) c );
        }

        System.out.println( "---" );

        names.add( "int" );
        try {
            classes = names
                    .stream()
                    .map( s -> {
                        try {
                            return (Class<?>) Class.forName( s );
                        } catch ( Exception e ) {
                            throw new RuntimeException();
                            // return null;
                        }
                    } ).toArray();
        } catch ( Exception e ) {
            System.out.println( "Une erreur s'est produite" );
        }
    }
}
