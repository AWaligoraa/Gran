package pl.gran.gran.trail;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewCrux(
		@NotBlank @Size(max = 200) String name,
		@Min(1) Integer position,
		@NotBlank @Size(max = 1000) String description,
		@Size(max = 20) String youtubeId) {
}
