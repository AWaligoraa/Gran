package pl.gran.gran.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(
								"/",
								"/login",
								"/register",
								"/home.html",
								"/login.html",
								"/register.html",
								"/index.html",
								"/trail.html",
								"/crux.html",
								"/home.css",
								"/catalog.css",
								"/trail-page.css",
								"/crux-page.css",
								"/add.css",
								"/style.css",
								"/auth.css",
								"/app.js",
								"/photos.js",
								"/auth.js",
								"/images/**",
								"/api/me",
								"/api/auth/register")
						.permitAll()
						.requestMatchers("/trails/new", "/trails/*/edit", "/add.html", "/edit.html")
						.hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/trails").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/api/trails/*").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api/trails/*").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/trails", "/trails/", "/trails/*", "/cruxes/**")
						.permitAll()
						.requestMatchers(HttpMethod.GET, "/api/trails", "/api/trails/*", "/api/cruxes/**")
						.permitAll()
						.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/trails", true)
						.failureUrl("/login?error")
						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.permitAll())
				.exceptionHandling(ex -> ex
						.defaultAuthenticationEntryPointFor(
								new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
								PathPatternRequestMatcher.withDefaults().matcher("/api/**")));
		return http.build();
	}
}
