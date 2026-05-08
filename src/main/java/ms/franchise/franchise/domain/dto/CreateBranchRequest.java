package ms.franchise.franchise.domain.dto;

public class CreateBranchRequest {

    private String name;

    public CreateBranchRequest() {
    }

    public CreateBranchRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
