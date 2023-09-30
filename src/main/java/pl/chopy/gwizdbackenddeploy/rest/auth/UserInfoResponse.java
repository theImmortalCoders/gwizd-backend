package pl.chopy.gwizdbackenddeploy.rest.auth;

import lombok.Data;
import pl.chopy.gwizdbackenddeploy.model.Role;
import pl.chopy.gwizdbackenddeploy.model.entity.Achievement;

import java.util.List;

@Data
public class UserInfoResponse {

    private Long id;
    private String username;
    private String email;
    private String photo;
    private List<Achievement> achievements;
    private Role role;

}
