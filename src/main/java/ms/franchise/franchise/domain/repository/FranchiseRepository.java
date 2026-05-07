package ms.franchise.franchise.domain.repository;

import ms.franchise.franchise.domain.model.Franchise;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FranchiseRepository extends ReactiveCrudRepository<Franchise, Long> {
}
