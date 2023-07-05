package az.ingress.errorhandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsErrorHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsErrorHandlingApplication.class, args);
    }

}
