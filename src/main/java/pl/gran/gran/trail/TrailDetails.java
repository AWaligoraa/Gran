package pl.gran.gran.trail;

import java.util.List;

public record TrailDetails(
		Long id,
		String name,
		String startPoint,
		Double distance,
		double technicalDifficulty,
		double exposure,
		double condition,
		List<Crux> cruxes) {

	static TrailDetails of(Trail trail, List<Crux> cruxes) {
		return new TrailDetails(
				trail.getId(),
				trail.getName(),
				trail.getStartPoint(),
				trail.getDistance(),
				trail.getTechnicalDifficulty(),
				trail.getExposure(),
				trail.getCondition(),
				cruxes);
	}

}
