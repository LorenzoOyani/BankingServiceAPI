//package org.example.bankingportal.configuration;
//
//import io.swagger.models.Info;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.License;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfiguration {
//
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        var securitySchemeName = "Bearer";
//        return new OpenAPI()
//                .info(new Info().title("Banking Portal API")
//                        .description("This is auth service use for validate the user.")
//                        .version("v0.0.1")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("BankingPortal-API Wiki Documentation")
//                        .url("https://github.com/abhi9720/BankingPortal-API/wiki"))
//                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//                .components(
//                        new Components()
//                                .addSecuritySchemes(securitySchemeName,
//                                        new SecurityScheme()
//                                                .name(securitySchemeName)
//                                                .type(SecurityScheme.Type.HTTP)
//                                                .scheme("bearer")
//                                                .bearerFormat("JWT")));
//    }
//}
