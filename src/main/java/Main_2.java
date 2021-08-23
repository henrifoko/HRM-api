import java.util.Date;
import java.util.Map;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main_2 {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main( String[] args ) {
        testConversion();
        System.out.println( "---" );
        testConversion2();
        System.out.println( "---" );
        testConversion3();
    }

    public static void testConversion() {
        User user = new User();
        Map<String, Object> map = objectMapper.convertValue( user, Map.class );
        for ( String key : map.keySet() ) {
            if ( map.get( key ).equals( true ) ) {
                System.out.println( "key : " + key );
            }
        }
        System.out.println( map );
    }

    public static void testConversion2() {
        System.out.println( objectMapper.convertValue( "2012-04-01", Date.class ) );
    }

    public static void testConversion3() {
        final String json = "{\"name\":\"Laura\",\"male\":\"false\",\"tt\":\"11\"}";
        try {
            User user = objectMapper.readValue( json, User.class );
            System.out.println( user );
        } catch ( JsonMappingException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( JsonProcessingException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static class User {
        private String  name  = "Jack";
        private boolean male  = true;

        @Email
        private String  email = "jack@outlook.com";

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public boolean isMale() {
            return male;
        }

        public void setMale( boolean male ) {
            this.male = male;
        }

        public String toString() {
            return "name : " + name + "\n"
                    + "male : " + male;
        }
    }

}
