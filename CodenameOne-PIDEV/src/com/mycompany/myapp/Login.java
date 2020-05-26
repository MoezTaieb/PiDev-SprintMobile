/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Affectations.AffectationList;
import Demandes.DemandeList;
import DonationArgent.DonationArgent;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author SIRINE
 */
public class Login extends Form {
    
    private Form affectationListForm, demandeListForm,
                    addAffectationForm, addDemandeForm, donationArgentForm;

    public Login() {
        super();
        Resources theme = UIManager.initFirstTheme("/theme");
        this.setLayout(BoxLayout.y());
        this.setTitle("Login");
        
        //construir la form
        TextField txtLogin = new TextField("", "login");
        TextField txtPassword = new TextField("", "password");
        txtPassword.setConstraint(TextField.PASSWORD);
        Button btnVal = new Button("valider");
        this.addAll(txtLogin, txtPassword, btnVal);
        
        //gerer l authentification
        //initialiser un objet cnx request qui gère la cnx au serveur web
        ConnectionRequest con = new ConnectionRequest();
        
        btnVal.addActionListener((e) -> {

            //construir l url vers le fichier login
            String url = "http://localhost/CodeNameOne/Pidev/login.php";
            con.setPost(false);
            con.addArgument("name", txtLogin.getText());
            con.addArgument("password", txtPassword.getText());

            //associer l url a l objet cnx
            con.setUrl(url);

            //lancer la cnx vers le fichier dans le serveur web
            NetworkManager.getInstance().addToQueueAndWait(con);
        });
        
        //au moment ou la cnx vers le serveur web est établit
        //on execute le fichier php et on recupere la resultat
        con.addResponseListener((e) -> {

            //recupere la reponse envoyer par le fichier php a travers l affichage echo
            String reponse = new String(con.getResponseData());
            System.out.println(reponse);            
            
            if(reponse.contains("admin")){
                //initialiset la page de liste d affectations
                affectationListForm = new AffectationList();                
                affectationListForm.show();
            }else if(reponse.contains("responsable")){
                demandeListForm = new DemandeList();
                demandeListForm.show();
            }else if(reponse.contains("benevole")){
                donationArgentForm = new DonationArgent();
                donationArgentForm.show();
            }else if(reponse.contains("invalid")){
                Dialog.show("error", "login ou pwd invalid", "ok", "cancel");
            }
        });
        
    }
    
}
