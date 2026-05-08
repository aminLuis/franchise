package ms.franchise.franchise.infrastructure.router;

import ms.franchise.franchise.domain.dto.CreateProductRequest;
import ms.franchise.franchise.domain.dto.UpdateStockRequest;
import ms.franchise.franchise.domain.model.Product;
import ms.franchise.franchise.domain.service.IProductService;
import ms.franchise.franchise.infrastructure.handler.ProductHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ProductRouterTest {

    private WebTestClient webTestClient;

    private IProductService service;

    @BeforeEach
    void setUp() {

        service = Mockito.mock(IProductService.class);

        ProductHandler handler =
                new ProductHandler(service);

        ProductRouter router =
                new ProductRouter();

        webTestClient = WebTestClient.bindToRouterFunction(
                router.productRoutes(handler)
        ).build();
    }

    @Test
    void shouldCreateProduct() {

        Product product =
                new Product(
                        1L,
                        "Ibuprofeno",
                        130,
                        1L
                );

        when(service.create(
                eq(1L),
                any(CreateProductRequest.class)
        )).thenReturn(Mono.just(product));

        webTestClient.post()
                .uri("/products?branchId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "name": "Ibuprofeno",
                            "stock": 130
                        }
                        """)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name")
                .isEqualTo("Ibuprofeno")
                .jsonPath("$.stock")
                .isEqualTo(130)
                .jsonPath("$.branchId")
                .isEqualTo(1);

    }

    @Test
    void shouldDeleteProduct() {

        when(service.delete(1L))
                .thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/products/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldUpdateStock() {

        Product updated =
                new Product(
                        1L,
                        "Ibuprofeno",
                        50,
                        1L
                );

        when(service.updateStock(
                eq(1L),
                any(UpdateStockRequest.class)
        )).thenReturn(Mono.just(updated));

        webTestClient.put()
                .uri("/products/1/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "stock": 50
                        }
                        """)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.stock")
                .isEqualTo(50);

    }

}
