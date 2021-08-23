import java.util.Arrays;
import java.util.List;

import com.frsummit.HRM.api.security.model.ERole;
import com.frsummit.HRM.api.security.model.Role;

public class Main_9 {

    public static void main( String[] args ) {
        List<Role> roleList = List.of( new Role( ERole.ROLE_ADMIN ), new Role( ERole.ROLE_USER ) );
        String[] roles = Arrays.copyOf(
                roleList.stream().map( r -> r.getName().toString() ).toArray(),
                roleList.size(),
                String[].class );
        System.out.println( roles[0] );
        System.out.println( roles[1] );
    }
}
