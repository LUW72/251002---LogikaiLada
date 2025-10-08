package test;

import model.LadaJatekModel;
import model.LadaModel;

public class LadaValasztasTest {

    public static void main(String[] args) {
        //letezoLadaE();
        pontEgyLada3Bol();

    }

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
}
