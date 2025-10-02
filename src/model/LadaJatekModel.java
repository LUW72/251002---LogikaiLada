package model;

import java.util.ArrayList;
import java.util.List;


public class LadaJatekModel 
{

    private List<LadaModel> ladak;
    private LadaModel valasztott; 

    public LadaJatekModel() {
        ladak = new ArrayList<>();
        
        ladak.add(new LadaModel("arany", false));
        ladak.add(new LadaModel("ezüst", true));
        ladak.add(new LadaModel("bronz", false));
    }

    public List<LadaModel> getLadak() 
    {
        return ladak;
    }

    public void setValasztott(LadaModel lada) 
    {
        this.valasztott = lada;
    }

    public LadaModel getValasztott() {
        return valasztott;
    }

    public LadaModel getKincsesLada() 
    {
        return ladak.get(1);
    }
}

