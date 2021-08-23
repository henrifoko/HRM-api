package com.frsummit.HRM.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class HrmApiApplication {

    private static ConfigurableApplicationContext applicationContext;

    public static void main( String[] args ) {

        applicationContext = SpringApplication.run( HrmApiApplication.class, args );
    }

    public static void exit() {
        int exitCode = SpringApplication.exit( applicationContext, () -> 0 );
        System.exit( exitCode );
    }

}
