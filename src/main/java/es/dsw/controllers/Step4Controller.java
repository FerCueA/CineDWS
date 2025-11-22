package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.dsw.models.FormularioReserva;

@SessionAttributes("formularioReserva")
@Controller
public class Step4Controller {
    @GetMapping("/step4")
    public String step4(@ModelAttribute("formularioReserva") FormularioReserva formularioReserva, Model model) {
       
        // mosstar datos en la consola
        System.out.println("FormularioReserva en Step4Controller:" + formularioReserva);
        
        // Lógica de cálculo de total
        double precioAdulto = 6.0; // Cambia según la lógica de tu actividad práctica 2
        double precioMenor = 3.5;
        double total = formularioReserva.getFnumentradasadult() * precioAdulto + formularioReserva.getFnumentradasmen() * precioMenor;

        model.addAttribute("formularioReserva", formularioReserva);
        model.addAttribute("total", total);
        return "views/step4";
    }
}
