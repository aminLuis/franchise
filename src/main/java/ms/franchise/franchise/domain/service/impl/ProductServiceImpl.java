package ms.franchise.franchise.domain.service.impl;

import ms.franchise.franchise.domain.dto.CreateProductRequest;
import ms.franchise.franchise.domain.dto.ProductStockResponse;
import ms.franchise.franchise.domain.dto.UpdateStockRequest;
import ms.franchise.franchise.domain.model.Product;
import ms.franchise.franchise.domain.repository.BranchRepository;
import ms.franchise.franchise.domain.repository.ProductQueryRepository;
import ms.franchise.franchise.domain.repository.ProductRepository;
import ms.franchise.franchise.domain.service.IProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    private final BranchRepository branchRepository;

    private final ProductQueryRepository productQueryRepository;

    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository, ProductQueryRepository productQueryRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.productQueryRepository = productQueryRepository;
    }

    @Override
    public Mono<Product> create(Long branchId, CreateProductRequest request) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(
                        Mono.error(
                                new RuntimeException("Branch not found")
                        )
                )
                .flatMap(branch -> {

                    Product product = new Product();

                    product.setName(request.getName());
                    product.setStock(request.getStock());
                    product.setBranchId(branch.getId());

                    return productRepository.save(product);
                });
    }

    @Override
    public Mono<Void> delete(Long productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(
                        Mono.error(
                                new RuntimeException("Product not found")
                        )
                )
                .flatMap(productRepository::delete
                );
    }

    @Override
    public Mono<Product> updateStock(Long productId, UpdateStockRequest request) {
        return productRepository.findById(productId)
                .switchIfEmpty(
                        Mono.error(
                                new RuntimeException("Product not found")
                        )
                )
                .flatMap(product -> {

                    product.setStock(request.getStock());

                    return productRepository.save(product);
                });
    }

    @Override
    public Flux<ProductStockResponse> findTopStockProductsByFranchise(Long franchiseId) {
        return productQueryRepository
                .findTopStockProductsByFranchise(
                        franchiseId
                );
    }

}
