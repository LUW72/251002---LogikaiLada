package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.LadaModel;
import view.LadaView;


public class LadaController 
{
    private LadaModel model;
    private LadaView view;

    public LadaController(LadaModel model, LadaView view) 
    {
        this.model = model;
        this.view = view;
        view.setVisible(true);
    }
    
    public void feladat()
    {
        view.getBtnValasztott().addActionListener((ActionEvent e) ->
        {
            String kimenet = "";
            if (view.getRadioBtnArany().isSelected())
            {
                kimenet = "";
                
            }
            else if(view.getRadioBtnEzust().isSelected())
            {
                kimenet = "";
            }
            else
            {
                kimenet = "";
            }
            view.setTxtFieldMegoldas(kimenet);
        });
    }
    
}
