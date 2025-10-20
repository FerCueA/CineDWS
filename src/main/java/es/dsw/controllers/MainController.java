package es.dsw.controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.dsw.models.FormularioReserva;
import es.dsw.models.IndexModel;
import es.dsw.models.Step1Model;
import es.dsw.models.Step2Model;

@Controller
public class MainController {

	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		LocalDateTime ahora = LocalDateTime.now();
		IndexModel.modeloIndex(model, ahora);
		return "index";
	}

	@GetMapping({ "/step1" })
	public String step1(Model model) {
		Step1Model.modeloStep1(model);
		return "views/step1";
	}

	@GetMapping("/step2")
	public String step2(
			@RequestParam(value ="imgSelec", required = false) String imgSelec,
			Model model) {

			if (imgSelec == null || imgSelec.isEmpty()) {
				return "redirect:/step1";
			}

		Step2Model.modeloStep2(model, imgSelec);
		return "views/step2";
	}

	@PostMapping({ "/step3" })
	public String step3(@ModelAttribute FormularioReserva formularioReserva) {

		//PRINT DE CONFIRMACION EN CONSOLA
		
		System.out.println("Nombre: " + formularioReserva.getFnom());
		System.out.println("Apellidos: " + formularioReserva.getFapell());
		System.out.println("Email: " + formularioReserva.getFmail());
		System.out.println("Repetir Email: " + formularioReserva.getFrepmail());
		System.out.println("Fecha: " + formularioReserva.getFdate());
		System.out.println("Hora: " + formularioReserva.getFhour());
		System.out.println("Adultos: " + formularioReserva.getFnumentradasadult());
		System.out.println("Menores: " + formularioReserva.getFnumentradasmen());
	
		
		return "views/step3";
	}


	


	@GetMapping({ "/step4" })
	public String step4() {
		return "views/step4";
	}

	@GetMapping({ "/end" })
	public String end() {
		return "views/end";
	}
}
