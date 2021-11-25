package mrDetailer.mrDetailer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class MrDetailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrDetailerApplication.class, args);
	}

}
