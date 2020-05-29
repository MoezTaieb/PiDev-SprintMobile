/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import Event.entities.Evenement;
import Event.entities.Invite;
import Event.services.Service;
import java.io.IOException;


/**
 *
 * @author Moez
 */
public class UpdateInvit extends Form {
     Form current;
    public UpdateInvit(Form previous, Invite i) {
        current = this;
        
        setTitle("Update Event");
        setLayout(BoxLayout.y());

        
    TextField nomI = new TextField("", "nom Invite : ");
      TextField prenomI = new TextField("", "prenom Invite : ");
     
      nomI.setText(i.getNomInvite());
      prenomI.setText(i.getPrenomInvite());
      

        Button btnValider = new Button("Update ");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nomI.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                     Invite I = new Invite(i.getId(),nomI.getText(), prenomI.getText() );
                        if (Service.getInstance().updateInvite(I)) {
                            Dialog.show("Success", "Invite  with ID= " + i.getId() + " sccessfully updated.", new Command("OK"));
                            new ListEvent(current).show();

                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    } catch (IOException ex) {
                   
                    }

                }

            }
        });

        addAll(nomI,prenomI, btnValider);
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
    
    
}
