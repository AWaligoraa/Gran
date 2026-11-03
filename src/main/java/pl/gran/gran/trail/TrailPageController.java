package pl.gran.gran.trail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrailPageController {

	@GetMapping("/")
	public String home() {
		return "forward:/home.html";
	}

	@GetMapping({"/trails", "/trails/"})
	public String page() {
		return "forward:/index.html";
	}
	@GetMapping("/trails/new")
	public String addTrail() {
		return "forward:/add.html";
	}

	@GetMapping("/trails/{id}/edit")
	public String editTrail() {
		return "forward:/edit.html";
	}

	@GetMapping("/trails/{id}")
	public String trail() {
		return "forward:/trail.html";
	}

	@GetMapping("/cruxes/{cruxId}")
	public String crux() {
		return "forward:/crux.html";
	}
}
