package sn.gainde2000.fichedotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableJpaAuditing
@EnableJpaRepositories(enableDefaultTransactions = false)
public class FicheDotationBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FicheDotationBackApplication.class, args);
    }
}
