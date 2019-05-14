package br.com.blog.config;

//import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	//@Autowired
    //private Environment env;

	@Bean
	public Docket getApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				//.groupName("blog-api-1.0")
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.blog.web.controller"))
				.paths(PathSelectors.any())
				//.paths(regex("/blog/v1*"))
				.build().apiInfo(apiInfo())
				.directModelSubstitute(XMLGregorianCalendar.class, String.class)
				.directModelSubstitute(XMLGregorianCalendar.class, Date.class)
				.useDefaultResponseMessages(false)
				.tags(new Tag("Blog API", "Blog API operations."));
	}

    private ApiInfo apiInfo() {
    	Contact contact = null;
    	return new ApiInfo("Blog API", "Blog API.", "1.0", "", contact, "", "");
    }
}