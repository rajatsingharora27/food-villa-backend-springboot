package com.foodvilla.backend;

import com.foodvilla.backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync

//@EnableSwagger2
public class BackendApplication {

	public static void main(String[] args) {
		Logger log= LoggerFactory.getLogger(BackendApplication.class);
		SpringApplication.run(BackendApplication.class, args);

		log.info("*************************************************************************");
		log.info("************* BackendApplication Micro service Started*******************");
		log.info("*************************************************************************");
	}

}
