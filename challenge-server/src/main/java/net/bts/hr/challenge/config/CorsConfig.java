package net.bts.hr.challenge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry)  {
        registry.addMapping("/**") // which paths are open to be consumed
                .allowedOrigins("*").allowedMethods( "GET", "POST", "PUT", "DELETE", "PATCH" ); // this request types also available

    }
}
