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
import com.mycompany.myapp.entities.Feedback;
import Event.services.Service;
import java.io.IOException;


/**
 *
 * @author Moez
 */
public class UpdateEvent extends Form{
    
    Form current;

    public UpdateEvent(Form previous, Evenement fb) {
        current = this;

        setTitle("Update Event");
        setLayout(BoxLayout.y());

         Picker dateev = new Picker();
        TextField nomev = new TextField("", "nomEv: ");
  TextField lieu = new TextField("", "lieu: ");
    TextField nbr = new TextField("", "nbr: ");
      TextField image = new TextField("", "image: ");
     
      nomev.setText(fb.getNomEvenement());
      dateev.setText(fb.getDateEvenement());
      lieu.setText(fb.getLieuEvenement());
      image.setText(fb.getImageEvenement());

        Button btnValider = new Button("Update ");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nomev.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                     Evenement E = new Evenement(fb.getId(),nomev.getText(), lieu.getText(), dateev.getValue().toString(),  (int) Float.parseFloat(nbr.getText()), image.getText());
                        if (Service.getInstance().updateEvent(E)) {
                            Dialog.show("Success", "Evenement with ID= " + fb.getId() + " sccessfully updated.", new Command("OK"));
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

        addAll(nomev,dateev,lieu,nbr,image, btnValider);
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
