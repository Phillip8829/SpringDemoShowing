package My_Spring_Security_Attempt_One.My_Spring_Security_Attempt_One;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
@SpringBootApplication
public class MySpringSecurityAttemptOneApplication {



	public static void main(String[] args) {
		SpringApplication.run(MySpringSecurityAttemptOneApplication.class, args);
	}


}
