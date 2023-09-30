package pl.chopy.gwizdbackenddeploy.rest.report;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.chopy.gwizdbackenddeploy.model.ReportFilter;
import pl.chopy.gwizdbackenddeploy.model.entity.Animal;
import pl.chopy.gwizdbackenddeploy.model.entity.Location;
import pl.chopy.gwizdbackenddeploy.model.mapper.LocationMapper;
import pl.chopy.gwizdbackenddeploy.model.mapper.ReportMapper;
import pl.chopy.gwizdbackenddeploy.model.repository.AnimalRepository;
import pl.chopy.gwizdbackenddeploy.model.repository.LocationRepository;
import pl.chopy.gwizdbackenddeploy.model.repository.ReportRepository;
import pl.chopy.gwizdbackenddeploy.rest.location.LocationService;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final AnimalRepository animalRepository;
    private final LocationRepository locationRepository;
    private final ReportMapper reportMapper;
    private final LocationMapper locationMapper;
    private final LocationService locationService;

    public void addReport(ReportAddRequest request) {
        var animal = Option.ofOptional(animalRepository.findById(request.getAnimalId()))
                .getOrElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Animal '" + request.getAnimalId() + "' not found."));
        var location = Option.of(request.getLocation())
                .map(locationMapper::map)
                .map(locationRepository::save)
                .getOrElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        Option.of(request)
                .map(reportMapper::map)
                .peek(rep -> rep.setAnimal(animal))
                .peek(rep -> rep.setLocation(location))
                .map(reportRepository::save)
                .getOrElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public List<SingleReportResponse> getReports(ReportFilter reportFilter) {
        Animal animal = null;
        Location location;
        if (reportFilter.getAnimalId() != null) {
            animal = Option.ofOptional(animalRepository.findById(reportFilter.getAnimalId()))
                    .getOrElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Animal '" + reportFilter.getAnimalId() + "' not found."));
        }
        var reportsJpa = reportRepository.getReportsByAnimalOrReportType(
                animal,
                reportFilter.getReportType()
        );
        if (reportFilter.getLocation() != null && reportFilter.getRange() != null) {
            location = Option.of(reportFilter.getLocation())
                    .map(locationMapper::map)
                    .map(locationRepository::save)
                    .getOrElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            reportsJpa = reportsJpa
                    .parallelStream()
                    .filter(rep -> locationService.isLocationsInRange(location, rep.getLocation(), reportFilter.getRange()))
                    .toList();
        }
        return reportsJpa
                .parallelStream()
                .map(reportMapper::map)
                .toList();
    }
}
