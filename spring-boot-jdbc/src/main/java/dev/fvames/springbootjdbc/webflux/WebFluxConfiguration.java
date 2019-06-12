package dev.fvames.springbootjdbc.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @version 2019/6/12 13:58
 */
@Configuration
public class WebFluxConfiguration {

    @Bean
    public RouterFunction<ServerResponse> saveUser(UserHandler userHandler) {
        return route(POST("/web/flux/user/save"), userHandler::save);
    }
}
