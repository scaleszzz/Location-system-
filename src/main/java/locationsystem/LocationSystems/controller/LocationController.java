package locationsystem.LocationSystems.controller;

import locationsystem.LocationSystems.model.Location;
import locationsystem.LocationSystems.service.LocationService;
import locationsystem.LocationSystems.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestParam String name,)
}
