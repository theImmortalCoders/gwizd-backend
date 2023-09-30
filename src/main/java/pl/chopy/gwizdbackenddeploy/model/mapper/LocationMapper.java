package pl.chopy.gwizdbackenddeploy.model.mapper;

import org.mapstruct.Mapper;
import pl.chopy.gwizdbackenddeploy.model.entity.Location;
import pl.chopy.gwizdbackenddeploy.rest.report.LocationAddRequest;

@Mapper
public interface LocationMapper {
    Location map(LocationAddRequest request);
}
