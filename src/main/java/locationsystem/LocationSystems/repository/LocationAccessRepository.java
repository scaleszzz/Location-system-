package locationsystem.LocationSystems.repository;

import locationsystem.LocationSystems.model.LocationAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationAccessRepository extends JpaRepository<LocationAccess, Long> {
}
