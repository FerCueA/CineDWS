
package es.dsw.controllers;

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

        // PRINT PARA VERIFICAR QUE LLEGAN BIEN LOS DATOS
        System.out.println("---------------------------------------");
        System.out.println("Nombre: " + formularioReserva.getFnom());
        System.out.println("Apellidos: " + formularioReserva.getFapell());
        System.out.println("Email: " + formularioReserva.getFmail());
        System.out.println("Repetir Email: " + formularioReserva.getFrepmail());
        System.out.println("Fecha: " + formularioReserva.getFdate());
        System.out.println("Hora: " + formularioReserva.getFhour());
        System.out.println("Adultos: " + formularioReserva.getFnumentradasadult());
        System.out.println("Menores: " + formularioReserva.getFnumentradasmen());
        System.out.println("Imagen seleccionada: " + formularioReserva.getImgSelec());
        System.out.println("---------------------------------------");

        model.addAttribute("formularioReserva", formularioReserva);
        return "views/step3";
    }

    @GetMapping("/step3/atras")
    public String volverAtrasStep2() {
        return "redirect:/step2";
    }
}
