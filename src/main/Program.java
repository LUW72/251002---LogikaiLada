package main;

import controller.LadaController;
import model.LadaJatekModel;
import model.LadaModel;
import view.LadaView;

public class Program {

    public Program() {        
        LadaJatekModel model = new LadaJatekModel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LadaView view = new LadaView();
                LadaController controller = new LadaController(model, view);                
                view.setVisible(true);
                controller.feladat();
            }
        });
    }

    public static void main(String[] args) {
        new Program();
    }

}
