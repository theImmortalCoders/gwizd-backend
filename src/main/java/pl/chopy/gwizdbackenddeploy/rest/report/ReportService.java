package pl.chopy.gwizdbackenddeploy.rest.report;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.chopy.gwizdbackenddeploy.model.mapper.LocationMapper;
import pl.chopy.gwizdbackenddeploy.model.mapper.ReportMapper;
import pl.chopy.gwizdbackenddeploy.model.repository.AnimalRepository;
import pl.chopy.gwizdbackenddeploy.model.repository.LocationRepository;
import pl.chopy.gwizdbackenddeploy.model.repository.ReportRepository;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final AnimalRepository animalRepository;
    private final LocationRepository locationRepository;
    private final ReportMapper reportMapper;
    private final LocationMapper locationMapper;
    public void addReport(ReportAddRequest request) {
        var animal = Option.ofOptional(animalRepository.findById(request.getAnimalId()))
                        .getOrElseThrow(()->new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Animal '"+request.getAnimalId()+"' not found."));
        var location = Option.of(request.getLocation())
                        .map(locationMapper::map)
                        .map(locationRepository::save)
                        .getOrElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
        Option.of(request)
                .map(reportMapper::map)
                .peek(rep->rep.setAnimal(animal))
                .peek(rep->rep.setLocation(location))
                .map(reportRepository::save)
                .getOrElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
