package pl.gran.gran.trail;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewTrail(
		@NotBlank @Size(max = 200) String name,
		@NotBlank @Size(max = 200) String startPoint,
		@NotNull @DecimalMin("0.0") @DecimalMax("50.0") Double distance,
		@DecimalMin("1.0") @DecimalMax("5.0") double technicalDifficulty,
		@DecimalMin("1.0") @DecimalMax("5.0") double exposure,
		@DecimalMin("1.0") @DecimalMax("5.0") double condition,
		@Size(max = 300) String mapUrl) {
}
