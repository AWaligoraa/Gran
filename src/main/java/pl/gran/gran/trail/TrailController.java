package pl.gran.gran.trail;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TrailController {
    private final TrailRepository trailRepository;
    private final CruxRepository cruxRepository;

    public TrailController(TrailRepository trailRepository, CruxRepository cruxRepository) {
        this.trailRepository = trailRepository;
        this.cruxRepository = cruxRepository;
    }
    @GetMapping("/api/trails")
    public List<Trail> findAll(
            @RequestParam(required = false) Double maxDistance,
            @RequestParam(required = false) Double maxTechnical,
            @RequestParam(required = false) Double maxExposure,
            @RequestParam(required = false) Double maxCondition,
            @RequestParam(required = false) Double minDistance,
            @RequestParam(required = false) Double minTechnical,
            @RequestParam(required = false) Double minExposure,
            @RequestParam(required = false) Double minCondition) {
        return trailRepository.findByDistanceGreaterThanEqualAndDistanceLessThanEqualAndTechnicalDifficultyGreaterThanEqualAndTechnicalDifficultyLessThanEqualAndExposureGreaterThanEqualAndExposureLessThanEqualAndConditionGreaterThanEqualAndConditionLessThanEqual(
                minDistance == null ? 0 : minDistance,
                maxDistance == null ? 100 : maxDistance,
                minTechnical == null ? 0 : minTechnical,
                maxTechnical == null ? 5 : maxTechnical,
                minExposure == null ? 0 : minExposure,
                maxExposure == null ? 5 : maxExposure,
                minCondition == null ? 0 : minCondition,
                maxCondition == null ? 5 : maxCondition
        );
    }

    @GetMapping("/api/trails/{id}")
    public TrailDetails findById(@PathVariable Long id) {
        Trail trail = trailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TrailDetails.of(trail, cruxRepository.findByTrailIdOrderByPositionAsc(id));
    }
}
