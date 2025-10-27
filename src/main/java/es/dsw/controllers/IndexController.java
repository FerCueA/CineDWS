package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import es.dsw.models.DatosCine;

@Controller
public class IndexController {
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        DatosCine datos = DatosCine.calcularParaAhora();
        model.addAttribute("horaLocal", datos.getHoraLocal());
        model.addAttribute("fechaLocal", datos.getFechaLocal());
        model.addAttribute("mensajeSemana", datos.getMensajeSemana());
        model.addAttribute("precioEntrada", datos.getPrecioEntrada());
        return "index";
    }
}