/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Event.entities.Evenement;
import com.mycompany.myapp.entities.Feedback;
import Event.entities.Invite;
import Event.services.Service;

/**
 *
 * @author Moez
 */
public class AjoutInvite extends Form{
    
    Form current;

    public AjoutInvite(Form previous, Evenement fb) {
        current = this;

        setTitle("Ajouter invite");
       

            Container cnt1 = new Container(BoxLayout.y());
            Label lbId = new Label(" iD= " + fb.getId());
            TextField nomev = new TextField("", "nom invite: ");
      TextField prenev = new TextField("", "prenom nvite: ");
        Button btnValider = new Button("Add Invite");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ( (nomev.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                   
                       
             Invite i = new Invite(fb.getId(), nomev.getText(), prenev.getText());
                       
                       
                        
                       
                       
                    try {
                        if (Service.getInstance().AddInvite(i, fb.getId())) {
                            
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (ParseException ex) {
                        System.out.println("aaaaa");
                    }
                        
                        
                  

                }

            }
        });


            cnt1.addAll(lbId,nomev,prenev,btnValider);
          

            addAll(cnt1);
        

        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
