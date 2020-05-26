/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demandes;

import Affectations.Entitys.Affectation;
import Demandes.Entitys.Demande;
import Demandes.GUI.AddDemande;
import Demandes.GUI.EditDemande;
import Demandes.Services.Service;
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
import java.util.ArrayList;

/**
 *
 * @author SIRINE
 */
public class DemandeList extends Form {

    private Form addDemandeForm, editDemandeForm;
    private String tag;
    private Container cntContent;
    private ArrayList<Demande> demandeList;

    public DemandeList() {
        super();
        Resources theme = UIManager.initFirstTheme("/theme");
        this.setLayout(BoxLayout.y());
        this.setTitle("Demande List");
        this.getToolbar().addCommandToLeftBar("logout", null, ev -> {
            new Login().showBack();});

        //initialiser la page d ajout d affectation
        addDemandeForm = new AddDemande();
        //ajouter cette page au menu
        this.getToolbar().addCommandToOverflowMenu("add", null, ev -> {
            addDemandeForm.show();
            //ajouter un menu de retour vers cette page
            addDemandeForm.getToolbar().addCommandToLeftBar("back", null, e -> {
                this.showBack();
            });
        });
        //créer un container de content
        cntContent = new Container(BoxLayout.y());

        //créer un container de recherche et gerer l action de recherche        
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
            demandeList = Service.getInstance().readDemandes(tag);
            //ajouter les affectations a la form
            for (Demande d : demandeList) {
                cntContent.add(addDemande(d));
            }
        });

        cntSearch.addAll(txtSearch, btnSearch);
        this.add(cntSearch);

        //récuperer la liste de tous les affectations
        demandeList = Service.getInstance().readDemandes("all");
        //ajouter les affectations a la form
        for (Demande d : demandeList) {
            cntContent.add(addDemande(d));
        }

        this.add(cntContent);
    }

    private Container addDemande(Demande d) {
        Container cntAffectation = new Container(BoxLayout.y());
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");        
        //Label lblDate = new Label("Date : "+dateFormat.format(a.getDate()));        
        SpanLabel lblRemarque = new SpanLabel("Remarque : " + d.getRemarque());
        SpanLabel lblEtat = new SpanLabel("Etat : " + d.getEtat());
        SpanLabel lblCas = new SpanLabel("Cas : " + d.getCas());
        SpanLabel lblDate = new SpanLabel("Date : " + d.getDate());
        Button btnGerer = new Button("Gerer la demande");
        btnGerer.addActionListener(e -> {
            editDemandeForm = new EditDemande(d);
            editDemandeForm.getToolbar().addCommandToLeftBar("back", null, a -> {
                this.showBack();
            });
            editDemandeForm.show();
        });
        cntAffectation.addAll(lblRemarque, lblEtat, lblCas, lblDate, btnGerer);
        return cntAffectation;
    }

}
