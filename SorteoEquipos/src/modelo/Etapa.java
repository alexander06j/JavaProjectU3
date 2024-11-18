package modelo;

import excepciones.NumeroDeEquipoInvalidoException;

public class Etapa {

	// atributos
	private String nombre;
	private Equipo[] equipos;

	//Metodos:
	public Etapa(String nombre, Equipo[] equipos) throws NumeroDeEquipoInvalidoException {
		this.nombre = nombre;
		validarNumeroEquipo(equipos.length);
		this.equipos = equipos;
	}

	private void validarNumeroEquipo(int numeroEquipos) throws NumeroDeEquipoInvalidoException {
		if ((nombre.equals("octavos") && numeroEquipos != 16) || (nombre.equals("cuartos") && numeroEquipos != 8)
				|| (nombre.equals("semifinales") && numeroEquipos != 4)
				|| (nombre.equals("final") && numeroEquipos != 2)) {
			throw new NumeroDeEquipoInvalidoException("El número de equipos no es válido para la etapa: " + nombre);
		}
	}

	public void realizarSorteo() {
		if (equipos.length < 2) {
			System.out.println("\nGanador del torneo: " + equipos[0].getNombre());
			return;
		}
		System.out.println("\nPartidos de " + nombre + ":");
		mezclarEquipos(equipos);

		Equipo[] ganadores = new Equipo[equipos.length / 2];
		for (int i = 0; i < equipos.length; i += 2) {
			System.out.println(equipos[i].getNombre() + " vs " + equipos[i + 1].getNombre());
			Equipo ganador = equipos[i]; // simulando que el primer equipo gana
			System.out.println("Ganador: " + ganador.getNombre());
			ganadores[i / 2] = ganador;
		}

		// crear la siguiente etapa
		String siguienteEtapa = switch (ganadores.length) {
		case 8 -> "cuartos";
		case 4 -> "semifinales";
		case 2 -> "final";
		default -> "";
		};

		if (!siguienteEtapa.isEmpty()) {
			try {
				Etapa etapaSiguiente = new Etapa(siguienteEtapa, ganadores);
				etapaSiguiente.realizarSorteo();
			} catch (NumeroDeEquipoInvalidoException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
	
	private void mezclarEquipos(Equipo[] equipos) {
		for(int i = equipos.length -1; i > 0; i--) {
			int j =(int)(Math.random()*(i+1));
			Equipo temp = equipos[i];
			equipos[i]=equipos[j];
			equipos[j]=temp;
		}
	}
}
