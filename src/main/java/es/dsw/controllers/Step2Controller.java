

package es.dsw.controllers;

import es.dsw.models.FormularioReserva;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Step2Controller {

	@GetMapping("/step2")
	public String step2(@RequestParam(value = "imgSelec", required = false) String imgSelec,
						@RequestParam(value = "error", required = false) String error,
						Model model) {
		if (imgSelec == null || imgSelec.isEmpty()) {
			return "redirect:/step1";
		}
		model.addAttribute("imgSelec", imgSelec);
		model.addAttribute("formularioReserva", new FormularioReserva());
		if (error != null) {
			model.addAttribute("error", error);
		}
		return "views/step2";
	}

	@PostMapping("/step2")
	public String validarFormulario(@RequestParam(value = "imgSelec") String imgSelec,
									@ModelAttribute FormularioReserva formularioReserva,
									Model model) {
		
		boolean hayError = false;
		StringBuilder mensaje = new StringBuilder();
		// Marcas de error para los campos
		boolean errorFnom = false, errorFapell = false, errorFmail = false, errorFrepmail = false, errorFdate = false, errorFhour = false;
		if (formularioReserva.getFnom() == null || formularioReserva.getFnom().isEmpty()) {
			hayError = true;
			errorFnom = true;
			mensaje.append("El nombre es obligatorio. ");
		}
		if (formularioReserva.getFapell() == null || formularioReserva.getFapell().isEmpty()) {
			hayError = true;
			errorFapell = true;
			mensaje.append("Los apellidos son obligatorios. ");
		}
		if (formularioReserva.getFmail() == null || formularioReserva.getFmail().isEmpty()) {
			hayError = true;
			errorFmail = true;
			mensaje.append("El email es obligatorio. ");
		}
		if (formularioReserva.getFrepmail() == null || formularioReserva.getFrepmail().isEmpty()) {
			hayError = true;
			errorFrepmail = true;
			mensaje.append("Repetir email es obligatorio. ");
		}
		if (formularioReserva.getFdate() == null || formularioReserva.getFdate().isEmpty()) {
			hayError = true;
			errorFdate = true;
			mensaje.append("La fecha es obligatoria. ");
		}
		if (formularioReserva.getFhour() == null || formularioReserva.getFhour().isEmpty()) {
			hayError = true;
			errorFhour = true;
			mensaje.append("La hora es obligatoria. ");
		}
		if (hayError) {
			model.addAttribute("imgSelec", imgSelec);
			model.addAttribute("formularioReserva", formularioReserva);
			model.addAttribute("error", mensaje.toString());
			model.addAttribute("errorFnom", errorFnom);
			model.addAttribute("errorFapell", errorFapell);
			model.addAttribute("errorFmail", errorFmail);
			model.addAttribute("errorFrepmail", errorFrepmail);
			model.addAttribute("errorFdate", errorFdate);
			model.addAttribute("errorFhour", errorFhour);
			return "views/step2";
		}
		// Si todo está bien, pasar a step3
		model.addAttribute("formularioReserva", formularioReserva);
		return "redirect:/step3";
	}
}

