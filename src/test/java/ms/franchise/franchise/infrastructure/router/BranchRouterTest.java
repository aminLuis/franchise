package ms.franchise.franchise.infrastructure.router;

import ms.franchise.franchise.domain.dto.CreateBranchRequest;
import ms.franchise.franchise.domain.model.Branch;
import ms.franchise.franchise.domain.service.IBranchService;
import ms.franchise.franchise.infrastructure.handler.BranchHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class BranchRouterTest {

    private WebTestClient webTestClient;

    private IBranchService service;

    @BeforeEach
    void setUp() {

        service = Mockito.mock(IBranchService.class);

        BranchHandler handler =
                new BranchHandler(service);

        BranchRouter router =
                new BranchRouter();

        webTestClient = WebTestClient.bindToRouterFunction(
                router.branchRoutes(handler)
        ).build();
    }

    @Test
    void shouldCreateBranch() {

        Branch branch =
                new Branch(
                        1L,
                        "Sucursal Norte",
                        1L
                );

        when(service.create(
                eq(1L),
                any(CreateBranchRequest.class)
        )).thenReturn(Mono.just(branch));

        webTestClient.post()
                .uri("/branches?franchiseId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "name": "Sucursal Norte"
                        }
                        """)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name")
                .isEqualTo("Sucursal Norte")
                .jsonPath("$.franchiseId")
                .isEqualTo(1);

    }

}
