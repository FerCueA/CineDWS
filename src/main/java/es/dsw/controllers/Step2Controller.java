package es.dsw.controllers;

import es.dsw.models.FormularioReserva;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("formularioReserva")
public class Step2Controller {

	@ModelAttribute("formularioReserva")
	public FormularioReserva crearReserva() {
		return new FormularioReserva();
	}

	@GetMapping("/step2")
	public String mostrarFormulario(
			@RequestParam(value = "imgSelec", required = false) String imgSelec,
			Model model,
			@ModelAttribute("formularioReserva") FormularioReserva reserva) {

		if (imgSelec != null && !imgSelec.isEmpty()) {
			reserva.setImgSelec(imgSelec);
		}

		if (reserva.getImgSelec() == null || reserva.getImgSelec().isEmpty()) {
			return "redirect:/step1";
		}

		model.addAttribute("imgSelec", reserva.getImgSelec());
		return "views/step2";
	}

	@PostMapping("/step2")
	public String procesarFormulario(
			@RequestParam(value = "imgSelec", required = false) String imgSelec,
			@ModelAttribute("formularioReserva") FormularioReserva reserva,
			Model model) {

		if (imgSelec != null && !imgSelec.isEmpty()) {
			reserva.setImgSelec(imgSelec);
		}

		boolean hayError = false;
		boolean errorNombre = false, errorEmail = false, errorRepetirEmail = false;
		boolean errorFecha = false, errorHora = false, errorAdultos = false;

		
		if (reserva.getFnom() == null || reserva.getFnom().trim().isEmpty()) {
			hayError = true;
			errorNombre = true;
		}
		if (reserva.getFmail() == null || reserva.getFmail().trim().isEmpty()) {
			hayError = true;
			errorEmail = true;
		}
		if (reserva.getFrepmail() == null || reserva.getFrepmail().trim().isEmpty()) {
			hayError = true;
			errorRepetirEmail = true;
		}
		if (!hayError && !reserva.getFmail().trim().equalsIgnoreCase(reserva.getFrepmail().trim())) {
			hayError = true;
			errorRepetirEmail = true;
		}
		if (reserva.getFdate() == null || reserva.getFdate().trim().isEmpty()) {
			hayError = true;
			errorFecha = true;
		}
		if (reserva.getFhour() == null || reserva.getFhour().trim().isEmpty()) {
			hayError = true;
			errorHora = true;
		}
		if (reserva.getFnumentradasadult() <= 0) {
			hayError = true;
			errorAdultos = true;
		}

		if (hayError) {
			model.addAttribute("imgSelec", reserva.getImgSelec());
			model.addAttribute("errorFnom", errorNombre);
			model.addAttribute("errorFmail", errorEmail);
			model.addAttribute("errorFrepmail", errorRepetirEmail);
			model.addAttribute("errorFdate", errorFecha);
			model.addAttribute("errorFhour", errorHora);
			model.addAttribute("errorNumentradas", errorAdultos);
			return "views/step2";
		}

		return "redirect:/step3";
	}
}