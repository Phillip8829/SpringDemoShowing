package My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Security;
import My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserService userService;
    @Bean
    PasswordEncoder bcryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bcryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/addrole").permitAll()
                .antMatchers("/adduser").permitAll()
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic();
    }



    //JDBC USER MANAGER
//    @Bean
//    JdbcUserDetailsManager users(DataSource dataSource) {
////        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
////        return jdbcUserDetailsManager;
//        return new JdbcUserDetailsManager(dataSource);
//    }

    //Old Way



//    @Bean
//    InMemoryUserDetailsManager users() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return new InMemoryUserDetailsManager(
//                User.withUsername("admin")
//                        .password(bCryptPasswordEncoder.encode("password"))
//                        .roles("ADMIN").build(),
//
//                User.withUsername("user")
//                        .password(bCryptPasswordEncoder.encode("password"))
//                        .roles("USER")
//                        .build()
//
//        );
//    }






}
