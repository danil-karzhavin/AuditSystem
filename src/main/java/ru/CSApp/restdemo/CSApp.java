package ru.CSApp.restdemo;

// Эти import-заявления импортируют необходимые классы и интерфейсы из библиотек `Spring Boot`, `Spring Actuator` и `Swagger`.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataSize;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.MultipartConfigElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

// Аннотация объединяет несколько других аннотаций, таких как `@Configuration`, `@EnableAutoConfiguration`, и
//// `@ComponentScan`, и обозначает основной класс Spring Boot приложения
@SpringBootApplication
public class CSApp {

	public static void main(String[] args) {
		SpringApplication.run(CSApp.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/contracts/*"))
				.apis(RequestHandlerSelectors.basePackage("ru.thinkconstructive.restdemo"))
				.build()
				.apiInfo(apiCustomData());
	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.parse("128KB"));
		factory.setMaxRequestSize(DataSize.parse("128KB"));
		return factory.createMultipartConfig();
	}

	private ApiInfo apiCustomData(){
		return new ApiInfo(
				"Cloud Vendor API Application",
				"Cloud Vendor Documentation",
				"1.0",
				"Cloud Vendor Service Terms",
				new Contact("Esha Puri", "http://thinkconstructive.com",
						"contact@thinkconstructive.com"),
				"Think Constructive License",
				"http://thinkconstructive.com",
				Collections.emptyList()
		);
	}

	@Bean
	public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
			WebEndpointsSupplier webEndpointsSupplier,
			ServletEndpointsSupplier servletEndpointsSupplier,
			ControllerEndpointsSupplier controllerEndpointsSupplier,
			EndpointMediaTypes endpointMediaTypes,
			CorsEndpointProperties corsProperties,
			WebEndpointProperties webEndpointProperties,
			Environment environment) {
		List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
		Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
		allEndpoints.addAll(webEndpoints);
		allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
		allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
		String basePath = webEndpointProperties.getBasePath();
		EndpointMapping endpointMapping = new EndpointMapping(basePath);
		boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(
				webEndpointProperties, environment, basePath);
		return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints,
				endpointMediaTypes, corsProperties.toCorsConfiguration(),
				new EndpointLinksResolver(allEndpoints, basePath),
				shouldRegisterLinksMapping, null);
	}

	private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties,
											   Environment environment, String basePath) {
		return webEndpointProperties.getDiscovery().isEnabled() &&
				(StringUtils.hasText(basePath) ||
						ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
	}
}

