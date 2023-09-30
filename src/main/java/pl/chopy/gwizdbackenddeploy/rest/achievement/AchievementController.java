package pl.chopy.gwizdbackenddeploy.rest.achievement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chopy.gwizdbackenddeploy.model.entity.Achievement;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/achievement")
@Tag(name = "Achievement")
public class AchievementController {
    private final AchievementService achievementService;

    @PostMapping
    @Operation(summary = "Add achievement")
    public void addAchievement(@RequestBody AchievementAddRequest achievementAddRequest) {
        achievementService.addAchievement(achievementAddRequest);
    }

    @GetMapping
    @Operation(summary = "Add achievement")
    public ResponseEntity<List<Achievement>> addAchievement() {
        return ResponseEntity.ok(achievementService.getAllAchievements());
    }
}
