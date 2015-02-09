package plt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class PartyLootTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartyLootTrackerApplication.class, args);
    }

    @Bean
    public PartyLootTrackerSecurity partyLootTrackerSecurity() {
        return new PartyLootTrackerSecurity();
    }

    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class PartyLootTrackerSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/", "/login", "/webjars/**", "/css/*", "/js/**", "/images/**",  "/**/favicon.ico", "/robots.txt", "/sitemap.xml").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login").failureUrl("/login?error")
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .and()
                    .exceptionHandling().accessDeniedPage("/access?error");
        }
    }

    @Bean
    public PartyLootTrackerWebConfigurer partyLootTrackerWebConfigurer() {
        return new PartyLootTrackerWebConfigurer();
    }

    protected static class PartyLootTrackerWebConfigurer extends WebMvcConfigurerAdapter {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            //registry.addViewController("/").setViewName("index");
        }
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Configuration
    protected static class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("admin").password("admin").roles("ADMIN", "USER")
                    .and()
                    .withUser("user").password("user").roles("USER");
        }

//        @Override
//        public void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.jdbcAuthentication().dataSource(this.dataSource);
//        }
    }
}
