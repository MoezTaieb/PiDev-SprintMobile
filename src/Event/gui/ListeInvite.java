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
import java.io.IOException;

/**
 *
 * @author Moez
 */
public class ListeInvite extends Form{
    Form current ;
       public ListeInvite(Form previous,Evenement ev ) {
        current = this;

        setTitle("Liste des invites");
       

            Container cnt1 = new Container(BoxLayout.y());
            Label lbId = new Label(" iD= "+ev.getId());
         for (Invite f : Service.getInstance().getInviteByid(ev.getId())) {

          
           
            SpanLabel lbDesc = new SpanLabel(" Nm invite= " + f.getNomInvite());
            Label lbDate = new Label(" Prenom invite= " + f.getPrenomInvite());
          
            Button btnSuppInvite= new Button("Supprimer Invite");
            Button btnModifInvit= new Button("Modifier invité");

             btnSuppInvite.addActionListener((e) -> {
                try {
                  
                    if (Service.getInstance().removeInvite(f)) {
                        Dialog.show("Success", "Feedback avec ID= " + f.getId() + " a ete supprimee avec succees", new Command("OK"));
                        new ListEvent(current).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException err) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                } catch (IOException ex) {
                  
                }
            });
             
             btnModifInvit.addActionListener(e -> new UpdateInvit(current,f).show());
            
            
            cnt1.addAll( lbDesc, lbDate, btnSuppInvite,btnModifInvit);
            
           // btnUpdateFeedback.addActionListener(e -> new UpdateFeedbackForm(current, fb).show());

           
        }


           
          

            addAll(cnt1);
        

        getToolbar().addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
