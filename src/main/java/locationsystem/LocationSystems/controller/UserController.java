package locationsystem.LocationSystems.controller;

import locationsystem.LocationSystems.model.User;
import locationsystem.LocationSystems.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestParam String name, @RequestParam String email) {
        User user = userService.registerUser(name, email);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
