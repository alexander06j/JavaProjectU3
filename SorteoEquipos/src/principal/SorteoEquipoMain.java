package principal;

import modelo.Equipo;
import modelo.Etapa;
import excepciones.NombreEquipoInvalidoException;
import excepciones.EquipoDuplicadoException;
import excepciones.NumeroDeEquipoInvalidoException;
import java.util.Scanner;

public class SorteoEquipoMain {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		//Bloque try-catch
		try {
			System.out.println("Seleccione la etapa del torneo (octavos, cuartos, semifinales, final):");
			String etapa = scanner.nextLine().toLowerCase();

			int numeroEquipos = obtenerNumeroDeEquipos(etapa);
			Equipo[] equipos = new Equipo[numeroEquipos];

			System.out.println("Ingrese los nombres de los " + numeroEquipos + " equipos:");
			for (int i = 0; i < numeroEquipos; i++) {
				System.out.print("Equipo " + (i + 1) + ": ");
				String nombre = scanner.nextLine();

				if (nombre == null || nombre.trim().isEmpty()) {
					throw new NombreEquipoInvalidoException("El nombre del equipo no puede estar vacío.");
				}

				for (int j = 0; j < i; j++) {
					if (equipos[j].getNombre().equalsIgnoreCase(nombre)) {
						throw new EquipoDuplicadoException("El equipo \"" + nombre + "\" ya está registrado.");
					}
				}
				equipos[i] = new Equipo(nombre);
			}

			Etapa etapaTorneo = new Etapa(etapa, equipos);
			etapaTorneo.realizarSorteo();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private static int obtenerNumeroDeEquipos(String etapa) throws NumeroDeEquipoInvalidoException {
		return switch (etapa) {
		case "octavos" -> 16;
		case "cuartos" -> 8;
		case "semifinales" -> 4;
		case "final" -> 2;
		default -> throw new NumeroDeEquipoInvalidoException("Etapa no válida: " + etapa);
		};
	}
}
