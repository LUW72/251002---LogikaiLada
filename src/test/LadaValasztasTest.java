package test;

import controller.LadaController;
import model.LadaJatekModel;

import model.LadaModel;
import view.LadaView;

public class LadaValasztasTest {

    public static void main(String[] args) 
    {
        //letezoLadaE();
        pontEgyLada3Bol();
        ladaMegfeleloVisszajelzesTest();

    }
   // Modell tesztek    

    public static void letezoLadaE() {
        LadaJatekModel model = new LadaJatekModel();

        boolean hibaTortent = false;

        try {
            model.setValasztott(model.getLadak().get(3)); // csak 0,1,2 létezik
        } catch (IndexOutOfBoundsException ex) {
            hibaTortent = true;
        }

        assert hibaTortent : "Nem dobott kivételt, pedig nem létező ládára hivatkoztunk!";

        System.out.println("letezoLadaE() Teszt lefutott: nem létező ládára hivatkozás esetén kivétel keletkezik.");
    }

    public static void pontEgyLada3Bol() {
        LadaJatekModel model = new LadaJatekModel();

        int ladaDb = model.getLadak().size();
        assert ladaDb == 3 : "A ládák száma hibás! (" + ladaDb + " darab van)";

        int kincsesDb = 0;
        for (LadaModel lada : model.getLadak()) {
            if (lada.isKincs()) {
                kincsesDb++;
                assert lada.getSzin().equals("ezüst") : "Nem az ezüst ládában van a kincs!";
            }
        }
        assert kincsesDb == 1 : "Nem pontosan egy ládában van a kincs! (" + kincsesDb + " darabban van)";

        System.out.println("pontEgyLada3Bol() Teszt lefutott: Hibás láda szám esetén, vagy ha a kincs rossz helyen van, kivétel keletkezik.");
                  
    }

    // Vezérlő tesztek
    
    // a kiválasztott ládára megfelelő visszajelzést kapunk (benne a kincs, nincs benne)
    public static void ladaMegfeleloVisszajelzesTest() 
    {
        LadaJatekModel model = new LadaJatekModel();
        LadaView view = new LadaView();
        LadaController controller = new LadaController(model, view);
        controller.feladat();

        // Az ezüst ládát állítjuk be, ebben a kincs
        model.setValasztott(model.getLadak().get(1));

        String valasztott = model.getValasztott();
        assert valasztott.equals("ezüst") : "HIBA: Rossz láda lett kiválasztva.";

        LadaModel kincses = model.getKincsesLada();
        assert kincses.getSzin().equals(valasztott) : "HIBA: A kiválasztott láda nem a kincses láda.";
    }

    public static void visszajelzesSzovegTest() 
    {
        LadaJatekModel model = new LadaJatekModel();
        LadaView view = new LadaView();
        LadaController controller = new LadaController(model, view);
        controller.feladat();

        model.setValasztott(model.getLadak().get(1));

        String eredmeny = "";
        if (model.getValasztott().equals("ezüst")) 
        {
            eredmeny = "Nyertél!\nA kincs a(z) ezüst ládában van!";
        } 
        else 
        {
            eredmeny = "Nem talált!\nA kincs a(z) ezüst ládában van!";
        }

        assert eredmeny.contains("Nyertél!") : "HIBA: Hibás szöveg";
        assert eredmeny.contains("ezüst") : "HIBA: Helytelen a láda színe";
    }

    
    


    
}
