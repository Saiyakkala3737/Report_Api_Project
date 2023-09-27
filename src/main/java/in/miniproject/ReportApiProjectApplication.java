package in.miniproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("in.miniproject")
public class ReportApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportApiProjectApplication.class, args);
	}

}
