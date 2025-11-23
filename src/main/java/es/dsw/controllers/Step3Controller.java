package es.dsw.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.dsw.models.FormularioReserva;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("formularioReserva")
public class Step3Controller {

    @GetMapping("/step3")
    public String step3Get(@ModelAttribute("formularioReserva") FormularioReserva formularioReserva,
            Model model) {

        // RETURN DE VERIFICACION MINIMA
        if (formularioReserva.getIdSesion() == null || formularioReserva.getIdPelicula() == null
                || formularioReserva.getNumSala() == null) {
            return "redirect:/index";
        }

        model.addAttribute("formularioReserva", formularioReserva);
        return "views/step3";
    }

    @PostMapping("/step4")
    public String step3Post(@ModelAttribute("formularioReserva") FormularioReserva formularioReserva,
            @RequestParam("FButacasSelected") String butacasSeleccionadas,
            Model model) {

        int totalButacas = formularioReserva.getFnumentradasadult() + formularioReserva.getFnumentradasmen();

        butacasSeleccionadas = butacasSeleccionadas.replace(";", ",").replaceAll(",$", "");
        String[] butacas = butacasSeleccionadas.split(",");

        if (butacasSeleccionadas == null || butacasSeleccionadas.trim().isEmpty() || butacas.length != totalButacas) {
            model.addAttribute("errorButacas", "Debes seleccionar exactamente " + totalButacas + " butacas.");
            model.addAttribute("formularioReserva", formularioReserva);
            return "views/step3";
        }
        formularioReserva.setButacasSeleccionadas(butacasSeleccionadas);
        model.addAttribute("formularioReserva", formularioReserva);
        return "redirect:/step4";
    }

    @GetMapping("/step3/atras")
    public String volverAtrasStep2() {
        return "redirect:/step2";
    }
}
