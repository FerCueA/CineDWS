package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import es.dsw.models.FormularioReserva;

@Controller
public class Step4Controller {
    @GetMapping("/step4")
    public String step4(@ModelAttribute("formularioReserva") FormularioReserva formularioReserva, Model model) {
        // Verificación: si no hay datos mínimos, redirigir al index
        if (formularioReserva.getIdSesion() == null || formularioReserva.getIdPelicula() == null || formularioReserva.getNumSala() == null) {
            return "redirect:/index";
        }
        // Lógica de cálculo de total
        double precioAdulto = 6.0; // Cambia según la lógica de tu actividad práctica 2
        double precioMenor = 3.5;
        double total = formularioReserva.getFnumentradasadult() * precioAdulto + formularioReserva.getFnumentradasmen() * precioMenor;

        model.addAttribute("formularioReserva", formularioReserva);
        model.addAttribute("total", total);
        return "views/step4";
    }
}
