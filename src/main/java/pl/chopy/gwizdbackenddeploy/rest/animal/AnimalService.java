package pl.chopy.gwizdbackenddeploy.rest.animal;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.chopy.gwizdbackenddeploy.model.entity.Animal;
import pl.chopy.gwizdbackenddeploy.model.mapper.AnimalMapper;
import pl.chopy.gwizdbackenddeploy.model.repository.AnimalRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public void addAnimal(AnimalAddRequest request) {
        Option.of(request)
                .map(animalMapper::map)
                .map(animalRepository::save)
                .getOrElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public List<Animal> getAnimals() {
        return Option.of(animalRepository.findAll())
                .getOrElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
