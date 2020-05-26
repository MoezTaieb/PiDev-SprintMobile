/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demandes.GUI;

import Demandes.Services.Service;
import Demandes.DemandeList;
import Demandes.Entitys.Demande;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

/**
 *
 * @author SIRINE
 */
public class EditDemande extends Form {
    
    private Form listDemandeForm;

    public EditDemande(Demande d) {
        super();
        Resources theme = UIManager.initFirstTheme("/theme");
        this.setLayout(BoxLayout.y());
        this.setTitle("Gerer la demande");
        
        //construir la form
        TextField txtRemarque = new TextField(d.getRemarque());
        TextField txtEtat = new TextField(d.getEtat());
        TextField txtCas = new TextField(d.getCas());
        Picker pkDate = new Picker();
        Button btnSupprimer = new Button("Supprimer");
        Button btnModifier = new Button("Modifier");
        
        //gerer la suppression
        btnSupprimer.addActionListener(e -> {
            Service.getInstance().supprimerDemande(d);
            listDemandeForm = new DemandeList();
            listDemandeForm.showBack();
        });
        
        //gerer la modification
        btnModifier.addActionListener(x -> {
            Demande dmd = new Demande(d.getId(), txtRemarque.getText(), txtEtat.getText(), 
            txtCas.getText(), new SimpleDateFormat("yyyy-MM-dd").format(pkDate.getDate()));
            Service.getInstance().modifierDemande(dmd);
            listDemandeForm = new DemandeList();
            listDemandeForm.showBack();
        });
        
        this.addAll(txtRemarque, txtEtat, txtCas, pkDate, btnModifier, btnSupprimer);
    }
    
}
