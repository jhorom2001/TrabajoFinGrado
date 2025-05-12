package dam2.TFG.Film24.security;

import org.springframework.beans.factory.annotation.Autowired;
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

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder()).and().build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // PERMIT ALL DEJA ACCEDER A LA
																							// URL A TODOS, SI EL
																							// USUARIO LOGEA
																							// SOLO DEBERIA DEJARLE A EL
																							// !!!!!!!!!!!!!!!!!
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/index", "/loginadmin", "/interfazAdmin", "/altaUsuario", "/altaPelicula",
						"/altaPelicula/submit", "/Confirmaciones", "/ConfirmacionesUsuario", "/listaPeliculas",
						"/eliminarPelicula", "/eliminarPelicula/submit", "/registroUsuario", "/registroUsuario/submit",
						"/listaUsuarios", "/eliminarUsuario", "/eliminarUsuario/submit", "/listaPeliculasParaUsuario",
						"/acercade", "/asignarPelicula", "/asignarPelicula/submit", "/ConfirmacionVisualizacion" , "/localizacion",
						"/devolverPelicula", "/devolverPelicula/submit","/producto/consulta","/producto/lista","/altaProducto",
						"/altaProducto/submit", "/ConfirmacionRegistro" , "/css/**", "/js/**", "/images/**")
				.permitAll().anyRequest().authenticated()).formLogin(login -> login.loginPage("/") // Página de login
																									// personalizada (tu
																									// formulario está
																									// en "/")
						.defaultSuccessUrl("/usuarioLogeado", true).permitAll())
				.logout(logout -> logout.invalidateHttpSession(false) // O true, según si quieres destruir la sesión
						.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/?logout") // Redirige al home con parámetro ?logout
						.permitAll())
				
				
				.sessionManagement(
						session -> session.sessionFixation().newSession().maximumSessions(1).expiredUrl("/").and()
								.invalidSessionUrl("/").sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

		return http.build();
	}
}
