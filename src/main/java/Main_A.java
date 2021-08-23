import com.frsummit.HRM.api.util.Util;

public class Main_A {

    public static void main( String[] args ) {
        // System.out.println( Byte[].class );
        // System.out.println( Byte[].class.toString() );
        // try {
        // System.out.println( Class.forName( Byte[].class.toString() ) );
        // } catch ( ClassNotFoundException e ) {
        // System.err.println( "Class not found!" );
        // // ...
        // e.printStackTrace();
        // }
        //
        // try {
        // testClassOp();
        // } catch ( ClassNotFoundException e ) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        testGlobalType();
    }

    public static void testClassOp() throws ClassNotFoundException {
        System.out.println( int.class.getCanonicalName() );
        System.out.println( int.class.getName() );
        System.out.println( int.class.getPackageName() );
        System.out.println( int.class.getSimpleName() );

        System.out.println();

        System.out.println( int[].class.getCanonicalName() );
        System.out.println( int[].class.getName() );
        System.out.println( int[].class.getPackageName() );
        System.out.println( int[].class.getSimpleName() );

        System.out.println( int[].class.isPrimitive() );

        Util.parseType( "void" );
        Util.parseType( "[B" );
    }

    public static void nothing( byte[] bytes ) {
        return;
    }

    public static void testGlobalType( final String... arguments ) {
        printNamesForClass(
                int.class,
                "int.class (primitive)" );
        printNamesForClass(
                String.class,
                "String.class (ordinary class)" );
        printNamesForClass(
                byte[].class,
                "byte[].class (array class)" );
        printNamesForClass(
                java.util.HashMap.SimpleEntry.class,
                "java.util.HashMap.SimpleEntry.class (nested class)" );
        printNamesForClass(
                new java.io.Serializable() {
                }.getClass(),
                "new java.io.Serializable(){}.getClass() (anonymous inner class)" );
    }

    private static void printNamesForClass( final Class<?> clazz, final String label ) {
        System.out.println( label + ":" );
        System.out.println( "    getName():          " + clazz.getName() );
        System.out.println( "    getCanonicalName(): " + clazz.getCanonicalName() );
        System.out.println( "    getSimpleName():    " + clazz.getSimpleName() );
        System.out.println( "    getTypeName():      " + clazz.getTypeName() ); // added
                                                                                // in
                                                                                // Java
                                                                                // 8
        System.out.println();
    }
}
