package ms.franchise.franchise.domain.repository;

import ms.franchise.franchise.domain.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    Flux<Product> findByBranchId(Long branchId);

}
