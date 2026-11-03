package pl.gran.gran.trail;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PostMapping("/api/trails")
    @ResponseStatus(HttpStatus.CREATED)
    public Trail create(@Valid @RequestBody NewTrail request) {
        String mapUrl = request.mapUrl();
        if (mapUrl != null && mapUrl.isBlank()) {
            mapUrl = null;
        }
        return trailRepository.save(new Trail(
                request.name(),
                request.startPoint(),
                request.distance(),
                request.technicalDifficulty(),
                request.exposure(),
                request.condition(),
                mapUrl));
    }

    @PutMapping("/api/trails/{id}")
    public Trail update(@PathVariable Long id, @Valid @RequestBody NewTrail request) {
        Trail trail = trailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String mapUrl = request.mapUrl();
        if (mapUrl != null && mapUrl.isBlank()) {
            mapUrl = null;
        }
        trail.update(
                request.name(),
                request.startPoint(),
                request.distance(),
                request.technicalDifficulty(),
                request.exposure(),
                request.condition(),
                mapUrl);
        return trailRepository.save(trail);
    }

    @DeleteMapping("/api/trails/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable Long id) {
        if (!trailRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        cruxRepository.deleteByTrailId(id);
        trailRepository.deleteById(id);
    }

    @GetMapping("/api/trails/{id}")
    public TrailDetails findById(@PathVariable Long id) {
        Trail trail = trailRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TrailDetails.of(trail, cruxRepository.findByTrailIdOrderByPositionAsc(id));
    }
}
