package ms.franchise.franchise.domain.dto;

public class ProductStockResponse {

    private Long branchId;

    private String branchName;

    private Long productId;

    private String productName;

    private Integer stock;

    public ProductStockResponse() {
    }

    public ProductStockResponse(
            Long branchId,
            String branchName,
            Long productId,
            String productName,
            Integer stock
    ) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
    }

    public Long getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
