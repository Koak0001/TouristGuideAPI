package tourism.repository;

import org.springframework.stereotype.Repository;
import tourism.model.TouristAttraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> touristAttractions = new ArrayList<>();

    // Create
    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristAttractions.add(touristAttraction);
    }

    // Read
    public List<TouristAttraction> getAllTouristAttractions() {
        return touristAttractions;
    }

    public Optional<TouristAttraction> getTouristAttractionByName(String name) {
        return touristAttractions.stream()
                .filter(attraction -> attraction.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    // Update
    public void updateTouristAttraction(String name, TouristAttraction updatedAttraction) {
        for (int i = 0; i < touristAttractions.size(); i++) {
            if (touristAttractions.get(i).getName().equalsIgnoreCase(name)) {
                touristAttractions.set(i, updatedAttraction);
                return;
            }
        }
    }

    // Delete
    public void deleteTouristAttraction(String name) {
        touristAttractions.removeIf(attraction -> attraction.getName().equalsIgnoreCase(name));
    }
}
