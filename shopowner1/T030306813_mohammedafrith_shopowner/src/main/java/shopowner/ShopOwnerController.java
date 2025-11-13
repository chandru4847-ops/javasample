package shopowner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopOwners")
@CrossOrigin(origins = "http://localhost:4200")
public class ShopOwnerController {

    @Autowired
    private ShopOwnerRepository repository;

    @PostMapping
    public ResponseEntity<?> registerShopOwner(@RequestBody ShopOwner owner) {
        Optional<ShopOwner> existing = repository.findByEmail(owner.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Shop owner with this email already exists!");
        }
        repository.save(owner);
        return ResponseEntity.ok("Shop Owner registered successfully!");
    }

    @GetMapping
    public List<ShopOwner> getAll() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ShopOwner owner) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop owner not found");
        }
        owner.setId(id);
        repository.save(owner);
        return ResponseEntity.ok("Updated successfully!");
    }
}
