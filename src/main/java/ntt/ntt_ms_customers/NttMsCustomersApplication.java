package ntt.ntt_ms_customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NttMsCustomersApplication {

	public static void main(String[] args) {
		SpringApplication.run(NttMsCustomersApplication.class, args);
	}

}
