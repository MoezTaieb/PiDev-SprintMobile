/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import Event.entities.Evenement;
import com.mycompany.myapp.entities.Feedback;
import Event.services.Service;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author LQss
 */
public class ListEvent extends Form {

    Form current;

    public ListEvent(Form previous) throws IOException {
        current = this;

        setTitle("List Event");
        for (Evenement f : Service.getInstance().getAllEvent()) {

            Container cnt1 = new Container(BoxLayout.y());

            Label lbId = new Label(" iD= " + f.getId());

            SpanLabel lbDesc = new SpanLabel(" Nom= " + f.getNomEvenement());
            //lbDesc.setAutoSizeMode(true);
            Label lbnbr = new Label(" nombre Participant= " + f.getNbrMaxParticipants());
            Label lbDate = new Label(" Date= " + f.getDateEvenement());
            Label lbRate = new Label(" Lieu= " + f.getLieuEvenement());
           
            SpanLabel lbSeparator = new SpanLabel(" \n ");

            Container cnt2 = new Container(BoxLayout.x());
            Button btnRemoveFeedback = new Button("Remove Event");
            Button btnFindFeedback = new Button("Afficher");
            
             String url = "http://127.0.0.1/pidev20/web/uploads/images/a/"+f.getImageEvenement();
 
            EncodedImage enc = EncodedImage.create("/twt.png");
      
            URLImage img = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
            ImageViewer imgV = new ImageViewer(img);
            
          
     
            
            
            
            Button btninvit = new Button("Ajouter Invite");
               Button btnlist = new Button("Liste des Invites");
                 Button btnup = new Button("Update");
                
            cnt1.addAll(lbId, lbDesc, lbDate, lbRate, lbSeparator,lbnbr, imgV);
            cnt2.addAll( btnRemoveFeedback,btninvit,btnlist,btnup);
            btnup.addActionListener(e -> new UpdateEvent(current, f).show());
            btninvit.addActionListener(e -> new AjoutInvite(current, f).show());
            btnlist.addActionListener(e -> new ListeInvite(current,f).show());
           btnRemoveFeedback.addActionListener((e) -> {
                try {
                    Feedback fb = new Feedback(f.getId());
                    if (Service.getInstance().removeEvent(f)) {
                        Dialog.show("Success", "Event  avec ID= " + f.getId() + " a ete supprimee avec succees", new Command("OK"));
                        new ListEvent(current).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException err) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                } catch (IOException ex) {
                  
                }
            });

            addAll(cnt1, cnt2);
        }
        if (previous.getTitle() == "Home") {
            getToolbar().addMaterialCommandToLeftBar("Home", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        } else {
            getToolbar().addMaterialCommandToLeftBar("Home", FontImage.MATERIAL_ARROW_DROP_UP, e -> new HomeForm().show());
            //getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK,e -> previous.showBack());

        }

    }
}
