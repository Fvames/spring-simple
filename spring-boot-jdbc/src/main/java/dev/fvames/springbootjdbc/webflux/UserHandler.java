package dev.fvames.springbootjdbc.webflux;

import dev.fvames.springbootjdbc.domain.UserInfo;
import dev.fvames.springbootjdbc.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @version 2019/6/12 13:59
 */
@Component
public class UserHandler {

    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {

        Mono<UserInfo> userInfoMono = serverRequest.bodyToMono(UserInfo.class);
        Mono<Boolean> booleanMono = userInfoMono.map(userRepository::save);
        return ServerResponse.ok().body(booleanMono, Boolean.class);
    }
}
