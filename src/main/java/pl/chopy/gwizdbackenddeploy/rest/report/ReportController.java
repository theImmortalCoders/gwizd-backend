package pl.chopy.gwizdbackenddeploy.rest.report;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.chopy.gwizdbackenddeploy.model.ReportType;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/report")
@Tag(name = "Reports")
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Add new report (auth required)")
    public void addReport(@RequestBody ReportAddRequest request) {
        reportService.addReport(request);
    }

    @PostMapping("/filter")
    @Operation(summary = "Get reports with filters")
    public ResponseEntity<List<SingleReportResponse>> getReports(
            @RequestBody(required = false) LocationAddRequest request,
            @RequestParam(required = false) Long animalId,
            @RequestParam(required = false) ReportType reportType,
            @RequestParam(required = false) Double distanceRange
    ) {
        return ResponseEntity.ok(reportService.getReports(
                animalId, reportType, distanceRange, request)
        );
    }
}
