package es.dsw.models;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.ui.Model;

public class IndexModel {

	private static final String[] MESES = {
			"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
			"Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
	};

	private static String obtenerNombreMes(int mesValor) {
		return MESES[mesValor - 1];
	}

	private static String formatHora(LocalDateTime hora) {
		int horaValor = hora.getHour();
		int minutoValor = hora.getMinute();
		String minutoStr = (minutoValor < 10) ? ("0" + minutoValor) : String.valueOf(minutoValor);
		return horaValor + "h " + minutoStr + "m";
	}

	public static void modeloIndex(Model model, LocalDateTime horaActual) {

		
		int dia = horaActual.getDayOfMonth();
		
		String horaLocal = formatHora(horaActual);
		
		String nombreMes = obtenerNombreMes(horaActual.getMonthValue());
		
		DayOfWeek diaSemana = horaActual.getDayOfWeek();

		
		String diaSemanaES;
		String mensajeSemana;
		double precioEntrada = 6.00;

		
		switch (diaSemana) {
			case MONDAY:
				diaSemanaES = "Lunes";
				mensajeSemana = "Comienza la semana a lo grande";
				break;
			case TUESDAY:
				diaSemanaES = "Martes";
				mensajeSemana = "Hoy doble de palomitas";
				break;
			case WEDNESDAY:
				diaSemanaES = "Miércoles";
				mensajeSemana = "Día del espectador";
				precioEntrada = 3.50;
				break;
			case THURSDAY:
				diaSemanaES = "Jueves";
				mensajeSemana = "La noche de las aventuras";
				break;
			case FRIDAY:
				diaSemanaES = "Viernes";
				mensajeSemana = "No te quedes en tu casa";
				break;
			case SATURDAY:
				diaSemanaES = "Sábado";
				mensajeSemana = "¿Ya has hecho planes para esta noche?";
				break;
			case SUNDAY:
				diaSemanaES = "Domingo";
				mensajeSemana = "Vente y carga las pilas";
				break;
			default:
				diaSemanaES = "";
				mensajeSemana = "Bienvenido!";
		}

		
		String fechaLocal = diaSemanaES + ", día " + dia + " de " + nombreMes;

		model.addAttribute("horaLocal", horaLocal);
		model.addAttribute("fechaLocal", fechaLocal);
		model.addAttribute("mensajeSemana", mensajeSemana);
		model.addAttribute("precioEntrada", precioEntrada);
	}

	

	

	
}