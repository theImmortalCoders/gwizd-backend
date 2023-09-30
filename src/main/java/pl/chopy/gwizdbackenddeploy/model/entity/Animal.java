package pl.chopy.gwizdbackenddeploy.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Animal {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String name;
    private String description;
    private String photo;
}
