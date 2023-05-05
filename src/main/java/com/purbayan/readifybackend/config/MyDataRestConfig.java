package com.purbayan.readifybackend.config;

import com.purbayan.readifybackend.entity.Book;
import com.purbayan.readifybackend.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Configuration class for Spring Data REST. Defines the allowed HTTP
 * methods and the CORS policy for the API.
 */
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    // The allowed origins for CORS requests
    private final String allowedOrigins = "*";


    /**
     * Configures the Spring Data REST repository.
     * Exposes the IDs of the Book entity and disables unsupported HTTP methods.
     * Also sets up the CORS policy for the API.
     *
     * @param config the RepositoryRestConfiguration to configure
     * @param cors   the CorsRegistry to configure for CORS requests
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors) {

        // Disable PATCH, PUT, DELETE, and POST HTTP methods
        HttpMethod[] unsupportedActions = {
                HttpMethod.PATCH,
                HttpMethod.PUT,
                HttpMethod.DELETE,
                HttpMethod.POST
        };
        disableHttpMethods(Book.class, config, unsupportedActions);
        disableHttpMethods(Review.class, config, unsupportedActions);

        // Expose the IDs of the Book entity
        config.exposeIdsFor(Book.class);
        // Expose the IDs of the Review entity
        config.exposeIdsFor(Review.class);

        // Set up CORS policy for the API
        cors.addMapping(
                config.getBasePath() + "/**")
                .allowedOriginPatterns(allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * Disables the specified HTTP methods for the given domain type in the
     * Spring Data REST repository.
     *
     * @param domainType          the domain type to disable HTTP methods for
     * @param config              the RepositoryRestConfiguration to configure
     * @param unsupportedActions  the unsupported HTTP methods to disable
     */
    private void disableHttpMethods(Class domainType,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] unsupportedActions) {

        config.getExposureConfiguration()
                .forDomainType(domainType)
                .withAssociationExposure(((metdata, httpMethods) ->
                        httpMethods.disable(unsupportedActions)))
                .withCollectionExposure((metdata, httpMethods) ->
                        httpMethods.disable(unsupportedActions));
    }

}
