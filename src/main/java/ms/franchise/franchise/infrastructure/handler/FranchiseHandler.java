package ms.franchise.franchise.infrastructure.handler;

import ms.franchise.franchise.domain.dto.CreateFranchiseRequest;
import ms.franchise.franchise.domain.service.IFranchiseService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FranchiseHandler {

    private final IFranchiseService iFranchiseService;

    public FranchiseHandler(IFranchiseService iFranchiseService) {
        this.iFranchiseService = iFranchiseService;
    }

    public Mono<ServerResponse> create(ServerRequest request) {

        return request.bodyToMono(CreateFranchiseRequest.class)
                .flatMap(iFranchiseService::create)
                .flatMap(franchise ->

                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(franchise)
                );
    }
}
