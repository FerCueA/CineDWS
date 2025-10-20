package es.dsw.models;



import org.springframework.ui.Model;

public class Step2Model {

   
    public static void modeloStep2(Model model, String imgSelec) {
        model.addAttribute("imgSelec", imgSelec);
    }
}
