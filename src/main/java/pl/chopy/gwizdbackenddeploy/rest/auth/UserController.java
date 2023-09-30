package pl.chopy.gwizdbackenddeploy.rest.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.chopy.gwizdbackenddeploy.model.entity.Achievement;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "Get current user info (authentication required)")
    public ResponseEntity<UserInfoResponse> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/achievements")
    @Operation(summary = "Get achievements for user")
    public ResponseEntity<List<Achievement>> getAchievementsForUser() {
        return ResponseEntity.ok(userService.getAchievementsForUser());
    }
}
