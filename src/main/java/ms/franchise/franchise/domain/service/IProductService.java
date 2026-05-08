package ms.franchise.franchise.domain.service;

import ms.franchise.franchise.domain.dto.CreateProductRequest;
import ms.franchise.franchise.domain.dto.ProductStockResponse;
import ms.franchise.franchise.domain.dto.UpdateStockRequest;
import ms.franchise.franchise.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Mono<Product> create(Long branchId, CreateProductRequest request);

    Mono<Void> delete(Long productId);

    Mono<Product> updateStock(Long productId, UpdateStockRequest request);

    Flux<ProductStockResponse> findTopStockProductsByFranchise(Long franchiseId);

}
