package es.dsw.controllers;

import es.dsw.models.FormularioReserva;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Step3Controller {

    @PostMapping("/step3")
    public String step3(@ModelAttribute FormularioReserva formularioReserva, Model model) {
        // Print de los datos recibidos en consola para depuración
        System.out.println("---- DATOS FORMULARIO ----");
        System.out.println("Nombre: " + formularioReserva.getFnom());
        System.out.println("Apellidos: " + formularioReserva.getFapell());
        System.out.println("Email: " + formularioReserva.getFmail());
        System.out.println("Repetir Email: " + formularioReserva.getFrepmail());
        System.out.println("Fecha: " + formularioReserva.getFdate());
        System.out.println("Hora: " + formularioReserva.getFhour());
        System.out.println("Adultos: " + formularioReserva.getFnumentradasadult());
        System.out.println("Menores: " + formularioReserva.getFnumentradasmen());
        System.out.println("--------------------------");
        model.addAttribute("formularioReserva", formularioReserva);
        return "views/step3";
    }
}
