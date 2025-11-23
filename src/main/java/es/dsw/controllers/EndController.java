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

        // RETURN DE VERIFICACION MINIMA
        if (formularioReserva.getIdSesion() == null || formularioReserva.getIdPelicula() == null
                || formularioReserva.getNumSala() == null) {
            return "redirect:/index";
        }

        double precioAdulto = 6.0;
        double precioMenor = 3.5;
        double total = formularioReserva.getFnumentradasadult() * precioAdulto
                + formularioReserva.getFnumentradasmen() * precioMenor;

        BuyTicketsDAO compraDAO = new BuyTicketsDAO();
        int idCompra = compraDAO.insertarCompraYObtenerId(
                formularioReserva.getFnom(),
                formularioReserva.getFapell(),
                formularioReserva.getFmail(),
                formularioReserva.getTitular(),
                formularioReserva.getNumero(),
                formularioReserva.getMesCaduca(),
                formularioReserva.getAnioCaduca(),
                formularioReserva.getCcs(),
                (float) total);
        if (idCompra == -1) {
            return "redirect:/index";
        }

        TicketDAO ticketDAO = new TicketDAO();
        String[] butacas = formularioReserva.getButacasSeleccionadas().split(",");
        java.util.List<es.dsw.models.EntradaView> entradas = new java.util.ArrayList<>();
        for (int i = 0; i < butacas.length; i++) {
            boolean esMenor = i < formularioReserva.getFnumentradasmen();
            float precio = esMenor ? 3.5f : 6.0f;
            String serialCode = generarSerialUnico(idCompra, formularioReserva.getIdSesion(), butacas[i], i);
            ticketDAO.insertarTicket(
                    formularioReserva.getIdSesion(),
                    formularioReserva.getFdate(),
                    formularioReserva.getFhour(),
                    serialCode,
                    esMenor,
                    precio,
                    butacas[i],
                    idCompra);

            String fila = "";
            String butacaNum = butacas[i];
            if (butacaNum.matches("F\\d+B\\d+")) {
                String[] parts = butacaNum.substring(1).split("B");
                fila = parts[0];
                butacaNum = parts.length > 1 ? parts[1] : butacaNum;
            }
            entradas.add(new es.dsw.models.EntradaView(
                    serialCode,
                    formularioReserva.getTituloPelicula(),
                    String.valueOf(formularioReserva.getNumSala()),
                    formularioReserva.getFdate(),
                    formularioReserva.getFhour(),
                    fila,
                    butacaNum));
        }

        formularioReserva.setButacasSeleccionadas(String.join(",", butacas));
        model.addAttribute("entradas", entradas);
        model.addAttribute("formularioReserva", formularioReserva);
        model.addAttribute("qrGenerados", true);
        return "redirect:/end";

    }

    @GetMapping("/end")
    public String endGet(@ModelAttribute("formularioReserva") FormularioReserva formularioReserva,
            Model model) {
        // RETURN DE VERIFICACION MINIMA
        if (formularioReserva.getIdSesion() == null || formularioReserva.getIdPelicula() == null
                || formularioReserva.getNumSala() == null) {
            return "redirect:/index";
        }

        String[] butacas = formularioReserva.getButacasSeleccionadas() != null
                ? formularioReserva.getButacasSeleccionadas().split(",")
                : new String[0];

        java.util.List<es.dsw.models.EntradaView> entradas = new java.util.ArrayList<>();
        for (int i = 0; i < butacas.length; i++) {

            String serialCode = generarSerialUnico(
                    formularioReserva.getIdSesion() != null ? formularioReserva.getIdSesion() : 0,
                    formularioReserva.getIdSesion(),
                    butacas[i],
                    i);
            String fila = "";
            String butacaNum = butacas[i];

            if (butacaNum.matches("F\\d+B\\d+")) {
                String[] parts = butacaNum.substring(1).split("B");
                fila = parts[0];
                butacaNum = parts.length > 1 ? parts[1] : butacaNum;
            }
            entradas.add(new es.dsw.models.EntradaView(
                    serialCode,
                    formularioReserva.getTituloPelicula(),
                    String.valueOf(formularioReserva.getNumSala()),
                    formularioReserva.getFdate(),
                    formularioReserva.getFhour(),
                    fila,
                    butacaNum));
        }

        model.addAttribute("formularioReserva", formularioReserva);
        model.addAttribute("entradas", entradas);
        model.addAttribute("qrGenerados", true);

        return "views/end";
    }

    private String generarSerialUnico(int idCompra, Integer idSesion, String butaca, int index) {
        String base = String.format("%04d%04d%s%04d", idCompra, idSesion, butaca.replaceAll("[^A-Za-z0-9]", ""), index);
        String hash = Integer.toHexString(base.hashCode());
        String serial = (base + hash).replaceAll("[^A-Za-z0-9]", "");
        if (serial.length() < 16) {
            serial = String.format("%-16s", serial).replace(' ', 'X');
        }
        return serial.substring(0, 16);
    }
}
