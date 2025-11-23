package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.dsw.models.DatosCine;
import java.util.ArrayList;

import java.util.List;

import es.dsw.models.PeliculaSalaView;
import es.dsw.dao.SesionesSalasDAO;
import es.dsw.models.SesionSala;

@SessionAttributes("formularioReserva")
@Controller
public class Step1Controller {
    @GetMapping("/step1")
    public String step1(Model model) {
        DatosCine datosCine = DatosCine.calcularParaAhora();
        SesionesSalasDAO dao = new SesionesSalasDAO();
        List<SesionSala> sesiones = dao.getSesionesDisponibles();
        List<PeliculaSalaView> peliculas = new ArrayList<>();

        for (SesionSala sesion : sesiones) {
            String titulo = dao.getTituloPeliculaPorId(sesion.getIdPelicula());
            String rutaImagen = "/img/films/billboard/film" + sesion.getIdPelicula() + ".jpg";
            peliculas.add(new PeliculaSalaView(
                    sesion.getNumSala(),
                    sesion.getIdSesion(),
                    titulo,
                    datosCine.getPrecioEntrada(),
                    sesion.getIdPelicula(),
                    rutaImagen));
        }
        // Mezclar aleatoriamente las películas
        java.util.Collections.shuffle(peliculas);
        // Limitar el número de películas según el día
        int cantidad = DatosCine.getCantidadPeliculasPorDia();
        if (peliculas.size() > cantidad) {
            peliculas = peliculas.subList(0, cantidad);
        }
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("precioEntrada", datosCine.getPrecioEntrada());
        return "views/step1";
    }
}