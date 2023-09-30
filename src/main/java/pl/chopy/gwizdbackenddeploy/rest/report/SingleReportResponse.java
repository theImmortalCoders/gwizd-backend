package pl.chopy.gwizdbackenddeploy.rest.report;

import lombok.Data;
import pl.chopy.gwizdbackenddeploy.model.ReportType;
import pl.chopy.gwizdbackenddeploy.model.entity.Animal;
import pl.chopy.gwizdbackenddeploy.model.entity.Location;

import java.time.LocalDateTime;

@Data
public class SingleReportResponse {
    private ReportType reportType;
    private Location location;
    private Animal animal;
    private Integer quantity;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private boolean isActive;
}
