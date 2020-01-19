package com.sporting.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@Controller
public class SportingGoodsApplication {
	private static ConfigurableApplicationContext applicationContext;
	
	public static void main(String[] args) {
		 applicationContext = SpringApplication.run(SportingGoodsApplication.class, args);		        
	}
	
	@GetMapping("/shutdown")
	public void shutDown() {
		int exitCode = SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                return 0;
            }
        });
	}
}
