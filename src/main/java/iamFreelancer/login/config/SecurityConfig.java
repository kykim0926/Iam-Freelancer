package iamFreelancer.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import iamFreelancer.login.service.LoginService;

/**
 * @description : springSecurity ����
 * @author Koreasoft kykim
 * @version : 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private LoginService loginService;
	
	/* DaoAuthenticationProvider�� ���������� UserDetailsService�� �̿��� ����� ������ �д´�.*/
	@Bean
    public DaoAuthenticationProvider authenticationProvider(LoginService loginService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(loginService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		  auth.authenticationProvider(authenticationProvider(loginService));
	}

	@Override
	public void configure(WebSecurity web) { // scr/main/resources/static ���� ������ ���� �����ϰ� �ϱ�
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				// ������ ���� ����
	            .antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/pro/main").hasRole("MEMBER")
				.antMatchers("/**").permitAll()
			.and() // �α��� ����
				.formLogin()
				.loginPage("/pro/login")
				.defaultSuccessUrl("/main") // �α����� �������� �� �̵��Ǵ� �������̸�, ���������� ��Ʈ�ѷ����� URL ����
				.permitAll()
			.and() // �α׾ƿ� ����
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/pro/logout"))
				.logoutSuccessUrl("/main")
				.invalidateHttpSession(true)
			.and()
				// 403 ����ó�� �ڵ鸵
            	.exceptionHandling().accessDeniedPage("/access-denied");
	}
}
