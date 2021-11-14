package com.example.ISA2021.config;

import com.example.ISA2021.security.TokenUtils;
import com.example.ISA2021.security.auth.RestAuthenticationEntryPoint;
import com.example.ISA2021.security.auth.TokenAuthenticationFilter;
import com.example.ISA2021.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
// Ukljucivanje podrske za anotacije "@Pre*" i "@Post*" koje ce aktivirati autorizacione provere za svaki pristup metodi
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
	// BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Servis koji se koristi za citanje podataka o korisnicima aplikacije
	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	// Handler za vracanje 401 kada klijent sa neodogovarajucim korisnickim imenom i lozinkom pokusa da pristupi resursu
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	// Registrujemo authentication manager koji ce da uradi autentifikaciju korisnika za nas
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// Definisemo uputstvo za authentication managera koji servis da koristi da izvuce podatke o korisniku koji zeli da se autentifikuje,
	//kao i kroz koji enkoder da provuce lozinku koju je dobio od klijenta u zahtevu da bi adekvatan hash koji dobije kao rezultat bcrypt algoritma uporedio sa onim koji se nalazi u bazi (posto se u bazi ne cuva plain lozinka)
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	// Injektujemo implementaciju iz TokenUtils klase kako bismo mogli da koristimo njene metode za rad sa JWT u TokenAuthenticationFilteru
	@Autowired
	private TokenUtils tokenUtils;

	/*@Bean
	public CorsFilter corsFilter() {
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setMaxAge(1800L);

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}*/

	// Definisemo prava pristupa odredjenim URL-ovima
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

				// Allow all users to access URLs that have 'public' in them
				// Allow auth
				.authorizeRequests()
				.antMatchers("**/public/**").permitAll()
				.antMatchers("/auth/**").permitAll()
				.antMatchers("/api/hospitals/**").permitAll()
				.antMatchers("/medicament").permitAll()
				.antMatchers("/pharmacies/**").permitAll()
				.antMatchers("/examinations/unsubscribed/{id}").permitAll()
				.antMatchers("/examinations/**").permitAll()
				.antMatchers("/api/meds/**").permitAll()
				.antMatchers("/complaints/**").permitAll()
				.antMatchers("/complaintss/**").permitAll()
				.antMatchers("/examinationsPh/**").permitAll()
				.antMatchers("/prescriptions/**").permitAll()
				.antMatchers("/api/users/{userId}").permitAll()
				.antMatchers("/actions/**").permitAll()
				//.antMatchers("/complaints/**").permitAll()
				.antMatchers("/subscribed/**").permitAll()
				.antMatchers("/**/dermatologist").permitAll()
				.antMatchers("/{pharmacyId}/dermatologist").permitAll()
				.antMatchers("/{pharmacyId}/meds").permitAll()
				.antMatchers("/api/users/{dermatologistId}/pharmacy").permitAll()
				.antMatchers("/orderform/**").permitAll()

				// All other requests must be authorized
				.anyRequest().authenticated().and()

				// Intercept every request with filter
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

		http
				.cors().and()
				.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TokenAuthenticationFilter will ignore all URLs below
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");

		// TokenAuthenticationFilter will ignore all paths that have 'public' in them
		web.ignoring().antMatchers(HttpMethod.GET, "/**/public/**");
		web.ignoring().antMatchers(HttpMethod.POST, "/**/public/**");
		web.ignoring().antMatchers(HttpMethod.PUT, "/**/public/**");
		web.ignoring().antMatchers(HttpMethod.DELETE, "/**/public/**");
	}
}
