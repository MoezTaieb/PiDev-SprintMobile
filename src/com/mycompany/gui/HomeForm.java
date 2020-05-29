/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.entities.CameraDemo;
import com.mycompany.entities.User;
import com.mycompany.myapp.user.service.UserService;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;

    public HomeForm() {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        Button btnAddComment = new Button("Ajouter");
        
        
        
        ListCategorieProduitForm listCat = new ListCategorieProduitForm(current);
        AddCategorieProduitForm addCat = new AddCategorieProduitForm(current);
        ListProduitForm listProd = new ListProduitForm(current);
        AddProduitForm addProd = new AddProduitForm(current);
        
        
        current.getToolbar().addCommandToSideMenu("Liste des catégories", null, ev->{listCat.show();});
       
        current.getToolbar().addCommandToSideMenu("Liste des produits", null, ev->{listProd.show();});
         
        
        
        
        UserService us = new UserService();
        
        Label lbU = new Label("Utilisateur connecté: " + us.current().getUsername());
        
        if (us.current().getUsername().equals("admin")){
            current.getToolbar().addCommandToSideMenu("Ajouter une Catégorie Produit", null, ev->{addCat.show();});
            current.getToolbar().addCommandToSideMenu("Ajouter un Produit", null, ev->{addProd.show();});
        }
        
        /*
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Hello world");
        status.setExpires(3000);  // only show the status for 3 seconds, then have it automatically clear
        status.show();
        */
        
        
        

        addAll(lbU, btnAddComment);
        

    }

}
