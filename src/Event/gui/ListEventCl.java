/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import Event.entities.Evenement;
import com.mycompany.myapp.entities.Feedback;
import Event.entities.Participation;
import Event.services.Service;
import java.io.IOException;

/**
 *
 * @author Moez
 */
public class ListEventCl extends Form {
       Form current;

    public ListEventCl(Form previous) throws IOException {
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

                 String url = "http://127.0.0.1/pidev20/web/uploads/images/a/"+f.getImageEvenement();
 
            EncodedImage enc = EncodedImage.create("/twt.png");
      
            URLImage img = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
            ImageViewer imgV = new ImageViewer(img);
                  Image imqr ;
                     
        
                   
                     
                   imqr=  Qrcode.qrcode(f.getNomEvenement());
          
                     
             ImageViewer imgr = new ImageViewer(imqr);

            Container cnt2 = new Container(BoxLayout.x());
            Button btnPartEvent = new Button("Participer Event");
           
 cnt1.addAll(lbId, lbDesc, lbDate, lbRate, lbSeparator,lbnbr,imgV,imgr);
           cnt2.addAll( btnPartEvent);
             
                    int user = 1;
           btnPartEvent.addActionListener((e) -> {
                try {int v = 0;
                    
                     for (Participation i : Service.getInstance().verifEvent(f.getId(),user)) {
                     v++;
                       
                         
                     }
                     System.out.println("3"+v);
                     if(v>0)
                     {
                           Dialog.show("error", "vous etes deja participant", new Command("OK"));
                     
                     }
                     else  if(f.getNbrMaxParticipants()==0){
                          Dialog.show("Success", "3abinaa !", new Command("OK"));
                     }
                     else{
                                   
                    
                    
                    
                
                 Service.getInstance().ParticiperEvent(user, f.getId());
           
             
                  Service.getInstance().updateEventt(f.getId(),f.getNbrMaxParticipants()-1);
                  Dialog.show("Success", "Participation a ete affectué", new Command("OK"));
                    new ListEventCl(current).show();
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
