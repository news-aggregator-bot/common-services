package vlad110kg.news.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@EnableScheduling
@EnableJpaRepositories
public class NAService {

    public static void main(String[] args) {
        SpringApplication.run(NAService.class, args);
    }
}
