package com.frsummit.HRM.api.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class Util {

    /**
     * 
     * @param className
     * @return
     */
    public static String[] properties( Class<?> c ) {
        Field[] fields = c.getDeclaredFields();
        return (String[]) List.of( fields ).stream().map( field -> field.getName() ).toArray();
    }

    /**
     * 
     * @param className
     * @return
     */
    public static Class<?> parseType( final String className ) throws IllegalArgumentException {
        switch ( className ) {
        case "boolean":
            return boolean.class;
        case "byte":
            return byte.class;
        case "short":
            return short.class;
        case "int":
            return int.class;
        case "long":
            return long.class;
        case "float":
            return float.class;
        case "double":
            return double.class;
        case "char":
            return char.class;
        case "void":
            return void.class;
        default:
            try {
                return Class.forName( className );
            } catch ( ClassNotFoundException ex ) {
                throw new IllegalArgumentException( "Class not found: " + className );
            }
        }
    }

    /**
     * 
     * @param className
     * @return
     */
    public static String class2string( Class<?> c ) {
        return c.getName();
    }

    public static void main( String[] args ) {
        System.out.println( "eee/d".endsWith( "/" ) );
        System.out.println( "/eee/d/".endsWith( "/" ) );
        System.out.println();
        System.out.println( withEndingSlash( "a/a" ) );
        System.out.println( withEndingSlash( "a/a/" ) );
        System.out.println();
        System.out.println( withoutEndingSlash( "a/a" ) );
        System.out.println( withoutEndingSlash( "a/a/" ) );
        System.out.println();
        System.out.println( parseType( class2string( int.class ) ) == int.class );
        System.out.println( parseType( class2string( String.class ) ) == String.class );
        System.out.println( parseType( class2string( HashMap.class ) ) == HashMap.class );
        System.out.println( parseType( class2string( Double.class ) ) == Double.class );
        System.out.println( parseType( class2string( List.class ) ) == List.class );
        System.out.println( parseType( class2string( Object.class ) ) == Object.class );
        System.out.println( parseType( class2string( Object[].class ) ) == Object[].class );
        System.out.println( parseType( class2string( byte[].class ) ) == byte[].class );
    }

    /**
     * 
     * @param uri
     * @return
     */
    public static String withEndingSlash( String uri ) {
        if ( uri.endsWith( "/" ) ) {
            return uri;
        } else {
            return uri + "/";
        }
    }

    /**
     * 
     * @param uri
     * @return
     */
    public static String withoutEndingSlash( String uri ) {
        if ( uri.endsWith( "/" ) ) {
            return uri.substring( 0, uri.length() - 1 );
        } else {
            return uri;
        }
    }
}
