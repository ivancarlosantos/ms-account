package core.ms.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
		log.info("Application UP ["+ HttpStatus.OK+"]");
	}
}
