package ms.franchise.franchise.domain.dto;

public class CreateProductRequest {

    private String name;

    private Integer stock;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String name,
                                Integer stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
