package pl.gran.gran.trail;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CruxController {

	private final CruxRepository cruxRepository;
	private final TrailRepository trailRepository;

	public CruxController(CruxRepository cruxRepository, TrailRepository trailRepository) {
		this.cruxRepository = cruxRepository;
		this.trailRepository = trailRepository;
	}

	@GetMapping("/api/cruxes/{cruxId}")
	public Crux findById(@PathVariable Long cruxId) {
		return cruxRepository.findById(cruxId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/api/trails/{trailId}/cruxes")
	@ResponseStatus(HttpStatus.CREATED)
	public Crux create(@PathVariable Long trailId, @Valid @RequestBody NewCrux request) {
		Trail trail = trailRepository.findById(trailId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		int position = request.position() != null ? request.position() : nextPosition(trailId);
		return cruxRepository.save(new Crux(
				trail,
				request.name().trim(),
				position,
				request.description().trim(),
				request.youtubeId()));
	}

	@PutMapping("/api/cruxes/{cruxId}")
	public Crux update(@PathVariable Long cruxId, @Valid @RequestBody NewCrux request) {
		Crux crux = cruxRepository.findById(cruxId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		int position = request.position() != null ? request.position() : crux.getPosition();
		crux.update(
				request.name().trim(),
				position,
				request.description().trim(),
				request.youtubeId());
		return cruxRepository.save(crux);
	}

	@DeleteMapping("/api/cruxes/{cruxId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long cruxId) {
		if (!cruxRepository.existsById(cruxId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		cruxRepository.deleteById(cruxId);
	}

	private int nextPosition(Long trailId) {
		return cruxRepository.findByTrailIdOrderByPositionAsc(trailId).stream()
				.mapToInt(Crux::getPosition)
				.max()
				.orElse(0) + 1;
	}

}
