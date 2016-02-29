package plt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableConfigurationProperties(BuildInfoSettings.class)
public class PartyLootTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PartyLootTrackerApplication.class, args);
    }
    
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

}
