/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;

    public HomeForm() {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnListFeedback = new Button("List Feedback");
        Button btnAddFeedback = new Button("Add Feedback");
        Button btnAddEvent = new Button("Add Event");
        Button btnListEvent= new Button ("liste EVENT");
         Button btnfront= new Button ("Client");

     
        btnListEvent.addActionListener(e -> {
            try {
                new ListEvent(current).show();
            } catch (IOException ex) {
              
            }
        });
       // btnListFeedback.addActionListener(e -> new FindFeedbackForm(current).show());
          btnAddEvent.addActionListener(e -> new AddEvent(current).show());
             btnfront.addActionListener(e -> new Client(current).show());
        addAll(btnListFeedback, btnAddFeedback,btnListEvent,btnAddEvent,btnfront);

    }

}
