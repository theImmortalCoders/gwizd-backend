package pl.chopy.gwizdbackenddeploy.rest.report;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/report")
@Tag(name = "Reports")
public class ReportController {
    private final ReportService reportService;
    @PostMapping
    @Operation(summary = "Add new report")
    public void addReport(@RequestBody ReportAddRequest request){
        reportService.addReport(request);
    }
}
