package locationsystem.LocationSystems.repository;

import locationsystem.LocationSystems.model.Location;
import locationsystem.LocationSystems.model.LocationAccess;
import locationsystem.LocationSystems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationAccessRepository extends JpaRepository<LocationAccess, Long> {
    Optional<LocationAccess> findByLocationAndUser(Location location, User user);
}
