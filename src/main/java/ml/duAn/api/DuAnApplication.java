package ml.duAn.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"ml.duAn.api"})
public class DuAnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuAnApplication.class, args);
	}

}
