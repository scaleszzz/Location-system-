package locationsystem.LocationSystems.service;

import jakarta.transaction.Transactional;
import locationsystem.LocationSystems.model.AccessType;
import locationsystem.LocationSystems.model.Location;
import locationsystem.LocationSystems.model.LocationAccess;
import locationsystem.LocationSystems.model.User;
import locationsystem.LocationSystems.repository.LocationAccessRepository;
import locationsystem.LocationSystems.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationAccessRepository locationAccessRepository;

    public Location createLocation(String name, String address, User owner) {
        Location location = new Location();
        location.setName(name);
        location.setAddress(address);
        location.setOwner(owner);
        return locationRepository.save(location);
    }

    public boolean shareLocation(Long locationId, User friend, AccessType accessType) {
        Optional<Location> optionalLocation = locationRepository.findById(locationId);
        if (optionalLocation.isEmpty()) {
            return false;
        }
        Location location = optionalLocation.get();

        Optional<LocationAccess> existingAccess = locationAccessRepository.findByLocationAndUser(location, friend);
        if (existingAccess.isPresent()) {
            return false;
        }

        LocationAccess locationAccess = new LocationAccess();
        locationAccess.setLocation(location);
        locationAccess.setUser(friend);
        locationAccess.setAccessType(accessType);

        locationAccessRepository.save(locationAccess);
        return true;
    }

    public Optional<Location> getLocationById(Long locationId) {
        return locationRepository.findById(locationId);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
