package pl.gran.gran.trail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrailRepository extends JpaRepository<Trail, Long> {
    List<Trail> findByDistanceGreaterThanEqualAndDistanceLessThanEqualAndTechnicalDifficultyGreaterThanEqualAndTechnicalDifficultyLessThanEqualAndExposureGreaterThanEqualAndExposureLessThanEqualAndConditionGreaterThanEqualAndConditionLessThanEqual(
            double minDistance,
            double maxDistance,
            double minTechnical,
            double maxTechnical,
            double minExposure,
            double maxExposure,
            double minCondition,
            double maxCondition);
}
