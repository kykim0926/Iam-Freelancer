package iamFreelancer.SMS.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @description : springSecurity 설정
 * @author Koreasoft kykim
 * @version : 1.0
 */
/**
 * @EnableRedisHttpSession 어노테이션은 properties의 spring.session.store-type의 값에 redis를 설정하는 것과 동일하게 작동.
 * 동작 과정은 Filter를 implement하는 SpringSessionRepositoryFilter라는 스프링 빈을 생성. 
 * 해당 필터는 HttpSession의 구현체를 바꾸어서 Redis에서 Spring Session을 지원.
 */
@EnableRedisHttpSession
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    /*
     * 자바에서 레디스를 사용하기 위해서 레디스 클라이언트가 필요.
     * Spring Data Redis에서는 Jedis와 Lecttuce를 지원.
     * netty 위에서 구축되어 비동기로 요청을 처리하므로 성능에 장점이 있는 Lecttuce를 채택.
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setPassword(redisPassword);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceConnectionFactory;
    }

    /*
     * 레디스의 데이터 저장방식은 byte[]이기 때문에 값을 저장하고 가져오기 위해서는 직렬화가 필요.
     * RedisTemplate 클래스는 default Serializer가 JdkSerializationRedisSerializer이기 때문에
     * 문자열 저장의 특화된 RedisTemplate의 서브 클래스 StringRedisTemplate를 사용.
     * StringRedisTemplate의 default Serializer는 StringSerializer.
     */

    @Bean(name = "redisTemplate")
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }
}
