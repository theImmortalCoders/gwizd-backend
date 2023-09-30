package pl.chopy.gwizdbackenddeploy.rest.achievement;

import lombok.Data;

@Data
public class AchievementAddRequest {
    private String name;
    private String image;
    private String description;
}
