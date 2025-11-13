package shopowner;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopOwnerRepository extends JpaRepository<ShopOwner, Integer> {
    Optional<ShopOwner> findByEmail(String email);
}

