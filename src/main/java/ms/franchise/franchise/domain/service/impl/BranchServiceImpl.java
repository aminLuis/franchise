package ms.franchise.franchise.domain.service.impl;

import ms.franchise.franchise.domain.dto.CreateBranchRequest;
import ms.franchise.franchise.domain.model.Branch;
import ms.franchise.franchise.domain.repository.BranchRepository;
import ms.franchise.franchise.domain.repository.FranchiseRepository;
import ms.franchise.franchise.domain.service.IBranchService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BranchServiceImpl implements IBranchService {

    private final BranchRepository branchRepository;

    private final FranchiseRepository franchiseRepository;

    public BranchServiceImpl(BranchRepository branchRepository, FranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Mono<Branch> create(Long franchiseId, CreateBranchRequest request) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(
                        Mono.error(
                                new RuntimeException("Franchise not found")
                        )
                )
                .flatMap(franchise -> {

                    Branch branch = new Branch();
                    branch.setName(request.getName());
                    branch.setFranchiseId(franchise.getId());

                    return branchRepository.save(branch);
                });
    }
}
