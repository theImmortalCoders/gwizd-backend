package pl.chopy.gwizdbackenddeploy.model.entity;

import lombok.Data;
import pl.chopy.gwizdbackenddeploy.model.ReportType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Entity
public class Report {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private ReportType reportType;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Animal animal;
    private Integer quantity;
    @NotEmpty
    private String title;
    private String description;
    private LocalDateTime createdDate = LocalDateTime.now();
    private boolean isActive = true;
    private boolean isWarning = true;
}
