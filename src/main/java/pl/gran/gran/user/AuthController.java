package pl.gran.gran.user;

import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {

	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
		this.appUserRepository = appUserRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/api/me")
	public Map<String, String> me(Authentication authentication) {
		if (authentication == null
				|| !authentication.isAuthenticated()
				|| authentication instanceof AnonymousAuthenticationToken) {
			return Map.of("authenticated", "false");
		}
		String role = authentication.getAuthorities().stream()
				.findFirst()
				.map(a -> a.getAuthority().replace("ROLE_", ""))
				.orElse("USER");
		return Map.of(
				"authenticated", "true",
				"email", authentication.getName(),
				"role", role);
	}

	@PostMapping("/api/auth/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> register(@Valid @RequestBody RegisterRequest request) {
		String email = request.email().trim().toLowerCase();
		if (appUserRepository.existsByEmailIgnoreCase(email)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Ten e-mail jest już zajęty");
		}
		appUserRepository.save(new AppUser(email, passwordEncoder.encode(request.password()), Role.USER));
		return Map.of("email", email, "role", Role.USER.name());
	}
}
