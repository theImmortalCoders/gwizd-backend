package pl.chopy.gwizdbackenddeploy.model.mapper;

import org.mapstruct.Mapper;
import pl.chopy.gwizdbackenddeploy.model.entity.User;
import pl.chopy.gwizdbackenddeploy.rest.auth.UserInfoResponse;

@Mapper
public interface UserMapper {
    UserInfoResponse map(User request);
}
