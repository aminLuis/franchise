package ms.franchise.franchise.domain.service.impl;

import ms.franchise.franchise.domain.dto.CreateFranchiseRequest;
import ms.franchise.franchise.domain.model.Franchise;
import ms.franchise.franchise.domain.repository.FranchiseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FranchiseServiceImplTest {

    private FranchiseRepository repository;

    private FranchiseServiceImpl service;

    @BeforeEach
    void setUp() {

        repository = Mockito.mock(FranchiseRepository.class);

        service = new FranchiseServiceImpl(repository);
    }

    @Test
    void shouldCreateFranchise() {

        CreateFranchiseRequest request =
                new CreateFranchiseRequest("McDonalds");

        Franchise franchise =
                new Franchise(1L, "McDonalds");

        when(repository.save(any(Franchise.class)))
                .thenReturn(Mono.just(franchise));

        Mono<Franchise> result = service.create(request);

        StepVerifier.create(result)
                .expectNextMatches(saved ->
                        saved.getId().equals(1L)
                                && saved.getName().equals("McDonalds")
                )
                .verifyComplete();
    }

}
