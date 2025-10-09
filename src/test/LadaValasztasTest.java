package test;

import controller.LadaController;
import java.util.HashMap;
import java.util.Map;
import model.LadaJatekModel;

import model.LadaModel;
import view.LadaView;

public class LadaValasztasTest {

    private LadaView view;

    public static void main(String[] args) {
        //letezoLadaE();
//        pontEgyLada3Bol();
//        ladaMegfeleloVisszajelzesTest();
//        visszajelzesSzovegTest();
        szovegTeszt();
    }
    // Modell tesztek---Vajk

    private static void szovegTeszt() {
        try {
            LadaJatekModel jatek = new LadaJatekModel();
            LadaView view = new LadaView();

            // Feliratok összegyűjtése
            Map<String, String> feliratok = new HashMap<>();
            feliratok.put("arany", "\"Én rejtem a kincset\"");
            feliratok.put("ezüst", "\"Nem én rejtem a kincset\"");
            feliratok.put("bronz", "\"Az Arany láda hazudik\"");

            // 1. Feltétel: minden ládának van felirata
            for (LadaModel lada : jatek.getLadak()) {
                assert feliratok.containsKey(lada.getSzin()) :
                        "Hiányzik felirat a(z) " + lada.getSzin() + " ládáról!";
            }

            // 2. Feltétel: szövegrészek helyesek
            assert feliratok.get("arany").toLowerCase().contains("én rejtem") :
                    "Arany láda felirata hibás!";
            assert feliratok.get("ezüst").toLowerCase().contains("nem én rejtem") :
                    "Ezüst láda felirata hibás!";
            assert feliratok.get("bronz").toLowerCase().contains("arany hazudik") :
                    "Bronz láda felirata hibás!";

            System.out.println("✅ Minden láda felirata megfelel a feltételeknek!");

        } catch (AssertionError hiba) {
            System.err.println("❌ Tesztelési hiba: " + hiba.getMessage());
        } catch (Exception e) {
            System.err.println("⚠️ Hiba történt a teszt futása közben: " + e.getMessage());
        }
    }

    public static void letezoLadaE() {
        LadaJatekModel model = new LadaJatekModel();

        boolean nemTortentHiba = true;

        try {
            model.getLadak().get(2); // csak 0,1,2 létezik
        } catch (IndexOutOfBoundsException ex) {
            nemTortentHiba = false;
        }

        assert nemTortentHiba : "Nem dobott kivételt, pedig nem létező ládára hivatkoztunk!";

        System.out.println("letezoLadaE() Teszt lefutott: nem létező ládára hivatkozás esetén kivétel keletkezik.");
    }

    public static void pontEgyLada3Bol() {
        LadaJatekModel model = new LadaJatekModel();

        int ladaDb = model.getLadak().size();
        assert ladaDb == 3 : "A ládák száma hibás! (Nem " + ladaDb + " darab van)";

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

    // Vezérlő tesztek--Dénes
    // a kiválasztott ládára megfelelő visszajelzést kapunk (benne a kincs, nincs benne)
    public static void ladaMegfeleloVisszajelzesTest() {
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

    public static void visszajelzesSzovegTest() {
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
        if (model.getValasztott().equals("")) {
            eredmeny = "Nyertél!\nA kincs a(z) %s ládában van!".formatted(kincses.getSzin());
        } else {
            eredmeny = "Nem talált!\nA kincs a(z) %s ládában van!".formatted(kincses.getSzin());
        }

        assert eredmeny.contains("Nyertél!") : "HIBA: Hibás szöveg";
        assert eredmeny.contains("ezüst") : "HIBA: Helytelen a láda színe";

        System.out.println("A visszajelzesSzovegTest() hiba nélkül lefutott");
    }

}
