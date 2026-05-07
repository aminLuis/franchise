package ms.franchise.franchise.infrastructure.router;

import ms.franchise.franchise.infrastructure.handler.FranchiseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FranchiseRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(
            FranchiseHandler handler
    ) {

        return route(
                POST("/franchises"),
                handler::create
        );
    }

}
