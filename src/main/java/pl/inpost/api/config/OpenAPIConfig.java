package pl.inpost.api.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI myOpenAPI() {

        var local = new Server();
        local.setUrl("http://localhost:8080/");
        local.setDescription("Local development of petstore");


        Contact contact = new Contact();
        contact.setEmail("arekgrab@email.com");
        contact.setName("Arkadiusz Grabka");

        License mitLicense = new License().name("Licence type").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Discount Management API")
                .version("1.0.0")
                .contact(contact)
                .description("REST API for calculating a given product's price based on the number of products ordered.")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(local));
    }
}
