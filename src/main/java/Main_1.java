import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main_1 {
    @SuppressWarnings( "unused" )
    private int     a = 3;
    @SuppressWarnings( "unused" )
    private String  b = "Hello";
    @SuppressWarnings( "unused" )
    private boolean c = true;
    @SuppressWarnings( "unused" )
    private byte    d = 0x6A;
    @SuppressWarnings( "unused" )
    private long    e = 40874519480998L;
    @SuppressWarnings( "unused" )
    private char    f = '♫';
    @SuppressWarnings( "unused" )
    private double  g = 1.65;
    @SuppressWarnings( "unused" )
    private float   h = 0.00034f;

    public static void main( String[] args ) {
        Map<String, Object> map = parameters( new Main_1() );
        for ( Entry<String, Object> entry : map.entrySet() ) {
            System.out.println( entry.getKey() + ": " + entry.getValue() );
        }
    }

    public static Map<String, Object> parameters( Object obj ) {
        Map<String, Object> map = new HashMap<>();
        for ( Field field : obj.getClass().getDeclaredFields() ) {
            field.setAccessible( false );
            try {
                map.put( field.getName(), field.get( obj ) );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
