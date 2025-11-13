package shopowner;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopOwnerService {

    @Autowired
    private ShopOwnerRepository repo;

    // Save or update
    public ShopOwner save(ShopOwner shopOwner) {
        return repo.save(shopOwner);
    }

    // Get all
    public List<ShopOwner> listAll() {
        return repo.findAll();
    }

    // Get by ID
    public ShopOwner get(Integer id) throws NoSuchElementException {
        return repo.findById(id).orElseThrow();
    }

    // Delete by ID
    public void delete(Integer id) throws NoSuchElementException {
        repo.delete(get(id));
    }

    // Check if shop name exists
    public boolean existsByShopName(String shopName) {
        return repo.existsByShopName(shopName);
    }
}
