package tourism.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;

import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristController {

    private final TouristService touristService;

    @Autowired
    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    // GET /attractions
//    @GetMapping
//    public ResponseEntity<List<TouristAttraction>> getAllAttractions() {
//        List<TouristAttraction> attractions = touristService.getAllTouristAttractions();
//        return new ResponseEntity<>(attractions, HttpStatus.OK);
//    }
    @GetMapping
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllTouristAttractions();
        model.addAttribute("attractions", attractions);
        return "attractions" ;
    }

    // GET /attractions/{name}
//    @GetMapping("/{name}")
//    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name) {
//        TouristAttraction attraction = touristService.getTouristAttractionByName(name);
//        return new ResponseEntity<>(attraction, HttpStatus.OK);
//    }
    @GetMapping("/{name}")
    public String getAttractionByName(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getTouristAttractionByName(name);
        model.addAttribute("attraction", attraction); // Add the attraction object to the model
        return "attraction"; // Return the name of the HTML template to render
    }


    // POST /attractions/add
    @PostMapping("/add")
    public ResponseEntity<Void> addAttraction(@RequestBody TouristAttraction attraction) {
        touristService.addTouristAttraction(attraction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // POST /attractions/update
    @PostMapping("/update")
    public ResponseEntity<Void> updateAttraction(@RequestParam String name, @RequestBody TouristAttraction updatedAttraction) {
        touristService.updateTouristAttraction(name, updatedAttraction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET /attractions/delete/{name}
    @GetMapping("/delete/{name}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable String name) {
        boolean deleted = touristService.deleteTouristAttraction(name);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
