package test;

import controller.LadaController;
import model.LadaJatekModel;
import model.LadaModel;
import view.LadaView;


public class LadaValasztasTest 
{
    
    public static void main(String[] args)
    {
        ladaMegfeleloVisszajelzesTest();
    }
    
    // Modell tesztek
    
    
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
