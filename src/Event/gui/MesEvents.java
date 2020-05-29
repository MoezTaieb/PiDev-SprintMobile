/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import Event.entities.Evenement;
import Event.entities.Participation;
import Event.services.Service;

/**
 *
 * @author Moez
 */
public class MesEvents extends Form {
     Form current;
     public MesEvents(Form previous){
               current = this;
        setTitle("Mes Events");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
     
        int id=1;
           for (Participation f : Service.getInstance().getMyEvent(id)) {

            Container cnt1 = new Container(BoxLayout.y());

            Label lbId = new Label(" iD= " + f.getId());

            SpanLabel lbDesc = new SpanLabel(" Nom= " + f.getNomEvent());
            //lbDesc.setAutoSizeMode(true);
        
            Label lbDate = new Label(" Date= " + f.getDateParticipation());
            Label lbRate = new Label(" Lieu= " + f.getLieuEvent());
           
            SpanLabel lbSeparator = new SpanLabel(" \n ");

           

            Container cnt2 = new Container(BoxLayout.x());
            Button btnPartEvent = new Button("Annuler Event");
           
 cnt1.addAll(lbId, lbDesc, lbDate, lbRate, lbSeparator);
           cnt2.addAll( btnPartEvent);
          
           btnPartEvent.addActionListener((e) -> {
                try {
              
               if(  Service.getInstance().annulerEvent(f));
                  Dialog.show("Success", "Participation a ete annule", new Command("OK"));
                          new MesEvents(current).show();
                } catch (NumberFormatException err) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }
            });

            addAll(cnt1, cnt2);
        }
 
        
          getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
         
     }
}
