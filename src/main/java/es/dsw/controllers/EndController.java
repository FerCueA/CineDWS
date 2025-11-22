package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import es.dsw.models.FormularioReserva;
import es.dsw.dao.BuyTicketsDAO;
import es.dsw.dao.TicketDAO;

@Controller
@SessionAttributes({ "formularioReserva" })
public class EndController {
    @PostMapping("/end")
    public String endPost(@ModelAttribute("formularioReserva") FormularioReserva formularioReserva,
            Model model) {
        // Verificación: si no hay datos mínimos, redirigir al index
        if (formularioReserva.getIdSesion() == null || formularioReserva.getIdPelicula() == null
                || formularioReserva.getNumSala() == null) {
            return "redirect:/index";
        }
        // Calcular el total
        double precioAdulto = 6.0;
        double precioMenor = 3.5;
        double total = formularioReserva.getFnumentradasadult() * precioAdulto
                + formularioReserva.getFnumentradasmen() * precioMenor;
        // Registrar la compra
        BuyTicketsDAO compraDAO = new BuyTicketsDAO();
        boolean compraOk = compraDAO.insertarCompra(
                formularioReserva.getFnom(),
                formularioReserva.getFapell(),
                formularioReserva.getFmail(),
                formularioReserva.getTitular(),
                formularioReserva.getNumero(),
                formularioReserva.getMesCaduca(),
                formularioReserva.getAnioCaduca(),
                formularioReserva.getCcs(),
                (float) total);
        // Suponiendo que el ID de la compra se puede recuperar (aquí solo ejemplo)
        int idCompra = 1; // Debes obtener el ID real generado

        // Registrar los tickets (uno por cada butaca) y generar lista de QR
        TicketDAO ticketDAO = new TicketDAO();
        String[] butacas = formularioReserva.getButacasSeleccionadas().split(",");
        java.util.List<String> qrCodes = new java.util.ArrayList<>();
        for (int i = 0; i < butacas.length; i++) {
            boolean esMenor = i < formularioReserva.getFnumentradasmen();
            float precio = esMenor ? 3.5f : 6.0f;
            String serialCode = generarCodigoQR(formularioReserva, butacas[i]);
            qrCodes.add(serialCode);
            ticketDAO.insertarTicket(
                    formularioReserva.getIdSesion(),
                    formularioReserva.getFdate(),
                    formularioReserva.getFhour(),
                    serialCode,
                    esMenor,
                    precio,
                    butacas[i],
                    idCompra);
        }

        // Guardar los QR en el formulario para el GET
        formularioReserva.setButacasSeleccionadas(String.join(",", butacas));
        model.addAttribute("qrCodes", qrCodes);
        model.addAttribute("formularioReserva", formularioReserva);
        model.addAttribute("qrGenerados", true);
        return "redirect:/end";

    }

    @GetMapping("/end")
    public String endGet(@ModelAttribute("formularioReserva") FormularioReserva formularioReserva,
            Model model) {
        // Verificación: si no hay datos mínimos, redirigir al index
        if (formularioReserva.getIdSesion() == null || formularioReserva.getIdPelicula() == null
                || formularioReserva.getNumSala() == null) {
            return "redirect:/index";
        }
        // Generar lista de QR si no existe
        String[] butacas = formularioReserva.getButacasSeleccionadas() != null
                ? formularioReserva.getButacasSeleccionadas().split(",")
                : new String[0];
        java.util.List<String> qrCodes = new java.util.ArrayList<>();
        for (int i = 0; i < butacas.length; i++) {
            qrCodes.add(generarCodigoQR(formularioReserva, butacas[i]));
        }
        model.addAttribute("formularioReserva", formularioReserva);
        model.addAttribute("qrCodes", qrCodes);
        model.addAttribute("qrGenerados", true);
        return "views/end";
    }

    // Método para generar el código QR (simulado)
    private String generarCodigoQR(FormularioReserva reserva, String butaca) {
        // Aquí deberías usar una librería real de QR, pero para ejemplo:
        return "QR-" + reserva.getFmail() + "-" + butaca + "-" + System.currentTimeMillis();
    }
}
