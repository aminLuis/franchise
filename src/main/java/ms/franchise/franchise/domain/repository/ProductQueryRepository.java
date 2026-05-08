package ms.franchise.franchise.domain.repository;

import ms.franchise.franchise.domain.dto.ProductStockResponse;
import reactor.core.publisher.Flux;

public interface ProductQueryRepository {

    Flux<ProductStockResponse> findTopStockProductsByFranchise(Long franchiseId);

}
