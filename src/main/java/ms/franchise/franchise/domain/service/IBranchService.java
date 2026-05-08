package ms.franchise.franchise.domain.service;

import ms.franchise.franchise.domain.dto.CreateBranchRequest;
import ms.franchise.franchise.domain.model.Branch;
import reactor.core.publisher.Mono;

public interface IBranchService {

    Mono<Branch> create(Long franchiseId, CreateBranchRequest request);

}
