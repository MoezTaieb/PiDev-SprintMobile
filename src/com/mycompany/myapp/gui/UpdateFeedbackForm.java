/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author LQss
 */
import Event.gui.ListEvent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Feedback;
import Event.services.Service;
import java.io.IOException;


public class UpdateFeedbackForm extends Form {

    Form current;

    public UpdateFeedbackForm(Form previous, Feedback fb) {
        current = this;

        setTitle("Update Feedback");
        setLayout(BoxLayout.y());

        TextField tfDescription = new TextField("", "Description Feedback:");

        Button btnValider = new Button("Update");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDescription.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Feedback f = new Feedback(fb.getId(), tfDescription.getText());
                        if (Service.getInstance().updateFeedback(f)) {
                            Dialog.show("Success", "Feedback with ID= " + fb.getId() + " sccessfully updated.", new Command("OK"));
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

        addAll(tfDescription, btnValider);
        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
