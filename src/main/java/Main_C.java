import java.lang.reflect.Method;

public class Main_C {

    public static void main( String[] args ) {
        Method method;
        try {
            method = Main_C.class.getDeclaredMethod( "main", String[].class );
            System.out.println( method.toGenericString() );
            System.out.println( method.toString() );
        } catch ( NoSuchMethodException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( SecurityException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
