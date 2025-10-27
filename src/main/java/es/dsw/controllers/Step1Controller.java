package es.dsw.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.dsw.models.DatosCine;

@Controller
public class Step1Controller {

    @GetMapping("/step1")
    public String step1(Model model) {
        DatosCine datos = DatosCine.calcularParaAhora();
        model.addAttribute("datosCine", datos);

       
        java.time.DayOfWeek diaSemana = java.time.LocalDate.now().getDayOfWeek();
        int salasDisponibles;
        switch (diaSemana) {
            case MONDAY:
            case WEDNESDAY:
            case SUNDAY:
                salasDisponibles = 4;
                break;
            default:
                salasDisponibles = 7;
        }

        ArrayList<String> listaPeliculasTotales = new ArrayList<>();
        for (int i = 1; i <= 14; i++) {
            listaPeliculasTotales.add("film" + i + ".jpg");
        }
        if (salasDisponibles > listaPeliculasTotales.size()) {
            salasDisponibles = listaPeliculasTotales.size();
        }

        
        HashSet<Integer> indices = new HashSet<>();
        Random rnd = new Random();
        ArrayList<String> peliculasSeleccionadas = new ArrayList<>();
        while (indices.size() < salasDisponibles) {
            int k = rnd.nextInt(listaPeliculasTotales.size());
            if (indices.add(k)) {
                peliculasSeleccionadas.add(listaPeliculasTotales.get(k));
            }
        }

    model.addAttribute("peliculas", peliculasSeleccionadas);
    model.addAttribute("precioEntrada", datos.getPrecioEntrada());
    return "views/step1";
    }
}