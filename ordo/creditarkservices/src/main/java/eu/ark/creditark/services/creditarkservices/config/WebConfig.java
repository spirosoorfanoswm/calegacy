package eu.ark.creditark.services.creditarkservices.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.*;

//@ConditionalOnProperty(value = "security.basic.enabled", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

   @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS");;
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/index");
        registry.addViewController("/index");
        //registry.addViewController("/securedPage");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("/");
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**",
                "/assets/img/**",
                "/assets/fonts/**",
                "/assets/css/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/assets/css/",
                        "classpath:/static/assets/fonts/",
                        "classpath:/static/assets/img/");
    }
}
