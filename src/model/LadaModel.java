package model;

public class LadaModel {

    private String szin;
    private boolean kincs;

    public LadaModel(String szin, boolean kincs) {
        this.szin = szin;
        this.kincs = kincs;
    }

    public String getSzin() {
        return szin;
    }

    public boolean isKincs() {
        return kincs;
    }

    public void setSzin(String szin)
    {
        this.szin = szin;
    }

    public void setKincs(boolean kincs)
    {
        this.kincs = kincs;
    }
    
    

}
