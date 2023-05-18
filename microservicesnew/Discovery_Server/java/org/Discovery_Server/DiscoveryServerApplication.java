package org.Discovery_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;



/**
 * Hello world!
 *
 */
@SpringBootApplication


public class DiscoveryServerApplication
{
    public static void main( String[] args )
    {
		SpringApplication.run(DiscoveryServerApplication.class, args);

    }
}