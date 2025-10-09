package test;

import controller.LadaController;
import model.LadaJatekModel;

import model.LadaModel;
import view.LadaView;

public class LadaValasztasTest {

    public static void main(String[] args) 
    {
        letezoLadaE();
//        pontEgyLada3Bol();
//        ladaMegfeleloVisszajelzesTest();
//        visszajelzesSzovegTest();

    }
   // Modell tesztek    

    public static void letezoLadaE() {
        LadaJatekModel model = new LadaJatekModel();

        boolean nemTortentHiba = false;

        try {
            model.getLadak().get(2); // csak 0,1,2 létezik
        } catch (IndexOutOfBoundsException ex) {
            nemTortentHiba = true;
        }

        assert nemTortentHiba : "Nem dobott kivételt, pedig nem létező ládára hivatkoztunk!";

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
        
        System.out.println("A ladaMegfeleloVisszajelzesTest() hiba nélkül lefutott");
    }

    public static void visszajelzesSzovegTest() 
    {
        LadaJatekModel model = new LadaJatekModel();
        LadaView view = new LadaView();
        LadaController controller = new LadaController(model, view);
        controller.feladat();

        // Választott 1 ->
        model.setValasztott(model.getLadak().get(1));
        // Ezüstben a kincs
        LadaModel kincses = model.getKincsesLada();

        // "A kincs a(z) " + kincses.getSzin() + " ládában van!";
        String eredmeny = "";
        if (model.getValasztott().equals("")) 
        {
            eredmeny = "Nyertél!\nA kincs a(z) %s ládában van!".formatted(kincses.getSzin());
        } 
        else 
        {
            eredmeny = "Nem talált!\nA kincs a(z) %s ládában van!".formatted(kincses.getSzin());
        }

        assert eredmeny.contains("Nyertél!") : "HIBA: Hibás szöveg";
        assert eredmeny.contains("ezüst") : "HIBA: Helytelen a láda színe";
        
        System.out.println("A visszajelzesSzovegTest() hiba nélkül lefutott");
    }

    
    


    
}
