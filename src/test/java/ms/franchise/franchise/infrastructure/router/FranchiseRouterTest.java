package ms.franchise.franchise.infrastructure.router;

import ms.franchise.franchise.domain.dto.CreateFranchiseRequest;
import ms.franchise.franchise.domain.model.Franchise;
import ms.franchise.franchise.domain.service.IFranchiseService;
import ms.franchise.franchise.infrastructure.handler.FranchiseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FranchiseRouterTest {

    private WebTestClient webTestClient;

    private IFranchiseService service;

    @BeforeEach
    void setUp() {

        service = Mockito.mock(IFranchiseService.class);

        FranchiseHandler handler =
                new FranchiseHandler(service);

        FranchiseRouter router =
                new FranchiseRouter();

        webTestClient = WebTestClient.bindToRouterFunction(
                router.routes(handler)
        ).build();

    }

    @Test
    void shouldCreateFranchise() {

        Franchise franchise =
                new Franchise(1L, "McDonalds");

        when(service.create(any(CreateFranchiseRequest.class)))
                .thenReturn(Mono.just(franchise));

        webTestClient.post()
                .uri("/franchises")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "name": "McDonalds"
                        }
                        """)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("McDonalds");
    }

}
