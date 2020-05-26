/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affectations.GUI;

import Affectations.AffectationList;
import Affectations.Entitys.Affectation;
import Affectations.Services.Service;
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
public class EditAffectation extends Form {
    
    private Form listAffectationForm;

    public EditAffectation(Affectation a) {
        super();
        Resources theme = UIManager.initFirstTheme("/theme");
        this.setLayout(BoxLayout.y());
        this.setTitle("Gerer affectation");
        
        //construir la form
        TextField txtRemarque = new TextField(a.getRemarque());
        Picker pkDate = new Picker();
        Button btnSupprimer = new Button("Supprimer");
        Button btnModifier = new Button("Modifier");
        
        //gerer la suppression
        btnSupprimer.addActionListener(e -> {
            Service.getInstance().supprimerAffectation(a);
            listAffectationForm = new AffectationList();
            listAffectationForm.showBack();
        });
        
        //gerer la modification
        btnModifier.addActionListener(x -> {
            Affectation afc = new Affectation(a.getId(), txtRemarque.getText(), new SimpleDateFormat("yyyy-MM-dd").format(pkDate.getDate()));
            Service.getInstance().modifierAffectation(afc);
            listAffectationForm = new AffectationList();
            listAffectationForm.showBack();
        });
        
        this.addAll(txtRemarque, pkDate, btnModifier, btnSupprimer);
    }
    
}
