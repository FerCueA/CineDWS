package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.dsw.models.DatosCine;

@Controller
@SessionAttributes("datosCine")
public class IndexController {
    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        DatosCine datos = DatosCine.calcularParaAhora();
        model.addAttribute("datosCine", datos);
        return "index";
    }
}