package emerge.api.emergespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class EmergespringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmergespringApplication.class, args);
		// configureGlobalCORS();
	}
	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// return new WebMvcConfigurer() {
	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// registry.addMapping("/**")
	// .allowedOrigins("*")
	// .allowedMethods("GET", "POST", "PUT", "DELETE")
	// .allowedHeaders("*");
	// }
	// };
	// }

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
	
	// public static void configureGlobalCORS() {
    //     CorsRegistry registry = new CorsRegistry();
    //     registry.addMapping("/**")
    //             .allowedOrigins("*")
    //             // Opcional: Configura otros parámetros de CORS según sea necesario
    //             .allowedMethods("GET", "POST", "PUT", "DELETE")
    //             .allowedHeaders("Content-Type", "Authorization")
    //             .exposedHeaders("Authorization")
    //             .allowCredentials(true)
    //             .maxAge(3600);
    // }


}
