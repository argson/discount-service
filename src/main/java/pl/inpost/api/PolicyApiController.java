package pl.inpost.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.inpost.api.dto.model.PolicyDTO;

import java.util.List;

@RestController
@AllArgsConstructor
public class PolicyApiController implements PolicyApi {
    @Override
    public ResponseEntity<List<PolicyDTO>> getPolicies() throws Exception {
        return ResponseEntity.ok(List.of(PolicyDTO.values()));
    }
}
