package es.dsw.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.springframework.ui.Model;

public class Step1Model {

    public static void modeloStep1(Model model) {


        LocalDate fechaLocal = LocalDate.now();
        DayOfWeek diaSemana = fechaLocal.getDayOfWeek();

        double precioEntrada = 6.00;
        int salasDisponibles = 7;

        switch (diaSemana) {
            case MONDAY:
                salasDisponibles = 4;
                break;
            case TUESDAY:
                break;
            case WEDNESDAY:
                salasDisponibles = 4;
                precioEntrada = 3.50;
                break;
            case THURSDAY:
                break;
            case FRIDAY:
                break;
            case SATURDAY:
                break;
            case SUNDAY:
                break;
            default:
                
        }

        var listaPeliculasTotales = new ArrayList<String>();
        for (int i = 1; i <= 14; i++) {
            listaPeliculasTotales.add("film" + i + ".jpg");
        }

        if (salasDisponibles > listaPeliculasTotales.size()) {
            salasDisponibles = listaPeliculasTotales.size();
        }

       
        var indices = new HashSet<Integer>();
        var rnd = new Random();
        var peliculasSeleccionadas = new ArrayList<String>();

        while (indices.size() < salasDisponibles) {
            int k = rnd.nextInt(listaPeliculasTotales.size());
            if (indices.add(k)) {
                peliculasSeleccionadas.add(listaPeliculasTotales.get(k));
            }
        }

       
        model.addAttribute("peliculas", peliculasSeleccionadas);
        model.addAttribute("precioEntrada", precioEntrada);
    }
}