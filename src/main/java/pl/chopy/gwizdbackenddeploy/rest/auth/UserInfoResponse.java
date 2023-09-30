package pl.chopy.gwizdbackenddeploy.rest.auth;

import lombok.Data;
import pl.chopy.gwizdbackenddeploy.model.Rank;
import pl.chopy.gwizdbackenddeploy.model.Role;

@Data
public class UserInfoResponse {

    private Long id;
    private String username;
    private String email;
    private String photo;
    private Rank rank;
    private Role role;

}
