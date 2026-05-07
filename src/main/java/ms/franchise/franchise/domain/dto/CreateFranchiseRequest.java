package ms.franchise.franchise.domain.dto;

public class CreateFranchiseRequest {

    private String name;

    public CreateFranchiseRequest() {
    }

    public CreateFranchiseRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
