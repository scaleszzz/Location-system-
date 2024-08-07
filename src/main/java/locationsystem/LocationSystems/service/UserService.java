package locationsystem.LocationSystems.service;

import jakarta.transaction.Transactional;
import locationsystem.LocationSystems.model.User;
import locationsystem.LocationSystems.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User registerUser(String name, String email){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }


}
