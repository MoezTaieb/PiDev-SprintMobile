/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;


/**
 *
 * @author Moez
 */
public class Client extends Form{
     Form current;

     public Client(Form previous)
     {
             current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnList = new Button("List Evenemnt");
        Button btnMyEvent = new Button("Mes Events");
         btnList.addActionListener(e -> {
                 try {
                     new ListEventCl(current).show();
                 } catch (IOException ex) {
                   
                 }
             });
         btnMyEvent.addActionListener(e ->new MesEvents(current).show() );
             addAll(btnList, btnMyEvent);
          getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
     }
}
