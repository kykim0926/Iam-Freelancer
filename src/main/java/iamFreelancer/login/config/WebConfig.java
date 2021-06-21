package iamFreelancer.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(value= {"iamFreelancer.login.mapper"})
public class WebConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
