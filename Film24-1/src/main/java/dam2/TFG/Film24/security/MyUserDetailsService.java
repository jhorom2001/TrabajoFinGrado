package dam2.TFG.Film24.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dam2.TFG.Film24.modelo.Usuario;
import dam2.TFG.Film24.repository.UsuarioRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByCorreoElectronico(correoElectronico)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		// Devuelve tu clase personalizada MyUserDetails
		return new MyUserDetails(usuario);
	}
}
