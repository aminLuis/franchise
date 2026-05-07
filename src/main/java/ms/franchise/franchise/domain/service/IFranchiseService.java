package ms.franchise.franchise.domain.service;

import ms.franchise.franchise.domain.dto.CreateFranchiseRequest;
import ms.franchise.franchise.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface IFranchiseService {

    Mono<Franchise> create(CreateFranchiseRequest request);

}
