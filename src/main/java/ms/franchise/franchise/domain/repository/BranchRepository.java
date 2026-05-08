package ms.franchise.franchise.domain.repository;

import ms.franchise.franchise.domain.model.Branch;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BranchRepository extends ReactiveCrudRepository<Branch, Long> {
}
