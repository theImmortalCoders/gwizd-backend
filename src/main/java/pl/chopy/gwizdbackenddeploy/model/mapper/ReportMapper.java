package pl.chopy.gwizdbackenddeploy.model.mapper;

import org.mapstruct.Mapper;
import pl.chopy.gwizdbackenddeploy.model.entity.Report;
import pl.chopy.gwizdbackenddeploy.rest.report.ReportAddRequest;

@Mapper
public interface ReportMapper {
    Report map(ReportAddRequest request);
}
