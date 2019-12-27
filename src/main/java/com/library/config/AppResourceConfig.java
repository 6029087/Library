package com.library.config;

import com.library.repository.BookRepository;
import com.library.repository.BookRepositoryImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/v1")
public class AppResourceConfig extends ResourceConfig {

    private Logger logger = LoggerFactory.getLogger(AppResourceConfig.class);

    public AppResourceConfig() {
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(BookRepositoryImpl.class).to(BookRepository.class);
            }
        });
        property("jersey.config.server.provider.packages", "com.library.config");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        packages("com.library.controller");
    }


}
