package pl.chopy.gwizdbackenddeploy.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Achievement {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String image;
    private String description;
}
