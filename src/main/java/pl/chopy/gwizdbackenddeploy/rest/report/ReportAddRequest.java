package pl.chopy.gwizdbackenddeploy.rest.report;

import lombok.Data;
import pl.chopy.gwizdbackenddeploy.model.ReportType;

@Data
public class ReportAddRequest {
    private ReportType reportType;
    private LocationAddRequest location;
    private Long animalId;
    private Integer quantity;
    private String title;
    private String description;
}
