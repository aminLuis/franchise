package ms.franchise.franchise.domain.repository.impl;

import ms.franchise.franchise.domain.dto.ProductStockResponse;
import ms.franchise.franchise.domain.repository.ProductQueryRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final DatabaseClient databaseClient;

    public ProductQueryRepositoryImpl(
            DatabaseClient databaseClient
    ) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Flux<ProductStockResponse> findTopStockProductsByFranchise(Long franchiseId) {

        String sql = """
                SELECT
                    b.id AS branch_id,
                    b.name AS branch_name,
                    p.id AS product_id,
                    p.name AS product_name,
                    p.stock
                FROM (
                    SELECT
                        p.*,
                        ROW_NUMBER() OVER (
                            PARTITION BY p.branch_id
                            ORDER BY p.stock DESC
                        ) AS rn
                    FROM product p
                ) p
                INNER JOIN branch b
                    ON b.id = p.branch_id
                WHERE b.franchise_id = :franchiseId
                AND p.rn = 1
                """;

        return databaseClient.sql(sql)
                .bind("franchiseId", franchiseId)
                .map((row, metadata) ->

                        new ProductStockResponse(
                                row.get("branch_id",
                                        Long.class),
                                row.get("branch_name",
                                        String.class),
                                row.get("product_id",
                                        Long.class),
                                row.get("product_name",
                                        String.class),
                                row.get("stock",
                                        Integer.class)
                        )
                )
                .all();
    }

}
