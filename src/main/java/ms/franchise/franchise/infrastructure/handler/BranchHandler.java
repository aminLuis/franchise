package ms.franchise.franchise.infrastructure.handler;

import ms.franchise.franchise.domain.dto.CreateBranchRequest;
import ms.franchise.franchise.domain.service.IBranchService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BranchHandler {

    private final IBranchService iBranchService;

    public BranchHandler(IBranchService iBranchService) {
        this.iBranchService = iBranchService;
    }

    public Mono<ServerResponse> create(ServerRequest request) {

        Long franchiseId = request.queryParam("franchiseId")
                .map(Long::valueOf)
                .orElseThrow(() ->
                        new RuntimeException("franchiseId is required")
                );

        return request.bodyToMono(CreateBranchRequest.class)
                .flatMap(body ->
                        iBranchService.create(franchiseId, body)
                )
                .flatMap(branch ->

                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(branch)
                );
    }

}
