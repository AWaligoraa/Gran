package pl.gran.gran.trail;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CruxController {

	private final CruxRepository cruxRepository;

	public CruxController(CruxRepository cruxRepository) {
		this.cruxRepository = cruxRepository;
	}

	@GetMapping("/api/cruxes/{cruxId}")
	public Crux findById(@PathVariable Long cruxId) {
		return cruxRepository.findById(cruxId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}
