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
import com.mycompany.myapp.MyApplication;

/**
 *
 * @author SIRINE
 */
public class AddAffectation extends Form {
    
    private Form listAffectationForm;

    public AddAffectation() {
        super();
        Resources theme = UIManager.initFirstTheme("/theme");
        this.setLayout(BoxLayout.y());
        this.setTitle("Ajouter Affectation");                
        
        //construir la form
        TextField txtRemarque = new TextField("","Ajuter une remarque");
        Picker pkDate = new Picker();
        Button btnAjouter = new Button("Ajouter");
        
        //gerer la modification
        btnAjouter.addActionListener(x -> {
            Affectation afc = new Affectation(txtRemarque.getText(), new SimpleDateFormat("yyyy-MM-dd").format(pkDate.getDate()));
            Service.getInstance().ajouterAffectation(afc);
            listAffectationForm = new AffectationList();
            listAffectationForm.showBack();
        });
        
        this.addAll(txtRemarque, pkDate, btnAjouter);
    }
    
}
