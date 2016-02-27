package plt.jpa;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import plt.PartyLootTrackerApplication;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackageClasses = PartyLootTrackerApplication.class)
@EnableJpaRepositories(basePackageClasses = PartyLootTrackerApplication.class)
public class JpaConfiguration {

}
