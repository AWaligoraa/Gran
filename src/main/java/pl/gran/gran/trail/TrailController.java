package pl.gran.gran.trail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrailController {
    private final TrailRepository trailRepository;
    public TrailController(TrailRepository trailRepository) {
        this.trailRepository = trailRepository;
    }
    @GetMapping("/api/trails")
    public List<Trail> findAll() {
        return trailRepository.findAll();
    }
}
