package locationsystem.LocationSystems.controller;

import locationsystem.LocationSystems.model.AccessType;
import locationsystem.LocationSystems.model.Location;
import locationsystem.LocationSystems.model.User;
import locationsystem.LocationSystems.service.LocationService;
import locationsystem.LocationSystems.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestParam String name,
                                                   @RequestParam String address,
                                                   @RequestParam String ownerEmail) {
        User owner = userService.findUserByEmail(ownerEmail);
        if (owner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Location location = locationService.createLocation(name, address, owner);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @PostMapping("/{locationId}/share")
    public ResponseEntity<Void> shareLocation(@PathVariable Long locationId,
                                              @RequestParam String friendEmail,
                                              @RequestParam AccessType accessType) {
        User friend = userService.findUserByEmail(friendEmail);
        if (friend == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean success = locationService.shareLocation(locationId, friend, accessType);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long locationId) {
        Optional<Location> locationOpt = locationService.getLocationById(locationId);
        if (locationOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(locationOpt.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}
