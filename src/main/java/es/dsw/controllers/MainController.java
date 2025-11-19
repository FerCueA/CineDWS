package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/step4")
    public String step4() {
        return "views/step4";
    }

    @GetMapping("/end")
    public String endGet() {
        return "views/end";
    }

    // mapping para mostar si funciona la base de datos correctamente, para hacer
    // una prueba en peliculas.html
    @GetMapping("/peliculas")
    public String mostrarPeliculas(Model model) {
        es.dsw.dao.PeliculasDAO dao = new es.dsw.dao.PeliculasDAO();
        java.util.List<es.dsw.models.PeliculasBD> lista = dao.obtenerPeliculasActivas();
        model.addAttribute("peliculas", lista);
        return "peliculas";

    }

}
