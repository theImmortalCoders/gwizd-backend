package pl.chopy.gwizdbackenddeploy.rest.animal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chopy.gwizdbackenddeploy.model.entity.Animal;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/animal")
@Tag(name = "Animals")
public class AnimalController {
    private final AnimalService animalService;
    @PostMapping
    @Operation(summary = "Add animal")
    public void addAnimal(@RequestBody AnimalAddRequest request){
        animalService.addAnimal(request);
    }

    @GetMapping
    @Operation(summary = "Get all animals")
    public ResponseEntity<List<Animal>> getAnimals(){
        return ResponseEntity.ok(animalService.getAnimals());
    }

}
