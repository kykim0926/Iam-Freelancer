package iamFreelancer.core.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

	@Autowired
	SessionInterceptor sessionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/pro/login") // 로그인 페이지는 제외
				.excludePathPatterns("/pro/login/findLoginId") // 로그인 ID찾기는 제외
				.excludePathPatterns("/pro/login/findLoginPw") // 로그인 PW찬기는 제외
				.excludePathPatterns("/pro/css/**", "/pro/js/**", "/pro/img/**"); // 정적 리소스 파일은 제외. 제외하지 않으면 에러 발생
		// 바로 윗 줄을 추가하지 안으면
		// because its mime type 'text/html' is not executable and strict mime type checking is enabled. 에러 발생
	}
}
