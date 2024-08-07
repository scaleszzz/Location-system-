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

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestParam String name,@RequestParam String address,
                                                   @RequestParam String ownerEmail){
        User owner = userService.findUserByEmail(ownerEmail);
        Location location = locationService.createLocation(name, address, owner);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @PostMapping("/{locationId}/share")
    public ResponseEntity<Void> shareLocation(@PathVariable Long locationId, @RequestParam String friendEmail,
                                              @RequestParam AccessType accessType) {
        User friend = userService.findUserByEmail(friendEmail);
        locationService.shareLocation(locationId, friend, accessType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
