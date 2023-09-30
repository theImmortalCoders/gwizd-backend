package pl.chopy.gwizdbackenddeploy.rest.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.chopy.gwizdbackenddeploy.model.entity.Location;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LocationService {
    @Data
    @AllArgsConstructor
    static class LocationDistance {
        private Location location;
        private Long distance;
    }

    public Location findClosestLocation(List<Location> locations, Location target) {
        List<LocationDistance> distances = new ArrayList<>();
        locations.forEach(loc -> distances.add(new LocationDistance(
                loc,
                (long) Point2D.distance(
                        loc.getLatitude(),
                        loc.getLongitude(),
                        target.getLatitude(),
                        target.getLongitude()
                )
        )));
        LocationDistance closest = distances.stream()
                .min(Comparator.comparing(LocationDistance::getDistance))
                .orElse(null);
        return closest != null ? closest.getLocation() : null;
    }

    public boolean isLocationsInRange(Location source, Location target, Double range) {
        return Point2D.distance(
                source.getLatitude(),
                source.getLongitude(),
                target.getLatitude(),
                target.getLongitude()
        ) <= range;
    }
}
