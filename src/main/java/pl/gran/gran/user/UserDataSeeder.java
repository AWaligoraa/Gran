package pl.gran.gran.user;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDataSeeder implements ApplicationRunner {

	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;

	public UserDataSeeder(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
		this.appUserRepository = appUserRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(ApplicationArguments args) {
		createIfMissing("admin@gran.pl", "admin123", Role.ADMIN);
		createIfMissing("user@gran.pl", "user123", Role.USER);
	}

	private void createIfMissing(String email, String rawPassword, Role role) {
		if (!appUserRepository.existsByEmailIgnoreCase(email)) {
			appUserRepository.save(new AppUser(email, passwordEncoder.encode(rawPassword), role));
		}
	}
}
