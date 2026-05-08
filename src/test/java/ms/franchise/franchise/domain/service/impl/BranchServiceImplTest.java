package ms.franchise.franchise.domain.service.impl;

import ms.franchise.franchise.domain.dto.CreateBranchRequest;
import ms.franchise.franchise.domain.model.Branch;
import ms.franchise.franchise.domain.model.Franchise;
import ms.franchise.franchise.domain.repository.BranchRepository;
import ms.franchise.franchise.domain.repository.FranchiseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class BranchServiceImplTest {

    private BranchRepository branchRepository;

    private FranchiseRepository franchiseRepository;

    private BranchServiceImpl service;

    @BeforeEach
    void setUp() {

        branchRepository =
                Mockito.mock(BranchRepository.class);

        franchiseRepository =
                Mockito.mock(FranchiseRepository.class);

        service = new BranchServiceImpl(
                branchRepository,
                franchiseRepository
        );
    }

    @Test
    void shouldCreateBranch() {

        Long franchiseId = 1L;

        CreateBranchRequest request =
                new CreateBranchRequest("Sucursal Norte");

        Franchise franchise =
                new Franchise(1L, "McDonalds");

        Branch branch =
                new Branch(1L,
                        "Sucursal Norte",
                        franchiseId);

        when(franchiseRepository.findById(franchiseId))
                .thenReturn(Mono.just(franchise));

        when(branchRepository.save(any(Branch.class)))
                .thenReturn(Mono.just(branch));

        Mono<Branch> result =
                service.create(franchiseId, request);

        StepVerifier.create(result)
                .expectNextMatches(saved ->

                        saved.getId().equals(1L)
                                && saved.getName()
                                .equals("Sucursal Norte")
                                && saved.getFranchiseId()
                                .equals(1L)
                )
                .verifyComplete();

    }

}
