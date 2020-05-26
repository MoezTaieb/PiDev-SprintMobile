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
public class AddDemande extends Form {
    
    private Form listDemandeForm;

    public AddDemande() {
        super();
        Resources theme = UIManager.initFirstTheme("/theme");
        this.setLayout(BoxLayout.y());
        this.setTitle("Ajouter Demande");
        
        //construir la form
        TextField txtRemarque = new TextField("","ajouter une remarque");
        TextField txtEtat = new TextField("","ajouter un etat");
        TextField txtCas = new TextField("","ajouter un cas");
        Picker pkDate = new Picker();
        Button btnAjouter = new Button("Ajouter");
        
        //gerer la modification
        btnAjouter.addActionListener(x -> {
            Demande dmd = new Demande(txtRemarque.getText(), txtEtat.getText(), 
            txtCas.getText(), new SimpleDateFormat("yyyy-MM-dd").format(pkDate.getDate()));
            Service.getInstance().ajouterDemande(dmd);
            listDemandeForm = new DemandeList();
            listDemandeForm.showBack();
        });
        
        this.addAll(txtRemarque, txtEtat, txtCas, pkDate, btnAjouter);
    }
    
}
