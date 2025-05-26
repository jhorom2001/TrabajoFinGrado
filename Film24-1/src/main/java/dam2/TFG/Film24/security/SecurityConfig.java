package dam2.TFG.Film24.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;

	@Bean
	public CommandLineRunner crearAdminPorDefecto(UsuarioRepository repo, PasswordEncoder encoder) {
		return args -> {
			if (!repo.existsByCorreoElectronico("admin@gmail.com")) {
				Usuario admin = new Usuario();
				admin.setCorreoElectronico("admin@gmail.com");
				admin.setPassword(encoder.encode("admin"));
				admin.setRol("ADMIN");
				repo.save(admin);
				System.out.println("Admin creado con éxito");
			}
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder()).and().build();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // CAMBIAR RUTAS A PRIVADAS MAS
																					// ADELANTE
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/index", "/altaUsuario", "/altaPelicula",
				"/altaPelicula/submit", "/Confirmaciones", "/ConfirmacionesUsuario", "/listaPeliculas",
				"/eliminarPelicula", "/eliminarPelicula/submit", "/registroUsuario", "/registroUsuario/submit",
				"/listaUsuarios", "/eliminarUsuario", "/eliminarUsuario/submit", "/listaPeliculasParaUsuario",
				"/acercade", "/asignarPelicula", "/asignarPelicula/submit", "/ConfirmacionVisualizacion",
				"/localizacion", "/devolverPelicula", "/devolverPelicula/submit", "/producto/consulta",
				"/producto/lista", "/altaProducto", "/altaProducto/submit", "/detallePelicula/**", "/detallePelicula",
				"/visualizacionesEnProgreso", "/altaResenna/submit/**", "/ConfirmacionRegistro", "/altaNoticia",
				"/altaNoticia/submit", "/eliminarNoticia", "/eliminarNoticia/submit", "/postsParaUsuario",
				"/detalleNoticia/**", "/listaProductosAdmin", "/eliminarProducto/submit", "/eliminarProducto",
				"/listaNoticias", "/css/**", "/js/**", "/images/**").permitAll().anyRequest().authenticated())

				.formLogin(login -> login.loginPage("/").successHandler(successHandler).permitAll())

				.logout(logout -> logout.invalidateHttpSession(false).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/?logout")
						.permitAll())
				
				.sessionManagement(session -> session.sessionFixation().newSession().maximumSessions(1).expiredUrl("/")
						.and().invalidSessionUrl("/").sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

		return http.build();
	}

}