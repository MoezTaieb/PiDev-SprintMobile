/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Affectations;

import Affectations.Entitys.Affectation;
import Affectations.GUI.AddAffectation;
import Affectations.GUI.EditAffectation;
import Affectations.Services.Service;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Login;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author SIRINE
 */
public class AffectationList extends Form {

    private Form addAffectationForm, editAffectationForm;
    private String tag;
    private ArrayList<Affectation> affectationList;

    public AffectationList() {
        super();
        Resources theme = UIManager.initFirstTheme("/theme");
        this.setLayout(BoxLayout.y());
        this.setTitle("Affectation List");
        this.getToolbar().addCommandToLeftBar("logout", null, ev -> {
            new Login().showBack();});

        //initialiser la page d ajout d affectation
        addAffectationForm = new AddAffectation();
        //ajouter cette page au menu
        this.getToolbar().addCommandToOverflowMenu("add", null, ev -> {
            addAffectationForm.show();
            //ajouter un menu de retour vers cette page
            addAffectationForm.getToolbar().addCommandToLeftBar("back", null, e -> {
                this.showBack();
            });
        });

        //créer un container de content
        Container cntContent = new Container(BoxLayout.y());

        //créer un container de recherche        
        Container cntSearch = new Container(BoxLayout.y());
        TextField txtSearch = new TextField("", "Chercher");
        Button btnSearch = new Button("Chercher");
        
        btnSearch.addActionListener(e -> {
            //vider la page actuelle pour mettre a jour la liste
            this.drop(cntContent, TOP, TOP);
            cntContent.removeAll();
            if (txtSearch.getText().equalsIgnoreCase("")) {
                tag = "all";
            } else {
                tag = txtSearch.getText();
            }
            //récuperer la liste de tous les affectations
            affectationList = Service.getInstance().readAffectations(tag);
            //ajouter les affectations a la form
            for (Affectation a : affectationList) {
                cntContent.add(addAffectation(a));
            }
        });
        cntSearch.addAll(txtSearch, btnSearch);
        this.add(cntSearch);

        //récuperer la liste de tous les affectations
        affectationList = Service.getInstance().readAffectations("all");

        //ajouter les affectations a la form
        for (Affectation a : affectationList) {
            cntContent.add(addAffectation(a));
        }

        this.add(cntContent);
    }

    private Container addAffectation(Affectation a) {
        Container cntAffectation = new Container(BoxLayout.y());
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");        
        //Label lblDate = new Label("Date : "+dateFormat.format(a.getDate()));        
        SpanLabel lblRemarque = new SpanLabel("Remarque : " + a.getRemarque());
        SpanLabel lblDate = new SpanLabel("Date : " + a.getDate());
        Button btnGerer = new Button("Gerer affectation");
        btnGerer.addActionListener(e -> {
            editAffectationForm = new EditAffectation(a);
            editAffectationForm.getToolbar().addCommandToLeftBar("back", null, b -> {
                this.showBack();
            });
            editAffectationForm.show();
        });
        cntAffectation.addAll(lblRemarque, lblDate, btnGerer);
        return cntAffectation;
    }

}
