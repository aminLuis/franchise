package ms.franchise.franchise.infrastructure.router;

import ms.franchise.franchise.infrastructure.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> productRoutes(
            ProductHandler handler
    ) {

        return route(
                POST("/products"),
                handler::create
        ).andRoute(
                DELETE("/products/{productId}"),
                handler::delete
        ).andRoute(
                PUT("/products/{productId}/stock"),
                handler::updateStock
        ).andRoute(
                GET("/franchises/{franchiseId}/top-stock-products"),
                handler::findTopStockProductsByFranchise
        );
    }

}
