package ms.franchise.franchise.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("product")
public class Product {

    @Id
    private Long id;

    private String name;

    private Integer stock;

    private Long branchId;

    public Product() {
    }

    public Product(Long id, String name, Integer stock, Long branchId) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.branchId = branchId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

}
