package ms.franchise.franchise.infrastructure.handler;

import ms.franchise.franchise.domain.dto.CreateProductRequest;
import ms.franchise.franchise.domain.dto.UpdateStockRequest;
import ms.franchise.franchise.domain.service.IProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

    private final IProductService service;

    public ProductHandler(IProductService service) {
        this.service = service;
    }

    public Mono<ServerResponse> create(ServerRequest request) {

        Long branchId = request.queryParam("branchId")
                .map(Long::valueOf)
                .orElseThrow(() ->
                        new RuntimeException("branchId is required")
                );

        return request.bodyToMono(CreateProductRequest.class)
                .flatMap(body ->
                        service.create(branchId, body)
                )
                .flatMap(product ->

                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(product)
                );
    }

    public Mono<ServerResponse> delete(ServerRequest request) {

        Long productId =
                Long.valueOf(
                        request.pathVariable("productId")
                );

        return service.delete(productId)
                .then(
                        ServerResponse.ok().build()
                );
    }

    public Mono<ServerResponse> updateStock(ServerRequest request) {

        Long productId =
                Long.valueOf(
                        request.pathVariable("productId")
                );

        return request.bodyToMono(UpdateStockRequest.class)
                .flatMap(body ->
                        service.updateStock(productId, body)
                )
                .flatMap(product ->

                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(product)
                );
    }

    public Mono<ServerResponse>
    findTopStockProductsByFranchise(
            ServerRequest request
    ) {

        Long franchiseId =
                Long.valueOf(
                        request.pathVariable("franchiseId")
                );

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        service.findTopStockProductsByFranchise(
                                franchiseId
                        ),
                        Object.class
                );
    }

}
