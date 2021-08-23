import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main_3 {
    private String name = "Henri";

    public static void main( String[] args ) {
        Main_3 main_3 = new Main_3();
        try {
            Field field = Main_3.class.getDeclaredField( "name" );
            System.out.println( field.getModifiers() );
            System.out.println( Modifier.toString( field.getModifiers() ) );
            System.out.println( field.getName() );
            System.out.println( field.getType() );
            try {
                System.out.println( field.get( main_3 ) );
            } catch ( IllegalArgumentException e ) {
                e.printStackTrace();
            } catch ( IllegalAccessException e ) {
                e.printStackTrace();
            }
        } catch ( NoSuchFieldException e ) {
            e.printStackTrace();
        } catch ( SecurityException e ) {
            e.printStackTrace();
        }
    }
}
