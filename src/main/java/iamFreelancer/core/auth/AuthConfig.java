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
        		.excludePathPatterns("/pro/login") // �α��� �������� ����
        		.excludePathPatterns("/pro/css/**", "/pro/js/**", "/pro/img/**"); // ���� ���ҽ� ������ ����. �������� ������ ���� �߻�
        		// �ٷ� �� ���� �߰����� ������
        		// because its mime type 'text/html' is not executable and strict mime type checking is enabled. ���� �߻�
    }
}
