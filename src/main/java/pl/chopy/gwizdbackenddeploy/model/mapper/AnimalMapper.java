package pl.chopy.gwizdbackenddeploy.model.mapper;

import org.mapstruct.Mapper;
import pl.chopy.gwizdbackenddeploy.model.entity.Animal;
import pl.chopy.gwizdbackenddeploy.rest.animal.AnimalAddRequest;

@Mapper
public interface AnimalMapper {
    Animal map(AnimalAddRequest request);
}
