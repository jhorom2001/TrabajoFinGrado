package dam2.TFG.Film24.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class FiltrarMalasPalabras {

	private Set<String> malasPalabras;

	public FiltrarMalasPalabras() {
		malasPalabras = new HashSet<>();
		cargarMalasPalabrasDesdeURL(
				"https://raw.githubusercontent.com/LDNOOBW/List-of-Dirty-Naughty-Obscene-and-Otherwise-Bad-Words/master/es");
	}

	private void cargarMalasPalabrasDesdeURL(String url) {
		try {
			URL urlObj = new URL(url);
			try (BufferedReader in = new BufferedReader(new InputStreamReader(urlObj.openStream()))) {
				String linea;
				while ((linea = in.readLine()) != null) {
					malasPalabras.add(linea.trim().toLowerCase());
				}
			}
		} catch (Exception e) {
			System.err.println("Error al cargar lista de malas palabras: " + e.getMessage());
		}
	}

	public boolean contieneMalasPalabras(String texto) {
		String[] palabras = texto.toLowerCase().split("\\W+");
		for (String palabra : palabras) {
			if (malasPalabras.contains(palabra)) {
				return true;
			}
		}
		return false;
	}

	public String censurarTexto(String texto) {
		String[] palabras = texto.split("\\b");
		StringBuilder resultado = new StringBuilder();
		for (String palabra : palabras) {
			if (malasPalabras.contains(palabra.toLowerCase())) {
				resultado.append("*".repeat(palabra.length()));
			} else {
				resultado.append(palabra);
			}
		}
		return resultado.toString();
	}
}
