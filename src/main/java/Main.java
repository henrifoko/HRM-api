import java.lang.reflect.Field;
import java.util.ArrayList;

import com.frsummit.HRM.api.model.RemoteCallee;
import com.frsummit.HRM.api.rmi.model.User;

public class Main {

	class A {
		public String method() {
			return method1() + " " + method2();
		}
		
		public String method1() {
			return "A 1";
		}

		public String method2() {
			return "A 2";
		}
	}
	
	class B extends A {
		
		public String method2() {
			return "B 2";
		}
	}

	public static void main(String[] args) {
//		Iterable<User> users = new ArrayList<User>();
//		System.out.println("Name : " + Iterable.class.getName());
//		System.out.println("Package name : " + Iterable.class.getPackageName());
//		System.out.println("Simple name : " + Iterable.class.getSimpleName());
//		System.out.println("Type name : " + Iterable.class.getTypeName());
//		System.out.println("Canonical name : " + Iterable.class.getCanonicalName());
//
//		System.out.println("-----------");
//
//		for (Field f : User.class.getDeclaredFields()) {
//			System.out.println(f.getName());
//		}
//		Main m = new Main();
//		Main.B b = m.new B();
//		System.out.println(b.method());
		
	}
	
	public static void generateController(RemoteCallee callee) {
		//
	}

}
