package pl.chopy.gwizdbackenddeploy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.chopy.gwizdbackenddeploy.model.ReportType;
import pl.chopy.gwizdbackenddeploy.model.entity.Animal;
import pl.chopy.gwizdbackenddeploy.model.entity.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r " +
            "WHERE (:animal IS NULL OR r.animal = :animal) " +
            "AND (:reportType IS NULL OR r.reportType = :reportType) ")
    List<Report> getReportsByAnimalOrReportType(
            @Param("animal") Animal animal,
            @Param("reportType") ReportType reportType
    );

}


