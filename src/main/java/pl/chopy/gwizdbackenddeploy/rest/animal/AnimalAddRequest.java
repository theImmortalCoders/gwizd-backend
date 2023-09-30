package pl.chopy.gwizdbackenddeploy.rest.animal;

import lombok.Data;

@Data
public class AnimalAddRequest {
    private String name;
    private String description;
    private String photo;
}
