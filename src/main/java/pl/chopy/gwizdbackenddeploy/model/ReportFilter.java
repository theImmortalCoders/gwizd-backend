package pl.chopy.gwizdbackenddeploy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.chopy.gwizdbackenddeploy.rest.report.LocationAddRequest;

@Data
@AllArgsConstructor
public class ReportFilter {
    private Long animalId;
    private ReportType reportType;
    private LocationAddRequest location;
    private Double range;
}
