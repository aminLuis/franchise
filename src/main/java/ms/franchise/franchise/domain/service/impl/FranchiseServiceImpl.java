package ms.franchise.franchise.domain.service.impl;

import ms.franchise.franchise.domain.dto.CreateFranchiseRequest;
import ms.franchise.franchise.domain.model.Franchise;
import ms.franchise.franchise.domain.repository.FranchiseRepository;
import ms.franchise.franchise.domain.service.IFranchiseService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FranchiseServiceImpl implements IFranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Mono<Franchise> create(CreateFranchiseRequest request) {
        Franchise franchise = new Franchise();
        franchise.setName(request.getName());
        return this.franchiseRepository.save(franchise);
    }
}
