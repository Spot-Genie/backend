package kr.mainstream.genieai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class GenieaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenieaiApplication.class, args);
	}

}
