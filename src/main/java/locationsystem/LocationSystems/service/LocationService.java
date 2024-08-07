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

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationAccessRepository locationAccessRepository;

    public Location createLocation(String name, String address, User owner){
        Location location = new Location();
        location.setName(name);
        location.setAddress(address);
        location.setOwner(owner);
        return locationRepository.save(location);
    }

    public void shareLocation(Long locationId, User user, AccessType accessType){
        Location location = locationRepository.findById(locationId).orElse(null);
        LocationAccess locationAccess = new LocationAccess();
        locationAccess.setLocation(location);
        locationAccess.setUser(user);
        locationAccess.setAccessType(accessType);
        locationAccessRepository.save(locationAccess);
    }
}
