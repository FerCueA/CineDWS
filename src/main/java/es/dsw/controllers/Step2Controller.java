package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import es.dsw.models.FormularioReserva;
import es.dsw.dao.SesionesSalasDAO;

@Controller
@SessionAttributes("formularioReserva")
public class Step2Controller {

	@ModelAttribute("formularioReserva")
	public FormularioReserva crearReserva() {
		return new FormularioReserva();
	}

	@GetMapping("/step2")
	public String step2(Model model,
			@ModelAttribute("formularioReserva") FormularioReserva formularioReserva,
			@RequestParam(required = false) Integer idSesion,
			@RequestParam(required = false) Integer idPelicula,
			@RequestParam(required = false) Integer numSala) {
		// Verificación: si no hay datos mínimos, redirigir al index
		
		if (formularioReserva.getIdSesion() == null || formularioReserva.getIdPelicula() == null
				|| formularioReserva.getNumSala() == null) {
			return "redirect:/index";
		}
		// Si los parámetros llegan, se actualizan los datos
		if (idPelicula != null && numSala != null && idSesion != null) {
			SesionesSalasDAO dao = new SesionesSalasDAO();
			String tituloPelicula = dao.getTituloPeliculaPorId(idPelicula);
			String rutaImagen = "/img/films/billboard/film" + idPelicula + ".jpg";

			formularioReserva.setImgSelec(rutaImagen);
			formularioReserva.setTituloPelicula(tituloPelicula);
			formularioReserva.setNumSala(numSala);
			formularioReserva.setIdSesion(idSesion);
			formularioReserva.setIdPelicula(idPelicula);

			model.addAttribute("tituloPelicula", tituloPelicula);
			model.addAttribute("rutaImagen", rutaImagen);
		} else {
			// Si no llegan parámetros, se usan los datos ya guardados en el
			// formularioReserva
			model.addAttribute("tituloPelicula", formularioReserva.getTituloPelicula());
			model.addAttribute("rutaImagen", formularioReserva.getImgSelec());
		}
		
		
		
		model.addAttribute("formularioReserva", formularioReserva);
		return "views/step2";
	}

	@PostMapping("/step2")
	public String procesarFormulario(
			@ModelAttribute("formularioReserva") FormularioReserva formularioReserva,
			Model model) {
		boolean hayError = false;
		StringBuilder mensajeError = new StringBuilder();

		// Validaciones de campos obligatorios
		if (formularioReserva.getFnom() == null || formularioReserva.getFnom().trim().isEmpty()) {
			model.addAttribute("errorFnom", true);
			hayError = true;
			mensajeError.append("El nombre es obligatorio.<br>");
		}
		if (formularioReserva.getFapell() == null || formularioReserva.getFapell().trim().isEmpty()) {
			model.addAttribute("errorFapell", true);
			hayError = true;
			mensajeError.append("Los apellidos son obligatorios.<br>");
		}
		if (formularioReserva.getFmail() == null || formularioReserva.getFmail().trim().isEmpty()) {
			model.addAttribute("errorFmail", true);
			hayError = true;
			mensajeError.append("El email es obligatorio.<br>");
		}
		if (formularioReserva.getFrepmail() == null || formularioReserva.getFrepmail().trim().isEmpty()) {
			model.addAttribute("errorFrepmail", true);
			hayError = true;
			mensajeError.append("Debe repetir el email.<br>");
		}
		if (formularioReserva.getFmail() != null && formularioReserva.getFrepmail() != null
				&& !formularioReserva.getFmail().equals(formularioReserva.getFrepmail())) {
			model.addAttribute("errorFrepmail", true);
			hayError = true;
			mensajeError.append("Los emails no coinciden.<br>");
		}
		if (formularioReserva.getFdate() == null || formularioReserva.getFdate().trim().isEmpty()) {
			model.addAttribute("errorFdate", true);
			hayError = true;
			mensajeError.append("La fecha es obligatoria.<br>");
		}
		if (formularioReserva.getFhour() == null || formularioReserva.getFhour().trim().isEmpty()) {
			model.addAttribute("errorFhour", true);
			hayError = true;
			mensajeError.append("La hora es obligatoria.<br>");
		}
		if (formularioReserva.getFnumentradasadult() <= 0) {
			model.addAttribute("errorFnumentradasadult", true);
			hayError = true;
			mensajeError.append("Debe indicar al menos un adulto.<br>");
		}

		if (hayError) {
			model.addAttribute("mensajeError", mensajeError.toString());
			model.addAttribute("formularioReserva", formularioReserva);
			return "views/step2";
		}

		model.addAttribute("formularioReserva", formularioReserva);
		return "redirect:/step3";
	}
}