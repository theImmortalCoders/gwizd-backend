package pl.chopy.gwizdbackenddeploy.rest.report;

import lombok.Data;
import pl.chopy.gwizdbackenddeploy.model.ReportType;
import pl.chopy.gwizdbackenddeploy.model.entity.Animal;
import pl.chopy.gwizdbackenddeploy.model.entity.Location;
import pl.chopy.gwizdbackenddeploy.rest.auth.UserInfoResponse;

import java.time.LocalDateTime;

@Data
public class SingleReportResponse {
    private ReportType reportType;
    private Location location;
    private Animal animal;
    private UserInfoResponse author;
    private Integer quantity;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private boolean isActive;
}
