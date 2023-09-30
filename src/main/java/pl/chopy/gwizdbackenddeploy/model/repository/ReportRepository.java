package pl.chopy.gwizdbackenddeploy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.chopy.gwizdbackenddeploy.model.ReportType;
import pl.chopy.gwizdbackenddeploy.model.entity.Animal;
import pl.chopy.gwizdbackenddeploy.model.entity.Report;
import pl.chopy.gwizdbackenddeploy.model.entity.User;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r " +
            "WHERE (:animal IS NULL OR r.animal = :animal) " +
            "AND (:reportType IS NULL OR r.reportType = :reportType) " +
            "AND (:isActive IS NULL OR r.isActive = :isActive) " +
            "AND (:author IS NULL OR r.author = :author) ")
    List<Report> getReportsByAnimalOrReportTypeOrAuthorAndActive(
            @Param("animal") Animal animal,
            @Param("reportType") ReportType reportType,
            @Param("author") User author,
            @Param("isActive") boolean isActive
    );

}


