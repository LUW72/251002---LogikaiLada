package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.LadaJatekModel;
import model.LadaModel;
import view.LadaView;

public class LadaController {

    private LadaJatekModel model;
    private LadaView view;

    private ArrayList<LadaModel> ladak;

    public LadaController(LadaJatekModel model, LadaView view) {
        this.model = model;
        this.view = view;
        view.setVisible(true);
    }

    public void feladat() {
        valasztGomb();
        megoldGomb();
    }

    private void valasztGomb() {
        // Beállítjuk a model adattagjait
        view.getBtnValasztott().addActionListener((ActionEvent e)
                -> {
            if (view.getRadioBtnArany().isSelected()) {
                model.setValasztott(model.getLadak().get(0));
            } else if (view.getRadioBtnEzust().isSelected()) {
                model.setValasztott(model.getLadak().get(1));
            } else if (view.getRadioBtnBronz().isSelected()) {
                model.setValasztott(model.getLadak().get(2));
            }
            view.setLblBiztos("Biztos vagy benne?");
        });
    }

    private void megoldGomb() {
        // Lekérjük az adattagokat, majd beállítjuk a szöveget
        view.getBtnMegoldas().addActionListener((ActionEvent e)
                -> {
            LadaModel kincses = model.getKincsesLada();

            view.setLblBiztos("");
            String kiir = "";
            if (model.getValasztott() == "ezüst") {
                kiir = "Nyertél!\n";
            } else {
                kiir = "Nem talált!\n";
            }
            kiir += "A kincs a(z) " + kincses.getSzin() + " ládában van!";

            view.setTxtFieldMegoldas(kiir);

        });
    }

    
}
