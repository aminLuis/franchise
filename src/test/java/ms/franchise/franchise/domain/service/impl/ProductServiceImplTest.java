package ms.franchise.franchise.domain.service.impl;

import ms.franchise.franchise.domain.dto.CreateProductRequest;
import ms.franchise.franchise.domain.dto.UpdateStockRequest;
import ms.franchise.franchise.domain.model.Branch;
import ms.franchise.franchise.domain.model.Product;
import ms.franchise.franchise.domain.repository.BranchRepository;
import ms.franchise.franchise.domain.repository.ProductQueryRepository;
import ms.franchise.franchise.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    private ProductRepository productRepository;

    private BranchRepository branchRepository;

    private ProductServiceImpl service;

    private ProductQueryRepository productQueryRepository;

    @BeforeEach
    void setUp() {

        productRepository =
                Mockito.mock(ProductRepository.class);

        branchRepository =
                Mockito.mock(BranchRepository.class);

        service = new ProductServiceImpl(
                productRepository,
                branchRepository,
                productQueryRepository
        );
    }

    @Test
    void shouldCreateProduct() {

        Long branchId = 1L;

        CreateProductRequest request =
                new CreateProductRequest(
                        "Ibuprofeno",
                        130
                );

        Branch branch =
                new Branch(
                        1L,
                        "Sucursal numero 1",
                        1L
                );

        Product product =
                new Product(
                        1L,
                        "Ibuprofeno",
                        130,
                        branchId
                );

        when(branchRepository.findById(branchId))
                .thenReturn(Mono.just(branch));

        when(productRepository.save(any(Product.class)))
                .thenReturn(Mono.just(product));

        Mono<Product> result =
                service.create(branchId, request);

        StepVerifier.create(result)
                .expectNextMatches(saved ->

                        saved.getId().equals(1L)
                                && saved.getName()
                                .equals("Ibuprofeno")
                                && saved.getStock()
                                .equals(130)
                                && saved.getBranchId()
                                .equals(1L)
                )
                .verifyComplete();

    }

    @Test
    void shouldDeleteProduct() {

        Long productId = 1L;

        Product product =
                new Product(
                        1L,
                        "Ibuprofeno",
                        130,
                        1L
                );

        when(productRepository.findById(productId))
                .thenReturn(Mono.just(product));

        when(productRepository.delete(product))
                .thenReturn(Mono.empty());

        Mono<Void> result =
                service.delete(productId);

        StepVerifier.create(result)
                .verifyComplete();

    }

    @Test
    void shouldUpdateStock() {

        Long productId = 1L;

        Product product =
                new Product(
                        1L,
                        "Ibuprofeno",
                        130,
                        1L
                );

        UpdateStockRequest request =
                new UpdateStockRequest(50);

        Product updated =
                new Product(
                        1L,
                        "Ibuprofeno",
                        50,
                        1L
                );

        when(productRepository.findById(productId))
                .thenReturn(Mono.just(product));

        when(productRepository.save(any(Product.class)))
                .thenReturn(Mono.just(updated));

        Mono<Product> result =
                service.updateStock(productId,
                        request);

        StepVerifier.create(result)
                .expectNextMatches(saved ->

                        saved.getStock().equals(50)
                )
                .verifyComplete();

    }

}
