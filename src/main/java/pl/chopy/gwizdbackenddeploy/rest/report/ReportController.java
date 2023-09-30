package pl.chopy.gwizdbackenddeploy.rest.report;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chopy.gwizdbackenddeploy.model.ReportFilter;
import pl.chopy.gwizdbackenddeploy.model.ReportType;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/report")
@Tag(name = "Reports")
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    @Operation(summary = "Add new report")
    public void addReport(@RequestBody ReportAddRequest request) {
        reportService.addReport(request);
    }

    @GetMapping
    @Operation(summary = "Get reports with filters")
    public ResponseEntity<List<SingleReportResponse>> getReports(
            @RequestParam(required = false) Long animalId,
            @RequestParam(required = false) ReportType reportType,
            @RequestParam(required = false) Double distanceRange,
            @RequestBody(required = false) LocationAddRequest request
    ) {
        return ResponseEntity.ok(reportService.getReports(
                new ReportFilter(animalId, reportType, request, distanceRange)
        ));
    }
}
