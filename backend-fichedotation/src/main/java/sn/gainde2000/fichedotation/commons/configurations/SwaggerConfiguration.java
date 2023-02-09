/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :20/12/2021
 */

package sn.gainde2000.fichedotation.commons.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SwaggerConfiguration {

    @Value("${app.url.swagger}")
    private String swaggerURL;

    @Bean
    public OpenAPI customOpenAPI() {
        String securitySchemeName = "Auth JWT";
        return new OpenAPI()
                .addServersItem(new Server().url(swaggerURL))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("Bearer")
                                                .bearerFormat("JWT"))
                )
                .info(new Info().title("Fiche de dotation application API").version("v1.0.0"));
    }
}
